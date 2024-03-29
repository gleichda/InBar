package inbar.servlets.suche;

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
 * @author david
 */
@WebServlet("/BarSuche")
public class BarSuchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BarSuchServlet() {
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
		


			List<BarBean> barsList = new ArrayList<BarBean>();
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement(
							"SELECT * FROM ((bar LEFT JOIN musik_zu_bar ON bar.barid=musik_zu_bar.barid) LEFT JOIN musikarten ON musik_zu_bar.musikid=musikarten.musikid) WHERE bar.barname LIKE ?")) 
			{
				if (suchbegriff != "" && suchbegriff != null){
					statement.setString(1, "%" + suchbegriff + "%");
				}
				else {
					statement.setString(1, "%");
				}
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					if (musikart < 0 || rs.getInt("musik_zu_bar.musikid") == musikart) {
					BarBean bar = new BarBean();
					bar.setBarid(rs.getInt("bar.barid"));
					bar.setBarname(rs.getString("bar.barname"));
					bar.setVorname(rs.getString("bar.vorname"));
					bar.setNachname(rs.getString("bar.nachname"));
					bar.setChefmail(rs.getString("bar.chefmail"));
					bar.setStrasse(rs.getString("bar.strasse"));
					bar.setHausnummer(rs.getString("bar.hausnummer"));
					bar.setPlz(rs.getString("bar.plz"));
					bar.setOrt(rs.getString("bar.ort"));
					bar.setBarmail(rs.getString("bar.barmail"));
					bar.setBbeschreibung(rs.getString("bar.bbeschreibung"));
					bar.setMbeschreibung(rs.getString("bar.mbeschreibung"));
					bar.setLbeschreibung(rs.getString("bar.lbeschreibung"));
					barsList.add(bar);}
				}
				if (!barsList.isEmpty()) {
					request.setAttribute("suchergebnisse", barsList);

					RequestDispatcher dispatcher = request.getRequestDispatcher("suchergebnisse.jsp");
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
