package usedbooks.LoginGaip;

import java.awt.Color;
import java.awt.Container;
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

	public MemberGaip(String title) {
		super(title);
		cp = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 100, 320, 370);
		cp.setBackground(new Color(255, 192, 203));
		this.initDesign();
		// this.setVisible(true);
	}

	public void initDesign() {
		this.setLayout(null);

		// label
		titleLabel = new JLabel("중고책 온라인 서점", JLabel.CENTER);
		titleLabel.setBounds(10, 10, 270, 20);
		this.add(titleLabel);

		titleId = new JLabel("아이디");
		titleId.setBounds(30, 30, 50, 30);
		this.add(titleId);

		titlePassword = new JLabel("비밀번호");
		titlePassword.setBounds(30, 60, 50, 30);
		this.add(titlePassword);

		titleName = new JLabel("이름");
		titleName.setBounds(30, 90, 50, 30);
		this.add(titleName);

		titleBirth = new JLabel("생일");
		titleBirth.setBounds(30, 120, 50, 30);
		this.add(titleBirth);

		titlePhoneNumber = new JLabel("전화번호");
		titlePhoneNumber.setBounds(30, 150, 50, 30);
		this.add(titlePhoneNumber);

		titleAddress = new JLabel("주소");
		titleAddress.setBounds(30, 180, 50, 30);
		this.add(titleAddress);

		// tf
		tfId = new JTextField(4);
		tfId.setBounds(90, 35, 120, 20);
		this.add(tfId);

		tfPassword = new JTextField(4);
		tfPassword.setBounds(90, 65, 120, 20);
		this.add(tfPassword);

		tfName = new JTextField(4);
		tfName.setBounds(90, 95, 120, 20);
		this.add(tfName);

		tfBirth = new JTextField(4);
		tfBirth.setBounds(90, 125, 120, 20);
		this.add(tfBirth);

		tfPhoneNumber = new JTextField(4);
		tfPhoneNumber.setBounds(90, 155, 120, 20);
		this.add(tfPhoneNumber);

		tfAddress = new JTextField(4);
		tfAddress.setBounds(90, 185, 120, 20);
		this.add(tfAddress);

		btnIdcheck = new JButton("중복확인");
		btnIdcheck.setBounds(220, 35, 85, 20);
		btnIdcheck.addActionListener(this);
		this.add(btnIdcheck);

		btnGaip = new JButton("회원가입");
		btnGaip.setBounds(90, 250, 100, 30);
		this.add(btnGaip);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == btnIdcheck) {
			if (findById(tfId.getText()) == 0) {
				JOptionPane.showMessageDialog(this, "사용가능한 아이디입니다");
			} else {
				JOptionPane.showMessageDialog(this, "중복된 아이디입니다");
			}
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
/*
 * public static void main(String[] args) { // TODO Auto-generated method stub
 * 
 * 
 * 
 * new MemberGaip("회원가입폼"); }
 */
}