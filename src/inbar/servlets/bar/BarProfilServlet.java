package inbar.servlets.bar;

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

import inbar.beans.BarBean;

/**
 * Servlet implementation class BarAnzeigeServlet
 * @author Sabine
 */
@WebServlet("/BarProfil")
public class BarProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BarProfilServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO: wird das Benutzt falls ja mit BarAnzeigenServlet ersetzen
		System.out.println("BarProfilServlet benutzt mit Bar Anzeigen Servlet ersetzen");
		if (request.getParameter("id") != null) {
			int barID = Integer.parseInt(request.getParameter("id"));

			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * FROM bar where barid = ?")) {
				statement.setInt(1, barID);

				ResultSet rs = statement.executeQuery();

				if (rs.first() && rs.getInt("barid") == barID) {
					BarBean bar = new BarBean();
					bar.setBarname(rs.getString("barname"));
					bar.setVorname(rs.getString("vorname"));
					bar.setNachname(rs.getString("nachname"));
					bar.setChefmail(rs.getString("chefmail"));
					bar.setStrasse(rs.getString("strasse"));
					bar.setHausnummer(rs.getString("hausnummer"));
					bar.setPlz(rs.getString("plz"));
					bar.setOrt(rs.getString("ort"));
					bar.setBarmail(rs.getString("barmail"));
					bar.setBarid(barID);

					request.setAttribute("bar", bar);
					final RequestDispatcher dispatcher = request.getRequestDispatcher("barProfil.jsp");
					dispatcher.forward(request, response);
				} else {
					final RequestDispatcher dispatcher = request.getRequestDispatcher("barExistiertNicht.jsp");
					dispatcher.forward(request, response);
				}

			} catch (Exception e) {
				throw new ServletException(e.getMessage());
			}
		} else {
			final RequestDispatcher dispatcher = request.getRequestDispatcher("barExistiertNicht.jsp");
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
