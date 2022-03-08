package usedbooks.SellBuy;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import usedbooks.DbConnect;
import usedbooks.footer.TeamInfoLabel;
import usedbooks.purchase.view.BookInfoView;

public class UsedBooksSellBuy extends JFrame implements ActionListener {

	DbConnect db = new DbConnect();

	Container cp;
	DefaultTableModel model;
	JTable table;
	JButton btnSell, btnBuy; // btnSell 판매하기, btnBuy 구매하기
	JLabel titleLabel, cooperateLabel;

	// 판매하기 버튼 눌렀을때 UsedBooksSell 프레임 추가
	UsedBooksSell sellFrame = new UsedBooksSell("중고책 판매 등록");
	BookInfoView bookInfoView = new BookInfoView("중고책 리스트");

	public static Long memberId = null;

	public UsedBooksSellBuy(String title) {

		super(title);
		cp = this.getContentPane();

		// 프레임
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 630, 450); // JFrame 크기 통일
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
		titleLabel = new JLabel("온라인 중고책 서점", JLabel.CENTER); // 이름 온라인 중고책 서점으로 통일
		titleLabel.setBounds(110, 50, 400, 40); // 위치,크기 통일
		titleLabel.setOpaque(false); // 투명
		titleLabel.setFont(f_title);
		titleLabel.setForeground(c_title);
		this.add(titleLabel);

		TeamInfoLabel.set(cooperateLabel, f_smallText, c_gray, this);

		// JButton
		btnSell = new JButton("책 판매하기");
		btnSell.setBounds(110, 150, 150, 50);
		btnSell.setFont(f_text);
		btnSell.setBackground(c_button);
		btnSell.setForeground(c_white);
		btnSell.setBorderPainted(false);
		btnSell.setFocusPainted(false);

		btnSell.addActionListener(this);
		this.add(btnSell);

		btnBuy = new JButton("책 구매하기");
		btnBuy.setBounds(360, 150, 150, 50);
		btnBuy.setFont(f_text);
		btnBuy.setBackground(c_button);
		btnBuy.setForeground(c_white);
		btnBuy.setBorderPainted(false);
		btnBuy.setFocusPainted(false);

		btnBuy.addActionListener(this);
		this.add(btnBuy);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == btnSell) {
			sellFrame.setVisible(true);
		} else if (ob == btnBuy) {
			bookInfoView.writeAllBook();
			bookInfoView.setVisible(true);
		}
	}
}
