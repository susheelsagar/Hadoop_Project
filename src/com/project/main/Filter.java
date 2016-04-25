package com.project.main;
import java.sql.SQLException;
import java.sql.Statement;
public class Filter {

	public void filter(Statement st)
	{
	try {
		st.executeQuery("insert into table hc2 select h.id ,h.created_at,h.user.screen_name,h.user.name,"
		+ " h.user.time_zone,h.text,h.retweeted_status.retweet_count from hc h,hc2 h2 where h.id != h2.id and"
		+ " (h.user.time_zone='Alaska' or h.user.time_zone='America/Chicago' or h.user.time_zone='America/Los_Angeles' or h.user.time_zone='Arizona' "
		+ " or h.user.time_zone='Atlantic Time (Canada)' or h.user.time_zone= 'Central Time (US & Canada)' or "
		+ " h.user.time_zone= 'Eastern Time (US & Canada)' or h.user.time_zone= 'Georgetown'"
		+ "  or h.user.time_zone= 'Hawaii' or h.user.time_zone='International Date Line West' "
		+ " or h.user.time_zone= 'Mid-Atlantic' or h.user.time_zone='Mountain Time (US & Canada)' "
		+ " or h.user.time_zone= 'Pacific Time (US & Canada)')");
  
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	}
}
