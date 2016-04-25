package com.project.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect 
{
	
	// The required variables
	private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    Connection con;
	public static Statement stmt;
	
	// The Conn function for obtaining a connection which returns a statement object
	public   Statement  Conn()
	{
		try{
	    	 Class.forName(driverName);
			 con = DriverManager.getConnection("jdbc:hive://localhost:10000" , "", "");
		     stmt = con.createStatement();
		     System.out.println("connected to database");
		    }catch (Exception e) 
					{
			     		e.printStackTrace();
			     		System.exit(1);
					}
		return stmt;
	}
}
