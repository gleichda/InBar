package inbar.servlets.event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import inbar.beans.EventBean;
import inbar.beans.UserBean;

/**
 * Servlet implementation class BarBearbeitenServlet
 * @author Sabine
 */
@WebServlet("/EventBearbeiten")
public class EventBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (lookup = "jdbc/InBar")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventBearbeitenServlet() {
        super();    
        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		//Falls der User nicht eingeloggt ist wird er auf die nicht Eingeloggt Seite verwiesen weil selfUser dann NULL zurück gibt
		UserBean selfUser = (UserBean) session.getAttribute("selfUser");
		if (selfUser == null) {
			final RequestDispatcher dispatcher = request.getRequestDispatcher("nichtEingeloggt.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		int eventid = Integer.parseInt(request.getParameter("eventid"));	
		int barid = Integer.parseInt(request.getParameter("id"));
		//System.out.println("Inhalt der barid " + barid);
		String eventname = request.getParameter("eventname");
		String startdatum = request.getParameter("startdatum");
		String startzeit = request.getParameter("startzeit");
		String enddatum = request.getParameter("enddatum");
		String endzeit = request.getParameter("endzeit");
		String ebeschreibung = request.getParameter("ebeschreibung");	

		
		try(Connection con = ds.getConnection();
				PreparedStatement zuordnungStatement = con.prepareStatement("SELECT * from event_zu_bar WHERE eventid = ? AND barid = ?");
				PreparedStatement statement = con.prepareStatement("UPDATE bar SET eventname = ?, startdatum = ?, startzeit = ?, enddatum = ?, endzeit = ?, ebeschreibung = ?, WHERE eventid = ? "))
		{
			EventBean event = new EventBean();
			
			zuordnungStatement.setInt(1, eventid);
			zuordnungStatement.setInt(2, barid);
			ResultSet eventZuBar = zuordnungStatement.executeQuery();
			
			if (eventZuBar.first()) { //falls eine Zuordnung von eventid und barid existiert ausführen
				statement.setString(1, eventname);
				statement.setDate(2, new java.sql.Date(event.getStartdatum().getTime())); 
				statement.setTime(3,  new java.sql.Time(event.getStartzeit().getTime()));
				statement.setDate(4, new java.sql.Date(event.getEnddatum().getTime())); 
				statement.setTime(5,  new java.sql.Time(event.getEndzeit().getTime()));			
				statement.setString(6, ebeschreibung);


				statement.executeUpdate();
			}
		}catch  (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("erfolgreichGeaendert.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
