package ba.unsa.etf.pnwt.recommendationservice.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_RECOMMENDATION = "user_recommendation";
    public static final String EXCHANGE_RECOMMENDATION = "user_exchange_recommendation";
    public static final String ROUTING_KEY_RECOMMENDATION= "user_routingKey_exchange_recommendation";

    public static final String QUEUE_RECOMMENDATION_REVERSE = "user_recommendation_reverse";
    public static final String EXCHANGE_RECOMMENDATION_REVERSE = "user_exchange_recommendation_reverse";
    public static final String ROUTING_KEY_RECOMMENDATION_REVERSE= "user_routingKey_exchange_recommendation_reverse";

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
        return new Queue(QUEUE_RECOMMENDATION);
    }

    @Bean
    public TopicExchange exchangeRecommendationReverse() {
        return new TopicExchange(EXCHANGE_RECOMMENDATION);
    }

    @Bean
    public Binding bindingRecommendationReverse() {
        return BindingBuilder.bind(queueRecommendationReverse()).to(exchangeRecommendationReverse()).with(ROUTING_KEY_RECOMMENDATION);
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
