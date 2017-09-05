package orderFood;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

//size 200, 260

public class MenuPnlGui extends JPanel {
	private JButton upBtn, downBtn;
	private JLabel countLbl, menuImgLbl;
	public int count = 0;
	
	public MenuPnlGui(String menuImg) {
		Icon upBtnImg = new ImageIcon("up.png");
		Icon downbtnImg = new ImageIcon("down.png");
		Icon menuLblImg = new ImageIcon(menuImg);
		
		upBtn = new JButton(upBtnImg);
		downBtn = new JButton(downbtnImg);
		
		countLbl = new JLabel(Integer.toString(count));
		countLbl.setFont(new Font("��������", Font.BOLD, 20));
		
		menuImgLbl = new JLabel(menuLblImg, SwingConstants.CENTER); 
		
		setLayout(null);
		
		menuImgLbl.setBounds(13, 10, 160, 170);
		downBtn.setBounds(50, 190, 20, 20);
		countLbl.setBounds(85, 185, 30, 30);
		upBtn.setBounds(110, 190, 20, 20);
		
		setBackground(Color.WHITE);
		
		add(menuImgLbl);
		add(downBtn);
		add(countLbl);
		add(upBtn);
	}
}