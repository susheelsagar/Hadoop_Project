package com.project.main;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

public class HdfsToHC {
	
	public void addjar(Statement stmt) throws SQLException
	{
		   stmt.executeQuery("add jar /home/susheelsaagar/Desktop/Project/flumefiles/hive-serdes-1.0-SNAPSHOT.jar");
	}
	
	public void load(Statement stmt) 
	{
		  String k;
		  
		  int i=0,hour;
		  
		  String time[] = new String[4]; 
          
		  Date d = new Date();
          
          SimpleDateFormat s = new SimpleDateFormat("yyyy MM dd HH");
          
          k=s.format(d);
          
          StringTokenizer st = new StringTokenizer(k);
          
          while(st.hasMoreTokens())
          {
        	  time[i] = st.nextToken();
        	  i++;
          }
		  
          hour= Integer.parseInt(time[3]);
		  
		  if(hour==0)
				{
				hour=23;
				}
		 else
				hour--;
		
		  if(hour<10)
			{ 
			    try{
			    	stmt.executeQuery("load data inpath '/Project/Influenza_test/tweets/"+time[0]+
					 			"/"+time[1]+"/"+time[2]+"/0" + hour + "' into table hc " );
			    	throw new Exception ("data not found");   
			    	}catch(Exception e)
			    		{System.out.print(e);}
			}
		 else
		 	{
			 	try
			 	{
				 stmt.executeQuery("load data inpath '/Project/Influenza_test/tweets/"+time[0]+
			 			"/"+time[1]+"/"+time[2]+"/" + hour + "' into table hc " );
				 throw new Exception ("data not found");   
			 	}catch(Exception e)
			 		{System.out.print(e);}
		 }
	}

}
