package com.amundi.am.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converts a LocalDate to a String and vice-versa
 * 
 * @author Ala
 *
 */
public class DateConverter {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Converts a LocalDate to a String
	 * 
	 * @param date
	 * @return the String representation of the date
	 */
	public String dateToString(LocalDate date) {
		return FORMATTER.format(date);
	}
	
	/**
	 * Converts a String to a LocalDate
	 * @param dateStr
	 * @return the LocalDate representation of the String
	 */
	public LocalDate stringToDate(String dateStr) {
		LocalDate date = null;
		try {
			return date =  LocalDate.parse(dateStr, FORMATTER);
		} catch (DateTimeParseException e) {
			
		}
		return date;
	}
} 
