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
		registerAdminl = new JLabel("관리자 등록");
		adminIDl = new JLabel("* 관리자 ID");
		adminPWl = new JLabel("* 비밀번호");

		idSearchBtn = new JButton("중복확인");
		idSearchBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		idSearchBtn.setBounds(295, 93, 82, 30);
		idSearchBtn.addActionListener(new Listener());

		okBtn = new JButton("등 록");
		okBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		okBtn.setBounds(115, 190, 70, 30);
		okBtn.addActionListener(new Listener());
		cancelBtn = new JButton("취 소");
		cancelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		cancelBtn.setBounds(200, 190, 70, 30);
		cancelBtn.addActionListener(new Listener());

		idTf = new JTextField();
		idTf.setBounds(150, 93, 120, 30);
		pwf = new JPasswordField();
		pwf.setBounds(150, 133, 120, 30);

		registerAdminl.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		adminIDl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		adminPWl.setFont(new Font("맑은 고딕", Font.BOLD, 17));

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
			String  result = null;

			if (e.getSource() == okBtn) {

				if (!idcheckFlag) {
					JOptionPane.showMessageDialog(null, "아이디 중복을 확인하세요.");
				} else {
					AdminVO admin = new AdminVO();
					admin.setId(idTf.getText());
					admin.setPassword(new String(pwf.getPassword()));
					dao.AdminJoinInsert(admin);
					JOptionPane.showMessageDialog(null, "관리자가 등록되었습니다.");
					dispose();

				}
			} else if (e.getSource() == idSearchBtn) {
				result = dao.AdminIdSelect(idTf.getText());
				if (result.equals(null)) {
					JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
					idcheckFlag = true;
				}else if(!result.equals(null)) {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");
					idcheckFlag = false;
				}
			}
			else if(e.getSource()==cancelBtn) {
				dispose();
			}
		}
	}

}