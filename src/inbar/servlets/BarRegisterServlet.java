package inbar.servlets;

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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import inbar.beans.BarBean;
import inbar.beans.UserBean;

@WebServlet("/BarRegisterServlet")
@MultipartConfig
public class BarRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	// Sabine: Kopiert aus RegisterServlet und angepasst
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		// String read = "SELECT * from bar where barname LIKE '" +
		// request.getParameter("barname") + "'";
		// System.out.println(read);

		UserBean user = (UserBean) session.getAttribute("selfUser");
		

		
		
		if (user != null) {

			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * from bar where barname LIKE ?"))

			{
				statement.setString(1, request.getParameter("barname"));
				ResultSet rs = statement.executeQuery();
				// pruefen ob der Benutzername schon existiert
				if (!rs.first()) {

					// Benutzer fuer die Session speichern
					BarBean baruser = new BarBean();

					// Bildbehandlung
					Part filepart = request.getPart("image");
					baruser.setBildname(filepart.getSubmittedFileName());
					
					try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
							InputStream in = filepart.getInputStream()){
						int i=0;
						while ((i= in.read()) != -1) {
							baos.write(i);
						}
						baruser.setBild(baos.toByteArray());
						baos.flush();
					} catch (IOException ex){
						throw new ServletException(ex.getMessage());
					}
					
					baruser.setBarname(request.getParameter("barname"));
					baruser.setVorname(request.getParameter("vorname"));
					baruser.setNachname(request.getParameter("nachname"));
					baruser.setChefmail(request.getParameter("chefmail"));
					baruser.setStrasse(request.getParameter("strasse"));
					baruser.setHausnummer(request.getParameter("hausnummer"));
					baruser.setPlz(request.getParameter("plz"));
					baruser.setOrt(request.getParameter("ort"));
					baruser.setBarmail(request.getParameter("barmail"));

					baruser.setPasswort(request.getParameter("passwort"));

					baruser.setBbeschreibung(request.getParameter("text"));
					baruser.setMbeschreibung(request.getParameter("mbeschreibung"));
					baruser.setLbeschreibung(request.getParameter("lbeschreibung"));

					System.out.println(user.getNachname());
					barUserSpeichern(baruser, user);

					session.setAttribute("baruser", baruser);

					final RequestDispatcher dispatcher = request.getRequestDispatcher("barRegistriert.jsp");
					dispatcher.forward(request, response);
				} else {
					final RequestDispatcher dispatcher = request.getRequestDispatcher("barRegistrierungFehlgeschlagen.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.out);
			}
		}
		else {
			//Aufruf Fehlerseite, wenn kein User eingeloggt ist
			final RequestDispatcher dispatcher = request.getRequestDispatcher("nichtEingeloggt.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void barUserSpeichern(BarBean baruser, UserBean user) throws ServletException {

		/*
		 * String writeString =
		 * "INSERT INTO benutzer(benutzername, vorname, nachname, email, passwort) VALUES ("
		 * + user.getUsername() + ", " + user.getVorname() + ", " +
		 * user.getNachname() + ", " + user.getEmail() + ", " +
		 * user.getPassword() + ")";
		 */
		
		String[] generatedKeys = new String[] {"barid"};  //Aus Skript JDBC Folie 12 übernommen
		
		try (Connection con = ds.getConnection();
				PreparedStatement barCreationStatement = con.prepareStatement(
						"INSERT INTO bar(vorname, nachname, chefmail, strasse, hausnummer, plz ,ort ,barmail ,barname, bbeschreibung, mbeschreibung, lbeschreibung) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
						generatedKeys);
				PreparedStatement barAdminStatement = con.prepareStatement("INSERT INTO bar_zu_user(userid, barid) VALUES (?, ?)")) {
			
			barCreationStatement.setString(1, baruser.getVorname());
			barCreationStatement.setString(2, baruser.getNachname());
			barCreationStatement.setString(3, baruser.getChefmail());
			barCreationStatement.setString(4, baruser.getStrasse());
			barCreationStatement.setString(5, baruser.getHausnummer());
			barCreationStatement.setString(6, baruser.getPlz());
			barCreationStatement.setString(7, baruser.getOrt());
			barCreationStatement.setString(8, baruser.getBarmail());
			barCreationStatement.setString(9, baruser.getBarname());
			barCreationStatement.setString(10, baruser.getBbeschreibung());
			barCreationStatement.setString(11, baruser.getMbeschreibung());
			barCreationStatement.setString(12, baruser.getLbeschreibung());
			barCreationStatement.executeUpdate();

			ResultSet rs = barCreationStatement.getGeneratedKeys();
			rs.first();
				baruser.setBarid(rs.getInt(1));
			
			barAdminStatement.setInt(1, user.getUserid());
			barAdminStatement.setInt(2, baruser.getBarid());
			barAdminStatement.executeUpdate();
			
			//TODO: Bild und BildID hinzufügen und übertragen
		}

		catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}
}
