package turntabl.io.exchange_connectivity.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import turntabl.io.exchange_connectivity.model.TradeModel;

import java.util.logging.Logger;

public class ExchangeConnectivityReceiver implements RabbitListenerConfigurer {
    private static  final Logger logger = Logger.getLogger(ExchangeConnectivityReceiver.class.getName());


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

    }
    @RabbitListener(queues="${menu.rabbitmq.queue}")
    public void receivedMessage(TradeModel trade){
        logger.info("Menu details received is ..... " + trade);
    }
}
