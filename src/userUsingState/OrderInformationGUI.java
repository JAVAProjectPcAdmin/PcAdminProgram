package userUsingState;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;

public class OrderInformationGUI extends JFrame {

	public OrderInformationGUI() {
		super("상세주문정보");
		setSize(300, 200);

		JTable table = new JTable(new String[][] { { "포카칩", "1200원" }, { "", "" } }, new String[] { "제품명", "가격" });
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		setLocation(650, 400);
		setVisible(true);
	}

}