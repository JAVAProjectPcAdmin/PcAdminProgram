package adminMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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

	// 버튼이미지 사이즈 조절
	ImageIcon findButtonIcon = new ImageIcon("findbutton.jpg");
	Image findButton = findButtonIcon.getImage();
	Image newFindButtonImg = findButton.getScaledInstance(50, 30, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chFindButton = new ImageIcon(newFindButtonImg);

	// 로고이미지 사이즈 조절
	ImageIcon javaLogoIcon = new ImageIcon("JavaLogo.png");
	Image javaLogo = javaLogoIcon.getImage();
	Image newJavaLogoImg = javaLogo.getScaledInstance(170, 70, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chjavaLogo = new ImageIcon(newJavaLogoImg);

	////////////////////////////////////////////////////////////////////////////////////////

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
		JLabel inputCode_Label = new JLabel("이름 입력 : ");
		JTextField inputCode_Text = new JTextField(5);
		JButton findBtn = new JButton();
		findBtn.setIcon(new ImageIcon(newFindButtonImg));

		findBtn.setPreferredSize(new Dimension(50, 30));
		// findBtn.setBorderPainted(false);
		// findBtn.setContentAreaFilled(false);
		findBtn.setFocusPainted(false);
		// findBtn.setOpaque(false);

		f2 = new Font("맑은 고딕", Font.BOLD, 14);
		inputCode_Label.setFont(f2);
		findBtn.setFont(f2);

		findBtn.setBackground(Color.WHITE);
		findGuest_Panel.add(inputCode_Label);
		findGuest_Panel.add(inputCode_Text);
		findGuest_Panel.add(findBtn);

		findGuest_Panel.setPreferredSize(new Dimension(200, -30));

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

		finishedSp.setPreferredSize(new Dimension(200, 200));

		finishedSp.setBackground(Color.WHITE);
		finishedTable.setBackground(Color.WHITE);
		infoGuest_Panel.setBackground(Color.WHITE);
		infoGuest_Panel.add(finishedSp);

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

}
