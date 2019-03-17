package dbClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.BugContainer;
import model.BugPair;
import model.Pair;

public class DBClient {

    String jdbc_driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost/BugTracker";
    
    Statement stm;
    
    
    // User Login
    public DBClient()
    {
        try {
            Class.forName(jdbc_driver);
            Connection connection = DriverManager.getConnection( url ,"sukhad","sukhad1998");
            stm = connection.createStatement();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
         
    }
    
    public String UserValidate(String username,String password)
    {

        String userId=null;
        try {
            // System.out.println("Connected Success");
            String query = "select id from Users where username='"+username+"' and password='"+password+"';";
            // System.out.println(query);
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next())
            {
                userId = Integer.toString(rs.getInt("id"));
            //    System.out.println(userId);                
            }
            
        }catch(Exception e)
        {
            return null;
        }
        return userId;
    }
    
    // User Registration
    public boolean UserRegister(String username,String password,String email,int contact)
    {
        int rows=0;
        try {
            String query;
            query = String.format("insert into Users values(0,'%1$s','%2$s',%3$s,'u','%4$s')",username,email,contact,password);
//            System.out.println(query);
            rows = stm.executeUpdate(query);
        
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        if(rows==0)
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
    // Project table
    public String[] get_projects_list(int user_id)
    {
        // System.out.println(user_id);
        List<String> projects = new ArrayList<>();
        List<Integer> project_ids = new ArrayList<>();
        String[] project_list = null;
        ResultSet rs = null;
        ResultSet project_set = null;
        try {
            String query = "select project_id from Project_Users where user_id="+Integer.toString(user_id);
            // System.out.println(query);
            rs = stm.executeQuery(query);
            //    System.out.println(rs);
            while(rs.next())
            {
                int project_id = rs.getInt("project_id");
                project_ids.add(project_id);
                
            //projects.add(rs.getString("project_title"));
                
            }
            // System.out.println(project_ids);
            
            for(int project_id : project_ids)
            {
                String getProjQuery = "select project_title from Projects where project_id = " + project_id;
                project_set = stm.executeQuery(getProjQuery);
                
                while(project_set.next()) {
                    String project = project_set.getString("project_title");
                    projects.add(project);
                }
            }
        
            project_list = new String[projects.size()];
            project_list = projects.toArray(project_list);
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return project_list;
    }
    
    
    public int get_project_id(String project_title)
    {
        int project_id = -1;
        ResultSet rs;
        try {
            String query = "select project_id from Projects where project_title='"+project_title+"'";
//            System.out.println(query);
            rs = stm.executeQuery(query);
            while(rs.next())
            {
                project_id = rs.getInt("project_id");
            }
            
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return project_id;
    }
    
    public String get_project_description(String project_title)
    {
        String project_description = ""; 
        try {
            String query =    "select project_description from Projects where project_title = '" + project_title+"'";
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next())
            {
                project_description = rs.getString("project_description");
         //         System.out.println(project_description);
            }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return project_description;
    }
    
    // Bugs table
    public void post_bug(String bug_title,String os,String browser,String bug_description,int user_id,int project_id)
    {
        try {
//        	System.out.println(bug_title);
//        	System.out.println(bug_description);
            String query = String.format("insert into Bugs values(0,'%1$s','%2$s',%3$s,'%4$s',%5$s,'%6$s','%7$s')",bug_title, bug_description,user_id,"p",project_id,os,browser);
//            System.out.println(query);
            stm.executeUpdate(query);
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String get_role(int user_id) {
    	String role = null;
    	try {
    		String query = "select role from Users where id = " + user_id;
    		ResultSet rs = stm.executeQuery(query);
    		while(rs.next()) {
    			role = rs.getString("role");	
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return role;
    }
    
    public String get_username(int user_id) {
		
    	String username = "";
    	
    	try {
    		String query = "Select username from Users where id = " + user_id;
    		ResultSet rs = stm.executeQuery(query);
    		
    		while(rs.next()) {
    			username = rs.getString("username");
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    	
    	
    	return username;
    	
    }
    
    public ArrayList<Pair> get_projects(int user_id)
    {
        ArrayList<Pair> projects = new ArrayList<>();
        List<Integer> project_ids = new ArrayList<>();
        ResultSet rs = null;
        ResultSet project_set = null;
        try {
            String query = "select project_id from Project_Users where user_id="+Integer.toString(user_id);
            rs = stm.executeQuery(query);
            while(rs.next())
            {
                int project_id = rs.getInt("project_id");
                project_ids.add(project_id);
                
                
            }
            for(int project_id : project_ids)
            {
                String getProjQuery = "select project_title,project_description from Projects where project_id = " + project_id;
                project_set = stm.executeQuery(getProjQuery);
                
                while(project_set.next()) {
                    String project_title = project_set.getString("project_title");
                    String project_description = project_set.getString("project_description");
                 
                    Pair pair = new Pair(project_title,project_description);
                    projects.add(pair);
                }
            }
         
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return projects;
    }
    
    public boolean unique_username(String username)
    {
        ResultSet rs;
        int existing_id = -1;
        try {
            String query = "select id from Users where username='"+username+"'";
            // System.out.println(query);
            
            rs = stm.executeQuery(query);
            while(rs.next()) {
                existing_id = rs.getInt("id");
            }
            
        }catch(Exception e)
        {
            return false;        
        }
//        System.out.println("Existing id: " + existing_id);
        if(existing_id == -1) {
            return true;
        }
        else {
            return false;
        }
        
    }
    
    public HashMap<String,Integer> get_bug_status_count(int project_id)
    {
    	HashMap<String,Integer> status_count = new HashMap<String,Integer>();
    	ResultSet rs = null;
    	String bug_status;
    	int pending = 0;
    	int	working = 0;
    	int resolved = 0;
    	try {
    		
    		String query = "select bug_status from Bugs where project_id='"+project_id+"'";
//    		System.out.println(query);
    		
    		rs = stm.executeQuery(query);
    		while(rs.next())
    		{
    			bug_status = rs.getString("bug_status");
    			if(bug_status.equals("p"))
    			{
    				pending+=1;
    			}
    			else if(bug_status.equals("r"))
    			{
    				resolved+=1;
    			}
    			else
    			{
    				working+=1;
    			}
    		}
    	
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
//    	System.out.println("pending:"+pending);
//    	System.out.println("resolved:"+resolved);
//    	System.out.println("working:"+working);
//    	
    	status_count.put("pending", pending);
    	status_count.put("resolved", resolved);
    	status_count.put("working", working);
    	
    	return status_count;
    }
    
    public String get_description(int project_id) {
    	String description = "";
    	
    	try {
    		String query = "Select project_description from Projects where project_id = " + project_id;
    	
    		ResultSet rs = stm.executeQuery(query);
    		while(rs.next()) {
    			description = rs.getString("project_description");
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return description;
    }
    
    public ArrayList<BugPair> get_bug_list(int project_id)
    {
    	ArrayList<BugPair> bugList = new ArrayList<>();
    	String query = "select bug_id,bug_title from Bugs where project_id='"+project_id+"'";
    	ResultSet rs;
    	int bug_id;
    	String bug_title;
    	try {
//    		System.out.println(query);
    		rs = stm.executeQuery(query);
    		
    		while(rs.next())
    		{
    			bug_id = rs.getInt("bug_id");
    			bug_title = rs.getString("bug_title");
    			
    			bugList.add(new BugPair(bug_id,bug_title));
    		}
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return bugList;
    }
    
    public String get_project_title(int project_id)
    {
    	String title = "";
    	
    	try {
    		String query = "Select project_title from Projects where project_id = " + project_id;
    	
    		ResultSet rs = stm.executeQuery(query);
    		while(rs.next()) {
    			title = rs.getString("project_title");
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return title;
    }
    
    public BugContainer get_bug_data(int bug_id) {
    	String title = "";
    	String description = "";
    	String os = "";
    	String browser = "";
    	String bug_status= "";
    	
    	BugContainer b = null;
    	try {
    		String query = "Select bug_title,bug_description,bug_os,bug_browser,bug_status from Bugs where bug_id = " + bug_id;
//    		System.out.println(query);
    		ResultSet rs = stm.executeQuery(query);
    		while(rs.next()) {
    			title = rs.getString("bug_title");
    			description = rs.getString("bug_description");
    			os = rs.getString("bug_os");
    			browser = rs.getString("bug_browser");
    			bug_status = rs.getString("bug_status");
    			
    			b = new BugContainer(title,description,os,browser,bug_status);
    		}		
    	
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return b;
    	}
    	
    	return b;
    }
    
    public String get_user_role(int user_id)
    {
    	String role = "u";
    	try {
    		String query = "Select role from Users where id = " + user_id;
    		ResultSet rs = stm.executeQuery(query);
    		
    		while(rs.next()) {
    			role = rs.getString("role");
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    	return role;
    }
    
    
    public void change_bug_status(int bug_id,String bug_status)
    {
    	int rows = 0;
    	String query = "update Bugs set bug_status = '"+bug_status+"' where bug_id = "+bug_id;
//    	System.out.println(query);
    	try {
    		
    		rows = stm.executeUpdate(query);
    		
    		if(rows>0)
    		{
//    			System.out.println("Success");
    		}
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
}
