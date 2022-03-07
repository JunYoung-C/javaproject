package usedbooks;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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

public class UsedBooksSellBuy extends JFrame implements ItemListener, ActionListener {

  DbConnect db = new DbConnect();

  Container cp;
  DefaultTableModel mode;
  JTable table;
  JButton btnSell, btnBuy; // btnSell 판매하기, btnBuy 구매하기
  JLabel titleLabel;

  // 판매하기 버튼 눌렀을때 UsedBooksSell 프레임 추가
  UsedBooksSell sellFrame = new UsedBooksSell("판매하기");

  public UsedBooksSellBuy(String title) {

    super(title);
    cp = this.getContentPane();

    // 프레임
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(100, 100, 310, 500);
    cp.setBackground(new Color(255, 255, 200));
    this.initDesign();
    this.setVisible(true);
  }

  // 디자인
  public void initDesign() {
    this.setLayout(null);

    // JLabel
    titleLabel = new JLabel("중고책 온라인 서점", JLabel.CENTER);

    // JButton
    btnSell = new JButton("책 판매하기");
    btnBuy = new JButton("책 구매하기");

    // 모든 컴포넌트들(label,tf,cb,btn)의 위치지정하기
    // JLabel
    titleLabel.setBounds(75, 50, 150, 30);
    titleLabel.setOpaque(false); // 불투명
    this.add(titleLabel);

    // JButton
    btnSell.setBounds(90, 150, 120, 30);
    btnSell.addActionListener(this);
    this.add(btnSell);

    btnBuy.setBounds(90, 200, 120, 30);
    btnBuy.addActionListener(this);
    this.add(btnBuy);

  }


  public static void main(String[] args) {

    new UsedBooksSellBuy("판매 구매 메인창");

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();

    if (ob == btnSell) {
      sellFrame.setVisible(true);
    }

  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    // TODO Auto-generated method stub

  }

}
