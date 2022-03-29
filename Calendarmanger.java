package 실험용;

import java.util.Calendar;

public class Calendarmanger {
	String WEEK_DAY_NAME[] = { "SUN", "MON", "THU", "WED", "THU", "FRI", "SAT" }; // 월~일 배열
	final int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 1~12월의 끝날짜를 배열에담음
	final int Calendar_Array[] = { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL, Calendar.MAY,
			Calendar.JUNE, Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER,
			Calendar.DECEMBER }; // 월 이동을 위한 1~12월 배열
	final int CAL_WIDTH = 7; 
	final int CAL_HEIGHT = 6;
	int year; 
	int month;
	int date;
	int dayofweek = 7; // 월의 시작일 일요일=0
	int lastday; // 월의 가지막날
	int calStartingPos; // 월의 시작일
	String now_date; // 현재 년,월,일
	String now_day;//현재 월, 일

	Calendar today = Calendar.getInstance(); // 현재 날짜에 대한 정보를 가져옴
	int now = today.get(Calendar.MONTH); // 현재 해당 달의 정보를 설정

	public Calendarmanger() {
		setToday();
	}

	public void setToday() {
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;
		date = today.get(Calendar.DAY_OF_MONTH);
		calStartingPos = (today.get(Calendar.DAY_OF_WEEK) + 7 - (today.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
		if (month == 1) // 1월이라면 윤년인지 아닌지 검사
			lastday = calLastDateOfMonth[month - 1] + leapCheck(year);
		else
			lastday = calLastDateOfMonth[month - 1];
	}

	private int leapCheck(int year) { // 윤년인지 확인하는 함수
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return 1;
		else
			return 0;
		
	}

}
