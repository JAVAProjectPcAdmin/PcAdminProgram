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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import db.UserDao;
import db.UserVO;
import gui_member.UserMemberInfoGUI;

public class LeftMainGUI extends JPanel {
	// 손님 정보 테이블
	private String[] finishedTitle = { "사용자", "회원ID", "사용PC", "총 사용금액" };
	public String[][] finishedData = new String[20][];
	private String[] infoTitle1 = { "회원번호", "아이디", "이  름" };
	private String[] infoTitle2 = { "사용PC", "시작시간", "사용시간" };
	private String[] infoTitle3 = { "PC 금액", "음식 금액", "총 사용금액" };
	public String[][] infoData1 = new String[1][];
	public String[][] infoData2 = new String[1][];
	public String[][] infoData3 = new String[1][];

	public JLabel countGuest_Label1;
	private JLabel inputSeat_Label = new JLabel("자리 검색 : ");
	private JLabel inputMemberInfo_Label = new JLabel("회원 검색 : ");

	private JTextField inputSeat_Text = new JTextField(5);
	private JTextField inputMemberInfo_Text = new JTextField(5);

	private JButton findMemberBtn = new JButton();
	private JButton findSeatBtn = new JButton();

	private Font f1, f2;
	
	public static int countSeat = 0;

	static public boolean flag = true;

	private UserDao dao;
	
	DefaultTableModel infoModel1 = new DefaultTableModel(infoData1, infoTitle1) {
		public boolean isCellEditable(int row, int column) {
			if (column >= 0) {
				return false;
			} else {
				return true;
			}
		}
	};

	DefaultTableModel infoModel2 = new DefaultTableModel(infoData2, infoTitle2) {
		public boolean isCellEditable(int row, int column) {
			if (column >= 0) {
				return false;
			} else {
				return true;
			}
		}
	};

	DefaultTableModel infoModel3 = new DefaultTableModel(infoData3, infoTitle3) {
		public boolean isCellEditable(int row, int column) {
			if (column >= 0) {
				return false;
			} else {
				return true;
			}
		}
	};

	public DefaultTableModel finishedModel = new DefaultTableModel(finishedData, finishedTitle) {
		public boolean isCellEditable(int row, int column) {
			if (column >= 0) {
				return false;
			} else {
				return true;
			}
		}
	};

	public JTable infoTable1 = new JTable(infoModel1);
	public JTable infoTable2 = new JTable(infoModel2);
	public JTable infoTable3 = new JTable(infoModel3);
	public JTable finishedTable = new JTable(finishedModel);

	// 이미지 사이즈 조절
	ImageIcon findButtonIcon = new ImageIcon("images//findseat.jpg");
	Image findButton = findButtonIcon.getImage();
	Image newFindButtonImg = findButton.getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chFindButton = new ImageIcon(newFindButtonImg);

	ImageIcon javaLogoIcon = new ImageIcon("images//JavaLogo.png");
	Image javaLogo = javaLogoIcon.getImage();
	Image newJavaLogoImg = javaLogo.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chjavaLogo = new ImageIcon(newJavaLogoImg);

	ImageIcon findSeatIcon = new ImageIcon("images//findbutton.jpg");
	Image findSeat = findSeatIcon.getImage();
	Image newFindSeatImg = findSeat.getScaledInstance(50, 35, java.awt.Image.SCALE_SMOOTH);
	ImageIcon chFindSeat = new ImageIcon(newFindSeatImg);


	public JButton getFindSeatBtn() {
		return findSeatBtn;
	}

	public JTextField getInputSeat_Text() {
		return inputSeat_Text;
	}

	public LeftMainGUI() {
		JPanel countGuest_Panel = new JPanel();
		JPanel findGuest_Panel = new JPanel();
		JPanel infoGuest_Panel = new JPanel();
		JPanel fee_Panel = new JPanel();
		JPanel logo_Panel = new JPanel();

		JButton logoBtn = new JButton();

		logoBtn.setIcon(new ImageIcon(newJavaLogoImg));
		logoBtn.setPreferredSize(new Dimension(70, 70));
		logoBtn.setFocusable(false);
		logoBtn.setBorderPainted(false);
		logo_Panel.setPreferredSize(new Dimension(200, -10));
		logo_Panel.add(logoBtn);
		logo_Panel.setBackground(Color.WHITE);

		// 현재 손님 수
		countGuest_Label1 = new JLabel();

		countGuest_Label1.setText(countSeat + " / " + "25");
		f1 = new Font("맑은 고딕", Font.BOLD, 50);
		f2 = new Font("맑은 고딕", Font.BOLD, 14);

		countGuest_Label1.setFont(f1);

		countGuest_Panel.setPreferredSize(new Dimension(200, -10));
		countGuest_Panel.add(countGuest_Label1);
		countGuest_Panel.setBackground(Color.WHITE);

		// 손님 자리 검색
		inputSeat_Label.setFont(f2);
		findSeatBtn.setIcon(new ImageIcon(newFindButtonImg));
		findSeatBtn.setPreferredSize(new Dimension(50, 35));
		findSeatBtn.setFocusPainted(false);
		findSeatBtn.setFont(f2);
		findSeatBtn.setBackground(Color.WHITE);

		findGuest_Panel.add(inputSeat_Label);
		findGuest_Panel.add(inputSeat_Text);
		findGuest_Panel.add(findSeatBtn);
		findGuest_Panel.setPreferredSize(new Dimension(200, 0));
		findGuest_Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		findGuest_Panel.setBackground(Color.WHITE);

		// 회원 정보 검색
		inputMemberInfo_Label.setFont(f2);
		findMemberBtn.setIcon(new ImageIcon(newFindSeatImg));
		findMemberBtn.setPreferredSize(new Dimension(50, 35));
		findMemberBtn.setFocusPainted(false);
		findMemberBtn.setFont(f2);
		findMemberBtn.setBackground(Color.WHITE);
		findMemberBtn.setFocusPainted(false);
		findMemberBtn.addActionListener(new FindMemberActionListener());

		findGuest_Panel.add(inputMemberInfo_Label);
		findGuest_Panel.add(inputMemberInfo_Text);
		findGuest_Panel.add(findMemberBtn);
		findGuest_Panel.setPreferredSize(new Dimension(200, 0));
		findGuest_Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		findGuest_Panel.setBackground(Color.WHITE);

		// 손님 정보 테이블
		JScrollPane infoSp1 = new JScrollPane(infoTable1);
		JScrollPane infoSp2 = new JScrollPane(infoTable2);
		JScrollPane infoSp3 = new JScrollPane(infoTable3);

		// 테이블 열 이동 x, 크기조절 x
		infoTable1.getTableHeader().setReorderingAllowed(false);
		infoTable1.getTableHeader().setResizingAllowed(false);
		infoTable2.getTableHeader().setReorderingAllowed(false);
		infoTable2.getTableHeader().setResizingAllowed(false);
		infoTable3.getTableHeader().setReorderingAllowed(false);
		infoTable3.getTableHeader().setResizingAllowed(false);

		// 테이블 데이터 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm1 = infoTable1.getColumnModel();
		TableColumnModel tcm2 = infoTable2.getColumnModel();
		TableColumnModel tcm3 = infoTable3.getColumnModel();
		TableColumnModel tcm4 = finishedTable.getColumnModel();

		for (int i = 0; i < tcm1.getColumnCount(); i++) {
			tcm1.getColumn(i).setCellRenderer(dtcr);
			tcm2.getColumn(i).setCellRenderer(dtcr);
			tcm3.getColumn(i).setCellRenderer(dtcr);

		}
		for (int i = 0; i < tcm4.getColumnCount(); i++) {
			tcm4.getColumn(i).setCellRenderer(dtcr);
		}

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

		infoSp1.setPreferredSize(new Dimension(0, -10));
		infoSp2.setPreferredSize(new Dimension(0, -10));
		infoSp3.setPreferredSize(new Dimension(0, -10));

		infoGuest_Panel.setLayout(new BoxLayout(infoGuest_Panel, BoxLayout.Y_AXIS));
		infoGuest_Panel.add(infoSp1, BorderLayout.PAGE_START);
		infoGuest_Panel.add(infoSp2, BorderLayout.CENTER);
		infoGuest_Panel.add(infoSp3, BorderLayout.PAGE_END);
		infoGuest_Panel.setBackground(Color.WHITE);

		// 하루동안 사용자
		finishedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		finishedTable.getTableHeader().setReorderingAllowed(false);
		finishedTable.getTableHeader().setResizingAllowed(false);
		finishedTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		JScrollPane finishedSp = new JScrollPane(finishedTable);

		finishedSp.setPreferredSize(new Dimension(220, 280));

		finishedSp.setBackground(Color.WHITE);
		finishedTable.setBackground(Color.WHITE);
		fee_Panel.add(finishedSp);
		fee_Panel.setBackground(Color.WHITE);

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

	// 회원정보 검색 리스너
	private class FindMemberActionListener implements ActionListener {
		UserMemberInfoGUI umi;
		List<UserVO> uv;
		List<UserVO> empty;
		Object[] temp = new Object[5];
		Object[] tempEmpty = new Object[5];

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selected = (JButton) e.getSource();
			if (selected == findMemberBtn) {
				if (flag) {
					dao = new UserDao();
					umi = new UserMemberInfoGUI();
					uv = new ArrayList<>();
					uv = dao.UserNameSelectList(inputMemberInfo_Text.getText());
					empty = new ArrayList<>();
					empty = dao.UserInfoList();

					if ((inputMemberInfo_Text.getText()).equals("")) {
						for (int index = 0; index < empty.size(); index++) {
							tempEmpty[0] = empty.get(index).getUserNumber();
							tempEmpty[1] = empty.get(index).getName();
							tempEmpty[2] = empty.get(index).getId();
							tempEmpty[3] = empty.get(index).getRegisterDate();
							tempEmpty[4] = empty.get(index).getResidentNumber();

							umi.model.insertRow(0, tempEmpty);
							umi.memberTbl.updateUI();
						}
					} else {
						for (int i = 0; i < uv.size(); i++) {
							if (uv.get(i).getName().equals(inputMemberInfo_Text.getText())) {
								temp[0] = uv.get(i).getUserNumber();
								temp[1] = uv.get(i).getName();
								temp[2] = uv.get(i).getId();
								temp[3] = uv.get(i).getRegisterDate();
								temp[4] = uv.get(i).getResidentNumber();

								umi.model.insertRow(0, temp);
								umi.memberTbl.updateUI();
							}
						}
					}
				}
				flag = false;
			}
		}
	}
}
