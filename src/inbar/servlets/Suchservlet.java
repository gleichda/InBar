package inbar.servlets;

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

import inbar.beans.BarBean;

/**
 * Servlet implementation class Suchservlet
 */
@WebServlet("/Suchservlet")
public class Suchservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Suchservlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // erwartetes Format der
												// Formulardaten

		int musikart = Integer.parseInt(request.getParameter("musikart"));
		String suchbegriff = request.getParameter("suchbegriff");
		String suchart = request.getParameter("suchart");
		//TODO: alle debug System out entfernen
		System.out.println("Suchservlet aufgerufen: Suchart: " + suchart);

		switch (suchart.toLowerCase()) {
		case "bar":
			System.out.println("case Bar");
			List<BarBean> barsList = new ArrayList<BarBean>();
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement(
							"SELECT * FROM ((musik_zu_bar INNER JOIN bar ON bar.barid=musik_zu_bar.barid)" + ""
									+ " INNER JOIN musikarten ON musik_zu_bar.musikid=musikarten.musikid)"
									+ " WHERE musik_zu_bar.musikid = ? AND bar.barname LIKE ?")) {
				statement.setInt(1, musikart);
				statement.setString(2, "%" + suchbegriff + "%");
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					System.out.println("bar hinzugefuegt");
					BarBean bar = new BarBean();
					bar.setBarid(rs.getInt("bar.barid"));
					bar.setBarname(rs.getString("bar.barname"));
					// TODO: bar vervollstaendigen
					barsList.add(bar);
				}
				if (!barsList.isEmpty()) {
					System.out.println("BarsList laenge: " + barsList.size());
					request.setAttribute("suchergebnisse", barsList);

					RequestDispatcher dispatcher = request.getRequestDispatcher("suchergebnisse.jsp");
					dispatcher.forward(request, response);
				} else {
					System.out.println("Bars List leer");
					RequestDispatcher dispatcher = request.getRequestDispatcher("barExistiertNicht.jsp");
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
			break;
		case "event":
			// TODO: event suche schreiben
			break;

		default:
			break;
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
