package ½ÇÇè¿ë;

import javax.swing.*;

public class Main {
	
	JFrame main;
	JPanel mPane;
	
	public Main() {
		main = new JFrame();
		mPane = new JPanel();
		mPane.setLayout(null);
		
		Cur_time currentTime = new Cur_time();
		JPanel curtimePane = currentTime.cur_time();
		curtimePane.setBounds(10, 10, 400, 50);
		mPane.add(curtimePane);
		
		Schedule schedule = new Schedule();
		schedule.mainP.setBounds(10, 60, 400, 500);
		mPane.add(schedule.mainP);
		
		CalendarTab calendartab = new CalendarTab();
		JTabbedPane tabs = calendartab.calendartab();
		tabs.setBounds(430, 10, 550, 650);
		mPane.add(tabs);
		
		main.setContentPane(mPane);
		main.setTitle("Schedule Management Ver1.0");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(1000,700);
		main.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}
	

}
