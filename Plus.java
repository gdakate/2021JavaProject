package 실험용;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Plus implements ActionListener {
	static JButton add_button;
	JFrame frame = new JFrame();
	static int x, y, height;
	String inputDay, c;
	int inputStartTime = 0, warning1 = 0, inputEndTime = 0, warning2 = 0, time;
	
	public Plus() {	
	}
	
	public JButton plusbutton() {
		add_button = new JButton("+");
		add_button.setFont(new Font("돋움", Font.BOLD, 13));
		add_button.setBackground(Color.YELLOW);	
		add_button.addActionListener(this);
		return add_button;
	}

	//데일리 탭에 오늘의 고정 시간표 보이기
		public void dailyfix() {
			Time.setToday();
			if (inputDay == Time.whatday) {
				time = inputEndTime - inputStartTime;
				//오전시간
				if ((inputStartTime>=0)&&(inputStartTime<=11)&&(!(inputStartTime+time>11))) { 
					
					//시작시간에 일정 제목 추가
					Daily.AMschedule[inputStartTime].setText(c);
					//종료시간까지 라벨색 paint
					for (int k=0; k<time; k++) {
						Daily.AMschedule[inputStartTime+k].setBackground(new Color(255, 204, 204));
					}		
				}
				//일정시간이 오전, 오후 걸치는 경우
				else if ((inputStartTime+time>11)&&(inputStartTime<12)) { 
					Daily.AMschedule[inputStartTime].setText(c);

					int c=0;
					while (inputStartTime+c<=11) {
						Daily.AMschedule[inputStartTime+c].setBackground(new Color(255, 204, 204));
						c++;
						if (c==time-(12-inputStartTime)) break;
					}
					for (int k=0; k<time-c; k++) {
						Daily.PMschedule[k].setBackground(new Color(255, 204, 204));
					}
				}
				//오후시간
				else {
					//시작시간에 title 추가
					Daily.PMschedule[inputStartTime-12].setText(c);
					//종료시간까지 라벨색 paint
					for (int k=0; k<time; k++) {
						Daily.PMschedule[inputStartTime-12+k].setBackground(new Color(255, 204, 204));
					}
				}
			}
		}
	
	//위클리 탭에 고정 시간표 보이기
		public void weeklyfix() {			
			switch (inputDay) {
		      case "MON" : Weekly.mon_text.append(inputStartTime + "~" + inputEndTime + " " + c + "\n");
		               break;
		      case "TUE" : Weekly.tue_text.append(inputStartTime + "~" + inputEndTime + " " + c + "\n");
		               break;
		      case "WED" : Weekly.wen_text.append(inputStartTime + "~" + inputEndTime + " " + c + "\n");
		               break;
		      case "THU" : Weekly.thu_text.append(inputStartTime + "~" + inputEndTime + " " + c + "\n");
		               break;
		      case "FRI" : Weekly.fri_text.append(inputStartTime + "~" + inputEndTime + " " + c + "\n");
		               break;
		      case "SAT" : Weekly.sat_text.append(inputStartTime + "~" + inputEndTime + " " + c + "\n");
		               break;
		      case "SUN" : Weekly.sun_text.append(inputStartTime + "~" + inputEndTime + " " + c + "\n");
		            break;
		     }
		}
	
	//시간표에 일정 추가
		public void Schedule_add() {
			Color color = new Color((int)(Math.random()*255.0), 
					(int)(Math.random()*255.0), (int)(Math.random()*255.0));;		
					
			switch (inputDay) {
		      case "MON" : x = 0 + 7*(inputStartTime - 9);
		               break;
		      case "TUE" : x = 1 + 7*(inputStartTime - 9);
		               break;
		      case "WED" : x = 2 + 7*(inputStartTime - 9);
		               break;
		      case "THU" : x = 3 + 7*(inputStartTime - 9);
		               break;
		      case "FRI" : x = 4 + 7*(inputStartTime - 9);
		               break;
		      case "SAT" : x = 5 + 7*(inputStartTime - 9);
		               break;
		      case "SUN" : x = 6 + 7*(inputStartTime - 9);
		            break;
		     }
			
			Schedule.text[x].setBackground(color);
			Schedule.text[x].setFont(new Font("돋움", Font.BOLD, 11));
			Schedule.text[x].setLineWrap(true);
			Schedule.text[x].setText(c);
			
			int time = inputEndTime - inputStartTime;
			if (time > 0) {
				for (int i = 1; i < time; i++) {
					Schedule.text[x+7*i].setBackground(color);
				}
			}
		}
		
	//일정 추가 입력창
	@Override
	public void actionPerformed(ActionEvent e) {	
		add_button = (JButton) e.getSource();
		//요일 선택
		String[] days = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
		inputDay = (String) JOptionPane.showInputDialog(null, "요일을 선택하세요", "일정추가 - 요일선택",
				JOptionPane.QUESTION_MESSAGE, null, days, "MON");
		if (inputDay == null) return;
		
		//일정 시작시간 입력
		try {
			while (true) {
				inputStartTime = Integer.parseInt((String) JOptionPane.showInputDialog (null, "일정의 시작시간을 작성하세요 "
						+ "\n9에서 22사이의 정수만 입력가능합니다.", "일정추가 - 시작시간", 
						JOptionPane.INFORMATION_MESSAGE));

				if (9 <= inputStartTime && inputStartTime <= 22) break;
				JOptionPane.showMessageDialog(null, "9시에서 22시만 가능합니다.");
			}
		} catch (Exception e1) {
			while (true) {
				warning1 = Integer.parseInt((String) JOptionPane.showInputDialog(null, "숫자만 입력해주세요. ex) 16시 -> 16", "일정 시작시간 재입력",
						JOptionPane.INFORMATION_MESSAGE));

				if (9 <= warning1 && warning1 <= 22) break;
				JOptionPane.showMessageDialog(null, "9시에서 22시만 가능합니다.");
			}
		}
		if(inputStartTime == 0) inputStartTime = warning1;
		
		//일정 종료시간 입력
		try {
			while (true) {
				inputEndTime = Integer.parseInt((String) JOptionPane.showInputDialog (null, "일정의 종료시간을 작성하세요 "
						+ "\n9에서 23사이의 정수만 입력가능합니다", "일정추가 - 종료시간", JOptionPane.INFORMATION_MESSAGE));

				if (9 > inputEndTime || inputEndTime > 23) 
					JOptionPane.showMessageDialog(null, "10시에서 23시만 가능합니다.");
				else if (inputEndTime <= inputStartTime) 
					JOptionPane.showMessageDialog(null, "종료시간은 시작시간보다 커야합니다.");
				else break;
			}
		} catch (Exception e1) {
			while (true) {
				warning2 = Integer.parseInt((String) JOptionPane.showInputDialog(null, "숫자만 입력해주세요. ex) 16시 -> 16", "일정 종료시간 재입력",
						JOptionPane.INFORMATION_MESSAGE));

				if (10 > warning2 || warning2 > 23) 
					JOptionPane.showMessageDialog(null, "10시에서 23시만 가능합니다.");
				else if (warning2 <= inputStartTime) 
					JOptionPane.showMessageDialog(null, "종료시간은 시작시간보다 커야합니다.");
				else break;
			}
		}
		if(inputEndTime == 0) inputEndTime = warning2;
		
		
		//일정 내용 입력
		c = (String) JOptionPane.showInputDialog(null, "일정을 작성하세요", "일정추가 - 상세일정", 
				JOptionPane.INFORMATION_MESSAGE);
		if (c == null) return;

		
		Schedule_add();
		dailyfix();
		weeklyfix();
	}
}
