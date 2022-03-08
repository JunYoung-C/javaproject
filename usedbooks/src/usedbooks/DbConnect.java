package usedbooks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {

  //URL
  static final String ORACLE_LOCAL="jdbc:oracle:thin:@localhost:1521:XE";
  
  //Connection
  public Connection getOracle() {
    Connection conn=null;
    
    try {
      conn=DriverManager.getConnection(ORACLE_LOCAL,"usedbooks","1234");
    } catch (SQLException e) {
      System.out.println("오라클 서버 연결 성공"+e.getMessage());
    }
    return conn;
  }
  
  //Close...select sql(Statement)
  public void dbClose(ResultSet rs,Statement stmt, Connection conn) {
    
      try {
        if(rs!=null)rs.close();
        if(stmt!=null)stmt.close();
        if(conn!=null)conn.close();
        
      } catch (SQLException e) {
        e.printStackTrace();
      }
    
  }
  //Close...sql(Statement)  
  public void dbClose(Statement stmt, Connection conn) {
    
    try {
      if(stmt!=null)stmt.close();
      if(conn!=null)conn.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }
  //Close...select...sql(PrepareStatement)
  public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
    
    try {
      if(rs!=null)rs.close();
      if(pstmt!=null)pstmt.close();
      if(conn!=null)conn.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }
  //Close
  public void dbClose(PreparedStatement pstmt, Connection conn) {
    
    try {
      if(pstmt!=null)pstmt.close();
      if(conn!=null)conn.close();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }
}
