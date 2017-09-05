package adminMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import gui_member.UserMemberInfoGUI;

public class LeftMainGUI extends JPanel {
	// �մ� ���� ���κ�
	private String[] infoTitle1 = { "�� ��", "ȸ����ȣ", "���PC" };
	private String[] infoTitle2 = { "���۽ð�", "����ð�", "���ð�" };
	private String[] infoTitle3 = { "�� ���ݾ�", "���ұݾ�", "�ֹ��ݾ�" };
	private String[][] infoData1 = new String[1][1];
	private String[][] infoData2 = new String[1][1];
	private String[][] infoData3 = new String[1][1];
	// �Ϸ� ����� ���̺�
	private String[] finishedTitle = { "�����", "PC", "�� ���ݾ�" };
	private String[][] finishedData = new String[20][20];

	// ��Ʈ
	private Font f1, f2;

	// ã�� ��ư
	private JButton findMemberBtn = new JButton();
	private JButton findSeatBtn = new JButton();

	// ��ư�̹��� ������ ����
	ImageIcon findButtonIcon = new ImageIcon("findbutton.jpg");
	Image findButton = findButtonIcon.getImage();
	Image newFindButtonImg = findButton.getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chFindButton = new ImageIcon(newFindButtonImg);

	// �ΰ��̹��� ������ ����
	ImageIcon javaLogoIcon = new ImageIcon("JavaLogo.png");
	Image javaLogo = javaLogoIcon.getImage();
	Image newJavaLogoImg = javaLogo.getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chjavaLogo = new ImageIcon(newJavaLogoImg);

	////////////////////////////////////////////////////////////////////////////////////////

	JLabel inputCode_Label = new JLabel("�ڸ� �˻� : ");
	JTextField inputCode_Text = new JTextField(5);

	public LeftMainGUI() {
		JPanel countGuest_Panel = new JPanel();
		JPanel findGuest_Panel = new JPanel();
		JPanel infoGuest_Panel = new JPanel();
		JPanel fee_Panel = new JPanel();
		JPanel logo_Panel = new JPanel();

		///////////////////////////////////////////////////////////////////////////////////////
		// �ΰ�
		JButton logoBtn = new JButton();
		logoBtn.setIcon(new ImageIcon(newJavaLogoImg));

		logoBtn.setPreferredSize(new Dimension(170, 70));
		logo_Panel.setPreferredSize(new Dimension(200, -10));
		logo_Panel.add(logoBtn);
		logo_Panel.setBackground(Color.WHITE);
		///////////////////////////////////////////////////////////////////////////////////////

		///////////////////////////////////////////////////////////////////////////////////////
		// �մ� ��
		JLabel countGuest_Label1 = new JLabel();

		countGuest_Label1.setText("12 / 25");
		f1 = new Font("���� ���", Font.BOLD, 50);

		countGuest_Label1.setFont(f1);

		countGuest_Panel.setPreferredSize(new Dimension(200, -10));
		countGuest_Panel.add(countGuest_Label1);
		countGuest_Panel.setBackground(Color.WHITE);

		/////////////////////////////////////////////////////////////////////////////////////
		// �մ� �˻�
<<<<<<< HEAD
		findSeatBtn.setIcon(new ImageIcon(newFindButtonImg));
=======
		JLabel inputCode_Label2 = new JLabel("�̸� �Է� : ");
		JTextField inputCode_Text2 = new JTextField(5);
		findBtn.setIcon(new ImageIcon(newFindButtonImg));
>>>>>>> 3bb626007ad4760f8992793cee5605cff04439e9

		findSeatBtn.setPreferredSize(new Dimension(50, 35));
		// findBtn.setBorderPainted(false);
		// findBtn.setContentAreaFilled(false);
		findSeatBtn.setFocusPainted(false);
		// findBtn.setOpaque(false);

		f2 = new Font("���� ���", Font.BOLD, 14);
<<<<<<< HEAD
		inputCode_Label.setFont(f2);
		findSeatBtn.setFont(f2);
		findSeatBtn.setBackground(Color.WHITE);

		findSeatBtn.addActionListener(new FindSeatActionListener());
		findGuest_Panel.add(inputCode_Label);
		findGuest_Panel.add(inputCode_Text);
		findGuest_Panel.add(findSeatBtn);
		findSeatBtn.setFont(f2);

		findSeatBtn.setBackground(Color.WHITE);
		findGuest_Panel.add(findSeatBtn);
=======
		inputCode_Label2.setFont(f2);
		findBtn.setFont(f2);

		findBtn.setBackground(Color.WHITE);
		findGuest_Panel.add(inputCode_Label2);
		findGuest_Panel.add(inputCode_Text2);
		findGuest_Panel.add(findBtn);
>>>>>>> 3bb626007ad4760f8992793cee5605cff04439e9

		findGuest_Panel.setPreferredSize(new Dimension(200, -50));

		findGuest_Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		findGuest_Panel.setBackground(Color.WHITE);
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

		// �� ����
		infoTable1.setRowHeight(50);
		infoTable2.setRowHeight(50);
		infoTable3.setRowHeight(50);

		JScrollPane infoSp1 = new JScrollPane(infoTable1);
		JScrollPane infoSp2 = new JScrollPane(infoTable2);
		JScrollPane infoSp3 = new JScrollPane(infoTable3);

		infoSp1.setPreferredSize(new Dimension(0, -10));
		infoSp2.setPreferredSize(new Dimension(0, -10));
		infoSp3.setPreferredSize(new Dimension(0, -10));

		infoGuest_Panel.setLayout(new BoxLayout(infoGuest_Panel, BoxLayout.Y_AXIS));
		infoGuest_Panel.add(infoSp1, BorderLayout.PAGE_START);
		infoGuest_Panel.add(infoSp2, BorderLayout.CENTER);
		infoGuest_Panel.add(infoSp3, BorderLayout.PAGE_END);
		infoGuest_Panel.setBackground(Color.WHITE);

		/////////////////////////////////////////////////////////////////////////////////////
		// �Ϸ絿�� �����

		DefaultTableModel finishedModel = new DefaultTableModel(finishedData, finishedTitle);
		JTable finishedTable = new JTable(finishedModel);
		JScrollPane finishedSp = new JScrollPane(finishedTable);

		finishedSp.setPreferredSize(new Dimension(220, 280));

		finishedSp.setBackground(Color.WHITE);
		finishedTable.setBackground(Color.WHITE);
		fee_Panel.add(finishedSp);

		/////////////////////////////////////////////////////////////////////////////////////
		// ȸ���˻�

		findMemberBtn.setText("ȸ�� �˻�");
		findMemberBtn.setFont(f2);
		findMemberBtn.setBackground(Color.WHITE);
		findMemberBtn.setFocusPainted(false);
		findMemberBtn.addActionListener(new FindMemberActionListener());

		fee_Panel.add(findMemberBtn, BorderLayout.CENTER);
		fee_Panel.setBackground(Color.WHITE);

		/////////////////////////////////////////////////////////////////////////////////////

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(logo_Panel);
		add(countGuest_Panel);
		add(findGuest_Panel);
		add(infoGuest_Panel);
		add(fee_Panel);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ȸ���ڸ� �˻� ������
	private class FindSeatActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selected = (JButton) e.getSource();
			if (selected == findSeatBtn) {
				RightMainGUI RM_GUI = new RightMainGUI();
				// if(inputCode_Text.getText()==RM_GUI.get)
				{

				}
			}

		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ȸ������ �˻� ������
	private class FindMemberActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selected = (JButton) e.getSource();
			if (selected == findMemberBtn) {
				UserMemberInfoGUI UMI = new UserMemberInfoGUI();
			}

		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//

	//

}
