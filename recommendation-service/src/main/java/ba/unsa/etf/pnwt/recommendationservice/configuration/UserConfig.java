package ba.unsa.etf.pnwt.recommendationservice.configuration;

import ba.unsa.etf.pnwt.recommendationservice.entity.UserEntity;
import ba.unsa.etf.pnwt.recommendationservice.repository.UserRepository;
import ba.unsa.etf.pnwt.recommendationservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            UserEntity user = new UserEntity("user", "user@gmail.com");
            //repository.save(user);
        };
    }
}
