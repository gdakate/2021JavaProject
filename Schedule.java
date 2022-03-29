package 실험용;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Schedule { //고정 시간표
	static JPanel mainP, topP, timeP, scheP;
	static JTextArea[] text;
	
	public Schedule() {

		//모든걸 붙일 메일 패널
		mainP = new JPanel();
		mainP.setLayout(new BorderLayout());
		
		//위쪽 패널 (제목'시간표'와 요일 텍스트 들어갈 부분)
		topP = new JPanel();
		topP.setLayout(new BorderLayout());
		
		JLabel name = new JLabel();
		name.setText("시간표");
		name.setHorizontalAlignment(JLabel.CENTER);
		name.setFont(new Font("돋움", Font.BOLD, 25));
		
		JPanel dayP = new JPanel();
		dayP.setLayout(new GridLayout(1,8));
		dayP.add(new JLabel(" "));
		dayP.add(new JLabel("Mon"));
		dayP.add(new JLabel("Tue"));
		dayP.add(new JLabel("Wed"));
		dayP.add(new JLabel("Thu"));
		dayP.add(new JLabel("Fri"));
		dayP.add(new JLabel("Sat"));
		dayP.add(new JLabel("Sun"));
		
		//일정 추가할 수 잇는 Plus클래스 생성
		Plus plusbt = new Plus();
		
		topP.add(name, BorderLayout.NORTH);
		topP.add(dayP, BorderLayout.SOUTH);
		topP.add(plusbt.plusbutton(), BorderLayout.WEST);
		
		//왼쪽 시간구분 텍스트가 들어갈 패널
		timeP = new JPanel();
		timeP.setLayout(new GridLayout(14,1));
		timeP.setBorder(new EmptyBorder(0,10,0,0));
		
		timeP.add(new JLabel("  9 "));
		timeP.add(new JLabel(" 10 "));
		timeP.add(new JLabel(" 11 "));
		timeP.add(new JLabel(" 12 "));
		timeP.add(new JLabel(" 13 "));
		timeP.add(new JLabel(" 14 "));
		timeP.add(new JLabel(" 15 "));
		timeP.add(new JLabel(" 16 "));
		timeP.add(new JLabel(" 17 "));
		timeP.add(new JLabel(" 18 "));
		timeP.add(new JLabel(" 19 "));
		timeP.add(new JLabel(" 20 "));
		timeP.add(new JLabel(" 21 "));
		timeP.add(new JLabel(" 22 "));
		
		
		//실제 일정이 들어가는 부분
		scheP = new JPanel();
		scheP.setLayout(new GridLayout(14,7));
		scheP.setBorder(new EmptyBorder(5,5,5,5));
		
		text = new JTextArea[98];
		for (int i=0; i<14*7; i++) {
			scheP.add(text[i] = new JTextArea(" "));
			text[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			text[i].setFont(new Font("돋움", Font.BOLD, 11));
		}
		
		//메일패널에 모든 패널 붙이기
		//버튼, 탑패널, 타임패널, 실제 일정부분
		mainP.add(topP, BorderLayout.NORTH);
		mainP.add(timeP, BorderLayout.WEST);
		mainP.add(scheP);
	}

}
