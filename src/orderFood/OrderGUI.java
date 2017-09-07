package orderFood;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import userUsingState.UserUsingStateGUI;

public class OrderGUI extends JFrame {
	private JPanel ramenPnl, drinkPnl, snackPnl, selectPnl, menuPnl, labelPnl;
	private JPanel wholePnl;
	private JButton orderBtn, cancleBtn;
	private JTabbedPane menuTab;
	private JLabel priceLbl;
	private MenuPnlGui ramen[], drink[], snack[];
	private int total = 0;
	private int ramenPrice[] = { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000 };
	private int drinkPrice[] = { 700, 1000, 1000, 1000, 1000, 1000, 1000, 1000 };
	private int snackPrice[] = { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1200 };
	private String ramenName[] = {"��«��", "�ʱ���", "���ĸ�", "�縮����", "������", "�Ŷ��", "��¡��«��", "������"};
	private String drinkName[] = {"������", "��Ű��", "��������Ʈ", "������", "��ī�ݶ�", "�Ŀ����̵�", "�ֽĽ�", "ȯŸ ������"};
	private String snackName[] = {"������", "�����佺", "����Ĩ", "����", "��¡���", "ġ�佺", "��īĨ ��������", "��īĨ ��Ͼ�"};
	
	public OrderGUI() {

		wholePnl = new JPanel();

		ramenPnl = new JPanel();
		drinkPnl = new JPanel();
		snackPnl = new JPanel();
		selectPnl = new JPanel();
		menuPnl = new JPanel();
		labelPnl = new JPanel();

		// �޴���
		menuTab = new JTabbedPane();
		menuTab.addTab("   ��   ��   ��   ", ramenPnl);
		menuTab.addTab("   ��   ��   ��   ", drinkPnl);
		menuTab.addTab("   ��   ��   ��   ", snackPnl);

		menuTab.setFont(new Font("���� ���", Font.BOLD, 25));
		menuTab.setTabPlacement(JTabbedPane.LEFT);

		menuPnl.setLayout(new BorderLayout());
		menuPnl.add(menuTab);
		menuPnl.setBackground(Color.WHITE);

		/// �� ���ݾ�
		priceLbl = new JLabel("<html><br>�� �ݾ� : " + total + " ��<br><br></html>");
		priceLbl.setFont(new Font("���� ���", Font.BOLD, 20));

		labelPnl.add(priceLbl);
		labelPnl.setBackground(Color.WHITE);

		////////////////////////////////////////////////////////////////////////////////////////////////

		ramenPnl.setLayout(new GridLayout(2, 4));
		int i;
		ramen = new MenuPnlGui[8];
		UpDownListener upDown;
		for (i = 0; i < ramen.length; i++) {
			ramen[i] = new MenuPnlGui("food\\ramen" + (i + 1) + ".png");
			ramen[i].setSize(200, 250);
			ramenPnl.add(ramen[i]);
			upDown = new UpDownListener(ramen[i], i);
			ramen[i].upBtn.addActionListener(upDown);
			ramen[i].downBtn.addActionListener(upDown);
		}

		////////////////////////////////////////////////////////////////////////////////////////////////

		drinkPnl.setLayout(new GridLayout(2, 4));

		drink = new MenuPnlGui[8];
		for (i = 0; i < drink.length; i++) {
			drink[i] = new MenuPnlGui("food\\drink" + (i + 1) + ".png");
			drink[i].setSize(200, 250);
			drinkPnl.add(drink[i]);
			upDown = new UpDownListener(drink[i], i);
			drink[i].upBtn.addActionListener(upDown);
			drink[i].downBtn.addActionListener(upDown);
		}

		////////////////////////////////////////////////////////////////////////////////////////////////

		snackPnl.setLayout(new GridLayout(2, 4));

		snack = new MenuPnlGui[8];
		for (i = 0; i < snack.length; i++) {
			snack[i] = new MenuPnlGui("food\\snack" + (i + 1) + ".png");
			snack[i].setSize(200, 260);
			snackPnl.add(snack[i]);
			upDown = new UpDownListener(snack[i], i);
			snack[i].upBtn.addActionListener(upDown);
			snack[i].downBtn.addActionListener(upDown);
		}

		/////////////////////////////////////////////////////////////////////////////////////////////////////////

		// ����, ���
		orderBtn = new JButton("    ��  ��    ");
		
		
		orderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String order = "";
				for(int i=0; i<ramen.length; i++) {
					if(ramen[i].count > 0) {
						order += " - " + ramenName[i] + " " + ramen[i].count + "��" + ","; 
					}
					if(drink[i].count > 0) {
						order += " - " + drinkName[i] + " " + drink[i].count + "��" + ","; 
					}
					if(snack[i].count > 0) {
						order += " - " + snackName[i] + " " + snack[i].count + "��" + ","; 
					}
				}
				AdminOrderGUI g = new AdminOrderGUI(order);
			}
		});

		cancleBtn = new JButton("    ��  ��    ");

		cancleBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserUsingStateGUI.flag3 = false;
				dispose();
			}
		});

		orderBtn.setFont(new Font("���� ���", Font.BOLD, 25));
		cancleBtn.setFont(new Font("���� ���", Font.BOLD, 25));
		orderBtn.setBackground(Color.WHITE);
		cancleBtn.setBackground(Color.WHITE);
		orderBtn.setFocusPainted(false);
		cancleBtn.setFocusPainted(false);

		selectPnl.setBackground(Color.WHITE);
		selectPnl.add(orderBtn);
		selectPnl.add(cancleBtn);

		////////////////////////////////////////////////////////////////////////////////////////////////////////////

		wholePnl.setLayout(new BoxLayout(wholePnl, BoxLayout.Y_AXIS));

		wholePnl.add(menuPnl);
		wholePnl.add(labelPnl);
		wholePnl.add(selectPnl);
		add(wholePnl);

		setSize(1000, 800);
		setResizable(false);
		setAlwaysOnTop(true);
		setBackground(Color.WHITE);
		setUndecorated(true);
		setLocation(150, 100);
		setVisible(true);
	}

	class UpDownListener implements ActionListener {
		MenuPnlGui o;
		int index;

		public UpDownListener(MenuPnlGui o, int index) {
			this.o = o;
			this.index = index;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selected = (JButton) e.getSource();
			if (selected == o.upBtn) {
				if (ramen[index] == o) {
					total += ramenPrice[index];
				} else if (drink[index] == o) {
					total += drinkPrice[index];
				} else if (snack[index] == o) {
					total += snackPrice[index];
				}
				o.count++;
			} else if (selected == o.downBtn) {
				if (o.count > 0) {
					if (ramen[index] == o) {
						total -= ramenPrice[index];
					} else if (drink[index] == o) {
						total -= drinkPrice[index];
					} else if (snack[index] == o) {
						total -= snackPrice[index];
					}
					o.count--;
				}
			}
			o.countLbl.setText(o.count + "");
			priceLbl.setText("<html><br>�� �ݾ� : " + total + " ��<br><br></html>");
		}
	}

	public static void main(String[] args) {
		OrderGUI orderGui = new OrderGUI();
	}
}
