package 실험용;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ShowSchedule {
	static JPanel showpane;
	static JLabel Show_Calendar_Label;
	static JTextArea Show_Calendar;

	public ShowSchedule() {
		showpane = new JPanel();
		Show_Calendar_Label = new JLabel("알  림", JLabel.CENTER);
		showpane.setLayout(new BorderLayout());
		showpane.setBorder(BorderFactory.createRaisedBevelBorder());

		Show_Calendar_Label.setFont(new Font("돋움", Font.BOLD, 18));

		showpane.add(Show_Calendar_Label, BorderLayout.NORTH); // 일정표시란라벨을 패널에 추가

		Show_Calendar = new JTextArea("");
		showpane.add(Show_Calendar, BorderLayout.CENTER);
	}
	
	public JPanel showSchedule() {

		showpane.setPreferredSize(new Dimension(150, 180));
		showpane.setBackground(new Color(255, 255, 133));
		showpane.setVisible(true);
		return showpane;
	}

}
