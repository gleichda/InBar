package inbar.servlets.event;

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

import inbar.beans.EventBean;

/**
 * Servlet implementation class BarBearbeitenServlet
 * @author sabine
 */
@WebServlet("/EventAnzeigen")
public class EventAnzeigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventAnzeigenServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//15.01 Sabine Test UTF-8 encoding
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//Ende test
		
		if (request.getParameter("id") != null) {
			int eventID = Integer.parseInt(request.getParameter("eventid"));
			System.out.println("Inhalt von eventid: " + eventID);


			try (Connection con = ds.getConnection();
					PreparedStatement eventStatement = con.prepareStatement("SELECT * FROM event where eventid LIKE ?");
					PreparedStatement barStatement = con.prepareStatement("SELECT * FROMT bar where barid =?"))
			{
				eventStatement.setInt(1, eventID);


				ResultSet eventRs = eventStatement.executeQuery();

				if (eventRs.first() && eventRs.getInt("event.eventid") == eventID) {
					EventBean event = new EventBean();
					event.setEventname(eventRs.getString("eventname"));
					event.setStartdatum(eventRs.getDate("startdatum"));
					event.setStartzeit(eventRs.getTime("startzeit"));
					event.setEnddatum(eventRs.getDate("enddatum"));
					event.setEndzeit(eventRs.getTime("endzeit"));
					event.setEbeschreibung(eventRs.getString("ebeschreibung"));
					event.setEventid(eventID);

					request.setAttribute("event", event);

					
				

					if (request.getParameter("bearbeiten") != null) {

						final RequestDispatcher dispatcher = request.getRequestDispatcher("eventBearbeiten.jsp");
						dispatcher.forward(request, response);
						request.setAttribute("id", request.getParameter("id"));
					}

					else {
						final RequestDispatcher dispatcher = request.getRequestDispatcher("eventAnzeige.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					final RequestDispatcher dispatcher = request.getRequestDispatcher("eventExistiertNicht.jsp");
					dispatcher.forward(request, response);
				}

			} catch (Exception e) {
				throw new ServletException(e.getMessage());
			}
		} else {
			final RequestDispatcher dispatcher = request.getRequestDispatcher("eventExistiertNicht.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
