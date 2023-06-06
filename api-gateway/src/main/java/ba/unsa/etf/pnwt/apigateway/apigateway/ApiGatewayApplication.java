package ba.unsa.etf.pnwt.apigateway.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RouteLocator routes(RouteLocatorBuilder builder, AuthenticationPrefilter authFilter) {
        return builder.routes()
                .route("userservice", p -> p
                        .path("/user-service/**")
                        .filters(f ->
                                f.filter(authFilter.apply(
                                        new AuthenticationPrefilter.Config())))
                        .uri("lb://userservice"))
                .route("jobservice", p -> p
                        .path("/job-service/**")
                        .filters(f ->
                                f.filter(authFilter.apply(
                                        new AuthenticationPrefilter.Config())))
                        .uri("lb://jobservice"))
                .route("recommendationservice", p -> p
                        .path("/recommendation-service/**")
                        .filters(f ->
                                f.filter(authFilter.apply(
                                        new AuthenticationPrefilter.Config())))
                        .uri("lb://recommendationservice"))
                .route("featureservice", p -> p
                        .path("/feature-service/**")
                        .filters(f ->
                                f.filter(authFilter.apply(
                                        new AuthenticationPrefilter.Config())))
                        .uri("lb://featureservice"))
                .build();

    }
}