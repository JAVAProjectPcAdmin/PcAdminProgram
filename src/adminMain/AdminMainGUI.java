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

import AdminServer.AdminServer;
import AdminServer.User;

public class AdminMainGUI extends JFrame {
	private JPopupMenu popup;
	private LeftMainGUI lmp = new LeftMainGUI(); //
	private RightMainGUI[] rightUserPanel = new RightMainGUI[25]; //
	private JPanel rightPanel = new JPanel();
	// private

	public AdminMainGUI() {

		for (int i = 0; i < 25; i++) {
			rightUserPanel[i] = new RightMainGUI();
			rightUserPanel[i].setSize(210, 170);
<<<<<<< HEAD
			rightUserPanel[i].addMouseListener(new ClickPanelListener());
			rightUserPanel[i].addMouseListener(new PopupListener());
=======
			rightUserPanel[i].setVisible(false);
>>>>>>> 2cdd1d3821556b8496112b9aa2b82d5f203eee98
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

<<<<<<< HEAD
=======
	public static void main(String[] args) {
		AdminMainGUI admin = new AdminMainGUI();
		new AdminServer();
	}

>>>>>>> 2cdd1d3821556b8496112b9aa2b82d5f203eee98
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

<<<<<<< HEAD
	class ClickPanelListener extends MouseAdapter {


		@Override
		public void mousePressed(MouseEvent e) {

			lmp.infoModel1.setValueAt(rightUserPanel[2].getUserNameL().getText(), 0, 0);
			lmp.infoModel1.setValueAt(rightUserPanel[2].getUserIDL().getText(), 0, 1);
			lmp.infoModel1.setValueAt(rightUserPanel[2].getUsePCNumberL().getText(), 0, 2);
			lmp.infoTable1.updateUI();
			
			lmp.infoModel2.setValueAt("���۽ð�", 0, 0);
			lmp.infoModel2.setValueAt("����ð�", 0, 1);
			lmp.infoModel2.setValueAt(rightUserPanel[2].getUseTimeL().getText(), 0, 2);
			lmp.infoTable2.updateUI();
			
			lmp.infoModel3.setValueAt(rightUserPanel[2].getTotalPriceL().getText(), 0, 0);
			lmp.infoModel3.setValueAt("pc���ݾ�", 0, 1);
			lmp.infoModel3.setValueAt("�����ֹ� ����", 0, 2);
			lmp.infoTable3.updateUI();
			
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		AdminMainGUI admin = new AdminMainGUI();

	}
=======
//	class AdminClient {
//		
//		Socket socket = null;
//		BufferedWriter bw = null;
//		User user2;
//		ObjectInputStream ois;
//
//		public AdminClient() {
//			try {
//				
//				socket = new Socket("127.0.0.1", 7777); 
//				System.out.println("���� ����!!");
//				ois = new ObjectInputStream(socket.getInputStream());
//				user2 = (User) ois.readObject();
//				System.out.println(user2.getName());
//				if (user2.getSeatNumber() == 1) {
//					rightUserPanel[1].setVisible(true);
//					System.out.println("�������!!!!!");
//				}
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
>>>>>>> 2cdd1d3821556b8496112b9aa2b82d5f203eee98
}
