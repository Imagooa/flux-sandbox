package okanatnikov.flux.sandbox.service;

import reactor.core.publisher.Flux;

public interface FluxProvider {

    Flux<String> getFlux();
}
