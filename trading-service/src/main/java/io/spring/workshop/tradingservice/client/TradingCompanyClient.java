package io.spring.workshop.tradingservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import io.spring.workshop.model.TradingCompany;
import io.spring.workshop.tradingservice.exception.TickerNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TradingCompanyClient {
    private WebClient webClient;

    public TradingCompanyClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Flux<TradingCompany> findAllCompanies() {
        return webClient.get().uri("http://localhost:8082/details").retrieve().bodyToFlux(TradingCompany.class);
    }

    public Mono<TradingCompany> getTradingCompany(String ticker) {
        return webClient.get().uri("http://localhost:8082/details/{ticker}", ticker).retrieve()
                .bodyToMono(TradingCompany.class).switchIfEmpty(Mono.error(TickerNotFoundException::new));
    }
}
