package net.codejava;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class demo {
	
	public static void connect() {  
        Connection conn = null;  
        try {  
            // db parameters  
            String url = "jdbc:sqlite:C:\\Users\\Hi\\sqlite-tools-win32-x86-3360000\\demodb.db";  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            try {  
                if (conn != null) {  
                    conn.close();  
                }  
            } catch (SQLException ex) {  
                System.out.println(ex.getMessage());  
            }  
        }  
    }  
	
	public static void createNewDatabase(String fileName) {  
		   
        String url = "jdbc:sqlite:C:\\\\Users\\\\Hi\\\\sqlite-tools-win32-x86-3360000\\\\" + fileName;  
   
        try {  
            Connection conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("The driver name is " + meta.getDriverName());  
                System.out.println("A new database has been created.");  
            }  
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	 public static void createNewTable() {  
	        // SQLite connection string  
	        String url = "jdbc:sqlite:C:\\\\Users\\\\Hi\\\\sqlite-tools-win32-x86-3360000\\\\Mydata.db";  
	          
	        // SQL statement for creating a new table  
	        String sql = "CREATE TABLE IF NOT EXISTS Movies(id Integer PRIMARY KEY,name TEXT,actor TEXT,actress TEXT, director TEXT,YOR INTEGER);";  
	          
	        try{  
	            Connection conn = DriverManager.getConnection(url);  
	            Statement stmt = conn.createStatement();  
	            stmt.execute(sql);  
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }  
	 
	 
	 public static void Insert(String name,String actor,String actress,String director,Integer YOR) {
		// SQLite connection string  
	        String url = "jdbc:sqlite:C:\\\\Users\\\\Hi\\\\sqlite-tools-win32-x86-3360000\\\\Mydata.db";  
	          
	        // SQL statement for inserting values 
	        String sql = "INSERT INTO Movies(name,actor,actress,director,YOR) VALUES (?,?,?,?,?)";  
	          
	        try{  
	            Connection conn = DriverManager.getConnection(url);  
	            PreparedStatement pstmt = conn.prepareStatement(sql);  
	            pstmt.setString(1, name);
	            pstmt.setString(2, actor);
	            pstmt.setString(3, actress);
	            pstmt.setString(4, director);
	            pstmt.setInt(5, YOR);  
	            pstmt.executeUpdate();   
	              
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	 }
	 
	 public static void selectAll() {
			// SQLite connection string  
		        String url = "jdbc:sqlite:C:\\\\Users\\\\Hi\\\\sqlite-tools-win32-x86-3360000\\\\Mydata.db";  
		          
		        // SQL statement for inserting values 
		        String sql = "SELECT * FROM Movies";  
		          
		        try{  
		            Connection conn = DriverManager.getConnection(url);  
		            Statement stmt  = conn.createStatement();  
		            ResultSet rs    = stmt.executeQuery(sql);  
		              
		            // loop through the result set  
		            while (rs.next()) {  
		                System.out.println(rs.getInt("id") +  "\t |" +   
		                                   rs.getString("name") + "\t |" +
		                                   rs.getString("actor") + "\t |" +
		                                   rs.getString("actress") + "\t |" +
		                                   rs.getString("director") + "\t |" +
		                                   rs.getInt("YOR")); 
		              
		        }
		        }catch (SQLException e) {  
		            System.out.println(e.getMessage());  
		        }  
		 }
	 
	 public static void select(String actorName) {
			// SQLite connection string  
		        String url = "jdbc:sqlite:C:\\\\Users\\\\Hi\\\\sqlite-tools-win32-x86-3360000\\\\Mydata.db";  
		          
		        // SQL statement for inserting values 
		        String sql = "SELECT id,name,actor,actress,director,YOR FROM Movies WHERE actor=?";  
		          
		        try{  
		            Connection conn = DriverManager.getConnection(url);  
		            PreparedStatement pstmt = conn.prepareStatement(sql);  
		            pstmt.setString(1,actorName);
		            ResultSet rs    = pstmt.executeQuery();  
		              
		            // loop through the result set  
		            while (rs.next()) {  
		                System.out.println(rs.getInt("id") +  "\t |" +   
		                                   rs.getString("name") + "\t |" +
		                                   rs.getString("actor") + "\t |" +
		                                   rs.getString("actress") + "\t |" +
		                                   rs.getString("director") + "\t |" +
		                                   rs.getInt("YOR")); 
		              
		        }
		        }catch (SQLException e) {  
		            System.out.println(e.getMessage());  
		        }  
		 }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		connect();
		createNewDatabase("Mydata.db");
		createNewTable();
		Insert("Kabir Singh","Shahid Kapoor","Kiara Advani","Sandeep Reddy Vanga",2017);
		Insert("PK","Amir Khan","Anushka Sharma","Rajkumar Hirani",2018);
		selectAll();
		select("Amir Khan");
		

}

}
