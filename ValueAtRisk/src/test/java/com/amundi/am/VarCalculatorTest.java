package com.amundi.am;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.amundi.am.App;
import com.amundi.am.entities.PortfolioValuation;
import com.amundi.am.exceptions.NotEnoughDataToProcessException;

public class VarCalculatorTest {

	List<PortfolioValuation> portfolioValuations;
	List<PortfolioValuation> incompletePortfolioValuations;
	@Before
	public void setUp() {
		portfolioValuations = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-01-01", formatter), -123987.45));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-02-01", formatter), 552345.75));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-03-01", formatter), 22349.27));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-04-01", formatter), -34754.23));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-05-01", formatter), 21879.84));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-06-01", formatter), 1250.75));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-07-01", formatter), 225459.98));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-08-01", formatter), -2549.11));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-09-01", formatter), -75342.83));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-10-01", formatter), -12455.66));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-11-01", formatter), 46851.23));
		portfolioValuations.add(new PortfolioValuation(LocalDate.parse(
				"2018-12-01", formatter), 123.25));
		
		incompletePortfolioValuations = new ArrayList<>();
	}

	@Test
	public void testCalculateValueAtRist() throws NotEnoughDataToProcessException {
		assertEquals(App.calculateValueAtRist(portfolioValuations, 95), -75342.83, 0);
		assertEquals(App.calculateValueAtRist(portfolioValuations, 84), -34754.23, 0);
	}
	
	@Test(expected = NotEnoughDataToProcessException.class)
	public void testEmptyCalculateValueAtRisk() throws NotEnoughDataToProcessException{
		App.calculateValueAtRist(incompletePortfolioValuations, 95);
	}

	@Test(expected = NotEnoughDataToProcessException.class)
	public void testNullCalculateValueAtRisk() throws NotEnoughDataToProcessException{
		App.calculateValueAtRist(null, 95);
	}
}
