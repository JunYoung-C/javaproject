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

public class MemberGaip extends JFrame implements ActionListener {
	DbConnect db = new DbConnect();

	Container cp;
	// 라벨
	JLabel titleId, titlePassword, titleName;
	JLabel titleBirth, titlePhoneNumber, titleAddress;
	JLabel titleLabel;
	// 텍스트
	JTextField tfId, tfPassword, tfName;
	JTextField tfBirth, tfPhoneNumber, tfAddress;

	JButton btnGaip, btnIdcheck;

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

	public MemberGaip(String title) {
		super(title);
		cp = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 630, 450);
		cp.setBackground(new Color(255, 255, 255));
		this.initDesign();
		// this.setVisible(true);
	}

	public void initDesign() {
		this.setLayout(null);

		// label
		titleLabel = new JLabel("온라인 중고책 서점", JLabel.CENTER);
		titleLabel.setBounds(110, 50, 400, 40);
		titleLabel.setOpaque(false); // 투명
		titleLabel.setFont(f_title);
		titleLabel.setForeground(c_title);
		this.add(titleLabel);

		titleId = new JLabel("아이디");
		titleId.setBounds(190, 100, 250, 30);
		this.add(titleId);

		titlePassword = new JLabel("비밀번호");
		titlePassword.setBounds(190, 132, 90, 30);
		this.add(titlePassword);

		titleName = new JLabel("이름");
		titleName.setBounds(190, 167, 90, 30);
		this.add(titleName);

		titleBirth = new JLabel("생일");
		titleBirth.setBounds(190, 202, 90, 30);
		this.add(titleBirth);

		titlePhoneNumber = new JLabel("전화번호");
		titlePhoneNumber.setBounds(190, 237, 90, 30);
		this.add(titlePhoneNumber);

		titleAddress = new JLabel("주소");
		titleAddress.setBounds(190, 272, 90, 30);
		this.add(titleAddress);

		// tf
		tfId = new JTextField(4);
		tfId.setBounds(245, 105, 180, 25);
		this.add(tfId);

		tfPassword = new JTextField(4);
		tfPassword.setBounds(245, 138, 180, 25);
		this.add(tfPassword);

		tfName = new JTextField(4);
		tfName.setBounds(245, 172, 180, 25);
		this.add(tfName);

		tfBirth = new JTextField(4);
		tfBirth.setBounds(245, 206, 180, 25);
		this.add(tfBirth);

		tfPhoneNumber = new JTextField(4);
		tfPhoneNumber.setBounds(245, 240, 180, 25);
		this.add(tfPhoneNumber);

		tfAddress = new JTextField(4);
		tfAddress.setBounds(245, 280, 180, 25);
		this.add(tfAddress);

		btnIdcheck = new JButton("중복확인");
		btnIdcheck.setBounds(430, 105, 100, 25);
		btnIdcheck.addActionListener(this);
		this.add(btnIdcheck);

		btnGaip = new JButton("회원가입");
		btnGaip.setBounds(240, 330, 150, 50);
		btnGaip.setFont(f_text);
		btnGaip.setBackground(c_button);
		btnGaip.setForeground(c_white);
		btnGaip.setBorderPainted(false);
		btnGaip.setFocusPainted(false);
		btnGaip.addActionListener(this);
		this.add(btnGaip);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		// 중복
		if (ob == btnIdcheck) {
			if (tfId.getText().trim().length() < 1) {
				JOptionPane.showMessageDialog(this, "ID를 입력하세요");
				tfId.requestFocus();
				return;
			}
			if (findById(tfId.getText()) == 0) {
				JOptionPane.showMessageDialog(this, "사용가능한 아이디입니다");
			} else {
				JOptionPane.showMessageDialog(this, "중복된 아이디입니다");
			}
		} else if (ob == btnGaip) {
			String id = tfId.getText();
			String password = tfPassword.getText();
			String name = tfName.getText();
			String birth = tfBirth.getText();
			String phoneNumber = tfPhoneNumber.getText();
			String address = tfAddress.getText();

			if (id.trim().length() == 0 || password.trim().length() == 0 || name.trim().length() == 0
					|| birth.trim().length() == 0 || phoneNumber.trim().length() == 0 || address.trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "비어있는 행이 있습니다.");
				return;
			}

			insertMember();
			
			JOptionPane.showMessageDialog(this, "회원가입이 되었습니다");

			// 초기화면
			tfId.setText("");
			tfPassword.setText("");
			tfName.setText("");
			tfBirth.setText("");
			tfPhoneNumber.setText("");
			tfAddress.setText("");

			// 회원가입 추가프레임 사라지게
			setVisible(false);
		}
	}

// 회원가입 중복 메서드
	public int findById(String id) {
		Connection conn = db.getOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select count(*) cnt from member where id=?";

		int number = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			rs.next();

			number = rs.getInt("cnt");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}
		return number;
	}
	
	public void insertMember() {
		String id = tfId.getText();
		String password = tfPassword.getText();
		String name = tfName.getText();
		String birth = tfBirth.getText();
		String phoneNumber = tfPhoneNumber.getText();
		String address = tfAddress.getText();

		String sql = "insert into member values(member_seq.nextval, ?,?,?,?,?,?)";

		Connection conn = db.getOracle();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			// 바인딩
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, phoneNumber);
			pstmt.setString(6, address);

			// 실행
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}
}