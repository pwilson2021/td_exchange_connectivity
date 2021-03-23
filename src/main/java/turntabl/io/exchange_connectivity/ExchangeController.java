package turntabl.io.exchange_connectivity;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import turntabl.io.exchange_connectivity.model.TradeModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/exchange_orders")
public class ExchangeController {
    private String api_key = "240ccb53-33cf-4453-b4b0-e9ada4a7409d";
    private String exchange1 = "https://exchange.matraining.com/";
    private String exchange2 = "https://exchange2.matraining.com/";

    WebClient client = WebClient.create();


    @GetMapping
    public void triggerDynamicFetch() {
       dynamicFetch("TSLA", 1, "buy").subscribe(e -> System.out.println(e.toString()));
    }

    public Flux<ExchangeOrder>  dynamicFetch(String ticker, int exchangeId , String side) {
        if (exchangeId == 1) {
            return client.get().uri(exchange1+"orderbook/"+ticker+"/"+side).retrieve().bodyToFlux(ExchangeOrder.class);
        } else {
            return client.get().uri(exchange2+"orderbook/"+ticker+"/"+side).retrieve().bodyToFlux(ExchangeOrder.class);
        }
    }

    public void createOrder(int exchangeId, TradeModel trade) {
        String exchange =  (exchangeId == 1 ) ? exchange1 : exchange2;
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.post();
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(exchange + api_key +"/order");

        LinkedMultiValueMap map = new LinkedMultiValueMap();
        map.add("product", trade.getProduct());
        map.add("quantity", trade.getQuantity());
        map.add("price", trade.getPrice());
        map.add("side", trade.getSide());
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(
                BodyInserters.fromMultipartData(map));

        Mono<String> response = headersSpec.exchangeToMono(res -> {
            if (res.statusCode()
                    .equals(HttpStatus.OK)) {
                return res.bodyToMono(String.class);
            } else if (res.statusCode()
                    .is4xxClientError()) {
                return Mono.just("Error response");
            } else {
                return res.createException()
                        .flatMap(Mono::error);
            }
        });



    }

    public void modifyOrder(){

    }

    public void cancelOrder(){

    }
}
