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
  JButton selectButton, returnButton;
  
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
    
  }

  public static void main(String[] args) {
    new BookView("책 구매하기");
  }
}
