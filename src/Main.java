import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws Exception{
		
		
		viewData();
		insertData();
		updateData();
		deleteData();
		
		

	}

	private static void deleteData() throws Exception{
		// TODO Auto-generated method stub
		Connection con=getConn();
		System.out.println("Deleting Data");
		String sql="delete from  emp where eno=?";	
		
		PreparedStatement pst=con.prepareStatement(sql);
		System.out.println("Enter eno");
		int eno=sc.nextInt();
		pst.setInt(1, eno);
		
		int i=pst.executeUpdate();
		
		if(i!=0)
			System.out.println("Emp deleted Successfully");
		viewData();
		
	}

	private static void updateData()  throws Exception{
		// TODO Auto-generated method stub
		Connection con=getConn();
		System.out.println("Updating Data");
		String sql="update emp set ename=?,salary=? where eno=?";	
		
		PreparedStatement pst=con.prepareStatement(sql);
		System.out.println("Enter eno");
		int eno=sc.nextInt();
		pst.setInt(3, eno);
		System.out.println("Enter new ename");
		String ename=sc.next();
		pst.setString(1, ename);
		
		System.out.println("Enter new salary");
		int sal=sc.nextInt();
		pst.setInt(2, sal);
		
		int i=pst.executeUpdate();
		
		if(i!=0)
			System.out.println("Emp updated Successfully");
		viewData();
		
	}

	private static void viewData() throws Exception{
		// TODO Auto-generated method stub
		System.out.println("Employee Details");
		String sql="select * from emp";	
		Connection con=getConn();
		PreparedStatement pst=con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();	
		if(rs.isBeforeFirst())
		{
			while(rs.next())
			{
			System.out.println("[Eno= "+rs.getInt(1)+" Ename= "+rs.getString(2)+" salary= "+rs.getInt(3)+" ]");
			}
		}
		
		
	}

	private static void insertData() throws Exception {
		
		Connection con=getConn();
		System.out.println("Inserting Data");
		String sql="insert into emp values(?,?,?)";	
		
		PreparedStatement pst=con.prepareStatement(sql);
		System.out.println("Enter eno");
		int eno=sc.nextInt();
		pst.setInt(1, eno);
		System.out.println("Enter ename");
		String ename=sc.next();
		pst.setString(2, ename);
		System.out.println("Enter salary");
		int sal=sc.nextInt();
		pst.setInt(3, sal);
		
		int i=pst.executeUpdate();
		if(i!=0)
			System.out.println("Emp inserted Successfully");
		viewData();
	}

	public static Connection getConn() throws Exception {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		Connection con=DriverManager.getConnection(url, "java", "java");
		
		return con;
	}

}
