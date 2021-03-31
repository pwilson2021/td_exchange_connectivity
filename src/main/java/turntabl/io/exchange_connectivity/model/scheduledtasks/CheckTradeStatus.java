package turntabl.io.exchange_connectivity.model.scheduledtasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import turntabl.io.exchange_connectivity.model.ExchangeOrder;
import turntabl.io.exchange_connectivity.model.trade.Trade;
import turntabl.io.exchange_connectivity.model.trade.TradeService;

import java.util.List;

@Component
public class CheckTradeStatus {
    private String api_key = "ce7d79a8-c9d5-4e89-a41d-683dd8193403";
    private String exchange1 = "https://exchange.matraining.com/";
    private String exchange2 = "https://exchange2.matraining.com/";
    WebClient client = WebClient.create();

    private TradeService tradeService;

    @Autowired
    public CheckTradeStatus(TradeService tradeService) {
        this.tradeService = tradeService;
    }


    @Scheduled(fixedRate = 10000)
    public void handleTrades() {
        System.out.println("Scheduled tasks have begun");
        List<Trade> tradeList = tradeService.findIncompleteTrades();
        tradeList.forEach( trade -> {
            checkTrade(trade
            );
        });
    }

    public void checkTrade(Trade trade) {
        String exchange =  (trade.getExchange_id() == 1 ) ? exchange1 : exchange2;

        ExchangeOrder exchangeOrder = client.get().uri(
                exchange+api_key+"/order/"+trade.getExchange_order_id()
        ).retrieve().bodyToMono(ExchangeOrder.class).block();

        if ( trade.getQuantity() == exchangeOrder.getCumulatitiveQuantity()){
            tradeService.updateTrade(trade.getId(), "completed");
        }
    }
}
