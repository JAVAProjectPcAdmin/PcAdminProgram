package orderFood;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import userLogin.UserClient;
import userLogin.UserLoginGUI;
import userUsingState.UserUsingStateGUI;

public class OrderGUI extends JFrame {
	private JPanel ramenPnl, drinkPnl, snackPnl, selectPnl, menuPnl, labelPnl;
	private JPanel wholePnl;
	private JButton orderBtn, cancleBtn;
	private JTabbedPane menuTab;
	private JLabel priceLbl;
	private MenuPnlGUI ramen[], drink[], snack[];
	private int total = 0;
	private int ramenPrice[] = { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000 };
	private int drinkPrice[] = { 700, 1000, 1000, 1000, 1000, 1000, 1000, 1000 };
	private int snackPrice[] = { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1200 };
	private String ramenName[] = { "간짬뽕", "너구리", "무파마", "사리곰탕", "새우탕", "신라면", "오징어짬뽕", "육개장" };
	private String drinkName[] = { "레쓰비", "밀키스", "스프라이트", "조지아", "코카콜라", "파워에이드", "핫식스", "환타 오렌지" };
	private String snackName[] = { "꼬깔콘", "도리토스", "스윙칩", "오잉", "오징어땅콩", "치토스", "포카칩 오리지널", "포카칩 어니언" };

	public OrderGUI(UserClient userclient) {

		wholePnl = new JPanel();
		ramenPnl = new JPanel();
		drinkPnl = new JPanel();
		snackPnl = new JPanel();
		selectPnl = new JPanel();
		menuPnl = new JPanel();
		labelPnl = new JPanel();

		// 메뉴탭
		menuTab = new JTabbedPane();
		menuTab.addTab("   라   면   류   ", ramenPnl);
		menuTab.addTab("   음   료   수   ", drinkPnl);
		menuTab.addTab("   스   낵   류   ", snackPnl);

		menuTab.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		menuTab.setTabPlacement(JTabbedPane.LEFT);

		menuPnl.setLayout(new BorderLayout());
		menuPnl.add(menuTab);
		menuPnl.setBackground(Color.WHITE);

		/// 총 사용금액
		priceLbl = new JLabel("<html><br>총 금액 : " + total + " 원<br><br></html>");
		priceLbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		labelPnl.add(priceLbl);
		labelPnl.setBackground(Color.WHITE);

		////////////////////////////////////////////////////////////////////////////////////////////////

		ramenPnl.setLayout(new GridLayout(2, 4));
		int i;
		ramen = new MenuPnlGUI[8];
		UpDownListener upDown;
		for (i = 0; i < ramen.length; i++) {
			ramen[i] = new MenuPnlGUI("food\\ramen" + (i + 1) + ".png");
			ramen[i].setSize(200, 250);
			ramenPnl.add(ramen[i]);
			upDown = new UpDownListener(ramen[i], i);
			ramen[i].upBtn.addActionListener(upDown);
			ramen[i].downBtn.addActionListener(upDown);
		}

		////////////////////////////////////////////////////////////////////////////////////////////////

		drinkPnl.setLayout(new GridLayout(2, 4));

		drink = new MenuPnlGUI[8];
		for (i = 0; i < drink.length; i++) {
			drink[i] = new MenuPnlGUI("food\\drink" + (i + 1) + ".png");
			drink[i].setSize(200, 250);
			drinkPnl.add(drink[i]);
			upDown = new UpDownListener(drink[i], i);
			drink[i].upBtn.addActionListener(upDown);
			drink[i].downBtn.addActionListener(upDown);
		}

		////////////////////////////////////////////////////////////////////////////////////////////////

		snackPnl.setLayout(new GridLayout(2, 4));

		snack = new MenuPnlGUI[8];
		for (i = 0; i < snack.length; i++) {
			snack[i] = new MenuPnlGUI("food\\snack" + (i + 1) + ".png");
			snack[i].setSize(200, 260);
			snackPnl.add(snack[i]);
			upDown = new UpDownListener(snack[i], i);
			snack[i].upBtn.addActionListener(upDown);
			snack[i].downBtn.addActionListener(upDown);
		}

		//////////////////////////////////////////////////////////////////////////////////////////////////
		// 결제, 취소
		orderBtn = new JButton("    주  문    ");

		orderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String order = "<html>";
				int price = 0;
				for (int i = 0; i < ramen.length; i++) {
					if (ramen[i].count > 0) {
						order += " - " + ramenName[i] + " " + ramen[i].count + "개" + "<br>";
						price += ramenPrice[i] * ramen[i].count;
					}
					if (drink[i].count > 0) {
						order += " - " + drinkName[i] + " " + drink[i].count + "개" + "<br>";
						price += drinkPrice[i] * drink[i].count;
					}
					if (snack[i].count > 0) {
						order += " - " + snackName[i] + " " + snack[i].count + "개" + "<br>";
						price += snackPrice[i] * snack[i].count;
					}
				}
				order += "</html>";
				System.out.println(order);
				UserLoginGUI.user.setOrder(order);
				UserLoginGUI.user.setAddPrice(UserLoginGUI.user.getAddPrice() + price);
				UserLoginGUI.user.setTotalPrice(UserLoginGUI.user.getTotalPrice() + price);
				userclient.setUser(UserLoginGUI.user);

				userclient.orderTOAdmin();
				System.out.println(UserLoginGUI.user.getOrder());

				UserUsingStateGUI.flag3 = false;
				dispose();
				JOptionPane.showMessageDialog(null, "   주문이 완료되었습니다.", "주문 완료", 1);
			}
		});

		cancleBtn = new JButton("    취  소    ");

		cancleBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserUsingStateGUI.flag3 = false;
				dispose();
			}
		});

		orderBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		cancleBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
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
		MenuPnlGUI o;
		int index;

		public UpDownListener(MenuPnlGUI o, int index) {
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
			priceLbl.setText("<html><br>총 금액 : " + total + " 원<br><br></html>");
		}
	}
}
