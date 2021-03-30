package turntabl.io.exchange_connectivity.model.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController {
    private TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    public List<Trade> getTrades() { return tradeService.getTrades(); }

//    @PostMapping
//    public void registerNewProduct(
//            @RequestParam(name = "ticker") String ticker
//    ) {
//        Product product = new Product(ticker);
////      System.out.println(product.toString());
//        productService.addNewProduct(product);
//        report.setTitle("client connectivity: product");
//        report.setMsg("New product registered");
//        template.convertAndSend(topic.getTopic(), report);
//    }
}
