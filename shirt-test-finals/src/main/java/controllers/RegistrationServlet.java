package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Participant;
import repositories.Repository;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Repository collection;
	
	public void init(ServletConfig config) throws ServletException {	
		collection = Repository.getInstance();	 
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String phone = request.getParameter("phone");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if (code == null || code.isEmpty() || firstName == null || firstName.isEmpty()
				|| lastName == null || lastName.isEmpty() || phone == null || phone.isEmpty()) {
			out.print("<p>Не сте въвели всички полета!</p>");	
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
		} else {
			Participant participant = new Participant(code, firstName, lastName, phone);
			if (collection.getParticipantByName(firstName) == null) {
				collection.addParticipant(participant);
				HttpSession newSession = request.getSession();
				newSession.setAttribute("participant", participant);
				response.sendRedirect("mydata");
			} else {
				out.print("<p>Този код е вече регистриран!</p>");	
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.include(request, response);
			}
		}
	}

}
