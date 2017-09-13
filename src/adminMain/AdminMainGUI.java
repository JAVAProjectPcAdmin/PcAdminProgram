package adminMain;

import java.awt.Color;
import java.awt.GridLayout;
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
	private JPopupMenu popup;
	private LeftMainGUI lmp = new LeftMainGUI(); //
	private RightMainGUI[] rightUserPanel = new RightMainGUI[25]; //
	private JPanel rightPanel = new JPanel();
	public static UserThread isUserThread;
	TimerThread timerThread;
	OrderThread orderThread;
	List<Thread> threadlist = new ArrayList<>();
	int i;
	// private

	UserDao userDao = new UserDao();


	public AdminMainGUI() {

		for (i = 0; i < 25; i++) {
			rightUserPanel[i] = new RightMainGUI();
			rightUserPanel[i].setSize(210, 170);
			rightUserPanel[i].addMouseListener(new ClickPanelListener());
			rightUserPanel[i].addMouseListener(new PopupListener());

			rightUserPanel[i].setVisible(false);
			rightPanel.add(rightUserPanel[i]);
		}
		isUserThread = new UserThread();
		isUserThread.start();

		lmp.getFindSeatBtn().addActionListener(new FindSeatActionListener());

		lmp.setBounds(0, 80, 220, 850);
		rightPanel.setLayout(new GridLayout(5, 5));
		rightPanel.setBounds(230, 90, 1030, 800);

		add(rightPanel);
		add(lmp);
		setResizable(false);
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		popup = new JPopupMenu();
		JMenuItem menuChat = new JMenuItem("대화걸기");
		JMenuItem menuLogout = new JMenuItem("로그아웃");
		popup.add(menuChat);
		popup.add(menuLogout);
		// menuChat.addActionListener(this);

		setLayout(null);
		setSize(1280, 924);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("관리자 화면");
		getContentPane().setBackground(Color.WHITE);

<<<<<<< HEAD
		
=======
		System.out.println("???");
//		AdminServer adminServer = new AdminServer();
>>>>>>> e7d64ad35b455369965ed3b7ca4502a11ceec0f1
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 회원자리 검색 리스너
	private class FindSeatActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println((lmp.getInputSeat_Text()).getText());
			JButton selected = (JButton) e.getSource();
			if (selected == lmp.getFindSeatBtn()) {
				boolean flag = false;
				for (int i = 0; i < rightUserPanel.length; i++) {

					if ((rightUserPanel[i].getUserNameL()).getText().equals((lmp.getInputSeat_Text()).getText())) {
						rightUserPanel[i].setBorder(new TitledBorder(new LineBorder(Color.RED)));
						flag = true;
					}

				}
				if (flag == false) {
					JOptionPane.showMessageDialog(null, "찾으시는 회원이 없습니다.", "자리 검색 결과", JOptionPane.WARNING_MESSAGE);
					for (int i = 0; i < rightUserPanel.length; i++) {
						rightUserPanel[i].setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
					}

				}

			}
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////

	class PopupListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			showPopup(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			showPopup(e);
		}

		private void showPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	class ClickPanelListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			for (int i = 0; i < rightUserPanel.length; i++) {
				if (rightUserPanel[i] == e.getSource()) {

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

					lmp.infoModel3.setValueAt((Integer.parseInt(totalPriceVal2) - Integer.parseInt(amoutVal2)), 0, 0); // PC사용금액
					lmp.infoModel3.setValueAt(amoutVal1, 0, 1); // 음식주문금액
					lmp.infoModel3.setValueAt(totalPriceVal1, 0, 2); // 총금액
					lmp.infoTable3.updateUI();
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////

	class UserThread extends Thread {
		User user;

		public UserThread() {
		}

		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < 25; i++) {
					if (Flagment.UserLoginState[i]) {
						user = AdminServer.userlist.get(AdminServer.userlist.size() - 1);// userlist에 가장 최근데 들어온
																							// user
						rightUserPanel[i].setUserPanel(user); // 패널에 현재 user로 셋팅
						rightUserPanel[i].setVisible(true);
						rightUserPanel[i].updateUI();
						LeftMainGUI.countSeat++;
						lmp.countGuest_Label1.setText(LeftMainGUI.countSeat + " / " + "25");
						lmp.updateUI();// 인원수 증가
						TimerThread timerThread = new TimerThread(user, i);
						threadlist.add(timerThread);
						timerThread.start();
						OrderThread orderThread = new OrderThread(user, i);
						threadlist.add(orderThread);
						orderThread.start();
						
						Flagment.UserLoginState[i] = false;
					}
					if (Flagment.UserLogout[i]) {
						System.out.println("로그아웃 했다요!!");
						rightUserPanel[i].setVisible(false);
						LeftMainGUI.countSeat--;
						lmp.countGuest_Label1.setText(LeftMainGUI.countSeat + " / " + "25");
						lmp.updateUI();// 인원수 증가
						Object[] temp = new Object[4];

						/////////////////////////// 수정중!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						for (int j = 0; j < AdminServer.userlist.size(); j++) {
								if (i == AdminServer.userlist.get(j).getSeatNumber()) {
									temp[0] = AdminServer.userlist.get(j).getName();
									System.out.println(temp[0]);
									temp[1] = AdminServer.userlist.get(j).getUserID();
									System.out.println(temp[1]);
									temp[2] = AdminServer.userlist.get(j).getSeatNumber()+1;
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
