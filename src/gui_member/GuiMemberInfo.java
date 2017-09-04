package gui_member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GuiMemberInfo extends JFrame {

	private JLabel infoLbl, joinNumLbl, nameLbl, idLbl, pwLbl,
				   birthLbl, phLbl, mailLbl, addLbl, memoLbl; 
	private JTextField joinNumTx, nameTx, idTx, birthTx,
					   phTx, mailTx, addTx, memoTx;
	private JPasswordField pwTx;
	private JButton storeBtn, searchBtn, resetBtn;
	private JPanel infoPnl, tablePnl;
	private JTable memberTbl;
	
	public GuiMemberInfo() {
		////////////////////////////////////////////////////// infoPnl
		infoLbl = new JLabel("회원정보");
		joinNumLbl = new JLabel("회원번호");
		nameLbl = new JLabel("이  름");
		idLbl = new JLabel("아 이 디");
		pwLbl = new JLabel("비밀번호");
		birthLbl = new JLabel("생년월일");
		phLbl = new JLabel("전화번호");
		mailLbl = new JLabel("E-Mail");
		addLbl = new JLabel("주 소");
		memoLbl = new JLabel("메  모");
		
		joinNumTx = new JTextField();
		nameTx = new JTextField();
		idTx = new JTextField();
		pwTx = new JPasswordField();
		birthTx = new JTextField();
		phTx = new JTextField();
		mailTx = new JTextField();
		addTx = new JTextField();
		memoTx = new JTextField();
		
		storeBtn = new JButton("저 장");
		searchBtn = new JButton("검 색");
		resetBtn = new JButton("초기화");
		
		infoPnl = new JPanel();
		tablePnl = new JPanel();
		
		infoPnl.setLayout(null);
		
		infoLbl.setFont(new Font("맑은고딕", Font.BOLD, 30));
		add(infoLbl);
		
		infoLbl.setBounds(125, 30, 150, 50);
		
		//회원번호
		infoPnl.add(joinNumLbl);
		joinNumLbl.setBounds(20, 110, 100, 15);
		infoPnl.add(joinNumTx);
		joinNumTx.setBounds(110, 105, 150, 25);
		
		//이름
		infoPnl.add(nameLbl);
		nameLbl.setBounds(20, 150, 100, 15);
		infoPnl.add(nameTx);
		nameTx.setBounds(110, 145, 150, 25);
		
		//아이디
		infoPnl.add(idLbl);
		idLbl.setBounds(20, 190, 100, 15);
		infoPnl.add(idTx);
		idTx.setBounds(110, 185, 150, 25);
		
		//비밀번호
		infoPnl.add(pwLbl);
		pwLbl.setBounds(20, 230, 100, 15);
		infoPnl.add(pwTx);
		pwTx.setBounds(110, 225, 150, 25);
		
		//생년월일
		infoPnl.add(birthLbl);
		birthLbl.setBounds(20, 270, 100, 15);
		infoPnl.add(birthTx);
		birthTx.setBounds(110, 265, 150, 25);
	
		//전화번호
		infoPnl.add(phLbl);
		phLbl.setBounds(20, 310, 100, 15);
		infoPnl.add(phTx);
		phTx.setBounds(110, 305, 150, 25);
		
		//이메일
		infoPnl.add(mailLbl);
		mailLbl.setBounds(20, 350, 100, 15);
		infoPnl.add(mailTx);
		mailTx.setBounds(110, 345, 150, 25);
		
		//주소
		infoPnl.add(addLbl);
		addLbl.setBounds(20, 390, 100, 15);
		infoPnl.add(addTx);
		addTx.setBounds(110, 385, 200, 25);
		
		//메모
		infoPnl.add(memoLbl);
		memoLbl.setBounds(20, 430, 100, 15);
		infoPnl.add(memoTx);
		memoTx.setBounds(110, 425, 150, 25);
		
		//버튼
		add(storeBtn);
		storeBtn.setBounds(70, 480, 70, 30);
		add(searchBtn);
		searchBtn.setBounds(150, 480, 70, 30);
		add(resetBtn);
		resetBtn.setBounds(230, 480, 80, 30);
		//////////////////////////////////////////////////// tablePnl
		
		String header[] = {"회원번호", "이름", "아이디", "등록일자", "생년월일"};
		
		// *나중에 DB에서 값 받아와야 함*
		String contents[][] = {{"1", "이유희", "hello", "2017-09-01", "930227"}};
		DefaultTableModel model = new DefaultTableModel(contents, header);
		memberTbl = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(memberTbl);
		scrollpane.setPreferredSize(new Dimension(400, 500));
		tablePnl.add(scrollpane, BorderLayout.CENTER);
		
		memberTbl.setRowHeight(23);
		memberTbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		memberTbl.getColumnModel().getColumn(1).setPreferredWidth(60);
		memberTbl.getColumnModel().getColumn(2).setPreferredWidth(70);
		
		//////////////////////////////////////////////////// frame
		setLayout(null);
		add(infoPnl);
		add(tablePnl);
		infoPnl.setBackground(Color.WHITE);
		tablePnl.setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		
		infoPnl.setBounds(0, 0, 370, 560);
		tablePnl.setBounds(370, 15, 400, 560);
		
		setTitle("회원정보");
		setSize(800,570);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		GuiMemberInfo g = new GuiMemberInfo();
	}
}
