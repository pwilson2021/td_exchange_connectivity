package turntabl.io.exchange_connectivity.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;
import turntabl.io.exchange_connectivity.ExchangeController;
import turntabl.io.exchange_connectivity.model.QueueTradeModel;

import java.util.logging.Logger;

@Component
public class ExchangeConnectivityReceiver implements RabbitListenerConfigurer {
    private static  final Logger logger = Logger.getLogger(ExchangeConnectivityReceiver.class.getName());

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

    }

    @RabbitListener(queues="${trade.engine.rabbitmq.queue}")
    public void receivedMessage(QueueTradeModel trade){
        logger.info("Trade details received is ..... " + trade);
        ExchangeController exchangeController = new ExchangeController();
        exchangeController.createOrder(trade);
    }
}
