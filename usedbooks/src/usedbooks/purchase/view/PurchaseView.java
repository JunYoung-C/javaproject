package usedbooks.purchase.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import usedbooks.purchase.domain.Book;
import usedbooks.purchase.domain.Member;
import usedbooks.purchase.repository.BookRepository;
import usedbooks.purchase.repository.MemberRepository;

public class PurchaseView extends JFrame implements ActionListener {
	private final BookRepository bookRepository = new BookRepository();
	BookInfoView bookInfoView;

	Container cp;
	JLabel titleLabel, sellerNameLabel, sellerPhoneNumberLabel;
	JButton purchaseButton, returnButton;
	Long selectedBookId;

	Font f_title = new Font("맑은 고딕", Font.BOLD, 30);
	Font f_bigText = new Font("맑은 고딕", Font.PLAIN, 23);
	Font f_text = new Font("맑은 고딕", Font.BOLD, 15);
	Font f_smallText = new Font("맑은 고딕", Font.PLAIN, 11);
	Color c_title = new Color(49, 130, 246);
	Color c_white = new Color(255, 255, 255);
	Color c_black = new Color(27, 29, 31);
	Color c_gray = new Color(169, 169, 169);
	Color c_button = new Color(23, 133, 242);

	public PurchaseView(String title, BookInfoView bookInfoView) {
		super(title);
		this.bookInfoView = bookInfoView;
		cp = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setBounds(100, 100, 630, 450); 
		cp.setBackground(new Color(255, 255, 255));

		initDesign();

		this.setVisible(false);
		// this.setVisible(true);
	}

	private void initDesign() {
		this.setLayout(null);

		setTitleLabel();
		setSellerLabels();
		setPurchaseAndReturnButton();

	}

	private void setTitleLabel() {
		titleLabel = new JLabel("온라인 중고책 서점", JLabel.CENTER); // 이름 온라인 중고책 서점으로 통일
		titleLabel.setBounds(110, 50, 400, 40); // 위치,크기 통일
		titleLabel.setOpaque(false); // 투명
		titleLabel.setFont(f_title);
		titleLabel.setForeground(c_title);
		this.add(titleLabel);
	}

	private void setSellerLabels() {
		sellerNameLabel = new JLabel("판매자 이름: ");
		sellerPhoneNumberLabel = new JLabel("핸드폰 번호: ");

		sellerNameLabel.setBounds(160, 120, 350, 50);
		sellerPhoneNumberLabel.setBounds(160, 160, 350, 50);
		
		sellerNameLabel.setFont(f_bigText);
		sellerPhoneNumberLabel.setFont(f_bigText);
		
		this.add(sellerNameLabel);
		this.add(sellerPhoneNumberLabel);
	}

	public void changeSellerInfo(long bookId, String name, String phoneNumber) {
		selectedBookId = bookId;
		sellerNameLabel.setText("판매자 이름: " + name);
		sellerPhoneNumberLabel.setText("핸드폰 번호: " + phoneNumber);
	}

	private void setPurchaseAndReturnButton() {
		purchaseButton = new JButton("구매하기");
		returnButton = new JButton("돌아가기");

		purchaseButton.setBounds(140, 320, 150, 50);
		returnButton.setBounds(340, 320, 150, 50);

		purchaseButton.setBackground(c_button);
		purchaseButton.setForeground(c_white);
		purchaseButton.setBorderPainted(false);
		purchaseButton.setFocusPainted(false);
		purchaseButton.setFont(f_text);

		returnButton.setBackground(c_button);
		returnButton.setForeground(c_white);
		returnButton.setBorderPainted(false);
		returnButton.setFocusPainted(false);
		returnButton.setFont(f_text);
		
		this.add(purchaseButton);
		this.add(returnButton);

		purchaseButton.addActionListener(this);
		returnButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == purchaseButton) {
			this.setVisible(false);
			bookRepository.deleteById(selectedBookId);
			JOptionPane.showMessageDialog(this, "구매하였습니다.");
			bookInfoView.writeAllBook();
		} else if (ob == returnButton) {
			setVisible(false);
		}
	}

	// public static void main(String[] args) {
	// new PurchaseView("책 구매하기");
	// }
}
