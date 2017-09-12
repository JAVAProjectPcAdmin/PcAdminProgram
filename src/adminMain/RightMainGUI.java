package adminMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import AdminServer.AdminClient;
import AdminServer.User;
import db.UserDao;
import db.UserVO;
import flagment.Flagment;
import orderFood.AdminOrderGUI;
import userLogin.UserLoginGUI;

/*
 * by.jaein
 */
//ÀÌ¸§L :Label
//Tf : TextField
public class RightMainGUI extends JPanel {
	private JLabel usePCNumberL;
	private JLabel userNameL;
	private JLabel userIDL;
	private JLabel userNumberL;
	private JLabel useTimeL;
	private JLabel totalPriceL;
	private JLabel addAmountL;
	private User user;

	static String SEAT_NUMBER = "1";
	static int seat_num = Integer.parseInt(SEAT_NUMBER);
	public int userPanelIndex;

	UserDao userDao = new UserDao();

	public JLabel getUserNumberL() {
		return userNumberL;
	}

	public JLabel getUserIDL() {
		return userIDL;
	}

	public JLabel getUserNameL() {
		return userNameL;
	}

	public JLabel getUsePCNumberL() {
		return usePCNumberL;
	}

	public JLabel getUseTimeL() {
		return useTimeL;
	}

	public JLabel getTotalPriceL() {
		return totalPriceL;
	}

	public JLabel getAddAmountL() {
		return addAmountL;
	}
	
	public User getUser() {
		return user;
	}

	public RightMainGUI() {
		setFocusable(true);
		requestFocus();

		usePCNumberL = new JLabel(SEAT_NUMBER, new ImageIcon("../icon-157349_1280.png"), SwingConstants.CENTER);

		userNameL = new JLabel();
		userNumberL = new JLabel();
		userIDL = new JLabel();
		useTimeL = new JLabel();
		totalPriceL = new JLabel("0¿ø");
		addAmountL = new JLabel();

		setLayout(null);
		setBorder(new TitledBorder(new LineBorder(Color.black)));

		usePCNumberL.setSize(50, 50);
		usePCNumberL.setLocation(10, 10);
		usePCNumberL.setHorizontalAlignment(JLabel.CENTER);
		usePCNumberL.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));

		userNameL.setSize(80, 30);
		userNameL.setLocation(80, 15);
		userNameL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));

		userNumberL.setSize(30, 30);
		userNumberL.setLocation(163, 17);
		userNumberL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));

		useTimeL.setSize(80, 20);
		useTimeL.setLocation(83, 43);
		useTimeL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));

		totalPriceL.setSize(150, 30);
		totalPriceL.setLocation(30, 70);
		totalPriceL.setHorizontalAlignment(JLabel.CENTER);
		totalPriceL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 23));

		addAmountL.setSize(150, 15);
		addAmountL.setLocation(30, 103);
		addAmountL.setHorizontalAlignment(JLabel.CENTER);
		totalPriceL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));

		add(userNameL);
		add(usePCNumberL);
		add(userNumberL);
		add(useTimeL);
		add(totalPriceL);
		add(addAmountL);

	}

	public void setUserPanel(User user) {
		userNameL.setText(user.getName());
		userNumberL.setText(user.getUserNumber());
		addAmountL.setText("");
		userIDL.setText(user.getUserID());
		this.user = UserLoginGUI.user;
	}
}
