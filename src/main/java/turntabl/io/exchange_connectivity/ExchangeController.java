package turntabl.io.exchange_connectivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import turntabl.io.exchange_connectivity.model.ExchangeMarketDataModel;
import turntabl.io.exchange_connectivity.model.ExchangeOrder;
import turntabl.io.exchange_connectivity.model.QueueTradeModel;
import turntabl.io.exchange_connectivity.model.TradeModel;
import turntabl.io.exchange_connectivity.model.order.Order;
import turntabl.io.exchange_connectivity.model.order.OrderRepository;
import turntabl.io.exchange_connectivity.model.order.OrderService;
import turntabl.io.exchange_connectivity.model.trade.Trade;
import turntabl.io.exchange_connectivity.model.trade.TradeService;

import java.util.Locale;
import java.util.Optional;

@RestController
public class ExchangeController {
    private String api_key = "ce7d79a8-c9d5-4e89-a41d-683dd8193403";
    private String exchange1 = "https://exchange.matraining.com/";
    private String exchange2 = "https://exchange2.matraining.com/";

    WebClient client = WebClient.create();

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TradeService tradeService;

    @GetMapping("/api/get_order_book/{ticker}/{exchangeId}/{side}")
    public Flux<ExchangeOrder>  dynamicFetch(
            @PathVariable("ticker") String ticker,
            @PathVariable("exchangeId") int exchangeId,
            @PathVariable("side") String side
    ) {
        if (exchangeId == 1) {
            return client.get().uri(exchange1+"orderbook/"+ticker+"/"+side).retrieve().bodyToFlux(ExchangeOrder.class);
        } else {
            return client.get().uri(exchange2+"orderbook/"+ticker+"/"+side).retrieve().bodyToFlux(ExchangeOrder.class);
        }
    }

    @GetMapping("/api/market_data/{ticker}/{exchangeId}")
    public Mono<ExchangeMarketDataModel> fetchMarketDataByTicker (
            @PathVariable(value= "ticker") String ticker,
            @PathVariable(value= "exchangeId") int exchangeId
    ) {
        String exchange =  (exchangeId == 1 ) ? exchange1 : exchange2;
        return client.get().uri(exchange+"/md/"+ticker).retrieve().bodyToMono(ExchangeMarketDataModel.class);
    }

    public void createOrder( QueueTradeModel trade) {
        String exchange =  (trade.getExchangeId() == 1 ) ? exchange1 : exchange2;


        TradeModel tradeModel = new TradeModel(trade.getProduct(), trade.getQuantity(), trade.getPrice(), trade.getSide().toUpperCase(Locale.ROOT));

        String orderId = client
                        .post()
                        .uri(exchange + "/" + api_key + "/order")
                        .body(Mono.just(tradeModel), TradeModel.class)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
        Optional<Order> order = orderRepository.findById(trade.getOrderId());

        Trade storeTrade = new Trade(trade.getPrice(), orderId, trade.getQuantity(),
                "pending", trade.getExchangeId(), order.get());
        tradeService.addNewTrade(storeTrade);
        System.out.println("Order placed successfully, orderId: " + orderId);
    }


    public void modifyOrder(){

    }

    public void cancelOrder(){

    }
}
