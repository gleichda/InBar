package inbar.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import inbar.beans.BarBean;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/BarLoginServlet")
public class BarLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BarLoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String barname = (String) request.getParameter("barname");
		String passwort = (String) request.getParameter("passwort");

		String query = "SELECT * FROM bar WHERE barname LIKE '" + barname + "'";
		System.out.println(query);

		final HttpSession session = request.getSession();

		try (Connection con = ds.getConnection();
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(query);) {
			if (rs.next()) {
				System.out.println("RecordSet Passwort: " + rs.getString("passwort"));
				if (passwort.equals(rs.getString("passwort"))) {
					System.out.println("Login erfolgreich");
					BarBean baruser = new BarBean();
					baruser.setBarname(rs.getString("barname"));
					baruser.setBarmail(rs.getString("barmail"));
					baruser.setVorname(rs.getString("vorname"));
					baruser.setNachname(rs.getString("nachname"));
					baruser.setChefmail(rs.getString("chefmail"));
					
					session.removeAttribute("fehlgeschlageneLoginVersuche");
					session.setAttribute("baruser", baruser);
					
					final RequestDispatcher dispatcher = request.getRequestDispatcher("barLoginErfolgreich.jsp");
					dispatcher.forward(request, response);
					
				} else {
					System.out.println("Falsche Zugangsdaten. Benutzer: " + barname + "; Passwort: " +passwort);
					loginFehlgeschlagen(session, request, response);
				}
			}
			else {
				System.out.println("leeres RecordSet");
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

		final RequestDispatcher dispatcher = request.getRequestDispatcher("barLogin.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
