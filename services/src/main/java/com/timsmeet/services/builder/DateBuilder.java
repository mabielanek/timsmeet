package com.timsmeet.services.builder;

import java.util.Calendar;
import java.util.Date;

public class DateBuilder {

	private static final Calendar calendarPattern = Calendar.getInstance();

	//private static final SimpleDateFormat timeTextFormat = new SimpleDateFormat("hh:mm");
	//private static final SimpleDateFormat dateTextFormat = new SimpleDateFormat("yyyy-MM-dd");

	{
		calendarPattern.set(2000, 6, 1, 0, 0, 0);
	}
	
	public Date buildDate(int year, int month, int day) {
		Calendar cal = (Calendar)calendarPattern.clone();
		cal.set(year, month, day);
		return cal.getTime();
	}
	
	public Date buildTime(int hour, int minute) {
		Calendar cal = (Calendar)calendarPattern.clone();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		return cal.getTime();
	}
	
//	public Date buildTime(String timeString) {
//		try {
//			Date date = timeTextFormat.parse(timeString);
//			return calendarPattern.set)
//		} catch (ParseException e) {
//			throw new IllegalArgumentException("Wrong date string format", e);
//		}
//	}
}
