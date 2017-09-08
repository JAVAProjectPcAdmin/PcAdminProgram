package adminMain;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import AdminServer.AdminClient;

public class AdminMainGUI extends JFrame {
	private JPopupMenu popup;
	private LeftMainGUI lmp = new LeftMainGUI(); //
	private RightMainGUI[] rightUserPanel = new RightMainGUI[25]; //
	private Flagment flag;
	private JPanel rightPanel = new JPanel();
	UserThread isUserThread;
	TimerThread timerThread;
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
		isUserThread = new UserThread(flag);
		isUserThread.start();
		timerThread = new TimerThread(flag);
		timerThread.start();
		
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
		private Flagment flag;

		public UserThread(Flagment flag) {
			// TODO Auto-generated constructor stub
			// TODO Auto-generated constructor stub
			this.flag = flag;
		}

		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < 25; i++) {
					if (flag.UserLoginState[i]) {
						rightUserPanel[i].setUserPanel(AdminClient.userlist.get(adminClient.userlist.size() - 1));
						rightUserPanel[i].setVisible(true);
						rightUserPanel[i].updateUI();
					}
				}
			}
		}
	}

	class TimerThread extends Thread {
		User user = null;
		Flagment flag;

		public TimerThread( Flagment flag) {
			// TODO Auto-generated constructor stub
			this.flag = flag;
		}

		@Override
		public void run() {
			String nowTime;
			SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm:ss");
			while (true) {
				for (int i = 0; i < 25; i++) {
					if (flag.UserLoginState[i]) {
						user=AdminClient.userlist.get(adminClient.userlist.size() - 1);
						long time = System.currentTimeMillis() - 1000 *( (60 * 60 * 9)+44);
						long checkTime = (time - user.getStartTimeCalc());
						String useTime = dayTime.format(new Date(checkTime));
						rightUserPanel[i].getUseTimeL().setText(useTime);
						rightUserPanel[i].getUseTimeL().updateUI();
						if (checkTime/1000%60==0) {
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
					}//로그인 한 유저를 유저 리스트에 넣어두고 가장 나중에 받아온 유저의 정보를 화면에 띄움 
					// 쓰레드가 돌면서 flag가 0 이면 이전의 유저가 아니라 가장 마지막의 유저로 덮어씌워짐 
					// 한개의 쓰레드가 계속 돌아서 그럼 ㅎㅅㅎ 
					// 그렇다고 25개 돌릴수도 없고.....
					//생각점.....
				}
			}
		}
	}

}
