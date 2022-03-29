package �����;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Memo extends Time implements ActionListener{
	TextArea textArea;
	JButton clearButton, addButton, newButton;

	public Memo() {
	}
	public JPanel memo() {
		JPanel memo = new JPanel();
		JLabel memotitle = new JLabel();
		memo.setBorder(BorderFactory.createRaisedBevelBorder());
		memo.setLayout(new BorderLayout());
		memotitle.setHorizontalAlignment(JLabel.CENTER);
		memotitle.setText("�޸�");
		textArea = new TextArea();
		textArea.setEditable(false);
		
		memo.add(memotitle, BorderLayout.NORTH);
		memo.add(textArea, BorderLayout.CENTER);
		
		//�Ʒ� ��ư �г� �ۼ�
		JPanel memo_bottom = new JPanel();
		//���� ����
		clearButton = new JButton("����");
		clearButton.addActionListener(this);
		clearButton.setBackground(Color.white);
		
		newButton = new JButton("�� �޸�");
		newButton.addActionListener(this);
		newButton.setBackground(Color.white);
		
		addButton = new JButton("�߰�");
		addButton.addActionListener(this);
		addButton.setBackground(Color.white);

		memo_bottom.setLayout(new GridLayout(1,3));
		memo_bottom.add(clearButton);
		memo_bottom.add(newButton);
		memo_bottom.add(addButton);
		memo_bottom.setBackground(Color.white);
		
		memo.add(memo_bottom, BorderLayout.SOUTH);
		memo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		memo.setBackground(Color.white);		
		memo.setVisible(true);
		
		return memo;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		textArea.setEditable(true);
		if(e.getSource()==clearButton) {
			textArea.setText(null);
			textArea.setEditable(false);
		}else if(e.getSource()==newButton) {
			String edittext = JOptionPane.showInputDialog("���ο� �޸� �Է����ּ���");
			textArea.setText(edittext);
			textArea.setEditable(false);
		}else if(e.getSource()==addButton) {
			String edittext = JOptionPane.showInputDialog("�߰��� �޸� �Է����ּ���");
			textArea.append("\n");
			textArea.append(edittext);
			textArea.setEditable(false);
		}
	}
}
