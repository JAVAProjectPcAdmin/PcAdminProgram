package userLogin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import db.UserDao;
import db.UserVO;
/* UI 수정사항 : 아이디 패스워드 찾기에 이메일과 전화번호가 필요하여 이메일 전화번호까지 *(필수사항) 처리하였습니다.
 * 회원 가입 진행 사항 
 * 입력 정보 데이터 베이스로 넘기기 완료
 * 아이디 중복 확인 한번이라도 해야만 회원가입 가능
 * 중복확인 기능 완료
 * 비밀번호 두개가 같아야 회원가입 가능 - 실시간 아님 
 */
public class UserJoinGUI extends JFrame {

	private JLabel joinLbl, idLbl, nameLbl, pwLbl, pwConfirmLbl, regiNumLbl, phLbl, mailLbl, addLbl, atLbl, minusLbl1,
			minusLbl2, minusLbl3, pwNoticeLbl;
	private JTextField idTx, nameTx, regiNumTx1, phTx1, phTx2, mailTx1, mailTx2, addTx;
	private JPasswordField pwTx, pwConfirmTx, regiNumTx2;
	private JButton idCheckBtn, joinBtn, cancelBtn;
	private JComboBox phCombo;

	private String[] phNum = { "010", "02", "031", "032", "033", "041", "043", "042", "044", "051", "052", "053", "054",
			"055", "061", "062", "063", "064", "070" };

	private boolean idCheckFlag = false; //false이면 아이디 중복확인을 안했거나 중복된 아이디//true 면 회원가입 됨
	private boolean pwCheckFlag = false;
	
	public UserJoinGUI() {
		joinLbl = new JLabel("회원가입");
		idLbl = new JLabel("*  아 이 디");
		nameLbl = new JLabel("*  이  름");
		pwLbl = new JLabel("*  비밀번호");
		pwConfirmLbl = new JLabel("*  비밀번호 확인");
		regiNumLbl = new JLabel("*  주민번호");
		phLbl = new JLabel("*  전화번호");
		mailLbl = new JLabel("*  E-Mail");
		addLbl = new JLabel("   주 소");
		atLbl = new JLabel("@");
		pwNoticeLbl = new JLabel("(최대 12자)");

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

		idCheckBtn = new JButton("중복확인");
		joinBtn = new JButton("회원가입");
		cancelBtn = new JButton("취      소");

		phCombo = new JComboBox();
		for (int i = 0; i < phNum.length; i++) {
			phCombo.addItem(phNum[i]);
		}

		JoinListener listener = new JoinListener();

		setLayout(null);

		joinLbl.setFont(new Font("맑은고딕", Font.BOLD, 30));
		add(joinLbl);

		minusLbl1.setFont(new Font("Serif", Font.BOLD, 20));
		minusLbl2.setFont(new Font("Serif", Font.BOLD, 20));
		minusLbl3.setFont(new Font("Serif", Font.BOLD, 20));

		pwNoticeLbl.setEnabled(false);

		joinLbl.setBounds(125, 30, 150, 50);

		// 아이디
		add(idLbl);
		idLbl.setBounds(15, 110, 100, 15);
		add(idTx);
		idTx.setBounds(110, 105, 150, 25);
		add(idCheckBtn);
		idCheckBtn.setBounds(270, 105, 90, 25);
		idCheckBtn.addActionListener(listener);

		// 이름
		add(nameLbl);
		nameLbl.setBounds(15, 150, 100, 15);
		add(nameTx);
		nameTx.setBounds(110, 145, 150, 25);

		// 비밀번호
		add(pwLbl);
		pwLbl.setBounds(15, 190, 100, 15);
		add(pwTx);
		pwTx.setBounds(110, 185, 150, 25);
		add(pwNoticeLbl);
		pwNoticeLbl.setBounds(270, 185, 150, 30);

		// 비밀번호 확인
		add(pwConfirmLbl);
		pwConfirmLbl.setBounds(15, 230, 100, 15);
		add(pwConfirmTx);
		pwConfirmTx.setBounds(110, 225, 150, 25);
		
		

		// 주민번호
		add(regiNumLbl);
		regiNumLbl.setBounds(15, 270, 100, 15);
		add(regiNumTx1);
		regiNumTx1.setBounds(110, 265, 70, 25);
		add(minusLbl1);
		minusLbl1.setBounds(186, 265, 15, 15);
		add(regiNumTx2);
		regiNumTx2.setBounds(200, 265, 70, 25);

		// 전화번호
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

		// 이메일
		add(mailLbl);
		mailLbl.setBounds(15, 350, 100, 15);
		add(mailTx1);
		mailTx1.setBounds(110, 345, 80, 25);
		add(atLbl);
		atLbl.setBounds(196, 350, 15, 15);
		add(mailTx2);
		mailTx2.setBounds(217, 345, 90, 25);

		// 주소
		add(addLbl);
		addLbl.setBounds(15, 390, 100, 15);
		add(addTx);
		addTx.setBounds(110, 385, 200, 25);

		// 버튼
		add(joinBtn);
		joinBtn.setBounds(100, 450, 90, 30);
		joinBtn.addActionListener(listener);
		add(cancelBtn);
		cancelBtn.setBounds(200, 450, 90, 30);

		getContentPane().setBackground(Color.WHITE);
		setTitle("회원가입");
		setSize(400, 560);
		setResizable(false);
		setLocation(400, 300);
		setVisible(true);
	}

	class JoinListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			UserDao dao = new UserDao();
			int result = -1;
			if(pwTx.getPassword()==pwConfirmTx.getPassword()) {
				pwCheckFlag=true;
			}
			if (arg0.getSource() == joinBtn) {
				if (!idCheckFlag) {
					JOptionPane.showMessageDialog(null, "아이디 중복을 확인해주세요", "아이디 오류", JOptionPane.OK_OPTION);
				}
//					else if(!pwCheckFlag) {
//					JOptionPane.showMessageDialog(null, "패스워드가 같지 않습니다.", "패스워드 오류", JOptionPane.OK_OPTION);
//				}
					else{
					UserVO user = new UserVO();
					user.setId(idTx.getText());
					user.setPassword(new String(pwTx.getPassword()));// 패스워드는 char배열로 반환 됨
					user.setName(nameTx.getText());
					String regiNum = (regiNumTx1.getText()) + (new String(regiNumTx2.getPassword()));
					user.setResidentNumber(regiNum);
					String phone = phCombo.getSelectedItem().toString() + phTx1.getText() + phTx2.getText();
					user.setPhone(phone);
					user.setEmailAddress(mailTx1.getText() + "@" + mailTx2.getText());
					user.setAddress(addTx.getText());
					dao.UserJoinInsert(user);
				}
			} else if (arg0.getSource() == idCheckBtn) {

				result = dao.UserIdSelect(idTx.getText());
				if (result <= 0) {
					System.out.println("쓸수있는 아이디");
					JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.");
					idCheckFlag=true;
				} else if (result > 0) {
					System.out.println("중복된 아이디");
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.", "중복확인", JOptionPane.OK_OPTION);
					idCheckFlag=false;
				}
			}
		}	

	}
}
