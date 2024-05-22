package com.tl.register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tl.model.Details;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("username");
		String pword = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Done");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AccDetails","root","Seed008@");
			//System.out.println("Connection Done");
			
			PreparedStatement pst = con.prepareStatement(" Select * from person_details where username = ? and password = ?");
			pst.setString(1, uname);
			pst.setString(2, pword);
			ResultSet rs = pst.executeQuery();
		
			response.setContentType("text/html");
			if (rs.next())
			{
				String name = rs.getString(1);
				response.getWriter().println("<h1 style = 'color : Grey'>Welcome : "+name+" <h1>");
				RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
				rd.include(request, response);
			}
			else
			{
				response.getWriter().print ("<h4 style = 'color : red'>Your Username & Password does not match !!! <br> <br> Try Again !!<h4>" );
    			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
				rd.include(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			response.getWriter().print ("<h4 style = 'color : red'> Error occured : "+e.getMessage()+" !!! <br> <br> Try Again !! <h4>" );
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
			e.printStackTrace();
		}
				
	}

}
