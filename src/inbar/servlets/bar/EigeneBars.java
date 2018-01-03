package inbar.servlets.bar;

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

import inbar.beans.BarBean;
import inbar.beans.UserBean;

/**
 * Servlet implementation class EigeneBars
 */
@WebServlet("/MeineBars")
public class EigeneBars extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EigeneBars() {
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
			List<BarBean> barsList = new ArrayList<BarBean>();
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement(
							"SELECT * FROM ((bar LEFT JOIN musik_zu_bar ON bar.barid=musik_zu_bar.barid) LEFT JOIN musikarten ON musik_zu_bar.musikid=musikarten.musikid) LEFT JOIN bar_zu_user ON bar_zu_user.barid=bar.barid WHERE bar_zu_user.userid = ?")) 
			{
				statement.setInt(1, userId);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
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
					barsList.add(bar);
				}
				if (!barsList.isEmpty()) {
					System.out.println("Ergebnisse enthalten");
					request.setAttribute("eigeneBars", barsList);
	
					RequestDispatcher dispatcher = request.getRequestDispatcher("meineBars.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("keineEigenenBars.jsp");
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
