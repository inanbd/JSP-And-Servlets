package dvdshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet({ "/Index", "/" })
public class Index extends HttpServlet {
	
	CookieJobs cj;
	DataAccess da;
	
	private static final long serialVersionUID = 1L;
    public Index() {
        super();
        cj = new CookieJobs();
        da = new DataAccess();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		
		Cookie[] cookies = request.getCookies();
		
		String username,password;
		
		if(cookies!=null){
			//check username and password cookie
			//writer.print("cookie has");
			username =  cj.getUsername(cookies);
			password = cj.getPassword(cookies);
			
			//writer.print("username: "+username+" password: "+password);
			//System.out.println(username+password);
			
			if(username!= null && password != null){ // username password db er shathe check korte hobe...
				if(username.equals("admin") && password.equals("admin")){
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("password", password);
					session.setAttribute("type", 1);
					
					
					
					response.setContentType("text/html");
				      // New location to be redirected
					String site = new String("./admin");
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					response.setHeader("Location", site); 
				}
				else{ // jodi admin na hoy tahole database er shathe cookie er valu gula 
					//check korbe user exist kina... 
					
					String sql = "Select count(*) as count from users where username = '"+username+"' and password = '"+password+"'";
					ResultSet result = da.getResultSet(sql);
					
					try {
						while(result.next()){
							//System.out.print(result.getString("count"));
							if(result.getString("count").equals("1")){
								
								HttpSession session = request.getSession();
								session.setAttribute("username", username);
								session.setAttribute("password", password);
								session.setAttribute("type", 2);
								
								
								response.setContentType("text/html");
								// New location to be redirected
								String site = new String("./user");
							    response.setStatus(response.SC_MOVED_TEMPORARILY);
							    response.setHeader("Location", site);  
							}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
			
			
		}
		
		//login page show korbe..
		
		response.setContentType("text/html");
		String str="<html><head><title>Login </title></head><body><center><form method='POST'><h2>Login To DVD Shop</h2><br /><hr /><table><tr><td>Username: </td><td><input type='text' name='username' /></td></tr><tr><td>Password: </td><td><input type='password' name='password' /></td></tr><tr><td></td><td><input type='checkbox' name='remember' value='remember' /><label>Remember me?</label></td></tr><tr><td></td><td><input type='submit' value='Login' /></td></tr><tr><td>Don't have any </td><td>account? Register <a href='#'>Here</a></td></tr></table></form></center></body></html>";
		writer.print(str);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);
		
		
		PrintWriter writer = response.getWriter();
		
		String username ="";
		String password = "";
		String remember = "";
		String sql="";
		Boolean loginFlag = true;
		
		username = request.getParameter("username");
		password = request.getParameter("password");
		remember = request.getParameter("remember");
		
		
		
		
		
		if(username.equals("admin") && password.equals("admin")){
			
			HttpSession session = request.getSession();
			
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("type", 1);
			
			if(remember!= null){
				if(remember.equals("remember")){//remember me checked kina check korbe...
					//writer.print("remember");
					//cookie
					cj.addUserDetail(username, password, response);		
				}
			}
			
			
			 response.setContentType("text/html");
		      // New location to be redirected
		      String site = new String("./admin");
		      response.setStatus(response.SC_MOVED_TEMPORARILY);
		      response.setHeader("Location", site);  
			
		}
		
		else if(!(username.equals("")||password.equals(""))){
			sql+="Select count(*) as count from users where username = '"+username+"' and password = '"+password+"'";
			ResultSet result = da.getResultSet(sql);
			
			try {
				while(result.next()){
					System.out.print(result.getString("count"));
					if(result.getString("count").equals("1")){
						
						HttpSession session = request.getSession();
						session.setAttribute("username", username);
						session.setAttribute("password", password);
						session.setAttribute("type", 2);
						
						if(remember!= null){
							if(remember.equals("remember")){//remember me checked kina check korbe...
								//writer.print("remember");
								//cookie
								cj.addUserDetail(username, password, response);//cookie save
							}
						}
						
						
						
						response.setContentType("text/html");
						// New location to be redirected
						String site = new String("./user");
					    response.setStatus(response.SC_MOVED_TEMPORARILY);
					    response.setHeader("Location", site);  
					}
					else {
						loginFlag = false;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				loginFlag = false;
			}
		}
		
		
		
		String opt1 = "<html><head><title>Login </title></head><body><center><form method='POST'><h2>Login To DVD Shop</h2><br /><hr /><table>";
		opt1+="<tr><td>Username: </td><td><input type='text' name='username' value = '"+username+"' /></td><td style='color: red;'>";
		
		if(username.equals("")){
			opt1+="Username Required";
		}
		opt1+="</td></tr><tr><td>Password: </td><td><input type='password' name='password' value = '"+password+"' /></td><td style='color: red;'>";
		if(password.equals("")){
			opt1+="Password Required";
		}
		opt1+="</td></tr><tr><td></td><td><input type='checkbox' name='remember' value='remember' /><label>Remember me?</label></td></tr><tr><td></td><td><input type='submit' value='Login' /></td></tr>";
		
		opt1+="<tr><td></td><td style='color: red;'>"; 
		//ekhane invalid username/ password boshbe
		if(loginFlag == false){
			opt1+="Invalid Username/Password";
		}
		
		opt1+= "</td></tr><tr><td>Don't have any </td><td>account? Register <a href='#'>Here</a></td></tr>";
		opt1+="</table></form></center></body></html>";
		
		writer.print(opt1);
		
		
		
		/*
		
		if(username.equals("admin") && password.equals("admin")){
			
			HttpSession session = request.getSession();
			
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("type", 1);
			
			String sql = "SELECT * FROM ";
			
			if(remember!= null){
				if(remember.equals("remember")){//remember me checked kina check korbe...
					//writer.print("remember");
					//cookie
					cj.addUserDetail(username, password, response);
					
				}
			}
			
			
			 response.setContentType("text/html");
		      // New location to be redirected
		      String site = new String("./admin");
		      response.setStatus(response.SC_MOVED_TEMPORARILY);
		      response.setHeader("Location", site);  
			
			
		}else{
			
			
			if(remember!= null){
				if(remember.equals("remember")){//remember me checked kina check korbe...
					//writer.print("remember");
					//cookie
					cj.addUserDetail(username, password, response);
					
				}
			}
			
		}
		
		
		*/
	}

}
