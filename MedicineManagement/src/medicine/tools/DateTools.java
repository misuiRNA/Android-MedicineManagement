package medicine.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {
	
	public static Date stringToDate(String timeString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date timeDate = null;
		try {
			timeDate = sdf.parse(timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeDate;
	}
	
	public static String dateToString(Date timeDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String timeString = sdf.format(timeDate);
		return timeString;
	}
	
	public static Date getDateAfter(Date date, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	public static String getDateAfter(String date, int day) {
		Date now = stringToDate(date);
		Date newDate = getDateAfter(now, day);
		String newDateString = dateToString(newDate);
		return newDateString;
	}
	
	public static String getCurrentDateAsString() {
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
		String dateStr  = dateToString(curDate);
		return dateStr;
	}
	
	public static int getYear(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date curDate = stringToDate(dateStr);
		return Integer.parseInt(sdf.format(curDate));
	}
	
	public static int getMonth(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		Date curDate = stringToDate(dateStr);
		return Integer.parseInt(sdf.format(curDate)) - 1;
	}
	
	public static int getDay(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		Date curDate = stringToDate(dateStr);
		return Integer.parseInt(sdf.format(curDate));
	}
}
