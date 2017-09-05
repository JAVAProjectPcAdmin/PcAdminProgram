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
import java.net.ServerSocket;
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

import AdminServer.User;

public class AdminMainGUI extends JFrame {
	private JPopupMenu popup;
	private LeftMainGUI lmp = new LeftMainGUI(); //
	private RightMainGUI[] rightUserPanel = new RightMainGUI[25]; //

	public AdminMainGUI() {

		JPanel rightPanel = new JPanel();

		lmp.setBounds(0, 80, 220, 850);

		rightPanel.setLayout(new GridLayout(5, 5));

		for (int i = 0; i < 25; i++) {
			rightUserPanel[i] = new RightMainGUI();
			rightUserPanel[i].setSize(210, 170);
			rightUserPanel[i].setVisible(false);
			rightPanel.add(rightUserPanel[i]);
			rightPanel.addMouseListener(new PopupListener());
		}
		rightPanel.setBounds(230, 90, 1030, 800);
		lmp.getFindSeatBtn().addActionListener(new FindSeatActionListener());
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

	public static void main(String[] args) {
		AdminMainGUI admin = new AdminMainGUI();

	}

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

	class AdminClient {
		ServerSocket serverSocket = null;
		Socket socket = null;
		BufferedWriter bw = null;
		User user2;
		ObjectInputStream ois;

		public AdminClient() {
			try {
				serverSocket = new ServerSocket(7777);
				System.out.println("��ٸ�����....");
				socket = serverSocket.accept(); // ��ٸ� - ����Ǹ� socket�� ��
				System.out.println("Ŭ���̾�Ʈ ��û ���� ");

				ois = new ObjectInputStream(socket.getInputStream());
				user2 = (User) ois.readObject();
				System.out.println(user2.getName());
				if (user2.getSeatNumber() == 1) {
					rightUserPanel[1].setVisible(true);
					System.out.println("�������!!!!!");
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
