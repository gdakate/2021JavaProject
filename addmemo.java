package 실험용;

import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.awt.*;

class Plan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1457755921343461105L;
	String M; //월
	String D; //일
	String title; //일정 제목
	
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
	        setTitle("일정을 추가하세요");
	        
	        JPanel NewWindowContainer = new JPanel();
	        setContentPane(NewWindowContainer);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        mmonth=new JTextField(2);
	        mmmonth=new JLabel("월"); 
	        dday=new JTextField(2);
	        ddday=new JLabel("일");
	        
	        mmonth.addActionListener(new EventHandler());
	        dday.addActionListener(new EventHandler());
	        
	        add(mmonth);
	        add(mmmonth);
	        add(dday);
	        add(ddday);
	        	               
	        JLabel say=new JLabel("일정 추가");
	        t=new JTextField("일정 제목",20);
	        
	        t.addActionListener(new EventHandler());
	        
	        add(say);
	        add(t);
	        
	        settime=new JLabel("시간 설정");
	        checktime=new JCheckBox ("",flag);
	        checktime.addActionListener(new EventHandler());
	        
	        add(settime);
	        add(checktime);
	        
	        //시간 설정 패널
	        settimepane = new JPanel();
	        
	        timeSpinner1 = new JTextField("00",2 );
	        from=new JLabel("시 부터");	        
	        timeSpinner2 = new JTextField("23",2);	    
	        until=new JLabel("시 까지");

	        settimepane.add(timeSpinner1);
	        settimepane.add(from);
	        settimepane.add(timeSpinner2);
	        settimepane.add(until);
	        
	        add(settimepane, BorderLayout.CENTER);
	        
	        settimepane.setVisible(flag);
	        
	        JPanel bottom = new JPanel();
	        
	        JButton cancel =new JButton("취소");
	        cancel.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e){
	        		dispose();
	        		}
	        });
	        
	        ok =new JButton("확인"); 
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
		
		//Monthly 탭에 추가하는 함수
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
		
	      //Weekly 탭에 추가하는 함수
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

		//Daily 탭에 추가하는 함수
		public void addtoDaily() {
			//일정 날짜가 오늘인지 확인
			Time.setToday();
			if ((monthcheck==Time.month)&&(daycheck==Time.date)) {
				
				//시간 설정 여부 확인
				if (flag==true) { //시간 설정O
					
					//입력받은 시간이 정수가 아닐 때
					try {
						startTcheck = Integer.parseInt(timeSpinner1.getText());
						endTcheck = Integer.parseInt(timeSpinner2.getText());
					} catch (java.lang.NumberFormatException e1) {
						startTcheck=0;
						endTcheck = 0;
					}
					during = endTcheck-startTcheck;
					
					//시간 설정-알맞게 입력했으면
					if ((startTcheck>=0)&&(endTcheck<=23)&&(during>0)) {

						//오전시간
						if ((startTcheck>=0)&&(startTcheck<=11)&&(!(startTcheck+during>11))) { 
							//고정시간표와 시간이 겹치는지 확인
							for (int k=0; k<during; k++) {
								if (Daily.AMschedule[startTcheck+k].getBackground()!=Color.white) {
									JOptionPane.showMessageDialog(frame, "시간이 겹칩니다.\n중복되지 않게 다시 입력하세요.", "시간 중복", JOptionPane.WARNING_MESSAGE);
									fixcheck=false;
									dispose();
									break;
								}
							}
							if (fixcheck) {
								//시작시간에 title 추가
								Daily.AMschedule[startTcheck].setText(addPlan.getTitle());
								//종료시간까지 라벨색 paint
								for (int k=0; k<during; k++) {
									Daily.AMschedule[startTcheck+k].setBackground(new Color(204, 255, 153));
								}
							}
							
						}
						//일정시간이 오전, 오후 걸치는 경우
						else if ((startTcheck+during>11)&&(startTcheck<12)) { 
							//고정시간표와 시간이 겹치는지 확인
							int c=0;
							while (startTcheck+c<=11) {
								if (Daily.AMschedule[startTcheck+c].getBackground()!=Color.white) {
									JOptionPane.showMessageDialog(frame, "시간이 겹칩니다.\n중복되지 않게 다시 입력하세요.", "시간 중복", JOptionPane.WARNING_MESSAGE);
									fixcheck=false;
									dispose();
									break;
								}
								c++;
								if (c==during-(12-startTcheck)) break;
							}
							
							for (int k=0; k<during-c; k++) {
								if (Daily.PMschedule[k].getBackground()!=Color.white) {
									JOptionPane.showMessageDialog(frame, "시간이 겹칩니다.\n중복되지 않게 다시 입력하세요.", "시간 중복", JOptionPane.WARNING_MESSAGE);
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
						//오후시간
						else {
							//고정시간표와 시간이 겹치는지 확인
							for (int k=0; k<during; k++) {
								if (Daily.PMschedule[startTcheck-12+k].getBackground()!=Color.white) {
									JOptionPane.showMessageDialog(frame, "시간이 겹칩니다.\n중복되지 않게 다시 입력하세요.", "시간 중복", JOptionPane.WARNING_MESSAGE);
									fixcheck=false;
									dispose();
									break;
								}
							}
							if (fixcheck) {
								//시작시간에 title 추가
								Daily.PMschedule[startTcheck-12].setText(addPlan.getTitle());
								//종료시간까지 라벨색 paint
								for (int k=0; k<during; k++) {
									Daily.PMschedule[startTcheck-12+k].setBackground(new Color(153, 204, 255));
								}
							}
						}
						if (fixcheck) {
							JOptionPane.showMessageDialog(frame, addPlan+"\n일정 등록 완료");
							dispose();
						}
					}
					//시간 설정-입력 오류시
					else {
						String timeerror = "알맞는 값을 입력하세요.\n 0~23시 \n 시간 간격은 1시간 이상이어야 합니다.";
						JOptionPane.showMessageDialog(frame, timeerror, "일정 추가 실패: 시간 설정 입력 오류", JOptionPane.WARNING_MESSAGE);
						dispose();
					}
				}
				else { //시간 설정X -> 그냥 알림란에 텍스트로 추가
					Daily_schedule.Show_Calendar.append(addPlan.getTitle());
					Daily_schedule.Show_Calendar.append("\n");
					Daily_schedule.Show_Calendar.setEditable(false);
					JOptionPane.showMessageDialog(frame, addPlan+"\n일정 등록 완료");
					dispose();
				}	
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == checktime) { //시간 설정에 체크하면 시간 설정란 보임
				if (flag==false) {
					flag=true;
					settimepane.setVisible(flag);
				}
				else if (flag==true) {  //시간 설정에 체크 해제하면 시간 설정란 안보임
					flag=false;
					settimepane.setVisible(flag);
				}
			}
			if (e.getSource() == ok) { //ok버튼을 누르면 입력한 내용 저장
				//날짜를 알맞게 입력했는지 확인
				//입력받은 월과 일이 정수가 아닐 때
				try {
					monthcheck = Integer.parseInt(mmonth.getText());
					daycheck = Integer.parseInt(dday.getText());
				} catch (java.lang.NumberFormatException e1) {
					monthcheck=0;
					daycheck = 0;
				}
				
				//날짜-알맞게 입력했으면
				if (((monthcheck>=1)&&(monthcheck<=12))&&((daycheck>=1)&&(daycheck<=31))) {
					addPlan = new Plan(mmonth.getText(), dday.getText(), t.getText());

					//Monthly에 일정 추가
					addtoMonthly();
					
					//Weekly에 일정 추가
					addtoWeekly();
					
					//daily에 일정 추가
					addtoDaily();
			}
			//알맞게 입력하지 않았으면
				else {
					String error = "알맞는 값을 입력하세요.\n 1~12월, 1~31일";
					JOptionPane.showMessageDialog(frame, error, "일정 추가 실패: 날짜 입력 오류", JOptionPane.WARNING_MESSAGE);
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
	 
