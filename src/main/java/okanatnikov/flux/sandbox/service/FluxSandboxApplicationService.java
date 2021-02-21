package okanatnikov.flux.sandbox.service;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface FluxSandboxApplicationService {

    Mono<ServerResponse> simple(ServerRequest request);

    Mono<ServerResponse> collection(ServerRequest request);

    Mono<ServerResponse> post(ServerRequest request);
}
