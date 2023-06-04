package ba.unsa.etf.pnwt.recommendationservice.rabbitmq;

import ba.unsa.etf.pnwt.recommendationservice.configuration.RabbitMQConfig;
import ba.unsa.etf.pnwt.recommendationservice.dto.JobDTO;
import ba.unsa.etf.pnwt.recommendationservice.service.JobService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @Autowired
    JobService jobService;

    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_RECOMMENDATION)
    public void receive(JobDTO jobDTO) {
        try{

            jobService.addNewJobDTO(jobDTO);
        }catch(Exception e){
            template.convertAndSend(RabbitMQConfig.EXCHANGE_RECOMMENDATION_REVERSE, RabbitMQConfig.ROUTING_KEY_RECOMMENDATION_REVERSE, jobDTO.getName());
            System.out.println("Greska u kreiranju posla!");
            System.out.println(e.getMessage());
        }

    }
}
