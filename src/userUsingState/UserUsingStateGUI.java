package userUsingState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import AdminServer.User;
import ChattingServer.Client;
import orderFood.OrderGUI;
import userLogin.UserClient;
import userLogin.UserLoginGUI;

public class UserUsingStateGUI extends JFrame {

	private JLabel nameLb, timeLb, moneyLb, talkLb, orderLb, informationLb, customerNumberLb, IdLb;
	private JButton talkBt, orderBt, informationBt;
	private JPanel panel;
	public JButton logoutBt;
	private User user;
	private UserClient userclient;
	
	private boolean logout = false;
	private boolean flag = false;
	public static boolean flag2 = false;
	public static boolean flag3 = false;// 창 중복을 막기위한 flag //창을띄우면 true를 반환하고 꺼질때 false를 반환 //false일때 켜지도록 if문

	private ImageIcon logoutIcon = new ImageIcon("images//logout.png");
	private Image logoutButton = logoutIcon.getImage();
	private Image newLogoutButtonImg = logoutButton.getScaledInstance(35, 32, java.awt.Image.SCALE_SMOOTH);
	private ImageIcon chLogoutButton = new ImageIcon(newLogoutButtonImg);

	public UserUsingStateGUI(UserClient userclient) {
		Calendar c = Calendar.getInstance();
		int currYear = c.get(Calendar.YEAR);

		// 19세 이하
		if ((currYear - Integer.parseInt(UserLoginGUI.user.getBirthYear())) < 19) {
			new shutdown();
		}

		this.user = UserLoginGUI.user;
		setLayout(null);
		setLocation(950, 50); // 시작위치 설정 메소드
		setSize(310, 280);
		setResizable(false);
		setUndecorated(true); // 윈도우창 윗부분 제거 메소드
		setAlwaysOnTop(true);
		getContentPane().setBackground(Color.WHITE);

		panel = null;
		try {
			panel = new JPanelWithBackground("images//graypanel.png");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 400);

		customerNumberLb = new JLabel((user.getSeatNumber() + 1) + "");
		customerNumberLb.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		timeLb = new JLabel("00:00");
		moneyLb = new JLabel(user.getTotalPrice() + "원");
		talkLb = new JLabel("문의");
		talkLb.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		orderLb = new JLabel("주문");
		orderLb.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		informationLb = new JLabel("요금정보");
		informationLb.setFont(new Font("맑은 고딕", Font.BOLD, 13));

		nameLb = new JLabel(user.getName());
		nameLb.setFont(new Font("맑은 고딕", Font.BOLD, 19));

		IdLb = new JLabel(user.getUserID());
		IdLb.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		logoutBt = new JButton(chLogoutButton);
		logoutBt.setBorderPainted(false);
		talkBt = new JButton(new ImageIcon("images//talk.png"));
		talkBt.setBorderPainted(false);

		talkBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!flag) {
					Client client = new Client((user.getSeatNumber() + 1) + "번좌석");
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
				if (!flag2) {
					ChargeInformationGUI ci = new ChargeInformationGUI();
					flag2 = true;
				}
			}
		});

		panel.setBounds(10, 80, 283, 84);
		customerNumberLb.setBounds(25, 0, 100, 100);
		nameLb.setBounds(110, 30, 100, 50);
		IdLb.setBounds(110, 10, 100, 50);
		talkBt.setBounds(50, 180, 44, 32);
		orderBt.setBounds(130, 180, 38, 29);
		orderBt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!flag3) {
					OrderGUI order = new OrderGUI(userclient);
					flag3 = true;
				}
			}
		});
		logoutBt.setBounds(250, 17, 35, 32);
		logoutBt.addActionListener(new ActionListener() {
			// 로그아웃시 left메인에 유저 정보전달
			@Override
			// 양태흠
			public void actionPerformed(ActionEvent e) {

				int check = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (check == 0) {
					try {
						userclient.getSocket().close();
						System.out.println("서버와 연결 끊어짐");
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally {
						dispose();
						new UserLoginGUI();
					}
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

		add(IdLb);
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

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image Iconimg = toolkit.getImage("images\\pcIcon.png");
		setIconImage(Iconimg);

		setVisible(true);

		TimerThread thread = new TimerThread();
		thread.start();
	}

	class JPanelWithBackground extends JPanel { // 패널에 이미지 채우기가 아닌 이미지를 백그라운드로 쓰기위한 클래스

		private Image backgroundImage;

		public JPanelWithBackground(String fileName) throws IOException {
			backgroundImage = ImageIO.read(new File("images//graypanel.png"));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, null);
		}
	}

	// 셧다운 알림
	class shutdown {
		public shutdown() {
			Timer timer = new Timer();

			Calendar cal = Calendar.getInstance();

			cal.set(Calendar.HOUR_OF_DAY, 19); // 시간 : 24 시간 기준
			cal.set(Calendar.MINUTE, 50); // 분

			long day = 1000 * 60 * 60 * 24; // 하루마다
			timer.scheduleAtFixedRate(new MyTask(), cal.getTime(), day);
		}
	}

	class MyTask extends TimerTask {
		public void run() {
			new WarningGUI();
		}
	}

	class TimerThread extends Thread {
		@Override
		public void run() {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm:ss");
			boolean timeflag = false;
			while (true) {
				if (logout) {
					System.out.println("로그아웃 : 유저");
					break;
				}
				long time = System.currentTimeMillis() - 1000 * 60 * 60 * 9;
				long checkTime = (time - user.getStartTimeCalc());
				String useTime = dayTime.format(new Date(checkTime));
				timeLb.setText(useTime);
				timeLb.updateUI();
				if (useTime.substring(6).equals("00") && !timeflag) {
					timeflag = true; // 00분에 가격 증가 한번만 실행
					UserLoginGUI.user.setTotalPrice(UserLoginGUI.user.getTotalPrice() + 20);
					moneyLb.setText(UserLoginGUI.user.getTotalPrice() + "");
					moneyLb.updateUI();
				}
				if (useTime.substring(6).equals("01")) {
					timeflag = false; // 00분인 동안 1번만 실행 시키기위해 01분까지 증가문 실행 못시키게 함
				}
				if (flag3) { // 주문하면 가격 증가
					moneyLb.setText(UserLoginGUI.user.getTotalPrice() + "");
					moneyLb.updateUI();
				}
			} // while문 종료
		}
	}
}
