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
import javax.sql.DataSource;

import inbar.beans.UserBean;

/**
 * Servlet implementation class BarLoeschenServlet
 * @author david
 * @date 14.01.2018
 */
@WebServlet("/BarLoeschenServlet")
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
		UserBean selfUser = (UserBean) request.getAttribute("selfUser");
		int barid = Integer.parseInt(request.getParameter("barid"));
		
		try (Connection con = ds.getConnection();
				PreparedStatement eventStatement = con.prepareStatement("SELECT * FROM event_zu_bar WHERE barid = ?");
				PreparedStatement barLoeschenStatement = con.prepareStatement("DELETE * FROM bar WHERE barid = ?");
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
				//TODO: bar löschen
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
		//TODO: Events Löschen
		return;
	}

}
