package com.project.main;
import java.sql.SQLException;
import java.sql.Statement;
public class Main {
public static void main(String args[]) throws InterruptedException, SQLException
	{
	//Declaring required variables 
	
	Statement st;
	boolean k = true ;
	
	
	//Obtaining connection to the Hive Database using the Connect.java class
    Connect c = new Connect();
 	st = c.Conn();

 	//creating object to load data 
 	HdfsToHC h = new  HdfsToHC();
 	
 	// creating object to load the most relevant data
 	Filter f = new Filter();
 	
 	//Creating object for Processing the tweets's text
 	TextProcessing t = new TextProcessing();
 	
 	while(k)
 			{
 			//loading data from hdfs to hive table hc.
 			h.addjar(st);
 			h.load(st);
 		
 			//Loading the filtered data into table hc2.
 			f.filter(st);
 		
 			// Processing the tweets's text
 			t.process(st);
 		
	
 			//making the program wait for 1 hour
 			Thread.sleep(3600000);
 			}	
	
 	
 	
	}
}

