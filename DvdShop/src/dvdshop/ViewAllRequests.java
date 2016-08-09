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

@WebServlet("/viewCustomerRequests")
public class ViewAllRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewAllRequests() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter writer = response.getWriter();
		response.setContentType("text/html");
		
		String output ="<html><head><title>View All Requests</title></head><body><center><h2>View All Requests</h2><hr /><br /><br /><table border=1><tr><td>ID</td><td>Title</td><td>Description</td><td>Requested By</td></tr>";
		
		DataAccess da = new DataAccess();
		String sql = "Select * from requests";
		ResultSet result = da.getResultSet(sql);
		
		try {
			while(result.next()){
				output+="<tr>";
				output+="<td>"+result.getString("Id")+"</td>";
				output+="<td>"+result.getString("Title")+"</td>";
				output+="<td>"+result.getString("Description")+"</td>";
				output+="<td>"+result.getString("RequestedBy")+"</td>";
				output+="</tr>";
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		output+="</table></center></body></html>";
		
		writer.print(output);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
