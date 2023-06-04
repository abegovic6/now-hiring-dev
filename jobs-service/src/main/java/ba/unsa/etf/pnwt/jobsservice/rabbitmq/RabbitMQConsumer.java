package ba.unsa.etf.pnwt.jobsservice.rabbitmq;

import ba.unsa.etf.pnwt.jobsservice.config.RabbitMQConfig;
import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;
import ba.unsa.etf.pnwt.jobsservice.service.JobService;
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

    @RabbitListener(queues = RabbitMQConfig.QUEUE_RECOMMENDATION_REVERSE)
    public void receive(String title) {
        try{
            JobEntity jobEntity = jobService.findJobByTitle(title);
            jobService.deleteById(jobEntity.getId());
        }catch(Exception e){
            System.out.println("Greska u brisanju posla!");
            System.out.println(e.getMessage());
        }

    }
}
