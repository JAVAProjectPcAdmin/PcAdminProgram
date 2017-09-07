package adminLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		registerAdminl = new JLabel("������ ���");
		adminIDl = new JLabel("* ������ ID");
		adminPWl = new JLabel("* ��й�ȣ");

		idSearchBtn = new JButton("�ߺ�Ȯ��");
		idSearchBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		idSearchBtn.setBounds(295, 93, 82, 30);
		idSearchBtn.addActionListener(new Listener());

		okBtn = new JButton("�� ��");
		okBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		okBtn.setBounds(115, 190, 70, 30);
		okBtn.addActionListener(new Listener());
		cancelBtn = new JButton("�� ��");
		cancelBtn.setFont(new Font("���� ���", Font.BOLD, 12));
		cancelBtn.setBounds(200, 190, 70, 30);
		cancelBtn.addActionListener(new Listener());

		idTf = new JTextField();
		idTf.setBounds(150, 93, 120, 30);
		pwf = new JPasswordField();
		pwf.setBounds(150, 133, 120, 30);

		registerAdminl.setFont(new Font("���� ���", Font.BOLD, 30));
		adminIDl.setFont(new Font("���� ���", Font.BOLD, 17));
		adminPWl.setFont(new Font("���� ���", Font.BOLD, 17));

		registerAdminl.setBounds(115, 20, 200, 50);
		adminIDl.setBounds(40, 90, 200, 30);
		adminPWl.setBounds(40, 130, 200, 30);

		add(idSearchBtn);
		add(okBtn);
		add(cancelBtn);
		add(idTf);
		add(pwf);
		add(registerAdminl);
		add(adminIDl);
		add(adminPWl);

		setLayout(null);

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
			String  result = null;

			if (e.getSource() == okBtn) {

				if (!idcheckFlag) {
					JOptionPane.showMessageDialog(null, "���̵� �ߺ��� Ȯ���ϼ���.");
				} else {
					AdminVO admin = new AdminVO();
					admin.setId(idTf.getText());
					admin.setPassword(new String(pwf.getPassword()));
					dao.AdminJoinInsert(admin);
					JOptionPane.showMessageDialog(null, "�����ڰ� ��ϵǾ����ϴ�.");
					dispose();

				}
			} else if (e.getSource() == idSearchBtn) {
				result = dao.AdminIdSelect(idTf.getText());
				if (result.equals(null)) {
					JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�.");
					idcheckFlag = true;
				}else if(!result.equals(null)) {
					JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.");
					idcheckFlag = false;
				}
			}
			else if(e.getSource()==cancelBtn) {
				dispose();
			}
		}
	}

}