package inbar.servlets.user;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;
import javax.sql.DataSource;

import inbar.beans.BarBean;
import inbar.beans.BildBean;
import inbar.beans.UserBean;
import inbar.datenbank.BildHandler;

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
		
		try (Connection con = ds.getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * from benutzer where benutzername LIKE ? OR email LIKE ?", Statement.RETURN_GENERATED_KEYS);
				PreparedStatement bildStatement = con.prepareStatement("SELECT * from bild where bildid LIKE ?")) //PreparedStatement f�r Bild hinzugef�gt. Sabine
		{
			statement.setString(1, request.getParameter("benutzer"));
			statement.setString(2, request.getParameter("email"));
			ResultSet rs = statement.executeQuery();
			System.out.println(rs.getFetchSize());
			
			//pruefen ob der Benutzername schon existiert
			if (!rs.first()){
				
				
				//Benutzer fuer die Session speichern
				UserBean user = new UserBean();
				BildBean bild = new BildBean(); //03.01 BildBean eingef�gt. Sabine
				
				user.setVorname(request.getParameter("vorname"));
				user.setNachname(request.getParameter("nachname"));
				user.setEmail(request.getParameter("email"));
				user.setUsername(request.getParameter("benutzer"));
				user.setPassword(request.getParameter("passwort"));
				

				
				//start 03.01 Sabine Profilbild

				// Neue BildBean mitgesetzter ID uebernehmen
				// Bildbehandlung
				Part filepart = request.getPart("bild");
				bild.setBildname(filepart.getSubmittedFileName());

				try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
						InputStream in = filepart.getInputStream()) {
					int i = 0;
					while ((i = in.read()) != -1) {
						baos.write(i);
					}
					bild.setBild(baos.toByteArray());
					baos.flush();
				} catch (IOException ex) {
					throw new ServletException(ex.getMessage());
				}
				
				BildHandler bildHandler = new BildHandler(bild);
				bild = bildHandler.bildSpeichern(ds); 
				//ende 03.01 Sabine Profilbild
				
				userSpeichern(user);
				//start 03.01 Sabine Profilbild speichern
				bild.setBildid(bild.getBildid());
				userZuBildZuweisen(user);
				//ende 03.01 Sabine Profilbild speichern
				
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
	
	//start 03.01 Sabine Profilbild speichern
	private void userZuBildZuweisen(UserBean user){
		try (Connection con = ds.getConnection();
				PreparedStatement barBildStatement = con.prepareStatement("INSERT INTO user_zu_bild (userid, bildid) VALUES (?, ?)")) {
			barBildStatement.setInt(1, user.getUserid());
			barBildStatement.setInt(2, user.getBildId());
			barBildStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	//ende 03.01 Sabine Profilbild speichern

}
