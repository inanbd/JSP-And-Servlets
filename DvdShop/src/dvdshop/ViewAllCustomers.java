package dvdshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/viewAllCustomers")
public class ViewAllCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ViewAllCustomers() {
        super();
    }

    
    private void checkUser(HttpServletRequest request ,HttpServletResponse response){
    	HttpSession se = request.getSession();
    	if(se.getAttribute("type")==null){
    		response.setContentType("text/html");
		      // New location to be redirected
		      String site = new String("./");
		      response.setStatus(response.SC_MOVED_TEMPORARILY);
		      response.setHeader("Location", site); 
    	}
    	else if((int)se.getAttribute("type")!=1){
    		 response.setContentType("text/html");
		      // New location to be redirected
		      String site = new String("./");
		      response.setStatus(response.SC_MOVED_TEMPORARILY);
		      response.setHeader("Location", site);  
    	}
    	
    	
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		checkUser(request, response);
		PrintWriter writer = response.getWriter();
		
		String output="<html><head><title>View All Dvd</title></head><body><center><h2>Search Customer</h2><hr /><table><tr><form><td><input type='text' name='txtSearch'/></td><td><input type='submit' value='Search' /></td></form></tr></table ><br /><br /><table border=1>";
		output+="<tr><td>ID</td><td>Username</td><td>Password</td><td>Name</td><td>Address</td><td>Email</td><td>Phone</td></tr>";
		
		
		String search = request.getParameter("txtSearch");
		String sql ="";
		
		if(search==null){
			sql ="SELECT * FROM `users` WHERE Username LIKE '%%' OR Name LIKE '%%' OR Email LIKE '%%' OR Phone LIKE '%%'";
		}else{
			sql ="SELECT * FROM `users` WHERE Username LIKE '%"+search+"%' OR Name LIKE '%"+search+"%' OR Email LIKE '%"+search+"%' OR Phone LIKE '%"+search+"%' ";
		}
		
		DataAccess da = new DataAccess();
		ResultSet result = da.getResultSet(sql);
		try {
			while(result.next()){
				output+="<tr>";
				output+="<td>"+result.getString("Id")+"</td>";
				output+="<td>"+result.getString("Username")+"</td>";
				output+="<td>"+result.getString("Password")+"</td>";
				output+="<td>"+result.getString("Name")+"</td>";
				output+="<td>"+result.getString("Address")+"</td>";
				output+="<td>"+result.getString("Email")+"</td>";
				output+="<td>"+result.getString("Phone")+"</td>";
				output+="</tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		output+="</table></center></body></html>";
		response.setContentType("text/html");
		writer.print(output);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
