package inbar.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import inbar.beans.UserBean;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(lookup="jdbc/InBar")
	private DataSource ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//String read = "SELECT * from benutzer where benutzername LIKE '" + request.getParameter("benutzer") + "' OR email LIKE '" + request.getParameter("email") + "'";
		//System.out.println(read);
		
		try (Connection con = ds.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * from benutzer where benutzername LIKE ? OR email LIKE ?", Statement.RETURN_GENERATED_KEYS);
			)
		{
			statement.setString(1, request.getParameter("benutzer"));
			statement.setString(2, request.getParameter("email"));
			ResultSet rs = statement.executeQuery();
			System.out.println(rs.getFetchSize());
			//pruefen ob der Benutzername schon existiert
			if (!rs.first()){
				
				
				//Benutzer fuer die Session speichern
				UserBean user = new UserBean();
				
				user.setVorname(request.getParameter("vorname"));
				user.setNachname(request.getParameter("nachname"));
				user.setEmail(request.getParameter("email"));
				user.setUsername(request.getParameter("benutzer"));
				user.setPassword(request.getParameter("passwort"));
				
				userSpeichern(user);
				
				
				
				final HttpSession session = request.getSession();
				session.setAttribute("selfUser", user);
				

				
				final RequestDispatcher dispatcher = request.getRequestDispatcher("registriert.jsp");
				dispatcher.forward(request, response);	
			}
			else {
				final RequestDispatcher dispatcher = request.getRequestDispatcher("registrierungFehlgeschlagen.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
		
		
		
		
	}
	
	private void userSpeichern (UserBean user) throws ServletException{
		
		/*String writeString = "INSERT INTO benutzer(benutzername, vorname, nachname, email, passwort) VALUES (" + user.getUsername() +
				", " + user.getVorname() +
				", " + user.getNachname() +
				", " + user.getEmail() +
				", " + user.getPassword() + ")";*/
		try (Connection con = ds.getConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO benutzer(benutzername, vorname, nachname, email, passwort) VALUES (?, ?, ?, ?, ?)");
			){
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getVorname());
			statement.setString(3, user.getNachname());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getPassword());
			statement.executeUpdate();
		}
		
		catch (Exception e) {
				throw new ServletException(e.getMessage());
			}
	}

}
