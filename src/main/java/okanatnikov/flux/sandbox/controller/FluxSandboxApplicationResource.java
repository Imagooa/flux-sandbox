package okanatnikov.flux.sandbox.controller;

import lombok.RequiredArgsConstructor;
import okanatnikov.flux.sandbox.service.FluxProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
@RequiredArgsConstructor
public class FluxSandboxApplicationResource {

    private final WebClient webClient;
    private final FluxProvider fluxProvider;

    @GetMapping("/collection")
    public Flux<String> getSomeCollection() {
        return fluxProvider.getFlux();
    }

    @GetMapping("/self/call")
    public Mono<String> selfInvocation() {
        return webClient.get()
                .uri("/router/simple")
                .retrieve()
                .bodyToMono(String.class)
                .map(s -> String.format("Request moved through self invocation: %s", s));
    }
}
