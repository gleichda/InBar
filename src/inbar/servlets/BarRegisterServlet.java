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
import inbar.datenbank.BildHandler;
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
		request.setCharacterEncoding("UTF-8");
		
		final HttpSession session = request.getSession();
		
		UserBean user = (UserBean) session.getAttribute("selfUser");

		if (user != null) {

			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * from bar where barname LIKE ?");
					PreparedStatement bildStatement = con.prepareStatement("SELECT * from bild where bildid LIKE ?"))

			{
				statement.setString(1, request.getParameter("barname"));
				ResultSet rs = statement.executeQuery();

				// pruefen ob der Barname schon existiert
				if (!rs.first()) {

					BarBean bar = new BarBean();
					BildBean bild = new BildBean();

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
					
					
					//BarBean fuellen
					bar.setBarname(request.getParameter("barname"));
					bar.setVorname(request.getParameter("vorname"));
					bar.setNachname(request.getParameter("nachname"));
					bar.setChefmail(request.getParameter("chefmail"));
					bar.setStrasse(request.getParameter("strasse"));
					bar.setHausnummer(request.getParameter("hausnummer"));
					bar.setPlz(request.getParameter("plz"));
					bar.setOrt(request.getParameter("ort"));
					bar.setBarmail(request.getParameter("barmail"));

					bar.setBbeschreibung(request.getParameter("text"));
					bar.setMbeschreibung(request.getParameter("mbeschreibung"));
					bar.setLbeschreibung(request.getParameter("lbeschreibung"));

					BildHandler bildHandler = new BildHandler(bild);
					bild = bildHandler.bildSpeichern(ds); 
					// Neue BildBean mitgesetzter ID uebernehmen
					System.out.println("Musikart: " + request.getParameter("musikart"));
					
					bar.setMusikId( Integer.parseInt(request.getParameter("musikart")));
					
					barSpeichern(bar);
					barZuUserZuweisen(bar, user);
					bar.setBildId(bild.getBildid());
					barZuBildZuweisen(bar);
					barZuMusikrichtung(bar);
					
					session.setAttribute("baruser", bar);
					

					final RequestDispatcher dispatcher = request.getRequestDispatcher("barRegistriert.jsp");
					dispatcher.forward(request, response);
				} else {
					final RequestDispatcher dispatcher = request
							.getRequestDispatcher("barRegistrierungFehlgeschlagen.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.out);
			}
		} else {
			// Aufruf Fehlerseite, wenn kein User eingeloggt ist
			final RequestDispatcher dispatcher = request.getRequestDispatcher("nichtEingeloggt.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void barSpeichern(BarBean bar) throws ServletException {
		String[] generatedKeys = new String[] { "barid" }; // Aus Skript JDBC Folie 12 uebernommen

		try (Connection con = ds.getConnection();
				PreparedStatement barCreationStatement = con.prepareStatement(
						"INSERT INTO bar(vorname, nachname, chefmail, strasse, hausnummer, plz ,ort ,barmail ,barname, bbeschreibung, mbeschreibung, lbeschreibung) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
						generatedKeys)) {

			
			barCreationStatement.setString(1, bar.getVorname());
			barCreationStatement.setString(2, bar.getNachname());
			barCreationStatement.setString(3, bar.getChefmail());
			barCreationStatement.setString(4, bar.getStrasse());
			barCreationStatement.setString(5, bar.getHausnummer());
			barCreationStatement.setString(6, bar.getPlz());
			barCreationStatement.setString(7, bar.getOrt());
			barCreationStatement.setString(8, bar.getBarmail());
			barCreationStatement.setString(9, bar.getBarname());
			barCreationStatement.setString(10, bar.getBbeschreibung());
			barCreationStatement.setString(11, bar.getMbeschreibung());
			barCreationStatement.setString(12, bar.getLbeschreibung());
			barCreationStatement.executeUpdate();

			ResultSet rs = barCreationStatement.getGeneratedKeys();
			rs.first();
			bar.setBarid(rs.getInt(1));

			
			

		}

		catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}
	
	private void barZuUserZuweisen(BarBean bar, UserBean user){
		// Zuweisung des aktuell angemeldeten Benutzers zu der angelegten Bar
		try (Connection con = ds.getConnection();
				PreparedStatement barAdminStatement = con.prepareStatement("INSERT INTO bar_zu_user (userid, barid) VALUES (?, ?)")){
			barAdminStatement.setInt(1, user.getUserid());
			barAdminStatement.setInt(2, bar.getBarid());
			barAdminStatement.executeUpdate();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void barZuBildZuweisen(BarBean bar){
		try (Connection con = ds.getConnection();
				PreparedStatement barBildStatement = con.prepareStatement("INSERT INTO bar_zu_bild (barid, bildid) VALUES (?, ?)")) {
			barBildStatement.setInt(1, bar.getBarid());
			barBildStatement.setInt(2, bar.getBildId());
			barBildStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void barZuMusikrichtung(BarBean bar) {
		//System.out.println("Bar zu Musikrichtung: Barid: " + bar.getBarid() + " Musikid: " + bar.getMusikId());
		try (Connection con = ds.getConnection();
				PreparedStatement barMusikrichtungStatement = con.prepareStatement("INSERT INTO musik_zu_bar (barid, musikid) VALUES (?, ?)")){
			barMusikrichtungStatement.setInt(1, bar.getBarid());
			barMusikrichtungStatement.setInt(2, bar.getMusikId());
			barMusikrichtungStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
