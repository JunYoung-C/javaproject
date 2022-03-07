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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import usedbooks.purchase.domain.Book;
import usedbooks.purchase.repository.BookRepository;

public class BookInfoView extends JFrame {
  private final BookRepository bookRepository = new BookRepository();

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

    // 검색 관련
    setSearchArea();

    // 책 테이블 관련
    setBookTableArea();

    // 책선택, 돌아가기 버튼
    setSelectAndReturnButton();

    booksTableModel.setRowCount(0);
    for (Book book : bookRepository.findAll()) {
      booksTableModel.addRow(getStringData(book));
    }
  }

  private void setSelectAndReturnButton() {
    selectButton = new JButton("선택");
    returnButton = new JButton("돌아가기");

    selectButton.setBounds(150, 600, 100, 40);
    returnButton.setBounds(300, 600, 100, 40);

    this.add(selectButton);
    this.add(returnButton);
    
    selectButton.addActionListener(null);
    returnButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
      }
    });
  }

  private void setBookTableArea() {
    String[] bookFieldnames = {"번호", "책 이름", "지은이", "출판 날짜", "판매가", "상품 상태"};
    booksTableModel = new DefaultTableModel(bookFieldnames, 0);
    booksTable = new JTable(booksTableModel);
    JScrollPane booksScrollpane = new JScrollPane(booksTable);
    booksScrollpane.setBounds(50, 150, 500, 400);
    this.add(booksScrollpane);
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
    
    searchButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        String name = searchTextField.getText();
        searchTextField.setText("");
        booksTableModel.setRowCount(0);
        for (Book book : bookRepository.findByName(name)) {
          booksTableModel.addRow(getStringData(book));
        }
      }
    });
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

  public static void main(String[] args) {
    new BookInfoView("책 구매하기");
  }
}
