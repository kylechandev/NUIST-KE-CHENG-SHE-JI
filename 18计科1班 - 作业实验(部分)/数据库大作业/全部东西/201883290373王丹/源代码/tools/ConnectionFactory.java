package tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionFactory {
	//利用静态代码块，在工具类被加载时即执行配置文件的读取
	private static Properties props = new Properties();
	static String driver;
	static String url;
	static String userName;
	static String password;
	static Connection con = null;
	static{
		try{
			props.load(new FileInputStream("database1.properties"));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		con = null;
		try{
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			userName = props.getProperty("userName");
			password = props.getProperty("password");
			Class.forName(driver);
			con = DriverManager.getConnection(url,userName,password);
	}catch(ClassNotFoundException e){
		System.out.println("failed to register driver.");
		e.printStackTrace();
	}catch(SQLException e){
		System.out.println("failed to execute sql.");
		e.printStackTrace();
	}
		return con;
	}
	
	public static void main(String[] args){
		System.out.println("1111");
		ConnectionFactory con=new ConnectionFactory();
		con.getConnection();
		System.out.println(con);
	}
}