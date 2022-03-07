package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

	//local�� ��� ����Ŭ �����ϴ� ���...localhost�� �ڱ� ip�ּҸ� �־ �ȴ�.
	static final String ORACLE_LOCAL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//cloud�� ��� ����Ŭ �����ϴ� ���..dbname�� �����������
//	static final String ORACLE_CLOUD="jdbc:oracle:thin:@dbname_medium?TNS_ADMIN=C:/sist0117/util/OracleCloud";
	
	//Oracle���� SAWON���� �ҷ�����
	public void connectSawon() {
		
		Connection conn=null; //��� �޼ҵ帶�� �� ������Ѵ�. �޼ҵ� �ȿ��� �ʱⰪ �ִ� ������
		Statement stmt=null; //��� �޼ҵ帶�� �� ������Ѵ�.
		ResultSet rs=null; //��ȸ�� ���� ������ �����ص� ��...select���� �ݵ�� ResultSet��ü�� ���� �Ѵ�.
		
		//sql���� ���� ��� �ǰ� ���߿� ��� �ȴ�.
		String sql="select * from sawon order by num";
		
		try {
			conn=DriverManager.getConnection(ORACLE_LOCAL, "giyam", "a1234");
			System.out.println("����Ŭ ���� ���� ����!!");
			
			stmt=conn.createStatement(); //����������Ʈ��Ʈ�� ���� ���⿡ sql�� ����Ѵ�. ����ְ� ���߿� ���ε� �����ش�.
			rs=stmt.executeQuery(sql);
			System.out.println("***��� ����Ʈ***");
			System.out.println("�����ȣ\t�����\t����\t�μ���\t�޿�"); //label�� ���̰������ �̷������� �ָ� �ȴ�.
			System.out.println("----------------------------------------");
			
			//�������� �о�;� �ϹǷ� while������ �����´�.
			//rs.next()��ü�� ���ؼ� �������� next��ü�� �о���µ� ������ �о������.
			while(rs.next()) {
				
				//db�κ��� ������ ��������
				int num=rs.getInt("num"); //num�� db���� ���� column���̴�.
				String name=rs.getString("name");
				String gender=rs.getString("gender");
				String buseo=rs.getString("buseo");
				int pay=rs.getInt("pay");
				
				System.out.println(num+"\t"+name+"\t"+gender+"\t"+buseo+"\t"+pay);
				
				
			}
			
			
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("����Ŭ ���� ���� ����!!");
		}finally { //finally�� �ݳ��ϱ�
			try {
				if(rs!=null)rs.close(); //null�� �ƴ� ��쿡 �ݾƶ�� ��
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	//food + jumun ����غ���
	//�ֹ���ȣ   �ֹ���   ���ĸ�   ����   ������ּ�
	public void connectionBaedal() {
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String sql="select f.fno, name, food, price, addr from food f, jumun j where f.fno=j.fno";
		
		try {
			conn=DriverManager.getConnection(ORACLE_LOCAL, "giyam", "a1234");
//			System.out.println("����Ŭ ���� ���� ����!");
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			System.out.println("***��� �ֹ� �� ����Ʈ***");
			System.out.println("�ֹ���ȣ\t�ֹ���\t���ĸ�\t����\t������ּ�");
			System.out.println("----------------------------------------");
			
			//�������� �о�;� �ϹǷ� while������ �����´�.
			//rs.next()��ü�� ���ؼ� �������� next��ü�� �о���µ� �ȿ� �ִ� ������ ������ �о��ش�.
			while(rs.next()) {
				int fno=rs.getInt("fno"); //"db��" - db���̶� ������ �Ȱ��ƾ��Ѵ�.
				String name=rs.getString("name");
				String food=rs.getString("food");
				int price=rs.getInt("price");
				String addr=rs.getString("addr");
				
				System.out.println(fno+"\t"+name+"\t"+food+"\t"+price+"\t"+addr);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close(); //null�� �ƴ� ��쿡 �ݾƶ�� ��...������ �ڿ��� ���������ϱ� �ڿ��� ������ �ݾƶ�� ��
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {

		JdbcTest ex=new JdbcTest();
		ex.connectSawon();
		System.out.println("========================================");
		ex.connectionBaedal();
	}

}
