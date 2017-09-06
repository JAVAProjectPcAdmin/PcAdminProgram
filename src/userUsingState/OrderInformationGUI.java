package userUsingState;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class OrderInformationGUI extends JFrame {

	public OrderInformationGUI() {
		super("상세주문정보");
		setSize(300, 200);

		JTable table = new JTable(new String[][] { { "포카칩", "1200원" }, { "", "" } }, new String[] { "제품명", "가격" });
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setLocation(650, 400);
		addWindowListener(new WindowAdapter() {    ////////윈도우창 x버튼 종료시 flag false값 입력하는 이벤트
			public void windowClosing(WindowEvent e) {
				UserUsingStateGUI.flag4 = false;
			}
		});
		setVisible(true);
	}

}