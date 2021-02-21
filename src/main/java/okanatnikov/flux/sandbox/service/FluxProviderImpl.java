package okanatnikov.flux.sandbox.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FluxProviderImpl implements FluxProvider {

    @Override
    public Flux<String> getFlux() {
        Flux<String> flux = Flux.just("a", "b", "c", "d", "e");
        return flux.map(String::toUpperCase)
                .concatWith(Flux.just("x", "y", "z"))
                .zipWith(Flux.range(1, 16), (a, b) -> a + b)
                .doOnError(Throwable::printStackTrace);
    }
}
