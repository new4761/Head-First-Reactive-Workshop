package io.spring.workshop.tradingservice.dto;

import io.spring.workshop.model.Quote;
import io.spring.workshop.model.TradingCompany;

public class TradingCompanySummary {

	private final Quote latestQuote;

	private final TradingCompany tradingCompany;

	public TradingCompanySummary(TradingCompany tradingCompany, Quote latestQuote) {
		this.latestQuote = latestQuote;
		this.tradingCompany = tradingCompany;
	}

	public Quote getLatestQuote() {
		return latestQuote;
	}

	public TradingCompany getTradingCompany() {
		return tradingCompany;
	}
}