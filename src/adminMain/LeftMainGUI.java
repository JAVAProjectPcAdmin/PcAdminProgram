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
	// 손님 정보 테인블
	private String[] infoTitle1 = { "이 름", "회원번호", "사용PC" };
	private String[] infoTitle2 = { "시작시간", "종료시간", "사용시간" };
	private String[] infoTitle3 = { "총 사용금액", "선불금액", "주문금액" };
	private String[][] infoData1 = new String[1][1];
	private String[][] infoData2 = new String[1][1];
	private String[][] infoData3 = new String[1][1];
	// 하루 사용자 테이블
	private String[] finishedTitle = { "사용자", "PC", "총 사용금액" };
	private String[][] finishedData = new String[20][20];

	// 폰트
	private Font f1, f2;

	// 찾기 버튼
	private JButton findMemberBtn = new JButton();
	private JButton findSeatBtn = new JButton();

	// 버튼이미지 사이즈 조절
	ImageIcon findButtonIcon = new ImageIcon("findbutton.jpg");
	Image findButton = findButtonIcon.getImage();
	Image newFindButtonImg = findButton.getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chFindButton = new ImageIcon(newFindButtonImg);

	// 로고이미지 사이즈 조절
	ImageIcon javaLogoIcon = new ImageIcon("JavaLogo.png");
	Image javaLogo = javaLogoIcon.getImage();
	Image newJavaLogoImg = javaLogo.getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chjavaLogo = new ImageIcon(newJavaLogoImg);

	////////////////////////////////////////////////////////////////////////////////////////

	JLabel inputCode_Label = new JLabel("자리 검색 : ");
	JTextField inputCode_Text = new JTextField(5);

	public LeftMainGUI() {
		JPanel countGuest_Panel = new JPanel();
		JPanel findGuest_Panel = new JPanel();
		JPanel infoGuest_Panel = new JPanel();
		JPanel fee_Panel = new JPanel();
		JPanel logo_Panel = new JPanel();

		///////////////////////////////////////////////////////////////////////////////////////
		// 로고
		JButton logoBtn = new JButton();
		logoBtn.setIcon(new ImageIcon(newJavaLogoImg));

		logoBtn.setPreferredSize(new Dimension(170, 70));
		logo_Panel.setPreferredSize(new Dimension(200, -10));
		logo_Panel.add(logoBtn);
		logo_Panel.setBackground(Color.WHITE);
		///////////////////////////////////////////////////////////////////////////////////////

		///////////////////////////////////////////////////////////////////////////////////////
		// 손님 수
		JLabel countGuest_Label1 = new JLabel();

		countGuest_Label1.setText("12 / 25");
		f1 = new Font("맑은 고딕", Font.BOLD, 50);

		countGuest_Label1.setFont(f1);

		countGuest_Panel.setPreferredSize(new Dimension(200, -10));
		countGuest_Panel.add(countGuest_Label1);
		countGuest_Panel.setBackground(Color.WHITE);

		/////////////////////////////////////////////////////////////////////////////////////
		// 손님 검색
<<<<<<< HEAD
		findSeatBtn.setIcon(new ImageIcon(newFindButtonImg));
=======
		JLabel inputCode_Label2 = new JLabel("이름 입력 : ");
		JTextField inputCode_Text2 = new JTextField(5);
		findBtn.setIcon(new ImageIcon(newFindButtonImg));
>>>>>>> 3bb626007ad4760f8992793cee5605cff04439e9

		findSeatBtn.setPreferredSize(new Dimension(50, 35));
		// findBtn.setBorderPainted(false);
		// findBtn.setContentAreaFilled(false);
		findSeatBtn.setFocusPainted(false);
		// findBtn.setOpaque(false);

		f2 = new Font("맑은 고딕", Font.BOLD, 14);
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
		// 손님 정보 테이블
		DefaultTableModel infoModel1 = new DefaultTableModel(infoData1, infoTitle1);
		DefaultTableModel infoModel2 = new DefaultTableModel(infoData2, infoTitle2);
		DefaultTableModel infoModel3 = new DefaultTableModel(infoData3, infoTitle3);

		JTable infoTable1 = new JTable(infoModel1);
		JTable infoTable2 = new JTable(infoModel2);
		JTable infoTable3 = new JTable(infoModel3);

		// 열과 행 선택의 동시실행 X
		infoTable1.setCellSelectionEnabled(false);
		infoTable2.setCellSelectionEnabled(false);
		infoTable3.setCellSelectionEnabled(false);

		// 열선택 X
		infoTable1.setColumnSelectionAllowed(false);
		infoTable2.setColumnSelectionAllowed(false);
		infoTable3.setColumnSelectionAllowed(false);

		// 셀 높이
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
		// 하루동안 사용자

		DefaultTableModel finishedModel = new DefaultTableModel(finishedData, finishedTitle);
		JTable finishedTable = new JTable(finishedModel);
		JScrollPane finishedSp = new JScrollPane(finishedTable);

		finishedSp.setPreferredSize(new Dimension(220, 280));

		finishedSp.setBackground(Color.WHITE);
		finishedTable.setBackground(Color.WHITE);
		fee_Panel.add(finishedSp);

		/////////////////////////////////////////////////////////////////////////////////////
		// 회원검색

		findMemberBtn.setText("회원 검색");
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
	// 회원자리 검색 리스너
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
	// 회원정보 검색 리스너
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
