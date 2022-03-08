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

public class BookInfoView extends JFrame implements ActionListener {
	private final BookRepository bookRepository = new BookRepository();
	private final MemberRepository memberRepository = new MemberRepository();
	PurchaseView perchaseview = new PurchaseView("책 구매하기", this);

	Container cp;
	JLabel titleLabel, searchLabel;
	JTable booksTable;
	DefaultTableModel booksTableModel;
	JTextField searchTextField;
	JButton searchButton, selectButton, returnButton;

	Font f_title = new Font("맑은 고딕", Font.BOLD, 30);
	Font f_text = new Font("맑은 고딕", Font.BOLD, 15);
	Font f_smallText = new Font("맑은 고딕", Font.PLAIN, 11);
	Color c_title = new Color(49, 130, 246);
	Color c_white = new Color(255, 255, 255);
	Color c_black = new Color(27, 29, 31);
	Color c_gray = new Color(169, 169, 169);
	Color c_button = new Color(23, 133, 242);

	public BookInfoView(String title) {
		super(title);
		cp = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 630, 700);
		cp.setBackground(new Color(255, 255, 255));

		initDesign();

		this.setVisible(false);
	}

	private void initDesign() {
		this.setLayout(null);

		setTitleLabel();
		setSearchArea();
		setBookTableArea();
		setSelectAndReturnButton();

		writeAllBook();
	}

	private void setTitleLabel() {
		titleLabel = new JLabel("온라인 중고책 서점", JLabel.CENTER); // 이름 온라인 중고책 서점으로 통일
		titleLabel.setBounds(110, 50, 400, 40); // 위치,크기 통일
		titleLabel.setOpaque(false); // 투명
		titleLabel.setFont(f_title);
		titleLabel.setForeground(c_title);
		this.add(titleLabel);
	}

	private void setSearchArea() {
		searchLabel = new JLabel("책 이름");
		searchTextField = new JTextField();
		searchButton = new JButton("확인");

		searchLabel.setBounds(50, 110, 60, 30);
		searchTextField.setBounds(120, 110, 340, 30);
		searchButton.setBounds(480, 110, 90, 30);

		searchLabel.setFont(f_text);
		searchTextField.setFont(f_text);
		searchButton.setFont(f_text);
		
		this.add(searchLabel);
		this.add(searchTextField);
		this.add(searchButton);

		searchButton.addActionListener(this);
	}

	private void setBookTableArea() {
		String[] bookFieldnames = { "번호", "책 이름", "지은이", "출판 날짜", "판매가", "상품 상태" };
		booksTableModel = new DefaultTableModel(bookFieldnames, 0);
		booksTable = new JTable(booksTableModel);
		booksTable.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		JScrollPane booksScrollpane = new JScrollPane(booksTable);
		booksScrollpane.setBounds(50, 150, 520, 370);
		this.add(booksScrollpane);
	}

	private void setSelectAndReturnButton() {
		selectButton = new JButton("선택");
		returnButton = new JButton("돌아가기");

		selectButton.setBounds(140, 570, 150, 50);
		returnButton.setBounds(340, 570, 150, 50);

		selectButton.setBackground(c_button);
		selectButton.setForeground(c_white);
		selectButton.setBorderPainted(false);
		selectButton.setFocusPainted(false);
		selectButton.setFont(f_text);

		returnButton.setBackground(c_button);
		returnButton.setForeground(c_white);
		returnButton.setBorderPainted(false);
		returnButton.setFocusPainted(false);
		returnButton.setFont(f_text);

		this.add(selectButton);
		this.add(returnButton);

		selectButton.addActionListener(this);
		returnButton.addActionListener(this);
	}

	public void writeAllBook() {
		booksTableModel.setRowCount(0);
		for (Book book : bookRepository.findAll()) {
			booksTableModel.addRow(getStringData(book));
		}
	}

	private void writeBooksByName(String name) {
		booksTableModel.setRowCount(0);
		for (Book book : bookRepository.findAllByName(name)) {
			booksTableModel.addRow(getStringData(book));
		}
	}

	private Vector<String> getStringData(Book book) {
		Vector<String> data = new Vector<String>();

		data.add(String.valueOf(book.getBookId()));
		data.add(book.getBookName());
		data.add(book.getAuthor());
		data.add(book.getPublicationDate().toString());
		data.add(String.valueOf(book.getPrice()) + "원");
		data.add(book.getQuality());

		return data;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == searchButton) {
			String name = searchTextField.getText();
			searchTextField.setText("");
			writeBooksByName(name);
		} else if (ob == selectButton) {
			if (booksTable.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "테이블을 선택해 주세요!");
				return;
			}
			Long selectedBookId = Long.parseLong(booksTableModel.getValueAt(booksTable.getSelectedRow(), 0).toString());
			Long selectedMemberId = bookRepository.findById(selectedBookId).getMemberId();
			Member selectedMember = memberRepository.findById(selectedMemberId);

			perchaseview.changeSellerInfo(selectedBookId, selectedMember.getName(), selectedMember.getPhoneNumber());
			perchaseview.setVisible(true);
		} else if (ob == returnButton) {
			setVisible(false);
		}
	}

}
