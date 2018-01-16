package inbar.servlets.event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import inbar.beans.EventBean;

/**
 * Servlet implementation class RegisterServlet
 * @author sabine
 */
@WebServlet("/EventAnlegenServlet")
@MultipartConfig 
public class EventAnlegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(lookup="jdbc/InBar")
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventAnlegenServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		//15.01 Sabine Test UTF-8 encoding
		response.setCharacterEncoding("UTF-8");
		//Ende test
		
		
		int bar = Integer.parseInt(request.getParameter("barid"));	
				
				EventBean event = new EventBean();
				
				
				//EventBean f�llen
				event.setEventname(request.getParameter("eventname"));
				event.setEbeschreibung(request.getParameter("ebeschreibung"));
				
				//Vorlage Ex11Num01CreateServlet.java Zeile 41-49 
				// Datum im Format yyyy-mm-dd
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
				String startTimeString = request.getParameter("start");
				String[] startTimeArray = startTimeString.split(":");
				startcal.set(startyear, startmonth, startday, Integer.parseInt(startTimeArray[0]), Integer.parseInt(startTimeArray[1]));
				event.setStartzeit(startcal.getTime());
				
				// Zeitfeld f�r Endzeit auswerten - Eingangsformat hh:mm
				String endTimeString = request.getParameter("ende");
				String[] endTimeArray = endTimeString.split(":");
				endcal.set(endyear, endmonth, endday, Integer.parseInt(endTimeArray[0]), Integer.parseInt(endTimeArray[1]));
				event.setEndzeit(endcal.getTime());
				
				eventSpeichern(event);
				eventZuBarZuweisen(event, bar);
				
				request.setAttribute("event", event);
				
				final RequestDispatcher dispatcher = request.getRequestDispatcher("eventAnzeige.jsp");
				dispatcher.forward(request, response);

		}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}		

	
	//start 05.01 Sabine eventSpeichern angelegt
	private void eventSpeichern (EventBean event) throws ServletException{
		String[] generatedKeys = new String[] { "eventid" }; // Aus Skript JDBC Folie 12 uebernommen
		try (Connection con = ds.getConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO event(eventname, ebeschreibung, startzeit, endzeit, startdatum, enddatum) VALUES (?, ?, ?, ?, ?, ?)", generatedKeys);
			){
			statement.setString(1, event.getEventname());
			statement.setString(2, event.getEbeschreibung());
			statement.setTime(3, new java.sql.Time(event.getStartzeit().getTime()));  //Vorlage Ex11Num01CreateServlet.java Zeile 80 +81
			statement.setTime(4, new java.sql.Time(event.getEndzeit().getTime()));
			statement.setDate(5, new java.sql.Date(event.getStartdatum().getTime()));
			statement.setDate(6, new java.sql.Date(event.getEnddatum().getTime()));

			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			rs.first();
			event.setEventid(rs.getInt(1));
		}		
		catch (Exception e) {
				throw new ServletException(e.getMessage());
			}
	}
	//ende 05.01 Sabine eventSpeichern angelegt
	
	//start 03.01 Sabine Profilbild speichern
	private void eventZuBarZuweisen(EventBean event, int bar){
		try (Connection con = ds.getConnection();
				PreparedStatement eventBarStatement = con.prepareStatement("INSERT INTO event_zu_bar (eventid, barid) VALUES (?, ?)")) {
			eventBarStatement.setInt(1, event.getEventid());
			eventBarStatement.setInt(2, bar);
			eventBarStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	//ende 03.01 Sabine Profilbild speichern

}
