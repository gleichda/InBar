package inbar.servlets.bar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
 * Servlet implementation class BarLoeschenServlet
 * @author david
 * @date 14.01.2018
 */
@WebServlet("/BarLoeschen")
public class BarLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarLoeschenServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		UserBean selfUser = (UserBean) session.getAttribute("selfUser");
		int barid = Integer.parseInt(request.getParameter("barid"));
		
		try (Connection con = ds.getConnection();
				PreparedStatement eventStatement = con.prepareStatement("SELECT * FROM event_zu_bar WHERE barid = ?");
				PreparedStatement barLoeschenStatement = con.prepareStatement("DELETE FROM bar WHERE barid = ?");
				PreparedStatement userStatement = con.prepareStatement("SELECT * FROM bar_zu_user WHERE barid = ? AND userid = ?")) 
		{
			//Berechtigung des Users pruefen
			userStatement.setInt(1, barid);
			userStatement.setInt(2, selfUser.getUserid());
			
			ResultSet userRs = userStatement.executeQuery();
			
			if(userRs.first()) { //User ist berechtigt
				eventStatement.setInt(1, barid);
				ResultSet eventRs = eventStatement.executeQuery();
				while (eventRs.next()) { //Alle events der Bar löschen
					eventLoeschen(eventRs.getInt("eventid"));
				}
				barLoeschenStatement.setInt(1, barid);
				barLoeschenStatement.executeUpdate();
				final RequestDispatcher dispatcher = request.getRequestDispatcher("barGeloescht.jsp");
				dispatcher.forward(request, response);
				
			}
			else {
				final RequestDispatcher dispatcher = request.getRequestDispatcher("nichtEingeloggt.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
		
	}
	
	private void eventLoeschen(int eventid) {
		try (Connection con = ds.getConnection();
				PreparedStatement eventLoeschenStatement = con.prepareStatement("DELETE FROM event WHERE eventid = ?"))
		{
			eventLoeschenStatement.setInt(1, eventid);
			eventLoeschenStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
	}

}
