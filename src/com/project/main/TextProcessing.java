package com.project.main;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.StringTokenizer;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class TextProcessing 
{
	public static String [] sent(String str) throws IOException 
	{
	String sentences[] ;
	InputStream is = new FileInputStream("/home/susheelsaagar/Desktop/Project/jar/apache-opennlp-1.5.3/en-sent.bin");
	SentenceModel model = new SentenceModel(is);
	SentenceDetectorME sdetector = new SentenceDetectorME(model);
    sentences  = sdetector.sentDetect(str);
	return sentences ;
	}
	
	public void process(Statement st) 
	{
		 long id;
		 String text ;
		 String text1[] = null;
		 
		 // Initialize the tagger
		 MaxentTagger tagger = new MaxentTagger("/home/susheelsaagar/Desktop/Project/jar/stanford-postagger-2014-08-27/models/english-bidirectional-distsim.tagger" );
         try 
         {
			ResultSet res = st.executeQuery("select h2.id,h2.text from hc2 h2 , affected a  where h2.id != a.id ");
			while(res.next())
			{
        	  int noofsent,score=0; 
        	  id = res.getLong(1);
        	  text = res.getString(2);
        	  
        	  	try 
        	  		{
        	  		text1 = sent(text);
        	  		} 
        	  		catch (IOException e) 	
        	  		{
        	  		e.printStackTrace();
        	  		}
        	  
        	  	noofsent= text1.length;
        	  	 
        	  	while(noofsent>0)
        	  	{
        	  		int i=-1;
        	  		
        	  		String tagged = tagger.tagString(text1[noofsent]);
        	  		StringTokenizer s =  new StringTokenizer(tagged);
        	  		String tags[] = new String[s.countTokens()];
        	  		while(s.hasMoreTokens())
        	  		 {
        	  			 tags[++i]=s.nextToken(); 
        	  			if((tags[i].endsWith("PRP")||tags[i].endsWith("PRP$")))
        	  			 {
        	  				 	  score++;
        	  			 }
                     }
        	  		noofsent--;
        	  	}
   			if(score>0)
   			{
   				st.executeQuery("insert into table affected select id,created_at,time_zone from hc2 where hc2.id = "+id);
   			}
			}
         } 
         catch (SQLException e) 
         {
			e.printStackTrace();
         }
	}
}
