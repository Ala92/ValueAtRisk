package com.amundi.am.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.amundi.am.entities.PortfolioValuation;

/**
 * The Csv reader enables to read portolio valuation data from a CSV file
 * @author Ala
 *
 */
public class CsvReader {

	private final static String CSV_SEPARTOR = ";";
	private DateConverter dateConverter;
	
	public CsvReader(DateConverter dateConverter) {
		this.dateConverter = dateConverter;
	}
	 
	/**
	 * reads the values from a CSV file
	 * 
	 * @param filePath the path to the CSV File
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return
	 */
	public List<PortfolioValuation> readValuesFromCsvFile(String filePath,
			LocalDate startDate, LocalDate endDate) {
		String line = "";
		List<PortfolioValuation> portfolioValuations = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			while ((line = br.readLine()) != null) {
				String[] values = line.split(CSV_SEPARTOR);
				LocalDate date = dateConverter.stringToDate(values[0]);
				if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0) {
					Double valuation = Double.parseDouble(values[1].replaceAll("\\s+",""));
					portfolioValuations.add (new PortfolioValuation(date, valuation));
				}
			}
		} catch (IOException e) {
			System.out.println("Error parsing the CSV File");
			return null;
		} catch (NumberFormatException e) {
			System.out.println("The CSV file data is not well formatted");
			return null;
		}
		return portfolioValuations;
	}
}
