package usedbooks.purchase.view;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookView extends JFrame {
  Container cp;
  JLabel titleLabel, searchLabel;
  JTable booksTable;
  DefaultTableModel booksTableModel;
  JTextField searchTextField;
  JButton searchButton, selectButton, returnButton;

  public BookView(String title) {
    super(title);
    cp = this.getContentPane();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(800, 100, 700, 500);
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
    searchLabel = new JLabel("책 검색");
    searchTextField = new JTextField();
    searchButton = new JButton("확인");

    searchLabel.setBounds(50, 100, 60, 40);
    searchTextField.setBounds(160, 100, 300, 40);
    searchButton.setBounds(500, 100, 60, 40);
    
    this.add(searchLabel);
    this.add(searchTextField);
    this.add(searchButton);

    
  }

  public static void main(String[] args) {
    new BookView("책 구매하기");
  }
}
