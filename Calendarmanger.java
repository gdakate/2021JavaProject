package �����;

import java.util.Calendar;

public class Calendarmanger {
	String WEEK_DAY_NAME[] = { "SUN", "MON", "THU", "WED", "THU", "FRI", "SAT" }; // ��~�� �迭
	final int calLastDateOfMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // 1~12���� ����¥�� �迭������
	final int Calendar_Array[] = { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL, Calendar.MAY,
			Calendar.JUNE, Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER,
			Calendar.DECEMBER }; // �� �̵��� ���� 1~12�� �迭
	final int CAL_WIDTH = 7; 
	final int CAL_HEIGHT = 6;
	int year; 
	int month;
	int date;
	int dayofweek = 7; // ���� ������ �Ͽ���=0
	int lastday; // ���� ��������
	int calStartingPos; // ���� ������
	String now_date; // ���� ��,��,��
	String now_day;//���� ��, ��

	Calendar today = Calendar.getInstance(); // ���� ��¥�� ���� ������ ������
	int now = today.get(Calendar.MONTH); // ���� �ش� ���� ������ ����

	public Calendarmanger() {
		setToday();
	}

	public void setToday() {
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH) + 1;
		date = today.get(Calendar.DAY_OF_MONTH);
		calStartingPos = (today.get(Calendar.DAY_OF_WEEK) + 7 - (today.get(Calendar.DAY_OF_MONTH)) % 7) % 7;
		if (month == 1) // 1���̶�� �������� �ƴ��� �˻�
			lastday = calLastDateOfMonth[month - 1] + leapCheck(year);
		else
			lastday = calLastDateOfMonth[month - 1];
	}

	private int leapCheck(int year) { // �������� Ȯ���ϴ� �Լ�
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return 1;
		else
			return 0;
		
	}

}
