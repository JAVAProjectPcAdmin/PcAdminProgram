package userUsingState;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class OrderInformationGUI extends JFrame {

	public OrderInformationGUI() {
		super("���ֹ�����");
		setSize(300, 200);

		JTable table = new JTable(new String[][] { { "��īĨ", "1200��" }, { "", "" } }, new String[] { "��ǰ��", "����" });
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setLocation(650, 400);
		addWindowListener(new WindowAdapter() {    ////////������â x��ư ����� flag false�� �Է��ϴ� �̺�Ʈ
			public void windowClosing(WindowEvent e) {
				UserUsingStateGUI.flag4 = false;
			}
		});
		setVisible(true);
	}

}