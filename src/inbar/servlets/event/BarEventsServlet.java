package inbar.servlets.event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

/**
 * Servlet implementation class EigeneBars
 * @author sabine
 */
@WebServlet("/BarEvents")
public class BarEventsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarEventsServlet() {
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
			int barid = Integer.parseInt(request.getParameter("id"));	
			List<EventBean> eventList = new ArrayList<EventBean>();
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * FROM event_zu_bar LEFT JOIN event ON event_zu_bar.eventid = event.eventid WHERE barid=?" ))
			{

				statement.setInt(1, barid);
				//Ausgabe barid
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					EventBean event = new EventBean();
					event.setEventid(rs.getInt("event.eventid"));
					event.setEventname(rs.getString("event.eventname"));
					event.setStartdatum(rs.getDate("startdatum"));
					event.setStartzeit(rs.getTime("startzeit"));
					event.setEnddatum(rs.getDate("enddatum"));
					event.setEndzeit(rs.getTime("endzeit"));
					event.setEbeschreibung(rs.getString("ebeschreibung"));
					eventList.add(event);
					
				}
				
				if (!eventList.isEmpty()) {
					request.setAttribute("barEvents", eventList);
					request.setAttribute("id", barid);
					
	
					RequestDispatcher dispatcher = request.getRequestDispatcher("barEventListe.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("keineEvents.jsp");
					dispatcher.forward(request, response);
				}
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.out);
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
