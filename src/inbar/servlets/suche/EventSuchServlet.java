package inbar.servlets.suche;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import inbar.beans.EventBean;
import inbar.beans.EventZuBarBean;

/**
 * Servlet implementation class Suchservlet
 * @author Sabine
 */
@WebServlet("/EventSuche")
public class EventSuchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventSuchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 


		String suchbegriff = request.getParameter("suchbegriff");

		String startdateString = request.getParameter("start");
		Date start;
		Date ende;
		
		if (!startdateString.isEmpty() && startdateString != null) {
			String[] startdateArray = startdateString.split("-");
			Calendar startcal = Calendar.getInstance();
			int startyear = Integer.parseInt(startdateArray[0]);
			int startmonth = Integer.parseInt(startdateArray[1])-1;
			int startday = Integer.parseInt(startdateArray[2]);
			startcal.set(startyear, startmonth, startday);
			start = startcal.getTime();
		}
		else {
			Calendar startcal = Calendar.getInstance();
			int startyear = 1970;
			int startmonth = 01;
			int startday = 01;
			startcal.set(startyear, startmonth, startday);
			start = startcal.getTime();
		}
		
		String enddateString = request.getParameter("ende");
		if (!enddateString.isEmpty() && enddateString != null) {
			String[] enddateArray = enddateString.split("-");
			Calendar endcal = Calendar.getInstance();
			int endyear = Integer.parseInt(enddateArray[0]);
			int endmonth = Integer.parseInt(enddateArray[1])-1;
			int endday = Integer.parseInt(enddateArray[2]);
			endcal.set(endyear, endmonth, endday);
			ende = endcal.getTime();
		}
		else {
			Calendar endcal = Calendar.getInstance();
			int endyear = 2038; //http://www.torsten-horn.de/techdocs/java-date.htm Ende des Wertebereichs unter 32-Bit-Programmierung am 2038-01-19
			int endmonth = 01;
			int endday = 18;
			endcal.set(endyear, endmonth, endday);
			ende = endcal.getTime();
		}



			List<EventBean> eventList = new ArrayList<EventBean>();
						
			
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * FROM event " + 
							"LEFT JOIN event_zu_bar ON event.eventid = event_zu_bar.eventid " + 
							"LEFT JOIN bar ON event_zu_bar.barid = bar.barid WHERE eventname LIKE ? " +
							"AND event.startdatum BETWEEN ? AND ?"))
			{
			
				//pruefen ob suchbegriff gesetzt ist
				if (!suchbegriff.isEmpty() && suchbegriff != null) {
					statement.setString(1, "%" + suchbegriff + "%");
				}
				else {
					statement.setString(1, "%");
				}
				
				statement.setDate(2, new java.sql.Date(start.getTime()));
				statement.setDate(3, new java.sql.Date(ende.getTime()));			
				
				ResultSet rs = statement.executeQuery();
				
				while (rs.next()) {
					
					EventZuBarBean event = new EventZuBarBean();

					event.setEventid(rs.getInt("event.eventid"));
					event.setEventname(rs.getString("event.eventname"));
					event.setStartdatum(rs.getDate("event.startdatum"));
					event.setStartzeit(rs.getTime("event.startzeit"));
					event.setEnddatum(rs.getDate("event.enddatum"));
					event.setEndzeit(rs.getTime("event.endzeit"));
					event.setEbeschreibung(rs.getString("event.ebeschreibung"));
					event.setBarname(rs.getString("bar.barname"));
					event.setBarid(rs.getInt("bar.barid"));

					eventList.add(event);					
					}

				if (!eventList.isEmpty()) {
					request.setAttribute("suchergebnisse", eventList);

					RequestDispatcher dispatcher = request.getRequestDispatcher("eventSuchergebnisse.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("keinSuchergebnis.jsp");
					dispatcher.forward(request, response);
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.out);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
