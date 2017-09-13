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
		setTitle("������ ȭ��");
		getContentPane().setBackground(Color.WHITE);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// ȸ���ڸ� �˻� ������
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
					JOptionPane.showMessageDialog(null, "ã���ô� ȸ���� �����ϴ�.", "�ڸ� �˻� ���", JOptionPane.WARNING_MESSAGE);
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
						String totalPriceVal2 = totalPriceVal1.replace("��", "");
						String amoutVal1 = rightUserPanel[i].getAddAmountL().getText();
						String amoutVal2 = amoutVal1.replace("��", "");

						lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNumberL().getText(), 0, 0); // ȸ����ȣ
						lmp.infoModel1.setValueAt(rightUserPanel[i].getUserIDL().getText(), 0, 1); // ���̵�
						lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNameL().getText(), 0, 2); // �̸�
						lmp.infoTable1.updateUI();

						lmp.infoModel2.setValueAt(rightUserPanel[i].getUser().getSeatNumber() + 1, 0, 0); // ��� PC
						lmp.infoModel2.setValueAt(rightUserPanel[i].getUser().getStartTime().substring(7), 0, 1); // ���۽ð�
						lmp.infoModel2.setValueAt(rightUserPanel[i].getUseTimeL().getText(), 0, 2); // ���ð�
						lmp.infoTable2.updateUI();

						lmp.infoModel3.setValueAt(
								(Integer.parseInt(totalPriceVal2) - Integer.parseInt(amoutVal2)) + "��", 0, 0); // PC���ݾ�
						lmp.infoModel3.setValueAt(amoutVal1, 0, 1); // �����ֹ��ݾ�
						lmp.infoModel3.setValueAt(totalPriceVal1, 0, 2); // �ѱݾ�
						lmp.infoTable3.updateUI();
					}
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////

	class UserThread extends Thread {	//�¼��� �г��� ������
										//������ �α����� �ϰų� �α׾ƿ��� �ҋ� �˸��� �޾� ����
		User user;

		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < 25; i++) {
					if (Flagment.UserLoginState[i]) {	//������ �α����ߴٰ� �������� �˸� 
						user = AdminServer.userlist.get(AdminServer.userlist.size() - 1);// userlist�� ���� �ֱٵ� ���� User ����
						rightUserPanel[i].setUserPanel(user); // �гο� �α����� user�� ����
						rightUserPanel[i].updateUI();
						LeftMainGUI.countSeat++;
						lmp.countGuest_Label1.setText(LeftMainGUI.countSeat + " / " + "25");
						lmp.updateUI();// �ο��� ����
						TimerThread timerThread = new TimerThread(user, i);
						timerThread.start();	//�ǽð����� ȭ�鿡 �ʰ� �ö󰡴°��� �����ֱ� ���� ������
						OrderThread orderThread = new OrderThread(user, i);
						orderThread.start();	//���� ��ǻ�Ϳ��� �ֹ������� �˸� �ޱ� ���� ������ 

						Flagment.UserLoginState[i] = false;	//�� �ڸ����� �� �۾��� �ѹ��� �����Ű�� ���� false ó�� 
					}
					if (Flagment.UserLogout[i]) {	//User�� �α׾ƿ��ߴٰ� �������� �˸�

						//rightUserPanel[i].getUsePCNumberL().setText(Integer.toString(i+1));

						rightUserPanel[i].setLogoutPanel();
						rightUserPanel[i].updateUI();
						
						LeftMainGUI.countSeat--;
						lmp.countGuest_Label1.setText(LeftMainGUI.countSeat + " / " + "25");
						lmp.updateUI();// �ο��� ����
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
			boolean timeflag = true; // ó�� 00�� ���� ����X
			while (true) {
				long time = System.currentTimeMillis() - 1000 * ((60 * 60 * 9));
				long checkTime = (time - user.getStartTimeCalc());
				String useTime = dayTime.format(new Date(checkTime));
				rightUserPanel[i].getUseTimeL().setText(useTime);
				rightUserPanel[i].getUseTimeL().updateUI();
				if (useTime.substring(6).equals("00") && !timeflag) {
					timeflag = true; // 00�п� ���� ���� �ѹ��� ����//�����尡 1�� �̳����� ������ ����Ǵ� ���� ���� ����
					for (int j = 0; j < AdminServer.userlist.size(); j++) {
						if (AdminServer.userlist.get(j).getUserNumber().equals(user.getUserNumber())) {
							user = AdminServer.userlist.get(j); // ���� ���ŵ� user ��ü �޾ƿ�
							user.setTotalPrice(user.getTotalPrice() + 20);
							rightUserPanel[i].getTotalPriceL().setText(user.getTotalPrice() + "��");
							rightUserPanel[i].getTotalPriceL().updateUI();
						}
					}
				}
				if (useTime.substring(6).equals("01")) {
					timeflag = false; // 00���� ���� 1���� ���� ��Ű������ 01�б��� ������ ���� ����Ű�� ��
				}
				if(Flagment.UserLogout[i]) {
					System.out.println("�α׾ƿ� : ����");
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
			this.i = i;// �����尡 ������ �г��� �ּ�
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (Flagment.UserOrder[i]) {// ���� �ȵ���....
					System.out.println("�ֹ�");
					for (int j = 0; j < AdminServer.userlist.size(); j++) {
						if (AdminServer.userlist.get(j).getUserNumber().equals(user.getUserNumber())) {
							user = AdminServer.userlist.get(j); // ���ŵ� User �޾ƿ�
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
