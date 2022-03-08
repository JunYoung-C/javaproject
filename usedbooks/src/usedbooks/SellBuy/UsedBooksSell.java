package usedbooks.SellBuy;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import usedbooks.DbConnect;

public class UsedBooksSell extends JFrame implements ActionListener {
	DbConnect db = new DbConnect();
	Container cp;
	JLabel titleLabel, Lb_information, Lb_name, Lb_author, Lb_publication_date, Lb_price, Lb_won, Lb_quality;
	JButton btnRegister, btnBack;
	JTextField Tf_information, Tf_name, Tf_author, Tf_publication_date, Tf_price;
	JRadioButton[] qualityRadioButton = new JRadioButton[4];
	String[] qualityNames = { "최상", "상", "중", "하" };
	String memberId = null;

	public UsedBooksSell(String title) {

		super(title);
		cp = this.getContentPane();

		// 프레임
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 630, 450);
		cp.setBackground(new Color(255, 255, 255));
		this.initDesign();
		this.setVisible(false);

	}

	// 디자인
	public void initDesign() {

		this.setLayout(null);
		// Title Font
		Font f_title = new Font("맑은 고딕", Font.BOLD, 30); // ...더 굵은폰트로 수정하기

		// Text Font
		Font f_text = new Font("맑은 고딕", Font.BOLD, 15);
		Font f_smallText = new Font("맑은 고딕", Font.PLAIN, 11);

		// Font Color
		Color c_title = new Color(49, 130, 246);
		Color c_white = new Color(255, 255, 255);
		Color c_black = new Color(27, 29, 31);
		Color c_gray = new Color(169, 169, 169);

		// Button Color
		Color c_button = new Color(23, 133, 242);

		// JLabel
		titleLabel = new JLabel("온라인 중고책 서점", JLabel.CENTER);
		Lb_information = new JLabel("\"등록할 책의 정보를 입력하세요.\"");
		Lb_name = new JLabel("제목");
		Lb_author = new JLabel("저자");
		Lb_publication_date = new JLabel("출간일");
		Lb_price = new JLabel("판매가");
		Lb_won = new JLabel("원");
		Lb_quality = new JLabel("상품상태");

		// JButton
		btnBack = new JButton("돌아가기");
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// 등록하기 ActionListener
		btnRegister = new JButton("등록하기");

		btnRegister.addActionListener(this);

		// JTextfield
		Tf_name = new JTextField();
		Tf_author = new JTextField();
		Tf_publication_date = new JTextField();
		Tf_price = new JTextField();

		// JRadioButton
		ButtonGroup btnGroup = new ButtonGroup();
		int qualityX = 255;
		for (int i = 0; i < qualityRadioButton.length; i++) {
			qualityRadioButton[i] = new JRadioButton(qualityNames[i]);
			btnGroup.add(qualityRadioButton[i]);

			qualityRadioButton[i].setBounds(qualityX, 273, 55, 30);
			qualityX += 50;
			qualityRadioButton[i].setOpaque(false);
			qualityRadioButton[i].setFont(f_text);
			qualityRadioButton[i].setForeground(c_black);
			this.add(qualityRadioButton[i]);

		}

		// 모든 컴포넌트 위치 지정
		// JLabel
		titleLabel.setBounds(110, 50, 400, 40); // 위치,크기 통일
		titleLabel.setOpaque(false); // 투명
		titleLabel.setFont(f_title);
		titleLabel.setForeground(c_title);
		this.add(titleLabel);

		Lb_information.setBounds(190, 100, 250, 30);
		Lb_information.setOpaque(false); // 투명
		Lb_information.setFont(f_text);
		Lb_information.setForeground(c_gray);
		this.add(Lb_information);

		Lb_name.setBounds(190, 132, 90, 30);
		Lb_name.setOpaque(false); // 투명
		Lb_name.setFont(f_text);
		Lb_name.setForeground(c_black);
		this.add(Lb_name);

		Lb_author.setBounds(190, 167, 90, 30);
		Lb_author.setOpaque(false); // 투명
		Lb_author.setFont(f_text);
		Lb_author.setForeground(c_black);
		this.add(Lb_author);

		Lb_publication_date.setBounds(190, 202, 90, 30);
		Lb_publication_date.setOpaque(false); // 투명
		Lb_publication_date.setFont(f_text);
		Lb_publication_date.setForeground(c_black);
		this.add(Lb_publication_date);

		Lb_price.setBounds(190, 237, 90, 30);
		Lb_price.setOpaque(false); // 투명
		Lb_price.setFont(f_text);
		Lb_price.setForeground(c_black);
		this.add(Lb_price);

		Lb_won.setBounds(425, 237, 90, 30);
		Lb_won.setOpaque(false); // 투명
		Lb_won.setFont(f_text);
		Lb_won.setForeground(c_black);
		this.add(Lb_won);

		Lb_quality.setBounds(190, 272, 90, 30);
		Lb_quality.setOpaque(false); // 투명
		Lb_quality.setFont(f_text);
		Lb_quality.setForeground(c_black);
		this.add(Lb_quality);

		// JButton
		btnBack.setBounds(140, 320, 150, 50);
		btnBack.setFont(f_text);
		btnBack.setBackground(c_button);
		btnBack.setForeground(c_white);
		btnBack.setBorderPainted(false);
		btnBack.setFocusPainted(false);
		this.add(btnBack);

		btnRegister.setBounds(340, 320, 150, 50);
		btnRegister.setFont(f_text);
		btnRegister.setBackground(c_button);
		btnRegister.setForeground(c_white);
		btnRegister.setBorderPainted(false);
		btnRegister.setFocusPainted(false);
		this.add(btnRegister);

		// JTextfield
		Tf_name.setBounds(240, 135, 180, 25);
		this.add(Tf_name);

		Tf_author.setBounds(240, 170, 180, 25);
		this.add(Tf_author);

		Tf_publication_date.setBounds(240, 205, 180, 25);
		this.add(Tf_publication_date);

		Tf_price.setBounds(240, 240, 180, 25);
		this.add(Tf_price);
	}

	public void save(String name, String author, String publication_date, int price, String quality) {
		String sql = "insert into book values(book_seq.nextval,?, ?,?,?,?,?)";
		Connection conn = db.getOracle();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			// ? 바인딩
			pstmt.setString(1, memberId);
			pstmt.setString(2, name);
			pstmt.setString(3, author);
			pstmt.setString(4, publication_date);
			pstmt.setInt(5, price);
			pstmt.setString(6, quality);

			pstmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == btnRegister) {

			String name = Tf_name.getText();
			String author = Tf_author.getText();
			String publication_date = Tf_publication_date.getText();
//	        int price = Integer.parseInt(Tf_price.getText());
			String priceStr = Tf_price.getText();
			String quality = null;
			for (int i = 0; i < qualityRadioButton.length; i++) {
				if (qualityRadioButton[i].isSelected()) {
					quality = qualityRadioButton[i].getText();
				}
			}

			if (name.trim().length() == 0 || author.trim().length() == 0 || publication_date.trim().length() == 0
					|| priceStr.trim().length() == 0 || quality == null) {
				JOptionPane.showMessageDialog(this, "비어있는 행이 있습니다.");
				return;
			}

			save(name, author, publication_date, Integer.parseInt(priceStr), quality);
			JOptionPane.showMessageDialog(btnRegister, "판매할 책 등록이 완료되었습니다.");
			// 초기화
			Tf_name.setText("");
			Tf_author.setText("");
			Tf_publication_date.setText("");
			Tf_price.setText("");
			for (int i = 0; i < qualityRadioButton.length; i++) {
				qualityRadioButton[i].setSelected(true);
				if (qualityRadioButton[i].isSelected()) {

				}
			}
		}
	}

	public static void main(String[] args) {

		new UsedBooksSell("판매폼 테스트");

	}

}
