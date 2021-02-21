package okanatnikov.flux.sandbox.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class FluxSandboxApplicationServiceImpl implements FluxSandboxApplicationService {

    private final FluxProvider fluxProvider;

    @Override
    public Mono<ServerResponse> simple(ServerRequest request) {
        Mono<String> body = Mono.from(fluxProvider.getFlux())
                .delaySubscription(Duration.ofSeconds(2));
        return ServerResponse.ok()
                .body(body, String.class);
    }

    @Override
    public Mono<ServerResponse> collection(ServerRequest request) {
        Flux<String> body = Flux.just("foo", "bar");
        return ServerResponse.ok()
                .body(body, String.class);
    }

    @Override
    public Mono<ServerResponse> post(ServerRequest request) {
        Mono<String> responseBody = request
                .bodyToMono(String.class)
                .doOnNext(bodyString -> log.info("Got post request with body '{}'", bodyString))
                .then(Mono.just("posted"));
        return ServerResponse.ok()
                .body(responseBody, String.class);
    }
}
