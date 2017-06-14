package inbar.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inbar.beans.SuchBean;


/**
 * Servlet implementation class Suchservlet
 */
@WebServlet("/Suchservlet")
public class Suchservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Suchservlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //erwartetes Format der Formulardaten
		
		//damit k�nnen die Getter- und Setter-Methoden der SuchBean �ber "form" angesprochen werden
		SuchBean form = new SuchBean();
		
		form.setMusikart(request.getParameterValues("musikart"));
		form.setSuchbegriff(request.getParameter("suchbegriff"));
		form.setSuchart(request.getParameter("suchart"));

		final HttpSession session = request.getSession();
		session.setAttribute("form", form);

		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("suchergebnisse.jsp");
		dispatcher.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
