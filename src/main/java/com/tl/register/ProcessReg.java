package com.tl.register;
import com.tl.model.Details;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ProcessReg
 */
//@WebServlet("/ProcessReg")
public class ProcessReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fname = request.getParameter("fullName");
		String email = request.getParameter("email");
		String uname = request.getParameter("username");
		String pword = request.getParameter("password");
		//Date dob = new Date(request.getParameter("dob"));
		String gender = request.getParameter("gender");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Done");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AccDetails","root","Seed008@");
			//System.out.println("Connection Done");
			
			Details d = new Details ();
			d.setName(fname);
			d.setEmail(email);
			d.setUname(uname);
			d.setPword(pword);
			d.setGender(gender);
			PreparedStatement pst = con.prepareStatement(" Insert into person_details values (?,?,?,?,?) ");
			pst.setString(1, d.getName());
			pst.setString(2, d.getEmail());
			pst.setString(3, d.getUname());
			pst.setString(4, d.getPword());
			pst.setString(5, d.getGender());
			int result = pst.executeUpdate();
		
			response.setContentType("text/html");
			if (result > 0)
			{
				response.setContentType("text/html");
				//System.out.println("Your account is created successfully !!!");
				response.getWriter().print ( "<h3 style = 'color : green'>Your account is created successfully !!! </h3>" );
				RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
				rd.include(request, response);
			}
			else
			{
				response.setContentType("text/html");
				response.getWriter().print (" <h3 style = 'color : red'> Your account could not be created !!! <br> <br> Try Again !! </h3>" );
    			RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
				rd.include(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			response.setContentType("text/html");
			response.getWriter().print ("<h3 style = 'color : red'> Your account could not be created as : "+e.getMessage()+" <br> <br> Try Again !! </h3>" );
			RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
			rd.include(request, response);
			e.printStackTrace();
		}
		
		
		
		
	}

}
