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

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import AdminServer.AdminClient;
import AdminServer.AdminServer;
import AdminServer.User;
import db.UserDao;
import flagment.Flagment;

public class AdminMainGUI extends JFrame {
	private JPopupMenu popup;
	private LeftMainGUI lmp = new LeftMainGUI(); //
	private RightMainGUI[] rightUserPanel = new RightMainGUI[25]; //
	private Flagment flag;
	private JPanel rightPanel = new JPanel();
	UserThread thread;
	int i;
	// private
	
	UserDao userDao = new UserDao();

	public AdminMainGUI() {

		for (i = 0; i < 25; i++) {
			rightUserPanel[i] = new RightMainGUI();
			rightUserPanel[i].setSize(210, 170);
			rightUserPanel[i].addMouseListener(new ClickPanelListener());
			rightUserPanel[i].addMouseListener(new PopupListener());
			rightUserPanel[i].seat_num++;
			rightUserPanel[i].SEAT_NUMBER = String.valueOf(rightUserPanel[i].seat_num);
<<<<<<< HEAD
<<<<<<< HEAD
			rightUserPanel[i].setVisible(true);
=======
			rightUserPanel[i].setVisible(false);
>>>>>>> 35e748bb1a47f2fc74d4441b1b808ca41f60b6a1
=======
			rightUserPanel[i].setVisible(false);
>>>>>>> 35e748bb1a47f2fc74d4441b1b808ca41f60b6a1
			flag = new Flagment(i);
			thread = new UserThread(i, flag);
			rightPanel.add(rightUserPanel[i]);
		}

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
					lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNameL().getText(), 0, 0);
					lmp.infoModel1.setValueAt(rightUserPanel[i].getUserNumberL().getText(), 0, 1);
					lmp.infoModel1.setValueAt(rightUserPanel[i].getUsePCNumberL().getText(), 0, 2);
					lmp.infoTable1.updateUI();

					lmp.infoModel2.setValueAt("���۽ð�", 0, 0);
					lmp.infoModel2.setValueAt("����ð�", 0, 1);
					lmp.infoModel2.setValueAt(rightUserPanel[i].getUseTimeL().getText(), 0, 2);
					lmp.infoTable2.updateUI();

					lmp.infoModel3.setValueAt(rightUserPanel[i].getUserIDL().getText(), 0, 0);
					lmp.infoModel3.setValueAt("���� �ֹ� �ݾ�", 0, 1);
					lmp.infoModel3.setValueAt(rightUserPanel[i].getTotalPriceL().getText(), 0, 2);
					lmp.infoTable3.updateUI();
				}
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		AdminMainGUI admin = new AdminMainGUI();
		new AdminServer();
		AdminClient adcli =new AdminClient();
	}
	class UserThread extends Thread{
		private int i;
		private Flagment flag;
		public UserThread(int i ,Flagment flag) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
			// TODO Auto-generated constructor stub
>>>>>>> 35e748bb1a47f2fc74d4441b1b808ca41f60b6a1
=======
			// TODO Auto-generated constructor stub
>>>>>>> 35e748bb1a47f2fc74d4441b1b808ca41f60b6a1
			this.i=i;
			this.flag=flag;
		}
		@Override
		public void run() {
<<<<<<< HEAD
<<<<<<< HEAD
=======
			// TODO Auto-generated method stub
>>>>>>> 35e748bb1a47f2fc74d4441b1b808ca41f60b6a1
=======
			// TODO Auto-generated method stub
>>>>>>> 35e748bb1a47f2fc74d4441b1b808ca41f60b6a1
			super.run();
			if(flag.UserLoginState[i]) {
				System.out.println(flag.UserLoginState[i]);
				rightUserPanel[i].setVisible(true);
			}
		}
<<<<<<< HEAD
<<<<<<< HEAD
	}
<<<<<<< HEAD
class AdminClient {
		
		Socket socket = null;
		BufferedWriter bw = null;
		User user2;
		ObjectInputStream ois;

		public AdminClient() {
			try {
				
				socket = new Socket("127.0.0.1", 7777); 
				System.out.println("���� ����!!");
				ois = new ObjectInputStream(socket.getInputStream());
				user2 = (User) ois.readObject();
				System.out.println(user2.getName());
				if (user2.getSeatNumber() == 1) {
					rightUserPanel[i].setVisible(false);
					System.out.println("�������!!!!!");
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

=======
>>>>>>> 35e748bb1a47f2fc74d4441b1b808ca41f60b6a1
=======
>>>>>>> 35e748bb1a47f2fc74d4441b1b808ca41f60b6a1
	}
=======
>>>>>>> 35e748bb1a47f2fc74d4441b1b808ca41f60b6a1
}
