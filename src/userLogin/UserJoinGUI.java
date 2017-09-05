package userLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import db.UserDao;
import db.UserVO;

public class UserJoinGUI extends JFrame {

	private JLabel joinLbl, idLbl, nameLbl, pwLbl, pwConfirmLbl, regiNumLbl, phLbl, mailLbl, addLbl, atLbl, minusLbl1,
			minusLbl2, minusLbl3, pwNoticeLbl;
	private JTextField idTx, nameTx, regiNumTx1, phTx1, phTx2, mailTx1, mailTx2, addTx;
	private JPasswordField pwTx, pwConfirmTx, regiNumTx2;
	private JButton idCheckBtn, joinBtn, cancelBtn;
	private JComboBox phCombo;

	private String[] phNum = { "010", "02", "031", "032", "033", "041", "043", "042", "044", "051", "052", "053", "054",
			"055", "061", "062", "063", "064", "070" };

	private boolean idCheckFlag = false; // false�̸� ���̵� �ߺ�Ȯ���� ���߰ų� �ߺ��� ���̵�//true �� ȸ������ ��
	private boolean pwCheckFlag = false;

	public UserJoinGUI() {
		joinLbl = new JLabel("ȸ������");
		idLbl = new JLabel("*  �� �� ��");
		nameLbl = new JLabel("*  ��  ��");
		pwLbl = new JLabel("*  ��й�ȣ");
		pwConfirmLbl = new JLabel("*  ��й�ȣ Ȯ��");
		regiNumLbl = new JLabel("*  �ֹι�ȣ");
		phLbl = new JLabel("    ��ȭ��ȣ");
		mailLbl = new JLabel("    E-Mail");
		addLbl = new JLabel("    �� ��");
		atLbl = new JLabel("@");
		pwNoticeLbl = new JLabel("(�ִ� 12��)");

		minusLbl1 = new JLabel("-");
		minusLbl2 = new JLabel("-");
		minusLbl3 = new JLabel("-");

		idTx = new JTextField();
		nameTx = new JTextField();
		pwTx = new JPasswordField();
		pwConfirmTx = new JPasswordField();
		regiNumTx1 = new JTextField();
		regiNumTx2 = new JPasswordField();
		phTx1 = new JTextField();
		phTx2 = new JTextField();
		mailTx1 = new JTextField();
		mailTx2 = new JTextField();
		addTx = new JTextField();

		idCheckBtn = new JButton("�ߺ�Ȯ��");
		joinBtn = new JButton("ȸ������");
		cancelBtn = new JButton("��      ��");

		phCombo = new JComboBox();
		for (int i = 0; i < phNum.length; i++) {
			phCombo.addItem(phNum[i]);
		}

		JoinListener listener = new JoinListener();

		setLayout(null);

		joinLbl.setFont(new Font("�������", Font.BOLD, 30));
		add(joinLbl);

		minusLbl1.setFont(new Font("Serif", Font.BOLD, 20));
		minusLbl2.setFont(new Font("Serif", Font.BOLD, 20));
		minusLbl3.setFont(new Font("Serif", Font.BOLD, 20));

		pwNoticeLbl.setEnabled(false);

		joinLbl.setBounds(125, 30, 150, 50);

		// ���̵�
		add(idLbl);
		idLbl.setBounds(15, 110, 100, 15);
		add(idTx);
		idTx.setBounds(110, 105, 150, 25);
		add(idCheckBtn);
		idCheckBtn.setBounds(270, 105, 90, 25);
		idCheckBtn.addActionListener(listener);

		// �̸�
		add(nameLbl);
		nameLbl.setBounds(15, 150, 100, 15);
		add(nameTx);
		nameTx.setBounds(110, 145, 150, 25);

		// ��й�ȣ
		add(pwLbl);
		pwLbl.setBounds(15, 190, 100, 15);
		add(pwTx);
		pwTx.setBounds(110, 185, 150, 25);
		add(pwNoticeLbl);
		pwNoticeLbl.setBounds(270, 185, 150, 30);

		// ��й�ȣ Ȯ��
		add(pwConfirmLbl);
		pwConfirmLbl.setBounds(15, 230, 100, 15);
		add(pwConfirmTx);
		pwConfirmTx.setBounds(110, 225, 150, 25);


		// �ֹι�ȣ
		add(regiNumLbl);
		regiNumLbl.setBounds(15, 270, 100, 15);
		add(regiNumTx1);
		regiNumTx1.setBounds(110, 265, 70, 25);
		add(minusLbl1);
		minusLbl1.setBounds(186, 265, 15, 15);
		add(regiNumTx2);
		regiNumTx2.setBounds(200, 265, 70, 25);

		// ��ȭ��ȣ
		add(phLbl);
		phLbl.setBounds(15, 310, 100, 15);
		add(phCombo);
		phCombo.setBounds(110, 305, 50, 25);
		add(minusLbl2);
		minusLbl2.setBounds(166, 305, 15, 15);
		add(phTx1);
		phTx1.setBounds(180, 305, 50, 25);
		add(minusLbl3);
		minusLbl3.setBounds(236, 305, 15, 15);
		add(phTx2);
		phTx2.setBounds(250, 305, 50, 25);

		// �̸���
		add(mailLbl);
		mailLbl.setBounds(15, 350, 100, 15);
		add(mailTx1);
		mailTx1.setBounds(110, 345, 80, 25);
		add(atLbl);
		atLbl.setBounds(196, 350, 15, 15);
		add(mailTx2);
		mailTx2.setBounds(217, 345, 90, 25);

		// �ּ�
		add(addLbl);
		addLbl.setBounds(15, 390, 100, 15);
		add(addTx);
		addTx.setBounds(110, 385, 200, 25);

		// ��ư
		add(joinBtn);
		joinBtn.setBounds(100, 450, 90, 30);
		joinBtn.addActionListener(listener);
		
		add(cancelBtn);
		cancelBtn.setBounds(200, 450, 90, 30);
		cancelBtn.addActionListener(listener);

		getContentPane().setBackground(Color.WHITE);
		setTitle("ȸ������");
		setSize(400, 560);
		setResizable(false);
		setLocation(400, 300);
		setVisible(true);
	}

	class JoinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			UserDao dao = new UserDao();
			int result = -1;
			if (arg0.getSource() == joinBtn) {
				
				//��й�ȣ Ȯ�� �κ�
				if (pwTx.getPassword().length != pwConfirmTx.getPassword().length) {
					pwCheckFlag = false;
				} else {
					for (int i = 0; i < pwTx.getPassword().length; i++) {
						if (pwTx.getPassword()[i] == pwConfirmTx.getPassword()[i]) {
							pwCheckFlag = true;
						} else {
							pwCheckFlag = false;
							break;
						}
					}
				}

				if (!idCheckFlag) { //���̵� �ߺ�
					JOptionPane.showMessageDialog(null, "���̵� �ߺ��� Ȯ�����ּ���", "���̵� ����", JOptionPane.OK_OPTION);
				} else if (!pwCheckFlag) { //��й�ȣ ����
					JOptionPane.showMessageDialog(null, "�н����尡 ���� �ʽ��ϴ�.", "�н����� ����", JOptionPane.OK_OPTION);
				} else {
					UserVO user = new UserVO();
					user.setId(idTx.getText());
					user.setPassword(new String(pwTx.getPassword())); // �н������ char�迭�� ��ȯ ��
					user.setName(nameTx.getText());
					String regiNum = (regiNumTx1.getText()) + (new String(regiNumTx2.getPassword()));
					user.setResidentNumber(regiNum);
					String phone = phCombo.getSelectedItem().toString() + phTx1.getText() + phTx2.getText();
					user.setPhone(phone);
					user.setEmailAddress(mailTx1.getText() + "@" + mailTx2.getText());
					user.setAddress(addTx.getText());
					dao.UserJoinInsert(user);
					JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.", "ȸ������ �Ϸ�", JOptionPane.OK_OPTION);
					dispose();
				}
			} else if (arg0.getSource() == idCheckBtn) {
				result = dao.UserIdSelect(idTx.getText());
				if (result <= 0) {
					System.out.println("�����ִ� ���̵�");
					JOptionPane.showMessageDialog(null, "��밡���� ���̵� �Դϴ�.");
					idCheckFlag = true;
				} else if (result > 0) {
					System.out.println("�ߺ��� ���̵�");
					JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.", "�ߺ�Ȯ��", JOptionPane.OK_OPTION);
					idCheckFlag = false;
				}
			} else if (arg0.getSource() == cancelBtn) {
				dispose();
			}
		}
	}
}
