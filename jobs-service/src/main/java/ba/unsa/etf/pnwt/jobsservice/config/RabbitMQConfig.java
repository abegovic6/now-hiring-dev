package ba.unsa.etf.pnwt.jobsservice.config;

import jakarta.inject.Qualifier;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_USER_NOTIFICATION = "user_notification";
    public static final String EXCHANGE_USER_NOTIFICATION = "user_exchange_notification";
    public static final String ROUTING_KEY_USER_NOTIFICATION = "user_routingKey_exchange_notification";

    public static final String QUEUE_RECOMMENDATION = "user_recommendation";
    public static final String EXCHANGE_RECOMMENDATION = "user_exchange_recommendation";
    public static final String ROUTING_KEY_RECOMMENDATION= "user_routingKey_exchange_recommendation";

    public static final String QUEUE_RECOMMENDATION_REVERSE = "user_recommendation_reverse";
    public static final String EXCHANGE_RECOMMENDATION_REVERSE = "user_exchange_recommendation_reverse";
    public static final String ROUTING_KEY_RECOMMENDATION_REVERSE= "user_routingKey_exchange_recommendation_reverse";

    @Bean
    public Queue queueUserNotification() {
        return new Queue(QUEUE_USER_NOTIFICATION);
    }

    @Bean
    public TopicExchange exchangeUserNotification() {
        return new TopicExchange(EXCHANGE_USER_NOTIFICATION);
    }

    @Bean
    public Binding bindingUserNotification() {
        return BindingBuilder.bind(queueUserNotification()).to(exchangeUserNotification()).with(ROUTING_KEY_USER_NOTIFICATION);
    }

    @Bean
    public Queue queueRecommendation() {
        return new Queue(QUEUE_RECOMMENDATION);
    }

    @Bean
    public TopicExchange exchangeRecommendation() {
        return new TopicExchange(EXCHANGE_RECOMMENDATION);
    }

    @Bean
    public Binding bindingRecommendation() {
        return BindingBuilder.bind(queueRecommendation()).to(exchangeRecommendation()).with(ROUTING_KEY_RECOMMENDATION);
    }

    @Bean
    public Queue queueRecommendationReverse() {
        return new Queue(QUEUE_RECOMMENDATION_REVERSE);
    }

    @Bean
    public TopicExchange exchangeRecommendationReverse() {
        return new TopicExchange(EXCHANGE_RECOMMENDATION_REVERSE);
    }

    @Bean
    public Binding bindingRecommendationReverse() {
        return BindingBuilder.bind(queueRecommendationReverse()).to(exchangeRecommendationReverse()).with(ROUTING_KEY_RECOMMENDATION_REVERSE);
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
