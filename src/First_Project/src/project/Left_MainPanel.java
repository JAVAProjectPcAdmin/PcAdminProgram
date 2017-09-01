package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Left_MainPanel extends JPanel {
	// �մ� ���� ���κ�
	private String[] infoTitle1 = { "�̸�", "ȸ����ȣ", "���PC" };
	private String[] infoTitle2 = { "���۽ð�", "����ð�", "���ð�" };
	private String[] infoTitle3 = { "�� ���ݾ�", "���ұݾ�", "�ֹ��ݾ�" };
	private String[][] infoData1 = new String[1][1];
	private String[][] infoData2 = new String[1][1];
	private String[][] infoData3 = new String[1][1];
	// �Ϸ� ����� ���̺�
	private String[] finishedTitle = { "�����", "PC", "�� ���ݾ�" };
	private String[][] finishedData = new String[20][20];

	// ��Ʈ
	private Font f1;
	

	public Left_MainPanel() {
		JPanel countGuest_Panel = new JPanel();
		JPanel findGuest_Panel = new JPanel();
		JPanel infoGuest_Panel = new JPanel();
		JPanel fee_Panel = new JPanel();
		// JPanel finishedGuest_Panel = new JPanel(); // �ʿ����

		///////////////////////////////////////////////////////////////////////////////////////
		// �մ� ��
		f1 = new Font("����", Font.BOLD, 50);
		JLabel countGuest_Label1 = new JLabel();
	
		countGuest_Label1.setText("12 / 25");
		
		
//		countGuest_Label1.setForeground(Color.WHITE);
//		countGuest_Panel.setBackground(Color.BLACK);
		
		

		
		
		countGuest_Label1.setFont(f1);
		
		countGuest_Panel.setPreferredSize(new Dimension(200, -10));
		countGuest_Panel.add(countGuest_Label1);
		

		/////////////////////////////////////////////////////////////////////////////////////
		// �մ� �˻�
		JLabel inputCode_Label = new JLabel("�̸� �Է� : ");
		JTextField inputCode_Text = new JTextField(5);
		JButton findBtn = new JButton("�˻�");
		
		
		
//		inputCode_Label.setForeground(Color.WHITE);
		findBtn.setBackground(Color.RED);
		findGuest_Panel.add(inputCode_Label);
		findGuest_Panel.add(inputCode_Text);
		findGuest_Panel.add(findBtn);
		
		findGuest_Panel.setPreferredSize(new Dimension(200, -30));
		
//		findGuest_Panel.setBackground(Color.BLACK);
		findGuest_Panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		/////////////////////////////////////////////////////////////////////////////////////
		// �մ� ���� ���̺�
		DefaultTableModel infoModel1 = new DefaultTableModel(infoData1, infoTitle1);
		DefaultTableModel infoModel2 = new DefaultTableModel(infoData2, infoTitle2);
		DefaultTableModel infoModel3 = new DefaultTableModel(infoData3, infoTitle3);

		JTable infoTable1 = new JTable(infoModel1);
		JTable infoTable2 = new JTable(infoModel2);
		JTable infoTable3 = new JTable(infoModel3);

		// ���� �� ������ ���ý��� X
		infoTable1.setCellSelectionEnabled(false);
		infoTable2.setCellSelectionEnabled(false);
		infoTable3.setCellSelectionEnabled(false);

		// ������ X
		infoTable1.setColumnSelectionAllowed(false);
		infoTable2.setColumnSelectionAllowed(false);
		infoTable3.setColumnSelectionAllowed(false);

		//�� ����
		infoTable1.setRowHeight(50);
		infoTable2.setRowHeight(50);
		infoTable3.setRowHeight(50);

		JScrollPane infoSp1 = new JScrollPane(infoTable1);
		JScrollPane infoSp2 = new JScrollPane(infoTable2);
		JScrollPane infoSp3 = new JScrollPane(infoTable3);

		infoSp1.setPreferredSize(new Dimension(0, -30));
		infoSp2.setPreferredSize(new Dimension(0, -30));
		infoSp3.setPreferredSize(new Dimension(0, 0));

		infoGuest_Panel.setLayout(new BoxLayout(infoGuest_Panel, BoxLayout.Y_AXIS));
		infoGuest_Panel.add(infoSp1, BorderLayout.PAGE_START);
		infoGuest_Panel.add(infoSp2, BorderLayout.CENTER);
		infoGuest_Panel.add(infoSp3, BorderLayout.PAGE_END);

		/////////////////////////////////////////////////////////////////////////////////////
		// �Ϸ絿�� �����

		DefaultTableModel finishedModel = new DefaultTableModel(finishedData, finishedTitle);
		JTable finishedTable = new JTable(finishedModel);
		JScrollPane finishedSp = new JScrollPane(finishedTable);

		finishedSp.setPreferredSize(new Dimension(200, 200));

		infoGuest_Panel.add(finishedSp);

		/////////////////////////////////////////////////////////////////////////////////////
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(countGuest_Panel);
		add(findGuest_Panel);
		add(infoGuest_Panel);
		add(fee_Panel);
		
		// add(finishedGuest_Panel); // �ʿ����
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

}
