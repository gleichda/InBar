package inbar.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
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

import inbar.beans.BarBean;
import inbar.beans.EventBean;
import inbar.beans.EventZuBarBean;

/**
 * Servlet implementation class Suchservlet
 * @author david
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
		String[] startdateArray = startdateString.split("-");
		Calendar startcal = Calendar.getInstance();
		int startyear = Integer.parseInt(startdateArray[0]);
		int startmonth = Integer.parseInt(startdateArray[1])-1;
		int startday = Integer.parseInt(startdateArray[2]);
		startcal.set(startyear, startmonth, startday);
		Date start = startcal.getTime();
		
		String enddateString = request.getParameter("ende");
		String[] enddateArray = enddateString.split("-");
		Calendar endcal = Calendar.getInstance();
		int endyear = Integer.parseInt(enddateArray[0]);
		int endmonth = Integer.parseInt(enddateArray[1])-1;
		int endday = Integer.parseInt(enddateArray[2]);
		endcal.set(endyear, endmonth, endday);
		Date ende = endcal.getTime();

		
		System.out.println("Suche: Suchbegriff: " + suchbegriff);
		System.out.println("Start Suchzeitraum: " + start);
		System.out.println("Ende des Suchzeitraums: " + ende );


			List<EventBean> eventList = new ArrayList<EventBean>();
						
			
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * FROM event\r\n" + 
							"LEFT JOIN event_zu_bar ON event.eventid = event_zu_bar.eventid\r\n" + 
							"LEFT JOIN bar ON event_zu_bar.barid = bar.barid WHERE eventname LIKE ?\r\n" +
							"AND event.startdatum BETWEEN ? AND ?"))
			{
				if (suchbegriff != "" && suchbegriff != null){
					statement.setString(1, "%" + suchbegriff + "%");
					statement.setDate(2, new java.sql.Date(1970-01-01));
					statement.setDate(3, new java.sql.Date(2333-01-01));
					System.out.println("Subegriff nicht null");
					
				}
				
				else if (start != null ) {
					statement.setString(1, "%");
					statement.setDate(2, new java.sql.Date(start.getTime()));
					statement.setDate(3, new java.sql.Date(start.getTime()));
					System.out.println("Startdatum wurde eingetragen");
					System.out.println("Suche: Suchbegriff: " + suchbegriff);
					System.out.println("Start Suchzeitraum: " + start);
					System.out.println("Ende des Suchzeitraums: " + ende );
				}
				else {
					statement.setString(1, "%");
					statement.setDate(2, new java.sql.Date(start.getTime()));
					statement.setDate(3, new java.sql.Date(start.getTime()));
					System.out.println("Subegriff suchbegriff ist leer und start ist null");
				}

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

					eventList.add(event);					
					}

				System.out.println("EventList Size:" + eventList.size());
				if (!eventList.isEmpty()) {
					System.out.println("Ergebnisse enthalten");
					request.setAttribute("suchergebnisse", eventList);

					RequestDispatcher dispatcher = request.getRequestDispatcher("eventSuchergebnisse.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("keinSuchergebnis.jsp");
					dispatcher.forward(request, response);
				}

				/*
				 * Suchservlet und suchergebnisse.jsp teilweise erstellt mit
				 * Hilfe von
				 * https://stackoverflow.com/questions/23492233/arraylist-of-
				 * bean-how-to-access-the-property-of-a-bean-using-el-in-jsp-
				 * throw
				 */

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
