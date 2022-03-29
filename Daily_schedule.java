package 실험용;

import java.awt.*;
import javax.swing.*;

public class Daily_schedule extends Memo{
	JPanel bottompane;
	public static JPanel showpane;
	public static JLabel Show_Calendar_Label;
	public static JTextArea Show_Calendar;
	

	public Daily_schedule() {
		setToday();
	}
	
	public JPanel dailybottom() {
		bottompane = new JPanel();
		Memo memo = new Memo();
		JPanel memopanel = new JPanel();
		memopanel=memo.memo();
		
		showpane = new JPanel();
		Show_Calendar_Label = new JLabel("알  림", JLabel.CENTER);
		showpane.setLayout(new BorderLayout());
		showpane.setBorder(BorderFactory.createRaisedBevelBorder());

		Show_Calendar_Label.setFont(new Font("돋움", Font.BOLD, 18));

		showpane.add(Show_Calendar_Label, BorderLayout.NORTH); // 일정표시란라벨을 패널에 추가

		Show_Calendar = new JTextArea("");
		showpane.add(Show_Calendar, BorderLayout.CENTER);
		
		showpane.setPreferredSize(new Dimension(150, 180));
		showpane.setBackground(new Color(255, 255, 204));
		showpane.setVisible(true);

		bottompane.add(memopanel, BorderLayout.EAST);
		bottompane.add(showpane, BorderLayout.WEST);
		bottompane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		bottompane.setBackground(Color.WHITE);
		bottompane.setLayout(new GridLayout(1, 0, 20, 20));
		return bottompane;
	}
	
}
