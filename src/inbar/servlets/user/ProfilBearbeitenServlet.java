package inbar.servlets.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
 * Servlet implementation class ProfilBearbeitenServlet
 * @author david
 */
@WebServlet("/ProfilBearbeiten")
public class ProfilBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilBearbeitenServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    // Keine doGet Methode wegen Passwort
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("selfUser");
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String email = request.getParameter("email");
		String passwort = request.getParameter("passwort");
		try (Connection con = ds.getConnection();
				PreparedStatement vStatement = con.prepareStatement("UPDATE benutzer SET vorname = ? WHERE userid = ?");
				PreparedStatement nStatement = con.prepareStatement("UPDATE benutzer SET nachname = ? WHERE userid = ?");
				PreparedStatement eStatement = con.prepareStatement("UPDATE benutzer SET email = ? WHERE userid = ?");
				PreparedStatement pStatement = con.prepareStatement("UPDATE benutzer SET passwort = ? WHERE userid = ?")){
			if (vorname != user.getVorname()){
				vStatement.setInt(2, user.getUserid());;
				vStatement.setString(1, vorname);
				vStatement.executeUpdate();
				user.setVorname(vorname);
			}
			if (nachname != user.getNachname()){
				nStatement.setInt(2, user.getUserid());
				nStatement.setString(1, nachname);
				nStatement.executeUpdate();
				user.setNachname(nachname);
			}
			if (email != user.getEmail()){
				eStatement.setInt(2, user.getUserid());
				eStatement.setString(1, email);
				eStatement.executeUpdate();
				user.setEmail(email);
			}
			if (passwort != user.getPassword() && !passwort.isEmpty()){
				pStatement.setInt(2, user.getUserid());
				pStatement.setString(1, passwort);
				pStatement.executeUpdate();
				user.setPassword(passwort);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("erfolgreichGeaendert.jsp");
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
	}

}
