package ba.unsa.etf.pnwt.userservice.rabbitmq;

import ba.unsa.etf.pnwt.userservice.config.RabbitMQConfig;
import ba.unsa.etf.pnwt.userservice.repository.NotificationRepository;
import ba.unsa.etf.pnwt.userservice.repository.UserRepository;
import ba.unsa.etf.pnwt.userservice.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @Autowired
    NotificationService notificationService;

    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_USER_NOTIFICATION)
    public void receive(String companyId) {
        try{
            notificationService.createCompanyCreatedAJobNotification(companyId);
        }catch(Exception e){
            System.out.println("Greska u kreiranju notifikacije za kompaniju!");
            System.out.println(e.getMessage());
        }

    }
}
