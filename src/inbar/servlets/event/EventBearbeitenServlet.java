package inbar.servlets.event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
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
		
		//15.01 Sabine Test UTF-8 encoding
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//Ende test
		
		//Falls der User nicht eingeloggt ist wird er auf die nicht Eingeloggt Seite verwiesen weil selfUser dann NULL zurück gibt
		UserBean selfUser = (UserBean) session.getAttribute("selfUser");
		if (selfUser == null) {
			final RequestDispatcher dispatcher = request.getRequestDispatcher("nichtEingeloggt.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		EventBean event = new EventBean();
		int eventid = Integer.parseInt(request.getParameter("eventid"));	
		String eventname = request.getParameter("eventname");
		String ebeschreibung = request.getParameter("ebeschreibung");
		
		String startdateString = request.getParameter("startdatum");
		String[] startdateArray = startdateString.split("-");
		Calendar startcal = Calendar.getInstance();
		int startyear = Integer.parseInt(startdateArray[0]);
		int startmonth = Integer.parseInt(startdateArray[1])-1;
		int startday = Integer.parseInt(startdateArray[2]);
		startcal.set(startyear, startmonth, startday);
		event.setStartdatum(startcal.getTime());
		
		String enddateString = request.getParameter("enddatum");
		String[] enddateArray = enddateString.split("-");
		Calendar endcal = Calendar.getInstance();
		int endyear = Integer.parseInt(enddateArray[0]);
		int endmonth = Integer.parseInt(enddateArray[1])-1;
		int endday = Integer.parseInt(enddateArray[2]);
		endcal.set(endyear, endmonth, endday);
		event.setEnddatum(endcal.getTime());
		
		//Vorlage Ex11Num01CreateServlet.java Zeile 51-55
		// Zeitfeld f�r Startzeit auswerten - Eingangsformat hh:mm
		String startTimeString = request.getParameter("startzeit");
		String[] startTimeArray = startTimeString.split(":");
		startcal.set(startyear, startmonth, startday, Integer.parseInt(startTimeArray[0]), Integer.parseInt(startTimeArray[1]));
		event.setStartzeit(startcal.getTime());
		
		// Zeitfeld f�r Endzeit auswerten - Eingangsformat hh:mm
		String endTimeString = request.getParameter("endzeit");
		String[] endTimeArray = endTimeString.split(":");
		endcal.set(endyear, endmonth, endday, Integer.parseInt(endTimeArray[0]), Integer.parseInt(endTimeArray[1]));
		event.setEndzeit(endcal.getTime());
		
		try(Connection con = ds.getConnection();
				PreparedStatement zuordnungStatement = con.prepareStatement("SELECT * from event_zu_bar WHERE eventid = ?");
				PreparedStatement statement = con.prepareStatement("UPDATE event SET eventname = ?, startdatum = ?, startzeit = ?, enddatum = ?, endzeit = ?, ebeschreibung = ? WHERE eventid = ? "))
		{

			
			zuordnungStatement.setInt(1, eventid);
			ResultSet eventZuBar = zuordnungStatement.executeQuery();
			
			if (eventZuBar.first()) { //falls eine Zuordnung von eventid und barid existiert ausführen
				statement.setString(1, eventname);
				statement.setDate(2,  new java.sql.Date(event.getStartdatum().getTime()));
				statement.setTime(3,  new java.sql.Time(event.getStartzeit().getTime()));
				statement.setDate(4, new java.sql.Date(event.getEnddatum().getTime())); 
				statement.setTime(5,  new java.sql.Time(event.getEndzeit().getTime()));			
				statement.setString(6, ebeschreibung);
				statement.setString(7, request.getParameter("eventid"));


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
