package adminMain;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/*
 * by.jaein
 */
//ÀÌ¸§L :Label
//Tf : TextField
public class RightMainGUI extends JPanel {
	private JLabel usePCNumberL;
	private JLabel userNameL;
	private JLabel userIDL;

	private JLabel useTimeL;
	private JLabel totalPriceL;
	private JLabel addAmountL;

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

	public RightMainGUI() {
		setFocusable(true);
		requestFocus();

		usePCNumberL = new JLabel("23", new ImageIcon("../icon-157349_1280.png"), SwingConstants.CENTER);
		userNameL = new JLabel("¼Õ´Ô");
		userIDL = new JLabel("(2)");
		useTimeL = new JLabel("09 : 24");
		totalPriceL = new JLabel("120000¿ø");
		addAmountL = new JLabel("3000¿ø Ãß°¡");

		setLayout(null);
		setBorder(new TitledBorder(new LineBorder(Color.black)));

		usePCNumberL.setSize(50, 50);
		usePCNumberL.setLocation(10, 10);
		usePCNumberL.setHorizontalAlignment(JLabel.CENTER);
		usePCNumberL.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));

		userNameL.setSize(80, 30);
		userNameL.setLocation(80, 15);
		userNameL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		
		userIDL.setSize(30, 30);
		userIDL.setLocation(125, 15);
		userIDL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));

		useTimeL.setSize(80, 20);
		useTimeL.setLocation(83, 43);
		useTimeL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));

		totalPriceL.setSize(150, 30);
		totalPriceL.setLocation(0, 70);
		totalPriceL.setHorizontalAlignment(JLabel.CENTER);
		totalPriceL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 23));

		addAmountL.setSize(150, 15);
		addAmountL.setLocation(0, 103);
		addAmountL.setHorizontalAlignment(JLabel.CENTER);
		totalPriceL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));

		add(userNameL);
		add(usePCNumberL);
		add(userIDL);
		add(useTimeL);
		add(totalPriceL);
		add(addAmountL);

	}

}
