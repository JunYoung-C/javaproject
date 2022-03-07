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

public class PurchaseView extends JFrame implements ActionListener {
  private final BookRepository bookRepository = new BookRepository();
  BookInfoView bookInfoView;
  
  Container cp;
  JLabel titleLabel, sellerNameLabel, sellerPhoneNumberLabel;
  JButton purchaseButton, returnButton;
  Long selectedBookId;
  
  public PurchaseView(String title, BookInfoView bookInfoView) {
    super(title);
    this.bookInfoView = bookInfoView;
    cp = this.getContentPane();

    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setBounds(200, 200, 450, 450);
    cp.setBackground(new Color(100, 100, 100));

    initDesign();

    this.setVisible(false);
    // this.setVisible(true);
  }

  private void initDesign() {
    this.setLayout(null);

    titleLabel = new JLabel("중고책 온라인 서점");
    titleLabel.setBounds(150, 20, 200, 50);
    this.add(titleLabel);

    sellerNameLabel = new JLabel("판매자 이름: ");
    sellerPhoneNumberLabel = new JLabel("핸드폰 번호: ");

    sellerNameLabel.setBounds(100, 100, 350, 50);
    sellerPhoneNumberLabel.setBounds(100, 150, 350, 50);

    this.add(sellerNameLabel);
    this.add(sellerPhoneNumberLabel);

    setPurchaseAndReturnButton();

  }

  public void changeSellerInfo(long bookId, String name, String phoneNumber) {
    selectedBookId = bookId;
    sellerNameLabel.setText("판매자 이름: " + name);
    sellerPhoneNumberLabel.setText("핸드폰 번호: " + phoneNumber);
  }

  private void setPurchaseAndReturnButton() {
    purchaseButton = new JButton("구매하기");
    returnButton = new JButton("돌아가기");

    purchaseButton.setBounds(100, 300, 100, 40);
    returnButton.setBounds(250, 300, 100, 40);

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
