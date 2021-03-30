package turntabl.io.exchange_connectivity.model.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;

    @Autowired

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public List<Trade> getTrades() {return tradeRepository.findAll(); }

    public void addNewTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    @Transactional
    public void updateTrade(int tradeId, String order_status) {
        Trade trade =  tradeRepository.findById(tradeId)
                .orElseThrow(() -> new IllegalStateException(
                        "Trade with order_id "+ tradeId + " does not exist"
                ));

        if (order_status != null && order_status.length() > 0)  {
            trade.setStatus(order_status);
        }
    }

    public List<Trade> findIncompleteTrades() {
        List<Trade> tradeList = tradeRepository.findAll();
        return tradeList.stream().filter(trade -> !trade.getStatus().equals("complete") && !trade.getStatus().equals("cancelled"))
                .collect(Collectors.toList());
    }
}
