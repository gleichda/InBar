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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String benutzer = (String) request.getParameter("benutzer");
		String passwort = (String) request.getParameter("passwort");

		final HttpSession session = request.getSession();

		try (Connection con = ds.getConnection();
				PreparedStatement statement = con.prepareStatement("SELECT * FROM benutzer WHERE benutzername LIKE ?");
				) {
			statement.setString(1, benutzer);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				if (passwort.equals(rs.getString("passwort"))) {
					UserBean user = new UserBean();
					user.setUsername(rs.getString("benutzername"));
					user.setEmail(rs.getString("email"));
					user.setVorname(rs.getString("vorname"));
					user.setNachname(rs.getString("nachname"));
					user.setUserid(rs.getInt("userid"));
					
					session.removeAttribute("fehlgeschlageneLoginVersuche");
					session.setAttribute("selfUser", user);
					
					final RequestDispatcher dispatcher = request.getRequestDispatcher("loginErfolgreich.jsp");
					dispatcher.forward(request, response);
					
				} else {
					loginFehlgeschlagen(session, request, response);
				}
			}
			else {
				loginFehlgeschlagen(session, request, response);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
	}

	private void loginFehlgeschlagen(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute("fehlgeschlageneLoginVersuche") == null) {
			session.setAttribute("fehlgeschlageneLoginVersuche", 1);
		} else {
			session.setAttribute("fehlgeschlageneLoginVersuche",
					((int) session.getAttribute("fehlgeschlageneLoginVersuche")) + 1);
		}

		final RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
