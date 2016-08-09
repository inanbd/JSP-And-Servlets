package dvdshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/Admin", "/admin" })
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		
		String output ="<html><head><title>Add New Dvd</title></head><body><center><h2>Admin Home</h2><hr /><table><tr>";
		output+="<td><form method='POST' action='./addNewDvd'><input type='submit' value='Add New Dvd' /></form></td>";
		
		output+="<td><form method='POST' action='./ViewAllDvdAdmin'><input type='submit' value='View All Dvd' /></form></td>";
		output+="</tr></table><table><tr>";
		
		output+="<td><form method='POST' action='./viewAllCustomers'><input type='submit' value='View All Customers' /></form></td>";
		output+="<td><form method='POST' action='./viewCustomerRequests'><input type='submit' value='View All Requests' /></form></td>";
		output+="</tr></table><form method='POST' action='./logout'><input type='submit' value='Logout' /></form></center></body></html>";
		
		writer.println(output);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
	}

}
