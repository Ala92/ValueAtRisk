package com.amundi.am;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import com.amundi.am.entities.PortfolioValuation;
import com.amundi.am.exceptions.NotEnoughDataToProcessException;
import com.amundi.am.utils.CsvReader;
import com.amundi.am.utils.DateConverter;
import com.amundi.am.utils.InputReader;

public class App {

	public static void main(String[] args) {
		DateConverter converter = new DateConverter();
		InputReader input = new InputReader(converter);
		LocalDate minDate = input.getCurrentDate().minusDays(input.getHistoricalDepth());
		List<PortfolioValuation> portfolioValuations = new CsvReader(converter).readValuesFromCsvFile(input
				.getCsvFilePath(), minDate, input.getCurrentDate() );
		try {
			double estimatedVaR = calculateValueAtRist(portfolioValuations, input.getPercentileCoefficient());
			System.out.println("The estimated VaR = " + estimatedVaR);
		} catch (NotEnoughDataToProcessException e) {
			System.out.println("Not enough Data");
		}
	}
	
	/**
	 * calculates the value at risk given a list of portfolioValuations and 
	 * the percentile coefficient
	 * @param portfolioValuations
	 * @param percentileCoefficient
	 * @return
	 * @throws NotEnoughDataToProcessException
	 */
	public static Double calculateValueAtRist(
			List<PortfolioValuation> portfolioValuations,
			int percentileCoefficient) throws NotEnoughDataToProcessException {
		if (portfolioValuations == null || portfolioValuations.size() == 0) {
			throw new NotEnoughDataToProcessException();
		}
		Collections.sort(
				portfolioValuations,
				(pv1, pv2) -> Double.compare(pv2.getValuation(),
						pv1.getValuation()));
		int index = Math.round(12f * percentileCoefficient / 100);
		return portfolioValuations.get(index == 0? index : index - 1).getValuation();
	}

}