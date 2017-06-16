package inbar.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import inbar.beans.BildBean;

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
					PreparedStatement statement = con.prepareStatement("SELECT * from bar where barname LIKE ?");
					PreparedStatement bildStatement = con.prepareStatement("SELECT * from bild where bild LIKE ?"))

			{
				statement.setString(1, request.getParameter("barname"));
				ResultSet rs = statement.executeQuery();
				// pruefen ob der Benutzername schon existiert
				if (!rs.first()) {

					// Benutzer fuer die Session speichern
					BarBean baruser = new BarBean();

					BildBean bild = new BildBean();
					
					// Bildbehandlung
					Part filepart = request.getPart("bild");
					bild.setBildname(filepart.getSubmittedFileName());
					
					try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
							InputStream in = filepart.getInputStream()){
						int i=0;
						while ((i= in.read()) != -1) {
							baos.write(i);
						}
						bild.setBild(baos.toByteArray());
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
					bildSpeichern(bild);
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

	private void bildSpeichern(BildBean bild) throws ServletException{
			String[] generatedKeys = new String[] {"bildid"};
			//TODO Meiner Vermutung nach funktioniert dieser Try nicht, bzw. in der FM springt er in das catch
			try (Connection con = ds.getConnection();
					PreparedStatement bildStatement = con.prepareStatement(
							"INSERT INTO bild(bild) VALUES (?)",generatedKeys)){
				ResultSet rs = bildStatement.getGeneratedKeys();
				rs.first();
					bild.setBildid(rs.getInt(1));
					
				//bildStatement.setBytes(1, bild.getBild());
				bildStatement.executeUpdate();				

			}

			catch (Exception e) {
				throw new ServletException(e.getMessage());
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
		
		String[] generatedKeys = new String[] {"barid"};  //Aus Skript JDBC Folie 12 �bernommen
		
		try (Connection con = ds.getConnection();
				PreparedStatement barCreationStatement = con.prepareStatement(
						"INSERT INTO bar(vorname, nachname, chefmail, strasse, hausnummer, plz ,ort ,barmail ,barname, bbeschreibung, mbeschreibung, lbeschreibung) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
						generatedKeys);
				PreparedStatement barAdminStatement = con.prepareStatement("INSERT INTO bar_zu_user(userid, barid) VALUES (?, ?)")){
			
			//TODO bar_zu_bild-Tabelle in der Datenbank erstellen
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

				
			//Zuweisung des aktuell angemeldeten Benutzers zu der angelegten Bar
			barAdminStatement.setInt(1, user.getUserid());
			barAdminStatement.setInt(2, baruser.getBarid());
			barAdminStatement.executeUpdate();

		}

		catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}
}
