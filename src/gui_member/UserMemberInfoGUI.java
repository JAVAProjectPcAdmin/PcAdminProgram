package gui_member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import adminMain.LeftMainGUI;
import db.UserDao;
import db.UserVO;

public class UserMemberInfoGUI extends JFrame {

	private JLabel infoLbl, joinNumLbl, nameLbl, idLbl, pwLbl,
				   birthLbl, phLbl, mailLbl, addLbl, memoLbl; 
	private JTextField joinNumTx, nameTx, idTx, birthTx,
					   phTx, mailTx, addTx, memoTx;
	private JPasswordField pwTx;
	private JButton storeBtn, cancleBtn;
	private JPanel infoPnl, tablePnl;
	public JTable memberTbl;
	private UserDao dao;
	public DefaultTableModel model;
	public String header[] = {"회원번호", "이름", "아이디", "등록일자", "생년월일"};
	public String contents[][] = new String[100][0];
	
	public UserMemberInfoGUI() {
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
		cancleBtn = new JButton("닫 기");
		
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
		joinNumTx.setEditable(false);
		
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
		idTx.setEditable(false);
		
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
		birthTx.setEditable(false);
		
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
		UserInfoListener InfoListener = new UserInfoListener();
		add(storeBtn);
		storeBtn.setBounds(100, 480, 70, 30);
		storeBtn.addActionListener(InfoListener);
		add(cancleBtn);
		cancleBtn.setBounds(200, 480, 70, 30);
		cancleBtn.addActionListener(InfoListener);
		
		//////////////////////////////////////////////////// tablePnl
		
		model = new DefaultTableModel(contents, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column >= 0) {
					return false;
				} else {
					return true;
				}
			}
		};
		
		memberTbl = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(memberTbl);
		scrollpane.setPreferredSize(new Dimension(400, 500));
		tablePnl.add(scrollpane, BorderLayout.CENTER);
		
		memberTbl.getTableHeader().setReorderingAllowed(false);
		memberTbl.getTableHeader().setResizingAllowed(false);
		
		memberTbl.setRowHeight(23);
		memberTbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		memberTbl.getColumnModel().getColumn(1).setPreferredWidth(60);
		memberTbl.getColumnModel().getColumn(2).setPreferredWidth(70);
		
		memberTbl.addMouseListener(new UserTableMouseListener());
		
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
		setUndecorated(true);
		setLocation(220, 170);
		setResizable(false);
		setVisible(true);
	}
	
	//버튼 클릭
	class UserInfoListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String updatePasswd = "";
			Scanner sc = new Scanner(System.in);
			UserVO user = new UserVO();
			dao = new UserDao();
			JButton selected = (JButton)e.getSource();
			
			if(selected == storeBtn) {
				user.setName(nameTx.getText());
				
//				for(int i=0; i<pwTx.getPassword().length; i++) {
//					updatePasswd += pwTx.getPassword()[i];
//				}
				
				updatePasswd = new String(pwTx.getPassword(), 0, pwTx.getPassword().length);
				user.setPassword(updatePasswd);
				user.setPhone(phTx.getText());
				user.setEmailAddress(mailTx.getText());
				user.setAddress(addTx.getText());
				user.setMemo(memoTx.getText());
				dao.userUpdate(user, Integer.parseInt(joinNumTx.getText()));
				
			}else if(selected == cancleBtn) {
				LeftMainGUI.flag = true;
				dispose();
			}
		}
	}
	
	//테이블 클릭시 상세정보 
	class UserTableMouseListener extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e) {
			dao = new UserDao();
			UserVO userList;
			int selectedIndex, userNum;
			
			JTable jt = (JTable)e.getSource();
			selectedIndex = jt.getSelectedRow();
			userNum = (int) memberTbl.getValueAt(selectedIndex, 0); //빈칸이면 try catch 해서 에러 해야할듯
			userList = dao.userNumSelectList(userNum);
			
			joinNumTx.setText(Integer.toString(userList.getUserNumber()));
			nameTx.setText(userList.getName());
			idTx.setText(userList.getId());
			pwTx.setText(userList.getPassword());
			birthTx.setText(userList.getResidentNumber());
			phTx.setText(userList.getPhone());
			mailTx.setText(userList.getEmailAddress());
			addTx.setText(userList.getAddress());
			memoTx.setText(userList.getMemo());
		}
	}
}
