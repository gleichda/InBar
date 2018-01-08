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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import inbar.beans.EventBean;
import inbar.beans.UserBean;

/**
 * Servlet implementation class EigeneBars
 */
@WebServlet("/BarEvents")
public class BarEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarEvents() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		UserBean selfUser = (UserBean) session.getAttribute("selfUser");
		if (selfUser != null) {
			int userId = selfUser.getUserid();
			List<EventBean> eventList = new ArrayList<EventBean>();
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement(
							"SELECT * FROM event_zu_bar WHERE barid=?" ))
			{
				int bar = Integer.parseInt(request.getParameter("barid"));	
				//statement.setInt(1, userId);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					EventBean event = new EventBean();
					event.setEventid(rs.getInt("event.eventid"));
					event.setEventname(rs.getString("event.eventname"));
					event.setStartdatum(rs.getDate("startdatum"));
					event.setStartzeit(rs.getTime("startzeit"));
					event.setEnddatum(rs.getDate("endzeit"));
					event.setEndzeit(rs.getTime("endzeit"));
					event.setEbeschreibung(rs.getString("ebeschreibung"));
					eventList.add(event);
				}
				if (!eventList.isEmpty()) {
					System.out.println("Ergebnisse enthalten");
					request.setAttribute("barEvents", eventList);
	
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
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("keineEigenenBars.jsp");
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
