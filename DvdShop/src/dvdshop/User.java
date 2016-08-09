package dvdshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public User() {
        super();
       
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		String output ="<html><head><title>Add New Dvd</title></head><body><center><h2>User Home</h2><hr /><table><tr><td><form method='POST' action='./viewAllDvd'><input type='submit' value='View All Dvd' /></form></td><td><form method='POST' action='./requsetForADvd'><input type='submit' value='Request For A Dvd' /></form></td></tr></table><form method='POST' action='./logout'><input type='submit' value='Logout' /></form></center></body></html>";
		
		response.setContentType("text/html");
		writer.print(output);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
