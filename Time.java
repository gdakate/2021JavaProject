package �����;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Time {
	//���� ��¥, �ð��� �ҷ����� Ŭ������, �� Ŭ������ ��ӹ޾� ����ϸ� �ش� �������� �״�� �� �� ����.
	static String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" }; // ��~�� �迭
	final static int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 1~12���� ����¥�� �迭�� ����
	final int Calendar_Array[] = { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL, Calendar.MAY,
			Calendar.JUNE, Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER,
			Calendar.DECEMBER }; // �� �̵��� ���� 1~12�� �迭
	
	final int CAL_WIDTH = 7; 
	final int CAL_HEIGHT = 6;
	public static int year, month, date, dayofweek = 7; // ���� ������ �Ͽ���=0
	static int day;
	static String whatday; //����
	public static int lastday; // ���� ��������
	public static int calStartingPos; // ���� ������
	public static int hour, minute, second;
	
	String now_date; // ���� ��,��,
	static Calendar today = Calendar.getInstance(); 
	static Calendar nottoday=Calendar.getInstance();// ���� ��¥�� ���� ������ ������
	
	int now = today.get(Calendar.MONTH); // ���� �ش� ���� ������ ����
	

	public static void setToday() {
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;
		date = today.get(Calendar.DAY_OF_MONTH);
		
		day=today.get(Calendar.DAY_OF_WEEK)-1;
		whatday = WEEK_DAY_NAME[day];
		
		calStartingPos = (today.get(Calendar.DAY_OF_WEEK) + 7 - (today.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
		if (month == 1) // 1���̶�� �������� �ƴ��� �˻�
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
		if (month == 1) // 1���̶�� �������� �ƴ��� �˻�
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
	public static int leapCheck(int year) { // �������� Ȯ���ϴ� �Լ�
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return 1;
		else
			return 0;
	}
}
