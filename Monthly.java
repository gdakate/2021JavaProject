

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
		YMLabel.setText(year + "��" + mmoonth + "��");
		YMLabel.setFont(new Font("����", Font.BOLD, 15));
		YMLabel.setHorizontalAlignment(JLabel.CENTER);
		NorthPanel_Center.add(YMLabel);

		JLabel MMonth=new JLabel(month+"��");
		MMonth.setFont(new Font("����", Font.BOLD, 13));
		//NorthPanel_Center.add(MMonth);
	
		
		
		NorthPanel_Center.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 70));
		monthly.add(NorthPanel_Center, BorderLayout.NORTH);
		
		monthlayout=new JPanel();
		JLabel[] WeekDaysButton = new JLabel[7];
		dateButton = new JTextArea [CAL_HEIGHT][CAL_WIDTH];
		todo= new JTextField[CAL_HEIGHT][CAL_WIDTH];
		plans= new JTextArea[CAL_HEIGHT][CAL_WIDTH];
		// ���� ��ư ���ڻ� => ��~��
				for (int i = 0; i < CAL_WIDTH; i++) {
					WeekDaysButton[i] = new JLabel(WEEK_DAY_NAME[i]);
					WeekDaysButton[i].setFont(new Font("����", Font.BOLD, 15));
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
						todo[i][j].setFont(new Font("����", Font.BOLD, 11));
						todo[i][j].setOpaque(true);
						todo[i][j].setBackground(new Color(255, 255, 255));
						plans[i][j].setForeground(Color.BLUE);
						plans[i][j].setFont(new Font("����", Font.BOLD, 11));
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
				// dayofweek �� ���� ����ó�� (�� 1�� ���� �ִ� ��ư�� ""���� �ؽ�Ʈ�� ����)
				for (int j = 0; j < 1; j++) {
					for (int i = 0; i < dayofweek - 1; i++) {
						dateButton[j][i].setText("");
						
						
						
					}
				}
				
				// �ش���� ���� ��¥��ư �˰���
				
				
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
				// �гο� ��ư�߰� (��12���� �´� ���ϴ�� ��ư �ؽ�Ʈ ����)
				for (int i = 0; i < CAL_HEIGHT; i++) {
					for (int j = 0; j < CAL_WIDTH; j++) {

						dateButton[i][j].add(todo[i][j]);
						

					}
				}
				monthlayout.setLayout(new GridLayout(0, 7, 2, 2));

				monthly.add(monthlayout, BorderLayout.CENTER); // ����г��� �����Ӿȿ� ����
				
				Dimension dim= new Dimension(500,500);
				JFrame frame=new JFrame("������ �߰��ϼ���");
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


	

