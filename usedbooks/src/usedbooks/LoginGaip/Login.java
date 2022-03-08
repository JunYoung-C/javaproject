package usedbooks.LoginGaip;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import usedbooks.DbConnect;
import usedbooks.SellBuy.UsedBooksSellBuy;
import usedbooks.footer.TeamInfoLabel;

public class Login extends JFrame implements ActionListener {

	UsedBooksSellBuy usedBooksSellBuy = new UsedBooksSellBuy("온라인 중고책 서점 메인");
	
	DbConnect db = new DbConnect();

	Container cp;

	JButton btnLogin, btnGaip;

	JLabel labelId, labelPassword, titleLabel, cooperateLabel;

	JTextField textId, textPassword, textLabel;

	// Font color
	Color c_title = new Color(49, 130, 246);
	Color c_white = new Color(255, 255, 255);
	Color c_black = new Color(27, 29, 31);
	Color c_gray = new Color(169, 169, 169);

	Color c_button = new Color(23, 133, 242);

	// Title Font
	Font f_title = new Font("맑은 고딕", Font.BOLD, 30);

	// Text Font
	Font f_text = new Font("맑은 고딕", Font.BOLD, 15);
	Font f_smallText = new Font("맑은 고딕", Font.PLAIN, 11);
	Font f_bigText = new Font("맑은 고딕", Font.PLAIN, 23);

	// memberGaip frame
	MemberGaip memberframe = new MemberGaip("회원가입");
	
	public Login(String title) {
		super(title);
		cp = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 630, 450);
		cp.setBackground(new Color(255, 255, 255));
		this.initDesign();
		this.setVisible(true);
	}

	public void initDesign() {
		this.setLayout(null);

		TeamInfoLabel.set(cooperateLabel, f_smallText, c_gray, this);
		
		textId = new JTextField();
		textId.setBounds(170, 130, 300, 30);
		textId.setForeground(Color.green);
		this.add(textId);

		textPassword = new JTextField();
		textPassword.setBounds(170, 175, 300, 30);
		textPassword.setForeground(Color.green);
		this.add(textPassword);

		titleLabel = new JLabel("온라인 중고책 서점", JLabel.CENTER);
		titleLabel.setBounds(110, 50, 400, 40);
		titleLabel.setOpaque(false); // 투명
		titleLabel.setFont(f_title);
		titleLabel.setForeground(c_title);
		this.add(titleLabel);

		labelId = new JLabel("ID", JLabel.LEFT);
		labelId.setBounds(140, 130, 70, 30);
		labelId.setFont(f_bigText);
		// labelId.setFont(new Font("맑은 고딕",Font.PLAIN, 12));
		this.add(labelId);

		labelPassword = new JLabel("PW", JLabel.LEFT);
		labelPassword.setBounds(130, 175, 70, 30);
		labelPassword.setFont(f_bigText);
		// labelPassword.setFont(new Font("맑은 고딕",Font.PLAIN, 12));
		this.add(labelPassword);

		// 로그인 버튼
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(140, 320, 150, 50);
		btnLogin.setFont(f_text);
		btnLogin.setBackground(c_button);
		btnLogin.setForeground(c_white);
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		this.add(btnLogin);
		btnLogin.addActionListener(this);

		// 회원가입 버튼
		btnGaip = new JButton("회원가입");
		btnGaip.setBounds(340, 320, 150, 50);
		btnGaip.setFont(f_text);
		btnGaip.setBackground(c_button);
		btnGaip.setForeground(c_white);
		btnGaip.setBorderPainted(false);
		btnGaip.setFocusPainted(false);
		this.add(btnGaip);
		btnGaip.addActionListener(this);

		setVisible(true);

		// 새로 생성된 회원가입 프레임 버튼
		memberframe.btnGaip.addActionListener(this);
		memberframe.btnIdcheck.addActionListener(this);

	}

	// 로그인 하기
	public Long findByLoginIdAndPassword(String id, String password) {
		Long findMemberid = null;
		Connection conn = db.getOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member where id=? and password=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				findMemberid = rs.getLong("member_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}

		return findMemberid;
	}

	// 버튼 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		String loginId = textId.getText();
		String loginPassword = textPassword.getText();

		Object ob = e.getSource();

		if (ob == btnLogin) {
			if (loginId.trim().length() < 1) {
				JOptionPane.showMessageDialog(this, "ID를 입력하세요");
				textId.requestFocus();
				return;
			} else if (loginPassword.trim().length() < 1) {
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
				textPassword.requestFocus();
				return;
			}
			
			Long findMemberId =findByLoginIdAndPassword(loginId, loginPassword);
			if (findMemberId != null) {
				JOptionPane.showMessageDialog(this, "로그인이 되었습니다");
				usedBooksSellBuy.memberId = findMemberId;
				System.out.println(usedBooksSellBuy.memberId);
				usedBooksSellBuy.setVisible(true);
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "아이디 비밀번호가 틀립니다");
			}

			
		}else if (ob == btnGaip) {
			memberframe.setVisible(true);
		} 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login("로그인 화면");
	}

}
