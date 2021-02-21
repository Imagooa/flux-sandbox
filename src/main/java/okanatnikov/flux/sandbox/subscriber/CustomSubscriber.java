package okanatnikov.flux.sandbox.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Objects;

@Slf4j
public class CustomSubscriber implements Subscriber<String> {

    private Subscription subscription = null;

    @Override
    public void onSubscribe(Subscription sub) {
        log.info("onSubscribe: 1 requested");
        if (sub == null) {
            throw new RuntimeException("No subscription was presented");
        }
        this.subscription = sub;
        request(1);
    }

    @Override
    public void onNext(String s) {
        log.info("onNext: " + s);
        request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("onError: " + throwable.getMessage());
        request(1);
    }

    @Override
    public void onComplete() {
        log.info("onComplete");
        request(1);
    }

    private void request(long amount) {
        if (Objects.nonNull(subscription)) {
            subscription.request(amount);
        }
        throw new RuntimeException("No subscription found");
    }
}
