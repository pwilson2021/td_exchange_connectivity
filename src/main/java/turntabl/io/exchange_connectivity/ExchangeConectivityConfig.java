package turntabl.io.exchange_connectivity;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConectivityConfig {
    @Autowired
    ExchangeController exchangeController;

    @Value("${trade.engine.rabbitmq.queue}")
    String queueName;
    @Value("${trade.engine.rabbitmq.exchange}")
    String exchange;
    @Value("${trade.engine.rabbitmq.key}")
    private String key;

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.username}")
    private String userName;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Bean
    Queue queue(){
        return new Queue(queueName, true);
    }
    @Bean
    DirectExchange exchange(){
        return new DirectExchange(exchange);
    }
    @Bean
    Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public CachingConnectionFactory rabbitConnectionFactory(){
        CachingConnectionFactory connection = new CachingConnectionFactory();
        connection.setHost(host);
        connection.setPort(port);
        connection.setUsername(userName);
        connection.setPassword(password);
//        connection.setVirtualHost(userName);
        return connection;
    }
    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(key);
    }

    @Bean
    public AmqpTemplate rabTemplate(){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(rabbitConnectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
