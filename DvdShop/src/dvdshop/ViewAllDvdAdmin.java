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
import javax.websocket.Session;

@WebServlet("/ViewAllDvdAdmin")
public class ViewAllDvdAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ViewAllDvdAdmin() {
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
		
		
		
		String output="<html><head><title>View All Dvd</title></head><body><center><h2>View All Dvd</h2><hr /><table><tr><form>";
		output+="<td><input type='text' name='txtSearch'/></td><td><input type='submit' value='Search' /></td></form></tr></table><br /><br /><table border=1>";
		output+="<tr><td>ID</td><td>Title</td><td>Description</td><td>Actors</td><td>Category</td><td>Audio Quality</td><td>Video Quality</td><td>Language</td><td>Options</td></tr>";
		
		String search = request.getParameter("txtSearch");
		String sql ="";
		
		if(search==null){
			sql ="SELECT * FROM `movies` WHERE Actors LIKE '%%' OR Title LIKE '%%' OR Category LIKE '%%'";
		}else{
			sql ="SELECT * FROM `movies` WHERE Actors LIKE '%"+search+"%' OR Title LIKE '%"+search+"%' OR Category LIKE '%"+search+"%'";
		}
		DataAccess da = new DataAccess();
		ResultSet result = da.getResultSet(sql);
		try {
			while(result.next()){
				output+="<tr>";
				output+="<td>"+result.getString("Id")+"</td>";
				output+="<td>"+result.getString("Title")+"</td>";
				output+="<td>"+result.getString("Description")+"</td>";
				output+="<td>"+result.getString("Actors")+"</td>";
				output+="<td>"+result.getString("Category")+"</td>";
				output+="<td>"+result.getString("AudioQuality")+"</td>";
				output+="<td>"+result.getString("VideoQuality")+"</td>";
				output+="<td>"+result.getString("Language")+"</td>";
				output+="<td> <a href='./edit?id="+result.getString("Id")+"'>edit</a> <a href='./delete?id="+result.getString("Id")+"'>delete</a> </td>";
				output+="</tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		output+="</table></center></body></html>";
		response.setContentType("text/html");
		writer.print(output);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		checkUser(request, response);
		doGet(request, response);
	}

}
