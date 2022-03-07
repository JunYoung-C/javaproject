package usedbooks.purchase.view;

import java.awt.Color;
import java.awt.Container;
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

  public BookInfoView(String title) {
    super(title);
    cp = this.getContentPane();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(100, 100, 700, 700);
    cp.setBackground(new Color(100, 100, 100));

    initDesign();

    this.setVisible(true);
  }

  private void initDesign() {
    this.setLayout(null);

    titleLabel = new JLabel("중고책 온라인 서점");
    titleLabel.setBounds(250, 30, 200, 50);
    this.add(titleLabel);

    setSearchArea();
    setBookTableArea();
    setSelectAndReturnButton();

    writeAllBook();
  }

  private void setSearchArea() {
    searchLabel = new JLabel("책 이름");
    searchTextField = new JTextField();
    searchButton = new JButton("확인");

    searchLabel.setBounds(50, 100, 60, 40);
    searchTextField.setBounds(160, 100, 300, 40);
    searchButton.setBounds(500, 100, 60, 40);

    this.add(searchLabel);
    this.add(searchTextField);
    this.add(searchButton);

    searchButton.addActionListener(this);
  }

  private void setBookTableArea() {
    String[] bookFieldnames = {"번호", "책 이름", "지은이", "출판 날짜", "판매가", "상품 상태"};
    booksTableModel = new DefaultTableModel(bookFieldnames, 0);
    booksTable = new JTable(booksTableModel);
    JScrollPane booksScrollpane = new JScrollPane(booksTable);
    booksScrollpane.setBounds(50, 150, 500, 400);
    this.add(booksScrollpane);
  }

  private void setSelectAndReturnButton() {
    selectButton = new JButton("선택");
    returnButton = new JButton("돌아가기");

    selectButton.setBounds(150, 600, 100, 40);
    returnButton.setBounds(300, 600, 100, 40);

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
      booksTableModel.setRowCount(0);
      for (Book book : bookRepository.findAllByName(name)) {
        booksTableModel.addRow(getStringData(book));
      }
    } else if (ob == selectButton) {
      if (booksTable.getSelectedRow() == -1) {
        JOptionPane.showMessageDialog(this, "테이블을 선택해 주세요!");
        return;
      }
      Long selectedBookId =
          Long.parseLong(booksTableModel.getValueAt(booksTable.getSelectedRow(), 0).toString());
      Long selectedMemberId = bookRepository.findById(selectedBookId).getMemberId();
      Member selectedMember = memberRepository.findById(selectedMemberId);

      perchaseview.changeSellerInfo(selectedBookId, selectedMember.getName(),
          selectedMember.getPhoneNumber());
      perchaseview.setVisible(true);
    } else if (ob == returnButton) {
      setVisible(false);
    }
  }

  public static void main(String[] args) {
    new BookInfoView("책 정보");
  }


}
