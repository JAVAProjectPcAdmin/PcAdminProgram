package adminMain;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import AdminServer.AdminClient;
import AdminServer.User;
import db.UserDao;
import flagment.Flagment;
import orderFood.AdminOrderGUI;

public class AdminMainGUI extends JFrame {
	private JPopupMenu popup;
	private LeftMainGUI lmp = new LeftMainGUI(); //
	private RightMainGUI[] rightUserPanel = new RightMainGUI[25]; //
	private JPanel rightPanel = new JPanel();
	UserThread isUserThread;
	TimerThread timerThread;
	OrderThread orderThread;
	int i;
	// private
	AdminClient adminClient;

	UserDao userDao = new UserDao();

	public static void main(String[] args) {

		AdminMainGUI admin = new AdminMainGUI();

	}

	public AdminMainGUI() {

		for (i = 0; i < 25; i++) {
			rightUserPanel[i] = new RightMainGUI();
			rightUserPanel[i].setSize(210, 170);
			rightUserPanel[i].addMouseListener(new ClickPanelListener());
			rightUserPanel[i].addMouseListener(new PopupListener());
			rightUserPanel[i].seat_num++;
			rightUserPanel[i].SEAT_NUMBER = String.valueOf(rightUserPanel[i].seat_num);

			rightUserPanel[i].setVisible(false);
			rightPanel.add(rightUserPanel[i]);
		}
		isUserThread = new UserThread();
		isUserThread.start();
		// orderThread = new OrderThread(flag);
		// orderThread.start();

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
		adminClient = new AdminClient();

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
					lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNameL().getText(), 0, 0);
					lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNumberL().getText(), 0, 1);
					lmp.infoModel1.setValueAt(rightUserPanel[i].getUsePCNumberL().getText(), 0, 2);
					lmp.infoTable1.updateUI();

					lmp.infoModel2.setValueAt("시작시간", 0, 0);
					lmp.infoModel2.setValueAt("종료시간", 0, 1);
					lmp.infoModel2.setValueAt(rightUserPanel[i].getUseTimeL().getText(), 0, 2);
					lmp.infoTable2.updateUI();

					lmp.infoModel3.setValueAt(rightUserPanel[i].getUserIDL().getText(), 0, 0);
					lmp.infoModel3.setValueAt("음식 주문 금액", 0, 1);
					lmp.infoModel3.setValueAt(rightUserPanel[i].getTotalPriceL().getText(), 0, 2);
					lmp.infoTable3.updateUI();
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////

	class UserThread extends Thread {

		public UserThread() {
			// TODO Auto-generated constructor stub
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < 25; i++) {
					if (Flagment.UserLoginState[i]) {
						User user = AdminClient.userlist.get(adminClient.userlist.size() - 1);
						rightUserPanel[i].setUserPanel(user);
						rightUserPanel[i].setVisible(true);
						rightUserPanel[i].updateUI();

						TimerThread timerThread = new TimerThread( user, i);
						timerThread.start();
						OrderThread orderThread = new OrderThread( user, i);
						orderThread.start();
						Flagment.UserLoginState[i] = false;
					}
				}
			}
		}
	}

	class TimerThread extends Thread {
		User user = null;
		int i;

		public TimerThread( User user, int i) {
			// TODO Auto-generated constructor stub
			this.user = user;
			this.i = i;
		}

		@Override
		public void run() {
			String nowTime;
			SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm:ss");
			while (true) {
				long time = System.currentTimeMillis() - 1000 * ((60 * 60 * 9));
				long checkTime = (time - user.getStartTimeCalc());
				String useTime = dayTime.format(new Date(checkTime));
				rightUserPanel[i].getUseTimeL().setText(useTime);
				rightUserPanel[i].getUseTimeL().updateUI();
				if (checkTime / 1000 % 60 == 0) {
					user.setTotalPrice(user.getTotalPrice() + 20);
					rightUserPanel[i].getTotalPriceL().setText(user.getTotalPrice() + "원");
					rightUserPanel[i].getTotalPriceL().updateUI();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	class OrderThread extends Thread {
		User user = null;
		int i;
		AdminOrderGUI orderGUI;
		
		public OrderThread( User user, int i) {
			this.user = user;
			this.i = i;// 쓰레드가 생성된 패널의 주소
		}

		@Override
		public void run() {
			while (true) {
				System.out.println(Flagment.UserOrder[i]+" , "+i);
				if (Flagment.UserOrder[i]) {//여기 안들어와....
					System.out.println("주문");
					for (int j = 0; j < AdminClient.userlist.size(); j++) {
						if (AdminClient.userlist.get(j).getUserNumber().equals(user.getUserNumber())) {
							user = AdminClient.userlist.get(j); // 갱신된 User 받아옴
								System.out.println("주문들어왔어요~!");
								orderGUI = new AdminOrderGUI(user.getOrder(), user.getSeatNumber());
								user.setOrder("");
								break;
						}
					}
					Flagment.UserOrder[i]=false;
				}
			}

		} // orderThread 종료
	}
}
