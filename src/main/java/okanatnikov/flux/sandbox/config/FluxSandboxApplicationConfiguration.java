package okanatnikov.flux.sandbox.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okanatnikov.flux.sandbox.service.FluxSandboxApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FluxSandboxApplicationConfiguration {

    private final FluxSandboxApplicationService service;

    @Bean
    public RouterFunction<ServerResponse> router() {

        RouterFunction<ServerResponse> simple = route(GET("/router/simple"), service::simple);
        RouterFunction<ServerResponse> collection = route(GET("/router/collection"), service::collection);
        RouterFunction<ServerResponse> post = route(POST("/router/post"), service::post);

        return simple.and(collection).and(post);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8080");
    }
}
