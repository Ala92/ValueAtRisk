package com.amundi.am.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class DateConverterTest {
	
	private DateConverter dateConverter;
	
	@Before
	public void setUp() {
		dateConverter = new DateConverter();
	}

	@Test
	public void testDateConversion() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		assertEquals(dateConverter.stringToDate("2018-01-01"), LocalDate.parse("2018-01-01", formatter));
		assertNull(dateConverter.stringToDate("Not a valid date"));
	}
	
	@Test
	public void testStringConversion() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		assertEquals(formatter.format(LocalDate.parse("2018-01-01", formatter)), "2018-01-01");
	}
	
}
