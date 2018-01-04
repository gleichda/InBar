package inbar.servlets.bar.bewertung;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import inbar.beans.UserBean;

/**
 * Servlet implementation class NeueBewertungServlet
 * @author david
 * @date 04.01.2018
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/NeueBewertung" })
public class NeueBewertungServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NeueBewertungServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("selfUser");
		if (user != null) {
			int barid = Integer.parseInt(request.getParameter("barid"));
			int bewertung = Integer.parseInt(request.getParameter("bewertung"));
			String kommentar = request.getParameter("kommentar");
			
			try(Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("INSERT INTO bewertung (barid, userid, bewertung, kommentar) VALUES (?, ?, ?, ?)");){
				statement.setInt(1, barid);
				statement.setInt(2, user.getUserid());
				statement.setInt(3, bewertung);
				statement.setString(4, kommentar);
				statement.executeUpdate();
				
				System.out.println("In DB geschrieben");
				final RequestDispatcher dispatcher = request.getRequestDispatcher("dummy.html");
				dispatcher.forward(request, response);	
				System.out.println("Dispatcher hat gearbeitet");

			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.out);
			}
			 
			
		}
		else {
			// Rückgabe Fehlercode, wenn kein User eingeloggt ist
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

}
