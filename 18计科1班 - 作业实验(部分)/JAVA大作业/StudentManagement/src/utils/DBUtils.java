package utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtils {
	
    /**
     * 鑾峰彇鏁版嵁搴撹繛鎺�
     * @return Connection瀵硅薄
     */
    public static Connection getConnection(){
    	String dbUserName = "root";
    	String dbUserPasswd = "123";
    	String dbURL = "jdbc:mysql://localhost:3306/studentinfomanagement?"
    	            + "user="+dbUserName+"&password="+dbUserPasswd+"&useUnicode=true&characterEncoding=UTF8";
    	Connection conn = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		conn = (Connection) DriverManager.getConnection(dbURL,dbUserName,dbUserPasswd);
    	} catch (ClassNotFoundException | SQLException e) {
    		e.printStackTrace();
    	} 
    	return conn;
    }
    
    /**
     * 鍏抽棴鏁版嵁搴撹繛鎺�
     * @param conn Connection瀵硅薄
     */
    public static void closeConnection(Connection conn) {
		//鍒ゆ柇conn鏄惁涓虹┖
    	if(conn != null){
    		try {
				conn.close();//鍏抽棴鏁版嵁搴撹繛鎺�
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    	}
	}
}
