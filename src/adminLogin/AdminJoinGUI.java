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
		adminIDl = new JLabel("관리자 ID");
		adminPWl = new JLabel("비밀번호");
		idTf = new JTextField();
		pwf = new JPasswordField();
		
		idSearchBtn = new JButton(new ImageIcon("images//adminfind.png"));
		okBtn = new JButton(new ImageIcon("images//addadmin.png"));
		cancelBtn = new JButton(new ImageIcon("images//cancelbtn.png"));
		
		idSearchBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		okBtn.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		cancelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 10));

		idSearchBtn.addActionListener(new Listener());
		okBtn.addActionListener(new Listener());
		cancelBtn.addActionListener(new Listener());

		registerAdminl.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		adminIDl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		adminPWl.setFont(new Font("맑은 고딕", Font.BOLD, 17));

		okBtn.setBounds(120, 200, 70, 26);
		cancelBtn.setBounds(205, 200, 70, 26);
		idSearchBtn.setBounds(285, 100, 82, 30);
		idTf.setBounds(150, 103, 120, 30);
		pwf.setBounds(150, 143, 120, 30);
		registerAdminl.setBounds(50, -5, 300, 100);
		adminIDl.setBounds(60, 100, 200, 30);
		adminPWl.setBounds(60, 140, 200, 30);

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
		setTitle("관리자 등록");
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
						JOptionPane.showMessageDialog(null, "관리자 ID를 입력해주세요.");
					}

					else if (pw.equals("")) {
						JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
					} else {

						AdminVO admin = new AdminVO();
						admin.setId(idTf.getText());
						admin.setPassword(new String(pwf.getPassword()));
						dao.AdminJoinInsert(admin);
						JOptionPane.showMessageDialog(null, "관리자가 등록되었습니다.");

						dispose();
					}

				} else {
					if (idTf.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "관리자 ID를 입력해주세요.");
					} else {
						JOptionPane.showMessageDialog(null, "아이디 중복을 확인하세요.");
					}
				}

			} else if (e.getSource() == idSearchBtn) {
				result = dao.AdminIdSelect(idTf.getText());
				if (result == null) {
					if (idTf.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "관리자 ID를 입력해주세요.");

					} else {
						JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
					}
					idcheckFlag = true;
				} else if (!result.equals(null)) {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
					idcheckFlag = false;
				}
			} else if (e.getSource() == cancelBtn) {
				dispose();
			}
		}
	}

}