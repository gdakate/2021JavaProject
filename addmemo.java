package �����;

import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.awt.*;

class Plan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1457755921343461105L;
	String M; //��
	String D; //��
	String title; //���� ����
	
	public Plan() {}
	
	public Plan(String M, String D, String title) {
		this.M = M;
		this.D = D;
		this.title = title;
	}
	
	public String toString() {
		return M+"/"+D+": "+ title;
	}
	
	public String getTitle() {
		return title;
	}
	public String getdate() {
		return M+"/"+D;
	}
}


public class addmemo extends JFrame implements ActionListener {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 9033251908498894965L;
	JPanel settimepane;
	JTextField t, mmonth, dday;
	JButton ok;
	JCheckBox checktime;
	boolean flag = false, pass = true;
	
	JLabel mmmonth, ddday, settime, from, until;
	JTextField timeSpinner1, timeSpinner2;

	public addmemo() {
	        setTitle("������ �߰��ϼ���");
	        
	        JPanel NewWindowContainer = new JPanel();
	        setContentPane(NewWindowContainer);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        mmonth=new JTextField(2);
	        mmmonth=new JLabel("��"); 
	        dday=new JTextField(2);
	        ddday=new JLabel("��");
	        
	        mmonth.addActionListener(new EventHandler());
	        dday.addActionListener(new EventHandler());
	        
	        add(mmonth);
	        add(mmmonth);
	        add(dday);
	        add(ddday);
	        	               
	        JLabel say=new JLabel("���� �߰�");
	        t=new JTextField("���� ����",20);
	        
	        t.addActionListener(new EventHandler());
	        
	        add(say);
	        add(t);
	        
	        settime=new JLabel("�ð� ����");
	        checktime=new JCheckBox ("",flag);
	        checktime.addActionListener(new EventHandler());
	        
	        add(settime);
	        add(checktime);
	        
	        //�ð� ���� �г�
	        settimepane = new JPanel();
	        
	        timeSpinner1 = new JTextField("00",2 );
	        from=new JLabel("�� ����");	        
	        timeSpinner2 = new JTextField("23",2);	    
	        until=new JLabel("�� ����");

	        settimepane.add(timeSpinner1);
	        settimepane.add(from);
	        settimepane.add(timeSpinner2);
	        settimepane.add(until);
	        
	        add(settimepane, BorderLayout.CENTER);
	        
	        settimepane.setVisible(flag);
	        
	        JPanel bottom = new JPanel();
	        
	        JButton cancel =new JButton("���");
	        cancel.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e){
	        		dispose();
	        		}
	        });
	        
	        ok =new JButton("Ȯ��"); 
	        ok.addActionListener(new EventHandler());
	        
	        bottom.add(cancel, BorderLayout.WEST);
	        bottom.add(ok, BorderLayout.EAST);
	        
	        add(bottom, BorderLayout.SOUTH);

	        setSize(300,250);
	        setResizable(false);
	        setVisible(true);
	        
	}
	
	class EventHandler extends Time implements ActionListener{
		static final int i=0,j=0;		
		JFrame frame;
		int monthcheck, daycheck, startTcheck, endTcheck;
		int during;
		Plan addPlan;
		boolean fixcheck = true;
		
		//Monthly �ǿ� �߰��ϴ� �Լ�
		public void addtoMonthly() {
			String s=dday.getText();
            String tododo=t.getText();
            Color color=new Color(0,0,255);
            
            for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
               if (i == 0)
                  k = calStartingPos;
               else
                  k = 0;
               for (int j = k; j < CAL_WIDTH; j++) {
                  if (num<=lastday) {
                     Monthly.dateButton[i][j].append("");
                     num++;
                     int When=Integer.parseInt(s);
                     if(num-1==When) {
                        int Num=num-1;
                        Monthly.dateButton[i][j].append(tododo);
                     }
                  }
               }
            }
            dispose();
		}
		
	      //Weekly �ǿ� �߰��ϴ� �Լ�
	      public void addtoWeekly() {
	         Time.setToday();
	         switch (Time.whatday) {
	            case "MON" : Weekly.mon_text.append(startTcheck + "~" + endTcheck + " " + addPlan.getTitle() + "\n");
	                     break;
	            case "TUE" : Weekly.tue_text.append(startTcheck + "~" + endTcheck + " " + addPlan.getTitle() + "\n");
	                     break;
	            case "WED" : Weekly.wen_text.append(startTcheck + "~" + endTcheck + " " + addPlan.getTitle() + "\n");
	                     break;
	            case "THU" : Weekly.thu_text.append(startTcheck + "~" + endTcheck + " " + addPlan.getTitle() + "\n");
	                     break;
	            case "FRI" : Weekly.fri_text.append(startTcheck + "~" + endTcheck + " " + addPlan.getTitle() + "\n");
	                     break;
	            case "SAT" : Weekly.sat_text.append(startTcheck + "~" + endTcheck + " " + addPlan.getTitle() + "\n");
	                     break;
	            case "SUN" : Weekly.sun_text.append(startTcheck + "~" + endTcheck + " " + addPlan.getTitle() + "\n");
	                  break;
	           }
	      }

		//Daily �ǿ� �߰��ϴ� �Լ�
		public void addtoDaily() {
			//���� ��¥�� �������� Ȯ��
			Time.setToday();
			if ((monthcheck==Time.month)&&(daycheck==Time.date)) {
				
				//�ð� ���� ���� Ȯ��
				if (flag==true) { //�ð� ����O
					
					//�Է¹��� �ð��� ������ �ƴ� ��
					try {
						startTcheck = Integer.parseInt(timeSpinner1.getText());
						endTcheck = Integer.parseInt(timeSpinner2.getText());
					} catch (java.lang.NumberFormatException e1) {
						startTcheck=0;
						endTcheck = 0;
					}
					during = endTcheck-startTcheck;
					
					//�ð� ����-�˸°� �Է�������
					if ((startTcheck>=0)&&(endTcheck<=23)&&(during>0)) {

						//�����ð�
						if ((startTcheck>=0)&&(startTcheck<=11)&&(!(startTcheck+during>11))) { 
							//�����ð�ǥ�� �ð��� ��ġ���� Ȯ��
							for (int k=0; k<during; k++) {
								if (Daily.AMschedule[startTcheck+k].getBackground()!=Color.white) {
									JOptionPane.showMessageDialog(frame, "�ð��� ��Ĩ�ϴ�.\n�ߺ����� �ʰ� �ٽ� �Է��ϼ���.", "�ð� �ߺ�", JOptionPane.WARNING_MESSAGE);
									fixcheck=false;
									dispose();
									break;
								}
							}
							if (fixcheck) {
								//���۽ð��� title �߰�
								Daily.AMschedule[startTcheck].setText(addPlan.getTitle());
								//����ð����� �󺧻� paint
								for (int k=0; k<during; k++) {
									Daily.AMschedule[startTcheck+k].setBackground(new Color(204, 255, 153));
								}
							}
							
						}
						//�����ð��� ����, ���� ��ġ�� ���
						else if ((startTcheck+during>11)&&(startTcheck<12)) { 
							//�����ð�ǥ�� �ð��� ��ġ���� Ȯ��
							int c=0;
							while (startTcheck+c<=11) {
								if (Daily.AMschedule[startTcheck+c].getBackground()!=Color.white) {
									JOptionPane.showMessageDialog(frame, "�ð��� ��Ĩ�ϴ�.\n�ߺ����� �ʰ� �ٽ� �Է��ϼ���.", "�ð� �ߺ�", JOptionPane.WARNING_MESSAGE);
									fixcheck=false;
									dispose();
									break;
								}
								c++;
								if (c==during-(12-startTcheck)) break;
							}
							
							for (int k=0; k<during-c; k++) {
								if (Daily.PMschedule[k].getBackground()!=Color.white) {
									JOptionPane.showMessageDialog(frame, "�ð��� ��Ĩ�ϴ�.\n�ߺ����� �ʰ� �ٽ� �Է��ϼ���.", "�ð� �ߺ�", JOptionPane.WARNING_MESSAGE);
									fixcheck=false;
									dispose();
									break;
								}
							}
							
							if (fixcheck) {
								Daily.AMschedule[startTcheck].setText(addPlan.getTitle());

								c=0;
								while (startTcheck+c<=11) {
									Daily.AMschedule[startTcheck+c].setBackground(new Color(204, 255, 153));
									c++;
									if (c==during-(12-startTcheck)) break;
								}
								for (int k=0; k<during-c; k++) {
									Daily.PMschedule[k].setBackground(new Color(204, 255, 153));
								}
							}
						}
						//���Ľð�
						else {
							//�����ð�ǥ�� �ð��� ��ġ���� Ȯ��
							for (int k=0; k<during; k++) {
								if (Daily.PMschedule[startTcheck-12+k].getBackground()!=Color.white) {
									JOptionPane.showMessageDialog(frame, "�ð��� ��Ĩ�ϴ�.\n�ߺ����� �ʰ� �ٽ� �Է��ϼ���.", "�ð� �ߺ�", JOptionPane.WARNING_MESSAGE);
									fixcheck=false;
									dispose();
									break;
								}
							}
							if (fixcheck) {
								//���۽ð��� title �߰�
								Daily.PMschedule[startTcheck-12].setText(addPlan.getTitle());
								//����ð����� �󺧻� paint
								for (int k=0; k<during; k++) {
									Daily.PMschedule[startTcheck-12+k].setBackground(new Color(153, 204, 255));
								}
							}
						}
						if (fixcheck) {
							JOptionPane.showMessageDialog(frame, addPlan+"\n���� ��� �Ϸ�");
							dispose();
						}
					}
					//�ð� ����-�Է� ������
					else {
						String timeerror = "�˸´� ���� �Է��ϼ���.\n 0~23�� \n �ð� ������ 1�ð� �̻��̾�� �մϴ�.";
						JOptionPane.showMessageDialog(frame, timeerror, "���� �߰� ����: �ð� ���� �Է� ����", JOptionPane.WARNING_MESSAGE);
						dispose();
					}
				}
				else { //�ð� ����X -> �׳� �˸����� �ؽ�Ʈ�� �߰�
					Daily_schedule.Show_Calendar.append(addPlan.getTitle());
					Daily_schedule.Show_Calendar.append("\n");
					Daily_schedule.Show_Calendar.setEditable(false);
					JOptionPane.showMessageDialog(frame, addPlan+"\n���� ��� �Ϸ�");
					dispose();
				}	
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == checktime) { //�ð� ������ üũ�ϸ� �ð� ������ ����
				if (flag==false) {
					flag=true;
					settimepane.setVisible(flag);
				}
				else if (flag==true) {  //�ð� ������ üũ �����ϸ� �ð� ������ �Ⱥ���
					flag=false;
					settimepane.setVisible(flag);
				}
			}
			if (e.getSource() == ok) { //ok��ư�� ������ �Է��� ���� ����
				//��¥�� �˸°� �Է��ߴ��� Ȯ��
				//�Է¹��� ���� ���� ������ �ƴ� ��
				try {
					monthcheck = Integer.parseInt(mmonth.getText());
					daycheck = Integer.parseInt(dday.getText());
				} catch (java.lang.NumberFormatException e1) {
					monthcheck=0;
					daycheck = 0;
				}
				
				//��¥-�˸°� �Է�������
				if (((monthcheck>=1)&&(monthcheck<=12))&&((daycheck>=1)&&(daycheck<=31))) {
					addPlan = new Plan(mmonth.getText(), dday.getText(), t.getText());

					//Monthly�� ���� �߰�
					addtoMonthly();
					
					//Weekly�� ���� �߰�
					addtoWeekly();
					
					//daily�� ���� �߰�
					addtoDaily();
			}
			//�˸°� �Է����� �ʾ�����
				else {
					String error = "�˸´� ���� �Է��ϼ���.\n 1~12��, 1~31��";
					JOptionPane.showMessageDialog(frame, error, "���� �߰� ����: ��¥ �Է� ����", JOptionPane.WARNING_MESSAGE);
					dispose();
				}	
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	 
