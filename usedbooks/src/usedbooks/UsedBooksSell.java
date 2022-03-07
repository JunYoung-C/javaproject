package usedbooks;

import java.awt.Color;
import java.awt.Container;
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

public class UsedBooksSell extends JFrame {
  DbConnect db = new DbConnect();
  Container cp;
  JLabel titleLabel, Lb_information, Lb_name, Lb_author, Lb_publication_date, Lb_price, Lb_won,
      Lb_quality;
  JButton btnRegister, btnBack, btnLogout;
  JTextField Tf_information, Tf_name, Tf_author, Tf_publication_date, Tf_price;
  JRadioButton[] qualityRadioButton = new JRadioButton[4];
  String[] qualityNames = {"최상", "상", "중", "하"};
  String memberId = null;

  public UsedBooksSell(String title) {

    super(title);
    cp = this.getContentPane();

    // 프레임
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(100, 100, 310, 500);
    cp.setBackground(new Color(255, 130, 150));
    this.initDesign();
    this.setVisible(true); // 잠깐 닫아둔거임(//)

  }

  // 디자인
  public void initDesign() {

    this.setLayout(null);

    // JLabel
    titleLabel = new JLabel("중고책 온라인 서점");
    Lb_information = new JLabel("등록할 책의 정보를 입력하세요.");
    Lb_name = new JLabel("책이름");
    Lb_author = new JLabel("지은이");
    Lb_publication_date = new JLabel("출판날짜");
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
    
    btnRegister = new JButton("등록하기");
    btnRegister.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String name = Tf_name.getText();
        String author = Tf_author.getText();
        String publication_date = Tf_publication_date.getText();
        int price = Integer.parseInt(Tf_price.getText()); // 왜 int가 안되는걸까..?
        String quality = null;
        for (int i = 0; i < qualityRadioButton.length; i++) {
          if (qualityRadioButton[i].isSelected()) {
            quality = qualityRadioButton[i].getText();
          }
        }

        save(name, author, publication_date, price, quality);
        
      }
    });
    
    
    btnLogout = new JButton("로그아웃");

    // JTextfield
    Tf_name = new JTextField();
    Tf_author = new JTextField();
    Tf_publication_date = new JTextField();
    Tf_price = new JTextField();

    // JRadioButton
    ButtonGroup btnGroup = new ButtonGroup();
    int qualityX = 100;
    for (int i = 0; i < qualityRadioButton.length; i++) {
      qualityRadioButton[i] = new JRadioButton(qualityNames[i]);
      btnGroup.add(qualityRadioButton[i]);

      qualityRadioButton[i].setBounds(qualityX, 210, 50, 30);
      qualityX += 40;
      qualityRadioButton[i].setOpaque(false);
      this.add(qualityRadioButton[i]);

    }

    // 모든 컴포넌트 위치 지정
    // JLabel
    titleLabel.setBounds(100, 50, 150, 30);
    this.add(titleLabel);

    Lb_information.setBounds(60, 70, 200, 30);
    this.add(Lb_information);

    Lb_name.setBounds(50, 100, 90, 30);
    this.add(Lb_name);

    Lb_author.setBounds(50, 130, 90, 30);
    this.add(Lb_author);

    Lb_publication_date.setBounds(50, 160, 90, 30);
    this.add(Lb_publication_date);

    Lb_price.setBounds(50, 190, 90, 30);
    this.add(Lb_price);

    Lb_won.setBounds(230, 185, 90, 30);
    this.add(Lb_won);

    Lb_quality.setBounds(50, 210, 90, 30);
    this.add(Lb_quality);

    // JButton...중복체크없애기(라디오버튼 그룹)


    btnBack.setBounds(50, 400, 90, 30);
    this.add(btnBack);

    btnRegister.setBounds(150, 400, 90, 30);
    this.add(btnRegister);

    btnLogout.setBounds(200, 10, 90, 30);
    this.add(btnLogout);

    // JTextfield
    Tf_name.setBounds(110, 100, 120, 20);
    this.add(Tf_name);

    Tf_author.setBounds(110, 130, 120, 20);
    this.add(Tf_author);

    Tf_publication_date.setBounds(110, 160, 120, 20);
    this.add(Tf_publication_date);

    Tf_price.setBounds(110, 190, 120, 20);
    this.add(Tf_price);
  }

  public void save(String name, String author, String publication_date, int price, String quality) {
    String sql = "insert into book values(book_seq.nextval,?, ?,?,?,?,?)"; // 해결하면 이걸로 쓰기
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
      pstmt.setString(6, quality); // 위에서 해결하고 수정하기 ???

      pstmt.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      db.dbClose(pstmt, conn);
    }
  }

  public static void main(String[] args) {

    new UsedBooksSell("판매폼 테스트");

  }

}
