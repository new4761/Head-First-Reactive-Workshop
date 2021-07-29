package io.spring.workshop.stockdetails.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import io.spring.workshop.stockdetails.domain.TradingCompany;
import reactor.core.publisher.Mono;

public interface TradingCompanyRepository extends ReactiveMongoRepository<TradingCompany, String> {

    Mono<TradingCompany> findByTicker(String ticker);
}
