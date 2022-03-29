package �����;

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
		add_button.setFont(new Font("����", Font.BOLD, 13));
		add_button.setBackground(Color.YELLOW);	
		add_button.addActionListener(this);
		return add_button;
	}

	//���ϸ� �ǿ� ������ ���� �ð�ǥ ���̱�
		public void dailyfix() {
			Time.setToday();
			if (inputDay == Time.whatday) {
				time = inputEndTime - inputStartTime;
				//�����ð�
				if ((inputStartTime>=0)&&(inputStartTime<=11)&&(!(inputStartTime+time>11))) { 
					
					//���۽ð��� ���� ���� �߰�
					Daily.AMschedule[inputStartTime].setText(c);
					//����ð����� �󺧻� paint
					for (int k=0; k<time; k++) {
						Daily.AMschedule[inputStartTime+k].setBackground(new Color(255, 204, 204));
					}		
				}
				//�����ð��� ����, ���� ��ġ�� ���
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
				//���Ľð�
				else {
					//���۽ð��� title �߰�
					Daily.PMschedule[inputStartTime-12].setText(c);
					//����ð����� �󺧻� paint
					for (int k=0; k<time; k++) {
						Daily.PMschedule[inputStartTime-12+k].setBackground(new Color(255, 204, 204));
					}
				}
			}
		}
	
	//��Ŭ�� �ǿ� ���� �ð�ǥ ���̱�
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
	
	//�ð�ǥ�� ���� �߰�
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
			Schedule.text[x].setFont(new Font("����", Font.BOLD, 11));
			Schedule.text[x].setLineWrap(true);
			Schedule.text[x].setText(c);
			
			int time = inputEndTime - inputStartTime;
			if (time > 0) {
				for (int i = 1; i < time; i++) {
					Schedule.text[x+7*i].setBackground(color);
				}
			}
		}
		
	//���� �߰� �Է�â
	@Override
	public void actionPerformed(ActionEvent e) {	
		add_button = (JButton) e.getSource();
		//���� ����
		String[] days = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
		inputDay = (String) JOptionPane.showInputDialog(null, "������ �����ϼ���", "�����߰� - ���ϼ���",
				JOptionPane.QUESTION_MESSAGE, null, days, "MON");
		if (inputDay == null) return;
		
		//���� ���۽ð� �Է�
		try {
			while (true) {
				inputStartTime = Integer.parseInt((String) JOptionPane.showInputDialog (null, "������ ���۽ð��� �ۼ��ϼ��� "
						+ "\n9���� 22������ ������ �Է°����մϴ�.", "�����߰� - ���۽ð�", 
						JOptionPane.INFORMATION_MESSAGE));

				if (9 <= inputStartTime && inputStartTime <= 22) break;
				JOptionPane.showMessageDialog(null, "9�ÿ��� 22�ø� �����մϴ�.");
			}
		} catch (Exception e1) {
			while (true) {
				warning1 = Integer.parseInt((String) JOptionPane.showInputDialog(null, "���ڸ� �Է����ּ���. ex) 16�� -> 16", "���� ���۽ð� ���Է�",
						JOptionPane.INFORMATION_MESSAGE));

				if (9 <= warning1 && warning1 <= 22) break;
				JOptionPane.showMessageDialog(null, "9�ÿ��� 22�ø� �����մϴ�.");
			}
		}
		if(inputStartTime == 0) inputStartTime = warning1;
		
		//���� ����ð� �Է�
		try {
			while (true) {
				inputEndTime = Integer.parseInt((String) JOptionPane.showInputDialog (null, "������ ����ð��� �ۼ��ϼ��� "
						+ "\n9���� 23������ ������ �Է°����մϴ�", "�����߰� - ����ð�", JOptionPane.INFORMATION_MESSAGE));

				if (9 > inputEndTime || inputEndTime > 23) 
					JOptionPane.showMessageDialog(null, "10�ÿ��� 23�ø� �����մϴ�.");
				else if (inputEndTime <= inputStartTime) 
					JOptionPane.showMessageDialog(null, "����ð��� ���۽ð����� Ŀ���մϴ�.");
				else break;
			}
		} catch (Exception e1) {
			while (true) {
				warning2 = Integer.parseInt((String) JOptionPane.showInputDialog(null, "���ڸ� �Է����ּ���. ex) 16�� -> 16", "���� ����ð� ���Է�",
						JOptionPane.INFORMATION_MESSAGE));

				if (10 > warning2 || warning2 > 23) 
					JOptionPane.showMessageDialog(null, "10�ÿ��� 23�ø� �����մϴ�.");
				else if (warning2 <= inputStartTime) 
					JOptionPane.showMessageDialog(null, "����ð��� ���۽ð����� Ŀ���մϴ�.");
				else break;
			}
		}
		if(inputEndTime == 0) inputEndTime = warning2;
		
		
		//���� ���� �Է�
		c = (String) JOptionPane.showInputDialog(null, "������ �ۼ��ϼ���", "�����߰� - ������", 
				JOptionPane.INFORMATION_MESSAGE);
		if (c == null) return;

		
		Schedule_add();
		dailyfix();
		weeklyfix();
	}
}
