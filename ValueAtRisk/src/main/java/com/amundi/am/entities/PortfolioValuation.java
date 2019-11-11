package com.amundi.am.entities;

import java.time.LocalDate;

/**
 * The entity representing a portfolio's profit and loss valuation at a given date
 * @author Ala
 *
 */
public class PortfolioValuation {

	private LocalDate date;
	private double profitAndLossValuation;
	
	public PortfolioValuation(LocalDate date, double valuation) {
		this.date = date;
		this.profitAndLossValuation = valuation;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getValuation() {
		return profitAndLossValuation;
	}
	public void setValuation(double valuation) {
		this.profitAndLossValuation = valuation;
	}
}
