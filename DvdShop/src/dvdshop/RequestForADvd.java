package dvdshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@WebServlet("/requsetForADvd")
public class RequestForADvd extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RequestForADvd() {
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
    	else if((int)se.getAttribute("type")!=2){
    		 response.setContentType("text/html");
		      // New location to be redirected
		      String site = new String("./");
		      response.setStatus(response.SC_MOVED_TEMPORARILY);
		      response.setHeader("Location", site);  
    	}
    	
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkUser(request, response);
		String output ="<html><head><title>Request</title></head><body><center><h2>Request Dvd</h2><hr /><form method='POST'><table><tr><td>Title: </td><td><input type='text' name='title'/></td><td style='color: red;'>";
		output+="</td></tr><tr><td>Description: </td><td><input type='text' name='desc'/></td><td style='color: red;'>";
		output+="</td></tr><tr><td></td><td><input type='submit' value='Request'/></td></tr></table></form></center></body></html>";
		
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		writer.print(output);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkUser(request, response);
		//doGet(request, response);
		HttpSession session = request.getSession();
		
		String title ="";
		String desc = "";
		String output="";
		String username=(String)session.getAttribute("username");
		
		title = request.getParameter("title");
		desc = request.getParameter("desc");
		
		if(title != null && desc!=null){
			boolean flag = true;
			
			output +="<html><head><title>Request</title></head><body><center><h2>Request Dvd</h2><hr /><form method='POST'><table><tr><td>Title: </td><td><input type='text' name='title'/></td><td style='color: red;'>";

			if(title.trim().equals("")){
				output+="Titel Required";
				flag = false;
			}
			output+="</td></tr><tr><td>Description: </td><td><input type='text' name='desc'/></td><td style='color: red;'>";

			if(desc.trim().equals("")){
				output+="Description Required";
				flag=false;
			}
			output+="</td></tr><tr><td></td><td><input type='submit' value='Request'/></td></tr></table></form></center></body></html>";
			
			
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			writer.print(output);
			
			if(flag == true){
				DataAccess da = new DataAccess();
				String sql ="Insert into requests values(null,'"+title+"','"+desc+"','"+username+"')";
				da.executeQuery(sql);
				
				 String site = new String("./user");
			     response.setStatus(response.SC_MOVED_TEMPORARILY);
			     response.setHeader("Location", site);  
				
				
			}
		}else{
			doGet(request, response);
		}
		
		
		
		
		
	}

}
