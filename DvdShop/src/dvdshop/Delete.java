package dvdshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.xmlrpc.base.Data;


@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Delete() {
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
		
		String id = request.getParameter("id");
		
		//PrintWriter writer = response.getWriter();
		//writer.print(id);
		
		String sql = "Delete From Movies where id='"+id+"'";
		DataAccess da = new DataAccess();
		da.executeQuery(sql);
		
		 response.setContentType("text/html");
	      // New location to be redirected
	      String site = new String("./ViewAllDvdAdmin");
	      response.setStatus(response.SC_MOVED_TEMPORARILY);
	      response.setHeader("Location", site); 
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}

}
