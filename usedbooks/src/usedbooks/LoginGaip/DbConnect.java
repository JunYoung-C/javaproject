package usedbooks.LoginGaip;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//����Ŭ�������� �ʿ�ø���
public class DbConnect {
	
	//url
	static final String ORACLE_LOCAL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//Connection
	public Connection getOracle() {
		Connection conn=null;
		
		try {
			conn=DriverManager.getConnection(ORACLE_LOCAL, "usedbooks", "1234");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("����Ŭ���� ����:"+e.getMessage());
		}
		
		return conn;
	}
	
	//close..select..�ϼ���sql(statement)
	public void dbClose(ResultSet rs,Statement stmt,Connection conn) {
	
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//close..select..�ϼ���sql(statement)
	public void dbClose(Statement stmt,Connection conn) {
		try {
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//close..select..�ϼ���sql(Preparestatement)
	public void dbClose(ResultSet rs,PreparedStatement pstmt, Connection conn) {
		
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	//close..select..�ϼ���sql(Preparestatement)
	public void dbClose(PreparedStatement ptmt, Connection conn) {
		try {
			if(ptmt!=null)ptmt.close();
			if(conn!=null)conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
