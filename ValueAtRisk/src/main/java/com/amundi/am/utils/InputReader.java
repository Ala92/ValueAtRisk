package com.amundi.am.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.amundi.am.exceptions.PercentileCoefficientOutOfBoundException;

/**
 * The InputReader enables the User to enter the current Date, Historical Depth,
 * Percentile Coefficient and the Csv file path
 * @author Ala
 *
 */
public class InputReader {
	private LocalDate currentDate;
	private Integer historicalDepth;
	private Integer percentileCoefficient;
	private String csvFilePath;
	private DateConverter dateConverter;
	
	private static final Scanner SCANNER = new Scanner(System.in);

	public InputReader(DateConverter dateConverter) {
		this.dateConverter = dateConverter;

		while (this.currentDate == null) {
			this.currentDate = scanDate();
			if (this.currentDate == null ) {
				System.out.println("Wrong Date Format");
			}
		}

		while (this.historicalDepth == null) {
			try {
				this.historicalDepth = scanHistoricalDepth();
			} catch (NumberFormatException e) {
				System.out.println("Historical depth must be an integer");
			}
		}

		while (this.percentileCoefficient == null) {
			try {
				this.percentileCoefficient = scanPercentileCoefficient();
			} catch (NumberFormatException e) {
				System.out
						.println("Percentile Coefficient must be an integer between 0 and 100");
			} catch (PercentileCoefficientOutOfBoundException e) {
				System.out
						.println("Percentile Coefficient must be between 0 and 100");
			}
		}
		while (csvFilePath == null) {
			try {
				this.csvFilePath = scanCsvPath();
			} catch (IOException e) {
				System.out.println("Directory is not valid");
			}
		}
		
	}

	/**
	 * Scans a String in the yyyy-mm-dd format
	 * @return the LocalDate representation of the String
	 */
	private LocalDate scanDate() {
		System.out
				.println("Please enter the current date in the following format YYYY-MM-DD :");
		String strDate = SCANNER.next();
		return dateConverter.stringToDate(strDate);
	}

	/**
	 * Scans the Historical Depth
	 * @return the historical depth
	 * @throws NumberFormatException
	 */
	private Integer scanHistoricalDepth() throws NumberFormatException {
		System.out.println("Please enter the historical depth:");
		return Integer.parseInt(SCANNER.next());
	}

	/**
	 * Scans the Percentile Coefficient, which must be an integer between 0 and 100
	 * @return the percentile coefficient
	 * @throws NumberFormatException
	 * @throws PercentileCoefficientOutOfBoundException
	 */
	private Integer scanPercentileCoefficient() throws NumberFormatException,
			PercentileCoefficientOutOfBoundException {
		int tempInt = -1;
		System.out.println("Please enter the percentile coefficient:");
		tempInt = Integer.parseInt(SCANNER.next());
		if (tempInt >= 0 && tempInt <= 100) {
			return new Integer(tempInt);
		} else {
			throw new PercentileCoefficientOutOfBoundException();
		}
	}

	/**
	 * scans the path of the csv File, if multiple csv files were found, the user 
	 * must choose one
	 * @return the csv file path
	 * @throws IOException
	 */
	private String scanCsvPath() throws IOException {
		List<String> fileNamesList = new ArrayList<>();
		File csvPath;
		String csvFileToRead;
		while (fileNamesList.size() == 0) {
			System.out.println("Please enter the CSV file path:");
			csvPath = new File(SCANNER.next());
			Pattern p = Pattern.compile("(.*)HISTORICAL_PnL_(.*).csv");
			fileNamesList = Files.list(Paths.get(csvPath.getAbsolutePath()))
					.map(Path::toFile).map(File::getAbsolutePath)
					.filter(s -> p.matcher(s).matches())
					.collect(Collectors.toList());
			if (fileNamesList.size() == 0) {
				System.out.println("No Files found in that directory");
			}
		}
		if (fileNamesList.size() == 1) {
			csvFileToRead = fileNamesList.get(0);
		} else {
			System.out
					.println("multiples files found, please choose one by entering its number");
			for (int i = 0; i < fileNamesList.size(); i++) {
				System.out.println((i + 1) + " - " + fileNamesList.get(i));
			}
			int index = -1;
			while (index < 1 || index > fileNamesList.size()) {
				try {
					System.out.println("enter file index");
					index = Integer.parseInt(SCANNER.next());
				} catch (NumberFormatException e) {
					System.out.println("index out of range");
				}
			}
			csvFileToRead = fileNamesList.get(index - 1);
		}
		return csvFileToRead;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getHistoricalDepth() {
		return historicalDepth;
	}

	public void setHistoricalDepth(Integer historicalDepth) {
		this.historicalDepth = historicalDepth;
	}

	public Integer getPercentileCoefficient() {
		return percentileCoefficient;
	}

	public void setPercentileCoefficient(Integer percentileCoefficient) {
		this.percentileCoefficient = percentileCoefficient;
	}

	public String getCsvFilePath() {
		return csvFilePath;
	}

	public void setCsvFilePath(String csvFile) {
		this.csvFilePath = csvFile;
	}

}
