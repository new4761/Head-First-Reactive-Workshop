package io.spring.workshop.tradingservice.controller;

import io.spring.workshop.model.Quote;
import io.spring.workshop.model.TradingCompany;
import io.spring.workshop.tradingservice.client.QuotesClient;
import io.spring.workshop.tradingservice.client.TradingCompanyClient;
import io.spring.workshop.tradingservice.dto.TradingCompanySummary;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class QuotesController {
    private final QuotesClient quotesClient;
    private final TradingCompanyClient tradingCompanyClient;
    public QuotesController(QuotesClient quotesClient,TradingCompanyClient tradingCompanyClient) {
        this.quotesClient = quotesClient;
        this.tradingCompanyClient = tradingCompanyClient;
    }

    @GetMapping(value = "/quotes/feed", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Quote> getQuotes() {
        return quotesClient.quotesFeed();
    }
    @GetMapping(value = "/quotes/summary/{ticker}", produces = APPLICATION_JSON_VALUE)
    public Mono<TradingCompanySummary> getTradingSummary(@PathVariable String ticker) {
        Mono<TradingCompany> tradingCompanyMono = tradingCompanyClient.getTradingCompany(ticker);
        Mono<Quote> quoteMono = quotesClient.getLatestQuote(ticker);
        return tradingCompanyMono.zipWith(quoteMono, TradingCompanySummary::new);
    }
}
