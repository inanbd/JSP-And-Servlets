package controllers;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import beans.Student;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Student s1 = new Student();
		s1.setId(5);
		s1.setName("John");
		s1.setCgpa(2.25);
		
		Student s2 = new Student();
		s2.setId(4);
		s2.setName("Ben");
		s2.setCgpa(3.25);
		
		Student s3 = new Student();
		s3.setId(4);
		s3.setName("Sally");
		s3.setCgpa(3.12);
		
		
		ArrayList<Student> stdlist = new ArrayList<Student>();
		stdlist.add(s1);
		stdlist.add(s2);
		stdlist.add(s3);
		
		request.setAttribute("slist", stdlist);
		
		String url = "/WEB-INF/views/first.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
