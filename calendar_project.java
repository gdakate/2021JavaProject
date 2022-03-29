package �����;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
public class calendar_project extends Time {
	private JFrame mainFrame;

	public calendar_project() {
	
		// ���� ���� �г�
		JPanel NorthPanel = new JPanel();
		NorthPanel.setBackground(new Color(204, 204, 255));

		NorthPanel.setLayout(new BorderLayout());
		// ���� �� �г� �ȿ� ���� 2���� �г�
		JPanel NorthPanel_Right = new JPanel();
		JPanel NorthPanel_Center = new JPanel();

		JButton LButton = new JButton("<<");

		LButton.setFont(new Font("����", Font.BOLD, 13));
		LButton.setForeground(Color.WHITE);
		LButton.setBackground(Color.LIGHT_GRAY);
		NorthPanel_Center.add(LButton);

		JLabel YMLabel = new JLabel();
		YMLabel.setText(year + "��" + month + "��");
		YMLabel.setFont(new Font("����", Font.BOLD, 15));
		YMLabel.setHorizontalAlignment(JLabel.CENTER);
		NorthPanel_Center.add(YMLabel);

		JButton RButton = new JButton(">>");

		RButton.setFont(new Font("����", Font.BOLD, 13));
		RButton.setForeground(Color.WHITE);
		RButton.setBackground(Color.LIGHT_GRAY);
		NorthPanel_Center.add(RButton);
		NorthPanel_Center.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 70));
		

		// ��� �г�
		JPanel CenterPanel = new JPanel();
		JButton[] WeekDaysButton = new JButton[7];
		JButton[][] dateButton = new JButton[CAL_HEIGHT][CAL_WIDTH];
		// ���� ��ư ���ڻ� => ��~��
		for (int i = 0; i < CAL_WIDTH; i++) {
			WeekDaysButton[i] = new JButton(WEEK_DAY_NAME[i]);
			WeekDaysButton[i].setFont(new Font("����", Font.BOLD, 15));
			WeekDaysButton[i].setBackground(new Color(255, 255, 204));
			if (i == 0)
				WeekDaysButton[i].setForeground(Color.red);
			else if (i == 6)
				WeekDaysButton[i].setForeground(Color.BLUE);
			else
				WeekDaysButton[i].setForeground(Color.BLACK);
			CenterPanel.add(WeekDaysButton[i]);
		}
		// ��ư�ʱ�ȭ 1~31�� ��ư
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				dateButton[i][j] = new JButton();
				dateButton[i][j].setForeground(Color.BLACK);
				dateButton[i][j].setFont(new Font("����", Font.BOLD, 15));
				dateButton[i][j].setBackground(new Color(255, 255, 255));
				CenterPanel.add(dateButton[i][j]);
			}
		}
		setToday();
		// dayofweek �� ���� ����ó�� (�� 1�� ���� �ִ� ��ư�� ""���� �ؽ�Ʈ�� ����)
		for (int j = 0; j < 1; j++) {
			for (int i = 0; i < dayofweek - 1; i++) {
				dateButton[j][i].setText("");
			}
		}
		// �ش���� ���� ��¥��ư �˰���
		for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
			if (i == 0)
				k = calStartingPos;
			else
				k = 0;
			for (int j = k; j < CAL_WIDTH; j++) {
				if (num <= lastday)
					dateButton[i][j].setText("" + num++);

			}
		}
		// �гο� ��ư�߰� (��12���� �´� ���ϴ�� ��ư �ؽ�Ʈ ����)
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {

				CenterPanel.add(dateButton[i][j]);

			}
		}
		CenterPanel.setLayout(new GridLayout(0, 7, 2, 2));

		mainFrame.add(CenterPanel, BorderLayout.CENTER); // ����г��� �����Ӿȿ� ����


		// ��ư Ŭ�� �̺�Ʈó�� ��ư Ŭ���� ����ǥ�� + �������� ���̾�α� ����

		RButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == RButton)
					today.set(Calendar.MONTH, Calendar_Array[++now]);
				setToday();
				YMLabel.setText(year + "��" + month + "��");
				System.out.println(month);
				// dayofweek �� ���� ����ó�� (�� 1�� ���� �ִ� ��ư�� ""���� �ؽ�Ʈ�� ����)
				for (int j = 0; j < 1; j++) {
					for (int i = 0; i < dayofweek - 1; i++) {
						dateButton[j][i].setText("");
					}
				}
				// �ش���� ���� ��¥��ư �˰���
				for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
					if (i == 0)
						k = calStartingPos;
					else
						k = 0;
					for (int j = k; j < CAL_WIDTH; j++) {
						if (num <= lastday)
							dateButton[i][j].setText("" + num++);

					}
				}

			}
		});
		LButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == LButton) {

					today.set(Calendar.MONTH, Calendar_Array[--now]);
					setToday();
					YMLabel.setText(year + "��" + month + "��");
					System.out.println(month);
				}

				// dayofweek �� ���� ����ó�� (�� 1�� ���� �ִ� ��ư�� ""���� �ؽ�Ʈ�� ����)
				for (int j = 0; j < 1; j++) {
					for (int i = 0; i < dayofweek - 1; i++) {
						dateButton[j][i].setText("");
					}
				}
				// �ش���� ���� ��¥��ư �˰���
				for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
					if (i == 0)
						k = calStartingPos;
					else
						k = 0;
					for (int j = k; j < CAL_WIDTH; j++) {
						if (num <= lastday)
							dateButton[i][j].setText("" + num++);
						else
							dateButton[i][j].setText("");
					}
				}

			}
			
		});

	}
	public static void main(String[] args) {
		new calendar_project();
	}

}