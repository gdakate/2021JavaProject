package 실험용;

import java.awt.*;
import javax.swing.*;

public class Daily extends Time {
	private JPanel todaypane;
	private JPanel bottompane;

	static JLabel[] AMschedule, PMschedule;
	JLabel[] AMtable, PMtable;
	

	public Daily() {
		//시간대 테이블 설정
		String[] clock = new String[24];
		
		for (int i =0; i<24; i++) {
			String cl = Integer.toString(i);
			clock[i]=cl+": 00";
		}

		setToday();
	
		todaypane = new JPanel(); //today프레임 객체 생성
		todaypane.setSize(600, 700);
		
		todaypane.setLayout(new GridLayout(3, 1));
		todaypane.setFont(new Font("돋움", Font.PLAIN, 15));
		todaypane.getFont();

		todaypane.setLayout(new BorderLayout(5, 5));
		
		//가장 위쪽 패널
		JPanel NorthPanel = new JPanel();
		NorthPanel.setLayout(new BorderLayout());
		
		JLabel todayLabel = new JLabel();
		todayLabel.setText(month+" / "+date+"Today's Schedule");
		todayLabel.setFont(new Font("Arial", Font.BOLD, 15));
		todayLabel.setHorizontalAlignment(JLabel.CENTER);
		
		NorthPanel.add(todayLabel, BorderLayout.CENTER);
		todaypane.add(NorthPanel, BorderLayout.NORTH);
		
		//가운데 패널
		JPanel CenterPanel = new JPanel();
		CenterPanel.setBackground(Color.white);
		CenterPanel.setLayout(new GridLayout(1,0));
		
		//가운데 패널 안에 넣은 2개의 패널(오전, 오후)
		JPanel morning = new JPanel();
		JPanel afternoon = new JPanel();
		morning.setLayout(new BorderLayout());
		afternoon.setLayout(new BorderLayout());
		
		JLabel morn = new JLabel("오전");
		morn.setHorizontalAlignment(JLabel.CENTER);
		morn.setBackground(Color.white);
		
		JLabel aftn = new JLabel("오후");
		aftn.setBackground(Color.white);
		aftn.setHorizontalAlignment(JLabel.CENTER);
				
		morning.add(morn, BorderLayout.NORTH);
		afternoon.add(aftn, BorderLayout.NORTH);

		//오전
		JPanel morn_table = new JPanel();
		morn_table.setLayout(null);
		
		//오후
		JPanel aftn_table = new JPanel();
		aftn_table.setLayout(null);
		
		
		JPanel morn_time = new JPanel();
		JPanel morn_sch = new JPanel();
		
		JPanel aftn_time = new JPanel();
		JPanel aftn_sch = new JPanel();
		
		morn_time.setBounds(10, 5, 45, 300);
		morn_sch.setBounds(57, 5, 200, 300);
		morn_sch.setLayout(new GridLayout(13, 1, 5, 2));
		
		aftn_time.setBounds(10, 5, 45, 300);
		aftn_sch.setBounds(57, 5, 200, 300);
		aftn_sch.setLayout(new GridLayout(13, 1, 5, 2));
		
		AMtable = new JLabel[12];
		AMschedule = new JLabel[12];
	
		PMtable = new JLabel[12];
		PMschedule = new JLabel[12];
		
		//오전 시간 테이블
		for (int i=0; i<12; i++) {
			AMtable[i] = new JLabel(clock[i]);
			AMtable[i].setHorizontalAlignment(JLabel.CENTER);
			morn_time.add(AMtable[i]);	
		}
		//오전 일정
		for (int i=0; i<12; i++) {
			AMschedule[i] = new JLabel("");
			AMschedule[i].setOpaque(true);
			AMschedule[i].setBackground(Color.white);
			morn_sch.add(AMschedule[i]);
		}
		
		//오후 시간 테이블
		for (int i=0; i<12; i++) {
			PMtable[i] = new JLabel(clock[i+12]);
			PMtable[i].setHorizontalAlignment(JLabel.CENTER);
			aftn_time.add(PMtable[i]);
		}
		//오후 일정
		for (int i=0; i<12; i++) {
			PMschedule[i] = new JLabel("                                                        ");
			PMschedule[i].setOpaque(true);
			PMschedule[i].setBackground(Color.white);
			aftn_sch.add(PMschedule[i]);
		}
		morn_table.add(morn_time);
		morn_table.add(morn_sch);
		aftn_table.add(aftn_time);
		aftn_table.add(aftn_sch);

		morning.add(morn_table, BorderLayout.CENTER);
		afternoon.add(aftn_table, BorderLayout.CENTER);
	
		CenterPanel.add(morning);
		CenterPanel.add(afternoon);
		todaypane.add(CenterPanel, BorderLayout.CENTER); //가운데 패널을 프레임 안에 삽입
		
		//가장 아래쪽에 배치될 패널
		Daily_schedule todaybottom = new Daily_schedule();
		bottompane = new JPanel();
		bottompane = todaybottom.dailybottom();
		//todaybottom.memoedit();
		
		todaypane.add(bottompane, BorderLayout.SOUTH);
		todaypane.setVisible(true);
	}
	
	public JPanel dailytab() { //dailytab으로 추가할 패널을 리턴
		return todaypane;
	}
	
	
}
