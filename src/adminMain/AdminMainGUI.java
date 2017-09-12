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
import orderFood.AdminOrderGUI;
import AdminServer.AdminClient;

public class AdminMainGUI extends JFrame {
	private JPopupMenu popup;
	private LeftMainGUI lmp = new LeftMainGUI(); //
	private RightMainGUI[] rightUserPanel = new RightMainGUI[25]; //
	private JPanel rightPanel = new JPanel();
	public static UserThread isUserThread;
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
		JMenuItem menuChat = new JMenuItem("��ȭ�ɱ�");
		JMenuItem menuLogout = new JMenuItem("�α׾ƿ�");
		popup.add(menuChat);
		popup.add(menuLogout);
		// menuChat.addActionListener(this);

		setLayout(null);
		setSize(1280, 924);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("������ ȭ��");
		getContentPane().setBackground(Color.WHITE);
		adminClient = new AdminClient();

	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ȸ���ڸ� �˻� ������
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
					JOptionPane.showMessageDialog(null, "ã���ô� ȸ���� �����ϴ�.", "�ڸ� �˻� ���", JOptionPane.WARNING_MESSAGE);
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
					String totalPriceVal2 = totalPriceVal1.replace("��", "");
					String amoutVal1 = rightUserPanel[i].getAddAmountL().getText();
					String amoutVal2 = amoutVal1.replace("��", "");
					
					lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNumberL().getText(), 0, 0); //ȸ����ȣ
					lmp.infoModel1.setValueAt(rightUserPanel[i].getUserIDL().getText(), 0, 1); //���̵�
					lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNameL().getText(), 0, 2); //�̸�
					lmp.infoTable1.updateUI();
				
					lmp.infoModel2.setValueAt(rightUserPanel[i].getUser().getSeatNumber()+1, 0, 0); //��� PC
					lmp.infoModel2.setValueAt(rightUserPanel[i].getUser().getStartTime().substring(7), 0, 1); //���۽ð�
					lmp.infoModel2.setValueAt(rightUserPanel[i].getUseTimeL().getText(), 0, 2); //���ð�
					lmp.infoTable2.updateUI();
					
					lmp.infoModel3.setValueAt((Integer.parseInt(totalPriceVal2) - 
							Integer.parseInt(amoutVal2)), 0, 0); //PC���ݾ�
					lmp.infoModel3.setValueAt(amoutVal1, 0, 1); //�����ֹ��ݾ�
					lmp.infoModel3.setValueAt(totalPriceVal1, 0, 2); //�ѱݾ�
					lmp.infoTable3.updateUI();
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////

	class UserThread extends Thread {

		public UserThread() {
		}

		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < 25; i++) {
					if (Flagment.UserLoginState[i]) {
						User user = AdminClient.userlist.get(adminClient.userlist.size() - 1);//userlist�� ���� �ֱٵ� ���� user
						rightUserPanel[i].setUserPanel(user);	// �гο� ���� user�� ����
						rightUserPanel[i].setVisible(true);
						rightUserPanel[i].updateUI();
						LeftMainGUI.countSeat++;
						lmp.countGuest_Label1.setText(LeftMainGUI.countSeat + " / " + "25");
						lmp.updateUI();//�ο��� ����
						TimerThread timerThread = new TimerThread(user, i);
						timerThread.start();
						OrderThread orderThread = new OrderThread(user, i);
						orderThread.start();
						Flagment.UserLoginState[i] = false;
					}
					
					if(Flagment.UserLogoutState[i]) {
						System.out.println("�α׾ƿ� �ߴٿ�!!");
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
			boolean timeflag=true;	//ó�� 00�� ���� ����X
			while (true) {
				long time = System.currentTimeMillis() - 1000 * ((60 * 60 * 9));
				long checkTime = (time - user.getStartTimeCalc());
				String useTime = dayTime.format(new Date(checkTime));
				rightUserPanel[i].getUseTimeL().setText(useTime);
				rightUserPanel[i].getUseTimeL().updateUI();
				if(useTime.substring(6).equals("00")&& !timeflag) {
					timeflag=true; //00�п� ���� ���� �ѹ��� ����//�����尡 1�� �̳����� ������ ����Ǵ� ���� ���� ����
					for (int j = 0; j < AdminClient.userlist.size(); j++) {
						if (AdminClient.userlist.get(j).getUserNumber().equals(user.getUserNumber())) {
							user=AdminClient.userlist.get(j);	//���� ���ŵ� user ��ü �޾ƿ�
							user.setTotalPrice(user.getTotalPrice() + 20);
							rightUserPanel[i].getTotalPriceL().setText(user.getTotalPrice() + "��");
							rightUserPanel[i].getTotalPriceL().updateUI();
						}
					}
				}
				if(useTime.substring(6).equals("01")) {
					timeflag=false; //00���� ���� 1���� ���� ��Ű������ 01�б��� ������ ���� ����Ű�� �� 
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
			this.i = i;// �����尡 ������ �г��� �ּ�
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (Flagment.UserOrder[i]) {// ���� �ȵ���....
					System.out.println("�ֹ�");
					for (int j = 0; j < AdminClient.userlist.size(); j++) {
						if (AdminClient.userlist.get(j).getUserNumber().equals(user.getUserNumber())) {
							user = AdminClient.userlist.get(j); // ���ŵ� User �޾ƿ�
							System.out.println("�ֹ����Ծ��~!");
							userOrder = new AdminOrderGUI(user.getOrder(), user.getSeatNumber());
							user.setOrder("");
							rightUserPanel[i].getAddAmountL().setText(user.getAddPrice() + "��");
							rightUserPanel[i].getTotalPriceL().setText(user.getTotalPrice() + "��");
							rightUserPanel[i].updateUI();
							break;
						}
					}
					Flagment.UserOrder[i] = false;
				}
			}

		} // orderThread ����
	}
}
