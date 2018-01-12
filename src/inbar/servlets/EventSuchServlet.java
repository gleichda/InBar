package inbar.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
		
		System.out.println("Suche: Suchbegriff: " + suchbegriff);


			List<EventBean> eventList = new ArrayList<EventBean>();
			List<BarBean> barList = new ArrayList<BarBean>();
						
			
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * FROM event\r\n" + 
							"LEFT JOIN event_zu_bar ON event.eventid = event_zu_bar.eventid\r\n" + 
							"LEFT JOIN bar ON event_zu_bar.barid = bar.barid WHERE eventname LIKE ?"))
			{
				if (suchbegriff != "" && suchbegriff != null){
					statement.setString(1, "%" + suchbegriff + "%");
				}
				else {
					statement.setString(1, "%");
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
