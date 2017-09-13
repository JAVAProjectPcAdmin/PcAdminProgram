package adminMain;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import AdminServer.AdminServer;
import AdminServer.User;
import db.UserDao;
import flagment.Flagment;
import orderFood.AdminOrderGUI;

public class AdminMainGUI extends JFrame {
	private LeftMainGUI lmp = new LeftMainGUI(); //
	private RightMainGUI[] rightUserPanel = new RightMainGUI[25]; //
	private JPanel rightPanel = new JPanel();
	public static UserThread isUserThread;
	TimerThread timerThread;
	OrderThread orderThread;
	int i;

	UserDao userDao = new UserDao();

	public AdminMainGUI() {
		for (i = 0; i < 25; i++) {
			rightUserPanel[i] = new RightMainGUI();
			rightUserPanel[i].setSize(210, 170);
			rightUserPanel[i].getUsePCNumberL().setText(Integer.toString(i+1));
			rightUserPanel[i].addMouseListener(new ClickPanelListener());
			rightPanel.add(rightUserPanel[i]);
		}
		isUserThread = new UserThread();
		isUserThread.start();

		lmp.getFindSeatBtn().addActionListener(new FindSeatActionListener());

		lmp.setBounds(0, 80, 220, 850);
		rightPanel.setLayout(new GridLayout(5, 5));
		rightPanel.setBounds(230, 50, 1030, 800);

		add(rightPanel);
		add(lmp);
		setResizable(false);

		setLayout(null);
		setSize(1280, 924);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image Iconimg = toolkit.getImage("images\\networking.png");
		setIconImage(Iconimg);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("관리자 화면");
		getContentPane().setBackground(Color.WHITE);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 회원자리 검색 리스너
	private class FindSeatActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color c = new Color(212, 212, 212);
			System.out.println((lmp.getInputSeat_Text()).getText());
			JButton selected = (JButton) e.getSource();
			if (selected == lmp.getFindSeatBtn()) {
				boolean findUser = false;
				for (int i = 0; i < rightUserPanel.length; i++) {
					rightUserPanel[i].setBorder(new TitledBorder(new LineBorder(Color.GRAY)));
					if ((rightUserPanel[i].getUserNameL()).getText().equals((lmp.getInputSeat_Text()).getText()) 
							&& !rightUserPanel[i].getUserNameL().getText().equals("")) {
						rightUserPanel[i].setBorder(new TitledBorder(new LineBorder(Color.GRAY,2)));
						rightUserPanel[i].setBackground(c);
						findUser = true;
					}
				}
				if (!findUser) {
					JOptionPane.showMessageDialog(null, "찾으시는 회원이 없습니다.", "자리 검색 결과", JOptionPane.WARNING_MESSAGE);
					for (int i = 0; i < rightUserPanel.length; i++) {
						rightUserPanel[i].setBorder(new TitledBorder(new LineBorder(Color.GRAY)));
					}
				}
			}
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////


	class ClickPanelListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			for (int i = 0; i < rightUserPanel.length; i++) {
				if (rightUserPanel[i] == e.getSource()) {
					if (rightUserPanel[i].getUserNumberL().getText().equals("")) {
						for (int j = 0; j < 3; j++) {
							lmp.infoModel1.setValueAt("", 0, j);
							lmp.infoModel2.setValueAt("", 0, j);
							lmp.infoModel3.setValueAt("", 0, j);
						}
						lmp.infoTable1.updateUI();
						lmp.infoTable2.updateUI();
						lmp.infoTable3.updateUI();
					} else {
						String totalPriceVal1 = rightUserPanel[i].getTotalPriceL().getText();
						String totalPriceVal2 = totalPriceVal1.replace("원", "");
						String amoutVal1 = rightUserPanel[i].getAddAmountL().getText();
						String amoutVal2 = amoutVal1.replace("원", "");

						lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNumberL().getText(), 0, 0); // 회원번호
						lmp.infoModel1.setValueAt(rightUserPanel[i].getUserIDL().getText(), 0, 1); // 아이디
						lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNameL().getText(), 0, 2); // 이름
						lmp.infoTable1.updateUI();

						lmp.infoModel2.setValueAt(rightUserPanel[i].getUser().getSeatNumber() + 1, 0, 0); // 사용 PC
						lmp.infoModel2.setValueAt(rightUserPanel[i].getUser().getStartTime().substring(7), 0, 1); // 시작시간
						lmp.infoModel2.setValueAt(rightUserPanel[i].getUseTimeL().getText(), 0, 2); // 사용시간
						lmp.infoTable2.updateUI();

						lmp.infoModel3.setValueAt(
								(Integer.parseInt(totalPriceVal2) - Integer.parseInt(amoutVal2)) + "원", 0, 0); // PC사용금액
						lmp.infoModel3.setValueAt(amoutVal1, 0, 1); // 음식주문금액
						lmp.infoModel3.setValueAt(totalPriceVal1, 0, 2); // 총금액
						lmp.infoTable3.updateUI();
					}
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////

	class UserThread extends Thread {	//좌석에 패널을 관리함
										//유저가 로그인을 하거나 로그아웃을 할떄 알림을 받아 실행
		User user;

		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < 25; i++) {
					if (Flagment.UserLoginState[i]) {	//유저가 로그인했다고 서버에서 알림 
						user = AdminServer.userlist.get(AdminServer.userlist.size() - 1);// userlist에 가장 최근데 들어온 User 정보
						rightUserPanel[i].setUserPanel(user); // 패널에 로그인한 user로 셋팅
						rightUserPanel[i].updateUI();
						LeftMainGUI.countSeat++;
						lmp.countGuest_Label1.setText(LeftMainGUI.countSeat + " / " + "25");
						lmp.updateUI();// 인원수 증가
						TimerThread timerThread = new TimerThread(user, i);
						timerThread.start();	//실시간으로 화면에 초가 올라가는것을 보여주기 위한 쓰레드
						OrderThread orderThread = new OrderThread(user, i);
						orderThread.start();	//유저 컴퓨터에서 주문했을떄 알림 받기 위한 쓰레드 

						Flagment.UserLoginState[i] = false;	//위 자리에서 위 작업을 한번만 실행시키기 위해 false 처리 
					}
					if (Flagment.UserLogout[i]) {	//User가 로그아웃했다고 서버에서 알림

						//rightUserPanel[i].getUsePCNumberL().setText(Integer.toString(i+1));

						rightUserPanel[i].setLogoutPanel();
						rightUserPanel[i].updateUI();
						
						LeftMainGUI.countSeat--;
						lmp.countGuest_Label1.setText(LeftMainGUI.countSeat + " / " + "25");
						lmp.updateUI();// 인원수 감소
						Object[] temp = new Object[4];
						for (int j = 0; j < AdminServer.userlist.size(); j++) {
							if (i == AdminServer.userlist.get(j).getSeatNumber()) {
								temp[0] = AdminServer.userlist.get(j).getName();
								System.out.println(temp[0]);
								temp[1] = AdminServer.userlist.get(j).getUserID();
								System.out.println(temp[1]);
								temp[2] = AdminServer.userlist.get(j).getSeatNumber() + 1;
								System.out.println(temp[2]);
								temp[3] = AdminServer.userlist.get(j).getTotalPrice();
								System.out.println(temp[3]);

								lmp.finishedModel.insertRow(0, temp);
								lmp.finishedTable.updateUI();

								AdminServer.userlist.remove(j);
							}

							Flagment.UserLogout[i] = false;
						}
					}
				}
			}
		}
	}

	class TimerThread extends Thread {
		User user = null;
		int i;

		public TimerThread(User user, int i) {
			this.user = user;
			this.i = i;
		}

		@Override
		public void run() {
			SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm:ss");
			boolean timeflag = true; // 처음 00은 가격 증가X
			while (true) {
				long time = System.currentTimeMillis() - 1000 * ((60 * 60 * 9));
				long checkTime = (time - user.getStartTimeCalc());
				String useTime = dayTime.format(new Date(checkTime));
				rightUserPanel[i].getUseTimeL().setText(useTime);
				rightUserPanel[i].getUseTimeL().updateUI();
				if (useTime.substring(6).equals("00") && !timeflag) {
					timeflag = true; // 00분에 가격 증가 한번만 실행//쓰레드가 1초 이내에도 여러번 실행되는 것을 막기 위함
					for (int j = 0; j < AdminServer.userlist.size(); j++) {
						if (AdminServer.userlist.get(j).getUserNumber().equals(user.getUserNumber())) {
							user = AdminServer.userlist.get(j); // 새로 갱신된 user 객체 받아옴
							user.setTotalPrice(user.getTotalPrice() + 20);
							rightUserPanel[i].getTotalPriceL().setText(user.getTotalPrice() + "원");
							rightUserPanel[i].getTotalPriceL().updateUI();
						}
					}
				}
				if (useTime.substring(6).equals("01")) {
					timeflag = false; // 00분인 동안 1번만 실행 시키기위해 01분까지 증가문 실행 못시키게 함
				}
				if(Flagment.UserLogout[i]) {
					System.out.println("로그아웃 : 메인");
					break;
				}
			}
		}
	}

	class OrderThread extends Thread {
		User user = null;
		AdminOrderGUI userOrder;
		int i;

		public OrderThread(User user, int i) {
			this.user = user;
			this.i = i;// 쓰레드가 생성된 패널의 주소
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (Flagment.UserOrder[i]) {// 여기 안들어와....
					System.out.println("주문");
					for (int j = 0; j < AdminServer.userlist.size(); j++) {
						if (AdminServer.userlist.get(j).getUserNumber().equals(user.getUserNumber())) {
							user = AdminServer.userlist.get(j); // 갱신된 User 받아옴
							System.out.println("주문들어왔어요~!");
							userOrder = new AdminOrderGUI(user.getOrder(), user.getSeatNumber());
							user.setOrder("");
							rightUserPanel[i].getAddAmountL().setText(user.getAddPrice() + "원");
							rightUserPanel[i].getTotalPriceL().setText(user.getTotalPrice() + "원");
							rightUserPanel[i].updateUI();
							break;
						}
					}
					Flagment.UserOrder[i] = false;
				}
			}
		} // orderThread 종료
	}
}
