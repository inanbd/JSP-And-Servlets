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


@WebServlet("/edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Edit() {
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
		String id = request.getParameter("id");
		
		HttpSession session  = request.getSession();
		session.setAttribute("movieId", id);
		
		DataAccess da = new DataAccess();
		String sql = "Select * from movies where id ='"+id+"'";
		
		String title="",desc="",actors="",category="",audio="",video="",lang="";
		
		
		ResultSet result = da.getResultSet(sql);
		
		try {
			while(result.next()){
				title = result.getString("Title");
				desc = result.getString("Description");
				actors = result.getString("Actors");
				category = result.getString("Category");
				audio = result.getString("AudioQuality");
				video = result.getString("VideoQuality");
				lang= result.getString("Language");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String output = "<html><head><title>Add New Dvd</title></head><body><center><h2>Add New Dvd</h2><hr /><form method='POST'><table><tr><td>Title: </td><td><input type='text' name='txtTitle' value='"+title+"' /></td><td><label style='color: red;'>";
		//title Required
		output+="</label></td></tr><tr><td>Description: </td><td><input type='text' name='txtDesc' value='"+desc+"' /></td><td><label style='color: red;'>";
		//desc required
		output+="</label></td></tr><tr><td>Actors: </td><td><input type='text' name='txtActors' value='"+actors+"' /></td><td><label style='color: red;'>";
		//actor names Required
		output+="</label></td></tr><tr><td>Category: </td><td><select name='category' style='width: 150px;'><option>Action</option><option>Adventure</option><option>Animation</option><option>Comedy</option><option>Detactive</option><option>Drama</option><option>Family</option><option>Fantasy</option><option>Horror</option><option>History</option><option>Musical</option><option>Mystery</option><option>Romance</option><option>Sport</option><option>Thriller</option><option>Sci-Fi</option><option>War</option><option>Western</option></select></td></tr><tr><td>Audio Quality: </td><td><select name='audioQuality'  style='width: 150px;'><option>1.0 Mono</option><option>2.0 Stereo</option><option>5.1 Surround</option></select></td></tr><tr><td>Video Quality: </td><td><select name='videoQuality' style='width: 150px;' ><option>360p</option><option>480p</option><option>720p</option><option>1080p</option><option>2k</option><option>4k</option></select></td></tr><tr><td>Language: </td><td><select name='language' style='width: 150px;' ><option>Bangla</option><option>Korean</option><option>English</option><option>Hindi</option><option>En-Hi Dual  Audio</option></select></td></tr><tr><td></td><td><input type='submit' value='Save Changes' /></td></tr></table></form></center></body></html>";
		response.setContentType("text/html");
		writer.print(output);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		checkUser(request, response);
		PrintWriter writer = response.getWriter();
		HttpSession session  = request.getSession();
	
		String id =(String)session.getAttribute("movieId");
		
		String title= request.getParameter("txtTitle");
		String desc= request.getParameter("txtDesc");
		String actors= request.getParameter("txtActors");
		String cat= request.getParameter("category");
		String audio= request.getParameter("audioQuality");
		String video= request.getParameter("videoQuality");
		String lang= request.getParameter("language");
		
		Boolean flag = true;
		
		String output = "<html><head><title>Add New Dvd</title></head><body><center><h2>Add New Dvd</h2><hr /><form method='POST'><table><tr><td>Title: </td><td><input type='text' name='txtTitle' /></td><td><label style='color: red;'>";

		
		
		
		if(title!=null && desc != null && actors!= null ){
			
			if(title.trim().equals("")){
				output+="Titel Required";
				flag = false;
			}
			output+="</label></td></tr><tr><td>Description: </td><td><input type='text' name='txtDesc' /></td><td><label style='color: red;'>";

			if(desc.trim().equals("")){
				output+="Desc Required";
				flag = false;
			}
			output+="</label></td></tr><tr><td>Actors: </td><td><input type='text' name='txtActors' /></td><td><label style='color: red;'>";

			if(actors.trim().equals("")){
				output+="Actro Names Required";
				flag = false;
			}
			output+="</label></td></tr><tr><td>Category: </td><td><select name='category' style='width: 150px;'><option>Action</option><option>Adventure</option><option>Animation</option><option>Comedy</option><option>Detactive</option><option>Drama</option><option>Family</option><option>Fantasy</option><option>Horror</option><option>History</option><option>Musical</option><option>Mystery</option><option>Romance</option><option>Sport</option><option>Thriller</option><option>Sci-Fi</option><option>War</option><option>Western</option></select></td></tr><tr><td>Audio Quality: </td><td><select name='audioQuality'  style='width: 150px;'><option>1.0 Mono</option><option>2.0 Stereo</option><option>5.1 Surround</option></select></td></tr><tr><td>Video Quality: </td><td><select name='videoQuality' style='width: 150px;' ><option>360p</option><option>480p</option><option>720p</option><option>1080p</option><option>2k</option><option>4k</option></select></td></tr><tr><td>Language: </td><td><select name='language' style='width: 150px;' ><option>Bangla</option><option>Korean</option><option>English</option><option>Hindi</option><option>En-Hi Dual  Audio</option></select></td></tr><tr><td></td><td><input type='submit' value='Save Changes' /></td></tr></table></form></center></body></html>";

			response.setContentType("text/html");
			writer.print(output);
			
			if(flag == true){
				DataAccess da = new DataAccess();
				
				String sql ="Update movies set Title='"+title+"', Description = '"+desc+"', Actors = '"+actors+"',Category = '"+cat+"', AudioQuality = '"+audio+"',VideoQuality= '"+video+"',Language = '"+lang+"' where Id ='"+id+"'";
				writer.print(sql);
				
				da.executeQuery(sql);
				
				//writer.println("Movie Inserted Successfully ");
				
				
				 String site = new String("./ViewAllDvdAdmin");
			     response.setStatus(response.SC_MOVED_TEMPORARILY);
			     response.setHeader("Location", site);  
				
				
			}
			
			
			
			
		}else{
			doGet(request, response);
		}
		
		
		
	}

}
