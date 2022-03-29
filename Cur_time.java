package 실험용;

import java.util.Calendar;
import java.awt.*;
import javax.swing.*;

public class Cur_time extends Time {
	JLabel infoClock, bottomInfo;
	Calendar today;
	//오늘 날짜와 현재 시간을 나타낼 패널을 만드는 클래스
	
	private JPanel CurTimepanel;
	public Cur_time() {
		CurTimepanel = new JPanel();
		CurTimepanel.setSize(300, 200);
		infoClock = new JLabel();
		
		setToday();
		bottomInfo = new JLabel("Welcome");
		ThreadConrol threadCnl = new ThreadConrol();
		threadCnl.start();	
		
		CurTimepanel.add(infoClock, BorderLayout.CENTER);
	}
	
	private class ThreadConrol extends Thread{
		public void run(){
			boolean msgCntFlag = false;
			int num = 0;
			String curStr = new String();
			while(true){
				try{
					today = Calendar.getInstance();
					String amPm = (today.get(Calendar.AM_PM)==0?"AM":"PM");
					String hour;
							if(today.get(Calendar.HOUR) == 0) hour = "12"; 
							else if(today.get(Calendar.HOUR) == 12) hour = " 0";
							else hour = (today.get(Calendar.HOUR)<10?" ":"")+today.get(Calendar.HOUR);
					String min = (today.get(Calendar.MINUTE)<10?"0":"")+today.get(Calendar.MINUTE);
					String sec = (today.get(Calendar.SECOND)<10?"0":"")+today.get(Calendar.SECOND);
					infoClock.setText(month+" / "+date+" "+ whatday+" "+amPm+" "+hour+":"+min+":"+sec);
					infoClock.setFont(new Font("Arial", Font.BOLD, 20));

					sleep(1000);
					String infoStr = bottomInfo.getText();
					
					if(infoStr != " " && (msgCntFlag == false || curStr != infoStr)){
						num = 5;
						msgCntFlag = true;
						curStr = infoStr;
					}
					else if(infoStr != " " && msgCntFlag == true){
						if(num > 0) num--;
						else{
							msgCntFlag = false;
							bottomInfo.setText(" ");
						}
					}		
				}
				catch(InterruptedException e){
					System.out.println("Thread:Error");
				}
			}
		}
	}
	
	public JPanel cur_time() { //현재 시간을 나타낼 패널을 리턴
		return CurTimepanel;
	}

}
