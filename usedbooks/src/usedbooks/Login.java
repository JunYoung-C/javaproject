package usedbooks;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.management.modelmbean.ModelMBean;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import org.w3c.dom.events.MouseEvent;

public class Login extends JFrame implements ActionListener{
  
  DbConnect db=new DbConnect();
  
  Container cp;
  
  JButton btnLogin,btnGaip;
  
  JLabel labelId,labelPassword,titleLabel; 
  
  JTextField textId,textPassword,textLabel;
  
  //memberGaip frame
  MemberGaip memberframe=new MemberGaip("회원가입");
  
  
  
  
  
  public Login(String title) {
    super(title);
    cp=this.getContentPane();
    
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(200, 100, 700, 500);
    cp.setBackground(new Color(255,192,203));
    this.initDesign();
    this.setVisible(true);
  }
  
  public void initDesign() {
    this.setLayout(null);
    
    textId=new JTextField();
    textId.setBounds(220, 120, 300, 30);
    textId.setForeground(Color.green);
    this.add(textId);
    
    textPassword=new JTextField();
    textPassword.setBounds(220, 220, 300, 30);
    textPassword.setForeground(Color.green);
    this.add(textPassword);
    
    titleLabel=new JLabel("중고책 온라인 서점",JLabel.CENTER);
    titleLabel.setBounds(220, 20, 250, 100);
    this.add(titleLabel);
    
    labelId=new JLabel("ID",JLabel.LEFT);
    labelId.setBounds(170, 120, 70, 30);
    this.add(labelId);
    
    labelPassword=new JLabel("PASSWORD",JLabel.LEFT);
    labelPassword.setBounds(120, 220, 70, 30);
    this.add(labelPassword);
    
    //로그인 버튼
    btnLogin=new JButton("로그인");
    btnLogin.setBounds(160, 350, 150, 50);
    this.add(btnLogin);
    btnLogin.addActionListener(this);
    
    //회원가입 버튼
    btnGaip=new JButton("회원가입");
    btnGaip.setBounds(380, 350, 150, 50);
    this.add(btnGaip);
    btnGaip.addActionListener(this);
    
    setVisible(true);
    
    //새로 생성된 회원가입 프레임 버튼
    memberframe.btnGaip.addActionListener(this);
    memberframe.btnIdcheck.addActionListener(this);
    
  }
  

  
  //btnGaip클릭시
  public void insertMember() {
   String id=memberframe.tfId.getText();
   String password=memberframe.tfPassword.getText();
   String name=memberframe.tfName.getText();
   String birth=memberframe.tfBirth.getText();
   //Date birth=memberframe.tfB
   String phoneNumber=memberframe.tfPhoneNumber.getText();
   String address=memberframe.tfAddress.getText();
   
   String sql="insert into member values(member_seq.nextval, ?,?,?,?,?,?)";
   
   
   Connection conn=db.getOracle();
   PreparedStatement pstmt=null;
   
   try {
    pstmt=conn.prepareStatement(sql);
    
    //바인딩
    pstmt.setString(1, id);
    pstmt.setString(2, password);
    pstmt.setString(3, name);
    pstmt.setString(4, birth);
    pstmt.setString(5, phoneNumber);
    pstmt.setString(6, address);
    
    //실행
    pstmt.execute();
    
  } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }finally {
    db.dbClose(pstmt, conn);
  }
  }
  
  //로그인 실패 메시지
  

  //회원가입 중복 메서드
  public int findById(String id) {
    Connection conn=db.getOracle();
    PreparedStatement pstmt=null;
    ResultSet rs = null;
        
    String sql="select count(*) cnt from member where id=?";
    
    int number=0;
    
    try {
      pstmt=conn.prepareStatement(sql);
      pstmt.setString(1, id);
      rs=pstmt.executeQuery();
      
      rs.next();
      
      
      number=rs.getInt("cnt");
      

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }finally {
      db.dbClose(rs, pstmt, conn);
    }
    return number;
  }


  //버튼 이벤트
  @Override
  public void actionPerformed(ActionEvent e) {
    String loginId=memberframe.tfId.getText();
    System.out.println(loginId);
    
    Object ob=e.getSource();
    
    if(ob==btnLogin) {
      
    }else if(ob==memberframe.btnIdcheck) {
     if (findById(loginId) == 0) {
       JOptionPane.showMessageDialog(this, "사용가능한아이디입니다");
     } else {
       JOptionPane.showMessageDialog(this, "중복된아이디입니다");
     }
    }
      
    
      
    
    if(ob==btnGaip) {
      memberframe.setVisible(true);
    }else if(ob==memberframe.btnGaip) {
      //회원가입 추가
      insertMember();
      
      //로그인 화면 다시 출력
      initDesign();
      
      //초기화면
      memberframe.tfId.setText("");
      memberframe.tfPassword.setText("");
      memberframe.tfName.setText("");
      memberframe.tfBirth.setText("");
      memberframe.tfPhoneNumber.setText("");
      memberframe.tfAddress.setText("");

      //회원가입 추가프레임 사라지게
      memberframe.setVisible(false);
      
    }
    
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    new Login("로그인,회원가입 화면");
  }
  
}
