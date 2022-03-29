package 실험용;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Time {
	//오늘 날짜, 시간을 불러오는 클래스로, 이 클래스를 상속받아 사용하면 해당 변수들을 그대로 쓸 수 있음.
	static String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" }; // 월~일 배열
	final static int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 1~12월의 끝날짜를 배열에 담음
	final int Calendar_Array[] = { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL, Calendar.MAY,
			Calendar.JUNE, Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER,
			Calendar.DECEMBER }; // 월 이동을 위한 1~12월 배열
	
	final int CAL_WIDTH = 7; 
	final int CAL_HEIGHT = 6;
	public static int year, month, date, dayofweek = 7; // 월의 시작일 일요일=0
	static int day;
	static String whatday; //요일
	public static int lastday; // 월의 마지막날
	public static int calStartingPos; // 월의 시작일
	public static int hour, minute, second;
	
	String now_date; // 현재 년,월,
	static Calendar today = Calendar.getInstance(); 
	static Calendar nottoday=Calendar.getInstance();// 현재 날짜에 대한 정보를 가져옴
	
	int now = today.get(Calendar.MONTH); // 현재 해당 달의 정보를 설정
	

	public static void setToday() {
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;
		date = today.get(Calendar.DAY_OF_MONTH);
		
		day=today.get(Calendar.DAY_OF_WEEK)-1;
		whatday = WEEK_DAY_NAME[day];
		
		calStartingPos = (today.get(Calendar.DAY_OF_WEEK) + 7 - (today.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
		if (month == 1) // 1월이라면 윤년인지 아닌지 검사
			lastday = calLastDateOfMonth[month - 1] + leapCheck(year);
		else
			lastday = calLastDateOfMonth[month - 1];
		hour = today.get(Calendar.HOUR);
		minute = today.get(Calendar.MINUTE);
		second = today.get(Calendar.SECOND);
	}
	public static void setNotToday() {
		year = nottoday.get(Calendar.YEAR);
		month = nottoday.get(Calendar.MONTH) + 1;
		date = nottoday.get(Calendar.DAY_OF_MONTH);
		
		day=nottoday.get(Calendar.DAY_OF_WEEK)-1;
		whatday = WEEK_DAY_NAME[day];
		
		calStartingPos = (nottoday.get(Calendar.DAY_OF_WEEK) + 7 - (nottoday.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
		if (month == 1) // 1월이라면 윤년인지 아닌지 검사
			lastday = calLastDateOfMonth[month - 1] + leapCheck(year);
		else
			lastday = calLastDateOfMonth[month - 1];
		hour = nottoday.get(Calendar.HOUR);
		minute = nottoday.get(Calendar.MINUTE);
		second = nottoday.get(Calendar.SECOND);
	}
	public String Today() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    Calendar c1 = Calendar.getInstance();
		 String strToday = sdf.format(c1.getTime());
		 return strToday;
	}
	public static int leapCheck(int year) { // 윤년인지 확인하는 함수
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return 1;
		else
			return 0;
	}
}
