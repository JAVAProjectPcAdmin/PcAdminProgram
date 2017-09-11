package userUsingState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.oracle.jrockit.jfr.UseConstantPool;

import AdminServer.User;
import orderFood.OrderGUI;
import userLogin.UserClient;
import userLogin.UserLoginGUI;

public class UserUsingStateGUI extends JFrame {
	private JLabel nameLb, timeLb, moneyLb, talkLb, orderLb, informationLb, customerNumberLb;
	private JButton talkBt, orderBt, informationBt;
	private JPanel panel, grayPanel;
	BufferedImage panelImg = null;
	public static boolean flag = false;
	public static boolean flag2 = false;
	public static boolean flag3 = false;// â �ߺ��� �������� flag //â������ true�� ��ȯ�ϰ� ������ false�� ��ȯ //false�϶� �������� if��
	public static boolean flag4 = false;
	public JButton logoutBt;
	User user;
	UserClient userclient;
	private boolean noticeFlag = false;

	public UserUsingStateGUI(User user, UserClient userclient) {
		this.user = user;
		setLayout(null);
		setLocation(950, 50); // ������ġ ���� �޼ҵ�
		setSize(310, 280);
		setResizable(false);
		setUndecorated(true); // ������â ���κ� ���� �޼ҵ�
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		getContentPane().setBackground(Color.WHITE);

		panel = null;
		try {
			panel = new JPanelWithBackground("images//graypanel.png");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 400);

		customerNumberLb = new JLabel(user.getSeatNumber() + "");
		customerNumberLb.setFont(new Font("���� ���", Font.BOLD, 40));
		timeLb = new JLabel("00:00");
		moneyLb = new JLabel(user.getTotalPrice() + "��");
		talkLb = new JLabel("����");
		talkLb.setFont(new Font("���� ���", Font.BOLD, 13));
		orderLb = new JLabel("�ֹ�");
		orderLb.setFont(new Font("���� ���", Font.BOLD, 13));
		informationLb = new JLabel("�������");
		informationLb.setFont(new Font("���� ���", Font.BOLD, 13));
		nameLb = new JLabel(user.getName());
		nameLb.setFont(new Font("���� ���", Font.BOLD, 16));
		logoutBt = new JButton(new ImageIcon("images//logout.png"));
		logoutBt.setBorderPainted(false);
		talkBt = new JButton(new ImageIcon("images//talk.png"));
		talkBt.setBorderPainted(false);

		talkBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				if (!flag) {
					TalkGUI talk = new TalkGUI();
					flag = true;
				}
			}
		});
		orderBt = new JButton(new ImageIcon("images//order.png"));
		orderBt.setBorderPainted(false);
		informationBt = new JButton(new ImageIcon("images//information.png"));
		informationBt.setBorderPainted(false);
		informationBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!flag2) {
					ChargeInformationGUI ci = new ChargeInformationGUI(user);
					flag2 = true;
				}
			}
		});

		panel.setBounds(10, 80, 283, 84);
		customerNumberLb.setBounds(30, 0, 100, 100);
		nameLb.setBounds(110, 30, 100, 50);
		talkBt.setBounds(50, 180, 44, 32);
		orderBt.setBounds(130, 180, 38, 29);
		orderBt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!flag3) {
					OrderGUI order = new OrderGUI(user, userclient);
					flag3 = true;
				}
			}
		});
		logoutBt.setBounds(200, 20, 63, 51);
		logoutBt.addActionListener(new ActionListener() {
			// �α׾ƿ��� left���ο� ���� ��������
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					userclient.getSocket().close();
					System.out.println("������ ���� ������");
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.", "�α׾ƿ�", 1);
					dispose();
					UserLoginGUI g = new UserLoginGUI();
				}
			}
		});
		informationBt.setBounds(210, 180, 42, 36);
		talkLb.setBounds(55, 210, 50, 50);
		orderLb.setBounds(137, 210, 50, 50);
		informationLb.setBounds(208, 210, 70, 50);

		panel.add(timeLb);
		panel.add(moneyLb);

		timeLb.setBounds(90, 10, 70, 30);
		moneyLb.setBounds(90, 45, 700, 30);

		add(logoutBt);
		add(talkBt);
		add(orderBt);
		add(informationBt);
		add(talkLb);
		add(orderLb);
		add(informationLb);
		add(customerNumberLb);
		add(nameLb);
		add(panel);

		setVisible(true);

		TimerThread thread = new TimerThread();
		thread.start();

	}

	class JPanelWithBackground extends JPanel { // �гο� �̹��� ä��Ⱑ �ƴ� �̹����� ��׶���� �������� Ŭ����

		private Image backgroundImage;

		public JPanelWithBackground(String fileName) throws IOException {
			backgroundImage = ImageIO.read(new File("images//graypanel.png"));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(backgroundImage, 0, 0, null);
		}
	}

	class TimerThread extends Thread {
		@Override
		public void run() {
			Calendar c = Calendar.getInstance();
			while (true) {
				long time = System.currentTimeMillis() - 1000 * 60 * 60 * 9;
				SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm:ss");
				long checkTime = (time - user.getStartTimeCalc());
				timeLb.setText(dayTime.format(new Date(checkTime)));
				timeLb.updateUI();
				if (checkTime / 1000 % 60 == 0) {
					user.setTotalPrice(user.getTotalPrice() + 20);
					moneyLb.setText(user.getTotalPrice() + "");
					moneyLb.updateUI();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//21:30���� �Ǹ� ! �̼����ڵ鿡�� �˸� !
				if(c.get(Calendar.HOUR_OF_DAY) == 21 && c.get(Calendar.MINUTE) == 30 && noticeFlag == false) {
					if((Calendar.YEAR - Integer.parseInt(user.getBirthYear())) < 19){
						WarningGUI g = new WarningGUI();
						noticeFlag = true;
					}
				}
			}
		}// while�� ����
	}
}

