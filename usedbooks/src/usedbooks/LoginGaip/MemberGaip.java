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
	
	
	//Font color
	Color c_title=new Color(49,130,246);
	Color c_white=new Color(255,255,255);
	Color c_black=new Color(27,29,31);
	Color c_gray=new Color(169,169,169);
	
	Color c_button=new Color(23,133,242);

	//Title Font
	Font f_title = new Font("맑은 고딕",Font.BOLD,30);

	//Text Font
	Font f_text = new Font("맑은 고딕",Font.BOLD,15);
	Font f_smallText = new Font("맑은 고딕",Font.PLAIN,11);
	


	public MemberGaip(String title) {
		super(title);
		cp = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 630, 450);
		cp.setBackground(new Color(255, 255, 255));
		this.initDesign();
		// this.setVisible(true);
	}

	public void initDesign() {
		this.setLayout(null);

		// label
		titleLabel = new JLabel("중고책 온라인 서점", JLabel.CENTER);
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