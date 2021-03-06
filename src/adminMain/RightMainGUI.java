package adminMain;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import AdminServer.User;
import db.UserDao;

public class RightMainGUI extends JPanel {
	private JLabel usePCNumberL;
	private JLabel userNameL;
	private JLabel userIDL;
	private JLabel userNumberL;
	private JLabel useTimeL;
	private JLabel totalPriceL;
	private JLabel addAmountL;
	private User user;

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

		usePCNumberL = new JLabel();

		userNameL = new JLabel();
		userNumberL = new JLabel();
		userIDL = new JLabel();
		useTimeL = new JLabel();
		totalPriceL = new JLabel();
		addAmountL = new JLabel();

		setLayout(null);

		usePCNumberL.setSize(50, 50);
		usePCNumberL.setLocation(10, 10);
		usePCNumberL.setHorizontalAlignment(JLabel.CENTER);
		usePCNumberL.setFont(new Font("���� ����", Font.BOLD, 30));

		userNameL.setSize(80, 30);
		userNameL.setLocation(80, 15);
		userNameL.setFont(new Font("���� ����", Font.PLAIN, 20));

		userNumberL.setSize(30, 30);
		userNumberL.setLocation(163, 17);
		userNumberL.setFont(new Font("���� ����", Font.PLAIN, 17));

		useTimeL.setSize(80, 20);
		useTimeL.setLocation(83, 43);
		useTimeL.setFont(new Font("���� ����", Font.PLAIN, 15));

		totalPriceL.setSize(150, 30);
		totalPriceL.setLocation(30, 70);
		totalPriceL.setHorizontalAlignment(JLabel.CENTER);
		totalPriceL.setFont(new Font("���� ����", Font.PLAIN, 23));

		addAmountL.setSize(150, 15);
		addAmountL.setLocation(30, 103);
		addAmountL.setHorizontalAlignment(JLabel.CENTER);
		totalPriceL.setFont(new Font("���� ����", Font.PLAIN, 15));

		setBorder(new TitledBorder(new LineBorder(Color.gray)));
		setBackground(Color.WHITE);
		add(userNameL);
		add(usePCNumberL);
		add(userNumberL);
		add(useTimeL);
		add(totalPriceL);
		add(addAmountL);
		
		setVisible(true);

	}

	public void setUserPanel(User user) {
		this.user = user;
		userNameL.setText(user.getName());
		userNumberL.setText(user.getUserNumber());
		addAmountL.setText("0��");
		totalPriceL.setText("0��");
		userIDL.setText(user.getUserID());
		usePCNumberL.setText(user.getSeatNumber()+1+"");
	} //�гο� ���� ����
	
	public void setLogoutPanel() {
		this.user = null;
		userNameL.setText("");
		userNumberL.setText("");
		addAmountL.setText("");
		totalPriceL.setText("");
		userIDL.setText("");
		useTimeL.setText("");
		setBackground(Color.WHITE);
		setBorder(new TitledBorder(new LineBorder(Color.gray)));
	}
}
