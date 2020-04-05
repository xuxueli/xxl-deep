package com.xxl.permission.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

	/**
	 * 日期格式化 (yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatDate(Date date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	/**
	 * 日期格式化 (yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatDateTime(Date date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 * @param patten
	 * @return
	 * @throws ParseException
	 */
	public static String format(Date date, String patten) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
		return dateFormat.format(date);
	}

	/**
	 * 日期解析 (yyyy-MM-dd)
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String dateString) throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = bartDateFormat.parse(dateString);
		return date;
	}

	/**
	 * 日期解析 (yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateTime(String dateString) throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = bartDateFormat.parse(dateString);
		return date;
	}

	/**
	 * 日期解析
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String dateString, String pattern)
			throws ParseException {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(pattern);
		Date date = bartDateFormat.parse(dateString);
		return date;
	}

}
