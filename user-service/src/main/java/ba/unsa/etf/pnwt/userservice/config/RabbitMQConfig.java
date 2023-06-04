package ba.unsa.etf.pnwt.userservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_USER_NOTIFICATION = "user_notification";
    public static final String EXCHANGE_USER_NOTIFICATION = "user_exchange_notification";
    public static final String ROUTING_KEY_USER_NOTIFICATION = "user_routingKey_exchange_notification";

    @Bean
    public Queue queueUserNotification() {
        return new Queue(QUEUE_USER_NOTIFICATION);
    }

    @Bean
    public TopicExchange exchangeUserNotification() {
        return new TopicExchange(EXCHANGE_USER_NOTIFICATION);
    }

    @Bean
    public Binding bindingUserNotification(Queue queueUserDesired, TopicExchange exchangeUserDesired) {
        return BindingBuilder.bind(queueUserDesired).to(exchangeUserDesired).with(ROUTING_KEY_USER_NOTIFICATION);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
