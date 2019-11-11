package com.amundi.am.utils;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.amundi.am.entities.PortfolioValuation;
import static org.junit.Assert.*;

public class CsvReaderTest {

	private DateConverter dateConverter;

	@Before
	public void setUp() {
		dateConverter = new DateConverter();
	}

	@Test
	public void testCsvRead() {
		String filePath = getClass().getClassLoader().getResource("HISTORICAL_PnL_1.csv").getFile();
		DateConverter converter = new DateConverter();
		List<PortfolioValuation> portfolioValuations= new CsvReader(converter).readValuesFromCsvFile(filePath,
				dateConverter.stringToDate("2018-01-01"), dateConverter.stringToDate("2018-12-01"));
		assertEquals(portfolioValuations.size(), 12);
		portfolioValuations= new CsvReader(converter).readValuesFromCsvFile(filePath,
				dateConverter.stringToDate("2018-01-01"), dateConverter.stringToDate("2018-01-31"));
		assertEquals(portfolioValuations.size(), 1);
		portfolioValuations= new CsvReader(converter).readValuesFromCsvFile(filePath,
				dateConverter.stringToDate("2017-01-01"), dateConverter.stringToDate("2017-12-01"));
		assertEquals(portfolioValuations.size(), 0);
		
	}

}
