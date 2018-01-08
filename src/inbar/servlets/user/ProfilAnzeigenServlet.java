package inbar.servlets.user;

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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import inbar.beans.UserBean;

/**
 * Servlet implementation class ProfilAnzeigen
 * @author Sabine
 */
@WebServlet("/ProfilAnzeigen")
public class ProfilAnzeigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilAnzeigenServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		final HttpSession session = request.getSession();
		
		
		UserBean selfUser = (UserBean) session.getAttribute("selfUser");
		
		
		if (request.getParameter("id") != null) {
			int userid = Integer.parseInt(request.getParameter("id"));
			
			//Falls der User eingeloggt ist und sich selbst sucht
			if (selfUser != null && selfUser.getUserid() == userid) {
				request.setAttribute("user", selfUser);
				final RequestDispatcher dispatcher = request.getRequestDispatcher("profilAnzeigen.jsp");
				dispatcher.forward(request, response);
			} 
			
			else {
				try (Connection con = ds.getConnection();
						PreparedStatement statement = con.prepareStatement("SELECT * FROM benutzer WHERE userid = ?")) {
					statement.setInt(1, userid);
					ResultSet rs = statement.executeQuery();
					if (rs.next()) {
						UserBean user = new UserBean();
						user.setUserid(rs.getInt("userid"));
						user.setUsername(rs.getString("benutzername"));
						user.setVorname(rs.getString("vorname"));
						user.setNachname(rs.getString("nachname"));
						user.setEmail(rs.getString("email"));
						user.setPassword(rs.getString("passwort"));
						request.setAttribute("user", user);
						final RequestDispatcher dispatcher = request.getRequestDispatcher("profilAnzeigen.jsp");
						dispatcher.forward(request, response);
					} else {
						final RequestDispatcher dispatcher = request.getRequestDispatcher("profilExistiertNicht.jsp");
						dispatcher.forward(request, response);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace(System.out);
				}
			}
		}
		
		//Falls dem User sein eigenes Profil angezeigt werden soll
		else if (selfUser != null){
			request.setAttribute("user", selfUser);
			final RequestDispatcher dispatcher = request.getRequestDispatcher("profilAnzeigen.jsp");
			dispatcher.forward(request, response);
		}
		else {
			final RequestDispatcher dispatcher = request.getRequestDispatcher("profilExistiertNicht.jsp");
			dispatcher.forward(request, response);
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
