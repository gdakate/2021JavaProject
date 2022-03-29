

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.Border;





public class Monthly extends Time {
	Time today;
	String mon,tue,wen,thu,fri,sat,sun;
	Memo memo;
	TextArea monthlymemo;
	final int CAL_WIDTH = 7; 
	final int CAL_HEIGHT = 6;
	
	public Monthly() {}
	public static JTextArea[][] dateButton;
	public static JTextField[][] todo;
	public static JTextArea[][] plans;
	public static JPanel monthlayout;
	public static int mmoonth=6;

	public JPanel monthlytab() {
		JPanel monthly = new JPanel();
		monthly.setLayout(new BorderLayout());
		JPanel monthlyCal = new JPanel();
		monthlyCal.setLayout(new BorderLayout());
		
		
		today = new Time();
		setToday();
		
		JLabel YMLabel = new JLabel();
		JPanel NorthPanel=new JPanel();
		NorthPanel.setLayout(new BorderLayout());
		JPanel NorthPanel_Center=new JPanel();
		YMLabel.setText(year + "년" + mmoonth + "월");
		YMLabel.setFont(new Font("돋움", Font.BOLD, 15));
		YMLabel.setHorizontalAlignment(JLabel.CENTER);
		NorthPanel_Center.add(YMLabel);

		JLabel MMonth=new JLabel(month+"월");
		MMonth.setFont(new Font("돋움", Font.BOLD, 13));
		//NorthPanel_Center.add(MMonth);
	
		
		
		NorthPanel_Center.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 70));
		monthly.add(NorthPanel_Center, BorderLayout.NORTH);
		
		monthlayout=new JPanel();
		JLabel[] WeekDaysButton = new JLabel[7];
		dateButton = new JTextArea [CAL_HEIGHT][CAL_WIDTH];
		todo= new JTextField[CAL_HEIGHT][CAL_WIDTH];
		plans= new JTextArea[CAL_HEIGHT][CAL_WIDTH];
		// 요일 버튼 글자색 => 월~일
				for (int i = 0; i < CAL_WIDTH; i++) {
					WeekDaysButton[i] = new JLabel(WEEK_DAY_NAME[i]);
					WeekDaysButton[i].setFont(new Font("돋움", Font.BOLD, 15));
					WeekDaysButton[i].setOpaque(true);
					WeekDaysButton[i].setBackground(new Color(255, 255, 204));
					if (i == 0)
						WeekDaysButton[i].setForeground(Color.red);
					else if (i == 6)
						WeekDaysButton[i].setForeground(Color.BLUE);
					else
						WeekDaysButton[i].setForeground(Color.BLACK);
					monthlayout.add(WeekDaysButton[i]);
				}
				for (int i = 0; i < CAL_HEIGHT; i++) {
					for (int j = 0; j < CAL_WIDTH; j++) {
						dateButton[i][j] = new JTextArea();
						todo[i][j]=new JTextField() {
						//public void setBorder(Border border) {}
						};
						plans[i][j]=new JTextArea();
						dateButton[i][j].setForeground(Color.BLACK);
						dateButton[i][j].setOpaque(true);
						todo[i][j].setForeground(Color.BLACK);
						todo[i][j].setFont(new Font("돋움", Font.BOLD, 11));
						todo[i][j].setOpaque(true);
						todo[i][j].setBackground(new Color(255, 255, 255));
						plans[i][j].setForeground(Color.BLUE);
						plans[i][j].setFont(new Font("돋움", Font.BOLD, 11));
						plans[i][j].setOpaque(true);
						plans[i][j].setBackground(new Color(255, 255, 255));
						dateButton[i][j].setBackground(new Color(255, 255, 255));
						dateButton[i][j].setPreferredSize(new Dimension(20, 20));
						todo[i][j].setPreferredSize(new Dimension(20, 20));
						plans[i][j].setPreferredSize(new Dimension(20, 20));
						monthlayout.add(dateButton[i][j]);
						dateButton[i][j].add(todo[i][j]);
						dateButton[i][j].add(plans[i][j]);
						
						
						
					}
				}
				setToday();
				// dayofweek 전 날은 공백처리 (즉 1일 전에 있는 버튼을 ""공백 텍스트로 설정)
				for (int j = 0; j < 1; j++) {
					for (int i = 0; i < dayofweek - 1; i++) {
						dateButton[j][i].setText("");
						
						
						
					}
				}
				
				// 해당월에 대한 날짜버튼 알고리즘
				
				
				for(int p=0;p<CAL_HEIGHT;p++) {
				for (int i = 0,num=1, k = 0; i <p; i++) {
					if (i == 0)
						k = calStartingPos;
					else
						k = 0;
					for (int j = k,q=0; j < CAL_WIDTH; j++) {
						
						if (num <= lastday) {
							dateButton[i][j].setText(""+num++);
							
							}
						

					}
				}}
				// 패널에 버튼추가 (즉12월에 맞는 요일대로 버튼 텍스트 설정)
				for (int i = 0; i < CAL_HEIGHT; i++) {
					for (int j = 0; j < CAL_WIDTH; j++) {

						dateButton[i][j].add(todo[i][j]);
						

					}
				}
				monthlayout.setLayout(new GridLayout(0, 7, 2, 2));

				monthly.add(monthlayout, BorderLayout.CENTER); // 가운데패널을 프레임안에 삽입
				
				Dimension dim= new Dimension(500,500);
				JFrame frame=new JFrame("일정을 추가하세요");
				frame.setLocation(200,400);
				frame.setPreferredSize(dim);
				
				JButton button=new JButton();
				JPanel weeklytitle = new JPanel();
				weeklytitle.setLayout(new FlowLayout());
				button=new JButton("+");
				
				button.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						new addmemo();
						
					
					}
				});
				weeklytitle.add(button);
				monthly.add(weeklytitle, BorderLayout.EAST);
			
				
				
				
				Memo memo= new Memo();
				JPanel memoPanel=new JPanel();
				memoPanel=memo.memo();
				
				
				monthly.add(memoPanel,BorderLayout.SOUTH);
				


	return monthly;
	}
	

}


	

