package inbar.servlets.event;

//import java.io.ByteArrayOutputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;
import javax.sql.DataSource;

import inbar.beans.BarBean;
//import inbar.beans.BildBean;
import inbar.beans.EventBean;
import inbar.beans.UserBean;
//import inbar.datenbank.BildHandler;

/**
 * Servlet implementation class RegisterServlet
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
		
		final HttpSession session = request.getSession();
		
		UserBean user = (UserBean) session.getAttribute("selfUser");
		//BarBean bar = (BarBean) session.getAttribute("baruser");
		int bar = Integer.parseInt(request.getParameter("barid"));	
				
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * from event where eventname LIKE ?"))

			{
				statement.setString(1, request.getParameter("eventname"));
				ResultSet rs = statement.executeQuery();

				EventBean event = new EventBean();
				
				
				//EventBean füllen
				event.setEventname(request.getParameter("eventname"));
				event.setEbeschreibung(request.getParameter("ebeschreibung"));
				
				//Vorlage Ex11Num01CreateServlet.java Zeile 41-49 
				// Datum im Format yyyy-mm-dd
				String dateString = request.getParameter("datum");
				String[] dateArray = dateString.split("-");
				Calendar cal = Calendar.getInstance();
				int year = Integer.parseInt(dateArray[0]);
				int month = Integer.parseInt(dateArray[1])-1;
				int day = Integer.parseInt(dateArray[2]);
				cal.set(year, month, day);
				event.setDatum(cal.getTime());
				
				
				//Vorlage Ex11Num01CreateServlet.java Zeile 51-55
				// Zeitfeld für Startzeit auswerten - Eingangsformat hh:mm
				String startTimeString = request.getParameter("start");
				String[] startTimeArray = startTimeString.split(":");
				cal.set(year, month, day, Integer.parseInt(startTimeArray[0]), Integer.parseInt(startTimeArray[1]));
				event.setStartzeit(cal.getTime());
				
				// Zeitfeld für Endzeit auswerten - Eingangsformat hh:mm
				String endTimeString = request.getParameter("ende");
				String[] endTimeArray = endTimeString.split(":");
				cal.set(year, month, day, Integer.parseInt(endTimeArray[0]), Integer.parseInt(endTimeArray[1]));
				event.setEndzeit(cal.getTime());
				
				eventSpeichern(event);
				eventZuBarZuweisen(event, bar);
				
				//session.setAttribute("event", event);
				
				final RequestDispatcher dispatcher = request.getRequestDispatcher("eventAnzeige.jsp");
				dispatcher.forward(request, response);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.out);
			}
	
		}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}		

	
	//start 05.01 Sabine eventSpeichern angelegt
	private void eventSpeichern (EventBean event) throws ServletException{
		String[] generatedKeys = new String[] { "eventid" }; // Aus Skript JDBC Folie 12 uebernommen
		try (Connection con = ds.getConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO event(eventname, ebeschreibung, startzeit, endzeit, datum) VALUES (?, ?, ?, ?, ?)", generatedKeys);
			){
			statement.setString(1, event.getEventname());
			statement.setString(2, event.getEbeschreibung());
			statement.setTime(3, new java.sql.Time(event.getStartzeit().getTime()));  //Vorlage Ex11Num01CreateServlet.java Zeile 80 +81
			statement.setTime(4, new java.sql.Time(event.getEndzeit().getTime()));
			statement.setDate(5, new java.sql.Date(event.getDatum().getTime()));

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
