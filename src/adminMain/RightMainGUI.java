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

//ÀÌ¸§L :Label
//Tf : TextField
public class RightMainGUI extends JPanel {
	private JLabel userNumberL;
	private JLabel userNameL;
	private JLabel useTimeL;
	private JLabel totalPriceL;
	private JLabel addAmountL;
		
	public RightMainGUI() {
		setFocusable(true);
		requestFocus();
		
		userNumberL= new JLabel("23",new ImageIcon("../icon-157349_1280.png"),SwingConstants.CENTER);
		userNameL=new JLabel("¼Õ´Ô(2)");
		useTimeL = new JLabel("09 : 24");
		totalPriceL = new JLabel("120000¿ø");
		addAmountL=new JLabel("3000¿ø Ãß°¡");
		
		setLayout(null);
		setBorder(new TitledBorder(new LineBorder(Color.black)));
		
		userNumberL.setSize(50,50);
		userNumberL.setLocation(10,10);
		userNumberL.setHorizontalAlignment(JLabel.CENTER);
		userNumberL.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,30));
		
		userNameL.setSize(80,30);
		userNameL.setLocation(80,15);
		userNameL.setFont(new Font("¸¼Àº °íµñ",Font.PLAIN,20));
		
		useTimeL.setSize(80,20);
		useTimeL.setLocation(83,43);
		useTimeL.setFont(new Font("¸¼Àº °íµñ",Font.PLAIN,15));
		
		totalPriceL.setSize(150,30);
		totalPriceL.setLocation(0,70);
		totalPriceL.setHorizontalAlignment(JLabel.CENTER);
		totalPriceL.setFont(new Font("¸¼Àº °íµñ",Font.PLAIN,23));
		
		addAmountL.setSize(150,15);
		addAmountL.setLocation(0,103);
		addAmountL.setHorizontalAlignment(JLabel.CENTER);
		totalPriceL.setFont(new Font("¸¼Àº °íµñ",Font.PLAIN,15));
		
		
		add(userNameL);
		add(userNumberL);
		add(useTimeL);
		add(totalPriceL);
		add(addAmountL);		
		
	}
	

}
