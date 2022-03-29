package �����;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class Schedule { //���� �ð�ǥ
	static JPanel mainP, topP, timeP, scheP;
	static JTextArea[] text;
	
	public Schedule() {

		//���� ���� ���� �г�
		mainP = new JPanel();
		mainP.setLayout(new BorderLayout());
		
		//���� �г� (����'�ð�ǥ'�� ���� �ؽ�Ʈ �� �κ�)
		topP = new JPanel();
		topP.setLayout(new BorderLayout());
		
		JLabel name = new JLabel();
		name.setText("�ð�ǥ");
		name.setHorizontalAlignment(JLabel.CENTER);
		name.setFont(new Font("����", Font.BOLD, 25));
		
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
		
		//���� �߰��� �� �մ� PlusŬ���� ����
		Plus plusbt = new Plus();
		
		topP.add(name, BorderLayout.NORTH);
		topP.add(dayP, BorderLayout.SOUTH);
		topP.add(plusbt.plusbutton(), BorderLayout.WEST);
		
		//���� �ð����� �ؽ�Ʈ�� �� �г�
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
		
		
		//���� ������ ���� �κ�
		scheP = new JPanel();
		scheP.setLayout(new GridLayout(14,7));
		scheP.setBorder(new EmptyBorder(5,5,5,5));
		
		text = new JTextArea[98];
		for (int i=0; i<14*7; i++) {
			scheP.add(text[i] = new JTextArea(" "));
			text[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			text[i].setFont(new Font("����", Font.BOLD, 11));
		}
		
		//�����гο� ��� �г� ���̱�
		//��ư, ž�г�, Ÿ���г�, ���� �����κ�
		mainP.add(topP, BorderLayout.NORTH);
		mainP.add(timeP, BorderLayout.WEST);
		mainP.add(scheP);
	}

}
