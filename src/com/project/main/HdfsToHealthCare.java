package com.project.main;
import java.sql.Statement;

public class HdfsToHealthCare 
{
 
	//The variables
	Statement stmt;
	
	//Load function to load data into the table using the statement object.
	public void load(Statement stmt,int hour,int day,int month)
	{
		this.stmt=stmt;
		
		try{
		   stmt.executeQuery("add jar /home/susheelsaagar/Desktop/Project/flumefiles/hive-serdes-1.0-SNAPSHOT.jar");
		   for (int i=0;i<10;i++)
	       {
	    	   try{
		   stmt.executeQuery("load data inpath '/Project/Influenza_test/tweets/2014/08/06/0" + i + "' into table hc " );   
	    	 throw new Exception ("");   
	    	   }catch(Exception e)
	    	   {System.out.print(e);}
	       }
		   
		   for (int i=10;i<24;i++)
	       {
	    	   try{
		   stmt.executeQuery("load data inpath '/Project/Influenza_test/tweets/2014/08/06/" + i + "' into table hc " );   
	    	 throw new Exception ("" );   
	    	   }catch(Exception e)
	    	   {System.out.print(e);}
	       }}catch(Exception e)
	   	  		{
	   		  		e.printStackTrace();
	   	  		}
	
	}
}
