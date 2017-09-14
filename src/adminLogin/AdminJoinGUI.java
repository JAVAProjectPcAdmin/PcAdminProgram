package adminLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.AdminDao;
import db.AdminVO;

public class AdminJoinGUI extends JFrame {
	private JLabel registerAdminl, adminIDl, adminPWl;
	private JButton idSearchBtn, okBtn, cancelBtn;
	private JTextField idTf;
	private JPasswordField pwf;
	private boolean idcheckFlag = false;

	public AdminJoinGUI() {
		registerAdminl = new JLabel(new ImageIcon("images//addadminlabel.png"));
		registerAdminl.setFont(new Font("���� ���", Font.BOLD, 30));
		registerAdminl.setBounds(50, -5, 300, 100);

		adminIDl = new JLabel("������ ID");
		adminIDl.setFont(new Font("���� ���", Font.BOLD, 17));
		adminIDl.setBounds(60, 100, 200, 30);

		adminPWl = new JLabel("��й�ȣ");
		adminPWl.setFont(new Font("���� ���", Font.BOLD, 17));
		adminPWl.setBounds(60, 140, 200, 30);

		idTf = new JTextField();
		idTf.setBounds(150, 103, 120, 30);

		pwf = new JPasswordField();
		pwf.setBounds(150, 143, 120, 30);

		idSearchBtn = new JButton(new ImageIcon("images//adminfind.png"));
		idSearchBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		idSearchBtn.addActionListener(new Listener());
		idSearchBtn.setBounds(285, 100, 82, 30);

		okBtn = new JButton(new ImageIcon("images//addadmin.png"));
		okBtn.setFont(new Font("���� ���", Font.BOLD, 10));
		okBtn.addActionListener(new Listener());
		okBtn.setBounds(120, 200, 70, 26);

		cancelBtn = new JButton(new ImageIcon("images//cancelbtn.png"));
		cancelBtn.setFont(new Font("���� ���", Font.BOLD, 10));
		cancelBtn.addActionListener(new Listener());
		cancelBtn.setBounds(205, 200, 70, 26);

		add(idSearchBtn);
		add(okBtn);
		add(cancelBtn);
		add(idTf);
		add(pwf);
		add(registerAdminl);
		add(adminIDl);
		add(adminPWl);

		setLayout(null);

		setUndecorated(true);
		getContentPane().setBackground(Color.WHITE);
		setTitle("������ ���");
		setSize(400, 280);
		setResizable(false);
		setLocation(400, 300);
		setVisible(true);
	}

	class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AdminDao dao = new AdminDao();
			String result = null;
			String pw = new String(pwf.getPassword(), 0, pwf.getPassword().length);

			if (e.getSource() == okBtn) {

				if (idcheckFlag) {
					if (idTf.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "������ ID�� �Է����ּ���.");
					}

					else if (pw.equals("")) {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
					} else {

						AdminVO admin = new AdminVO();
						admin.setId(idTf.getText());
						admin.setPassword(new String(pwf.getPassword()));
						dao.AdminJoinInsert(admin);
						JOptionPane.showMessageDialog(null, "�����ڰ� ��ϵǾ����ϴ�.");

						dispose();
					}

				} else {
					if (idTf.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "������ ID�� �Է����ּ���.");
					} else {
						JOptionPane.showMessageDialog(null, "���̵� �ߺ��� Ȯ���ϼ���.");
					}
				}

			} else if (e.getSource() == idSearchBtn) {
				result = dao.AdminIdSelect(idTf.getText());
				if (result == null) {
					if (idTf.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "������ ID�� �Է����ּ���.");

					} else {
						JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�.");
					}
					idcheckFlag = true;
				} else if (!result.equals(null)) {
					JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.");
					idcheckFlag = false;
				}
			} else if (e.getSource() == cancelBtn) {
				dispose();
			}
		}
	}

}