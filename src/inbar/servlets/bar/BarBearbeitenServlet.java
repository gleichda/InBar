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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import inbar.beans.UserBean;

/**
 * Servlet implementation class BarBearbeitenServlet
 * @author david
 */
@WebServlet("/BarBearbeiten")
public class BarBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (lookup = "jdbc/InBar")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarBearbeitenServlet() {
        super();    
        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		//Falls der User nicht eingeloggt ist wird er auf die nicht Eingeloggt Seite verwiesen weil selfUser dann NULL zurück gibt
		UserBean selfUser = (UserBean) session.getAttribute("selfUser");
		if (selfUser == null) {
			final RequestDispatcher dispatcher = request.getRequestDispatcher("nichtEingeloggt.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		int barid = Integer.parseInt(request.getParameter("barid"));		
		String barname = request.getParameter("barname");
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String chefmail = request.getParameter("chefmail");
		String strasse = request.getParameter("strasse");
		String hausnummer = request.getParameter("hausnummer");
		String plz = request.getParameter("plz");
		String ort = request.getParameter("ort");
		String barmail = request.getParameter("barmail");
		String bbeschreibung = request.getParameter("bbeschreibung");
		String mbeschreibung = request.getParameter("mbeschreibung");
		String lbeschreibung = request.getParameter("lbeschreibung");
		

		
		try(Connection con = ds.getConnection();
				PreparedStatement userStatement = con.prepareStatement("SELECT * from bar_zu_user WHERE userid = ? AND barid = ?");
				PreparedStatement statement = con.prepareStatement("UPDATE bar SET barname = ?, vorname = ?, nachname = ?, chefmail = ?, strasse = ?, hausnummer = ?, "
						+ "plz = ?, ort = ?, barmail = ?, bbeschreibung = ?, mbeschreibung = ?, lbeschreibung = ? WHERE barid = ? "))
		{
			userStatement.setInt(1, selfUser.getUserid());
			userStatement.setInt(2, barid);
			ResultSet userZuBar = userStatement.executeQuery();
			
			if (userZuBar.first()) { //falls eine Zuordnung von userid und barid existiert ausführen
				statement.setString(1, barname);
				statement.setString(2, vorname);
				statement.setString(3, nachname);
				statement.setString(4, chefmail);
				statement.setString(5, strasse);
				statement.setString(6, hausnummer);
				statement.setString(7, plz);
				statement.setString(8, ort);
				statement.setString(9, barmail);
				statement.setString(10, bbeschreibung);
				statement.setString(11, mbeschreibung);
				statement.setString(12, lbeschreibung);
				statement.setInt(13, barid);
<<<<<<< HEAD
				//05.01 Start Sabine Bar f�r Session merken zur Eventbearbeitung
				//session.setAttribute("barid", barid);
				//05.01 Ende Sabine Bar f�r Session merken zur Eventbearbeitung
=======

>>>>>>> refs/remotes/origin/master
				statement.executeUpdate();
			}
		}catch  (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("erfolgreichGeaendert.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
