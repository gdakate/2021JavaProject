package ½ÇÇè¿ë;

import javax.swing.*;

public class CalendarTab {
	JTabbedPane calendarTab;
	
	public CalendarTab() {
		calendarTab = new JTabbedPane();
		
		Monthly monthly=new Monthly();
		Weekly weekly = new Weekly();
		Daily daily = new Daily();
		
		calendarTab.addTab("Monthly", monthly.monthlytab());
		calendarTab.addTab("Weekly", weekly.weeklytab());
		calendarTab.addTab("Daily", daily.dailytab());
	}
	
	public JTabbedPane calendartab() {
		return calendarTab;
	}
	
	
}
