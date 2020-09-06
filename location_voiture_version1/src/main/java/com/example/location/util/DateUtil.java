package com.example.location.util;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.control.DatePicker;

public class DateUtil {
	public static Date getDateFromDatePicker(DatePicker datePicker) {
		LocalDate ld = datePicker.getValue();
		Calendar c = Calendar.getInstance();
		c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
		Date date = c.getTime();
		return date;
	}

	public static int getCurrentYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH);
	}

	public static int getDayFromDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getMonthFromDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}
	
	
}
