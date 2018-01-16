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
import javax.sql.DataSource;

import inbar.beans.BarBean;
import inbar.beans.BewertungBean;
import inbar.beans.UserBean;

/**
 * Servlet implementation class BarBearbeitenServlet
 * @author david
 */
@WebServlet("/BarAnzeigen")
public class BarAnzeigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarAnzeigenServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null) {
			int barID = Integer.parseInt(request.getParameter("id"));

			try (Connection con = ds.getConnection();
					PreparedStatement barStatement = con.prepareStatement("SELECT * FROM bar LEFT JOIN bar_zu_bild ON bar.barid=bar_zu_bild.barid where bar.barid = ?");
					PreparedStatement bewertungStatement = con.prepareStatement("SELECT * FROM bewertung LEFT JOIN benutzer ON bewertung.userid=benutzer.userid "
							+ "LEFT JOIN user_zu_bild ON benutzer.userid=user_zu_bild.userid WHERE barid = ?"))
			{
				barStatement.setInt(1, barID);

				ResultSet barRs = barStatement.executeQuery();

				if (barRs.first() && barRs.getInt("bar.barid") == barID) {
					BarBean bar = new BarBean();
					bar.setBarname(barRs.getString("barname"));
					bar.setVorname(barRs.getString("vorname"));
					bar.setNachname(barRs.getString("nachname"));
					bar.setChefmail(barRs.getString("chefmail"));
					bar.setStrasse(barRs.getString("strasse"));
					bar.setHausnummer(barRs.getString("hausnummer"));
					bar.setPlz(barRs.getString("plz"));
					bar.setOrt(barRs.getString("ort"));
					bar.setBarmail(barRs.getString("barmail"));
					bar.setBarid(barID);
					bar.setBbeschreibung(barRs.getString("bbeschreibung"));
					bar.setMbeschreibung(barRs.getString("mbeschreibung"));
					bar.setLbeschreibung(barRs.getString("lbeschreibung"));
					bar.setBildId(barRs.getInt("bildid"));
					request.setAttribute("bar", bar);
					
					bewertungStatement.setInt(1, barID);
					ResultSet bewertungRs = bewertungStatement.executeQuery();
					List<BewertungBean> bewertungList = new ArrayList<BewertungBean>();
					
					while (bewertungRs.next()) {
						BewertungBean bewertung = new BewertungBean();
						bewertung.setBarid(bewertungRs.getInt("bewertung.barid"));
						bewertung.setUserid(bewertungRs.getInt("bewertung.userid"));
						bewertung.setBewertungsid(bewertungRs.getInt("bewertung.bewertungsid"));
						bewertung.setBewertung(bewertungRs.getInt("bewertung.bewertung"));
						bewertung.setKommentar(bewertungRs.getString("bewertung.kommentar"));
						
						
						UserBean user = new UserBean();
						user.setUserid(bewertungRs.getInt("benutzer.userid"));
						user.setUsername(bewertungRs.getString("benutzer.benutzername"));
						user.setVorname(bewertungRs.getString("benutzer.vorname"));
						user.setNachname(bewertungRs.getString("nachname"));
						user.setEmail(bewertungRs.getString("benutzer.email"));
						user.setBildId(bewertungRs.getInt("user_zu_bild.bildid"));
						
						bewertung.setUser(user);
						bewertungList.add(bewertung);	
					}
					
					if(!bewertungList.isEmpty()) {
						request.setAttribute("bewertungen", bewertungList);
					}
					
					/*
					 * Der bearbeiten Parameter wird nur gesetzt, wenn auf die Bar Bearbeiten verlinkt 
					 * werden soll, ansonsten wird die Bar nur angezeigt
					 */
					if (request.getParameter("bearbeiten") != null) {
						final RequestDispatcher dispatcher = request.getRequestDispatcher("barBearbeiten.jsp");
						dispatcher.forward(request, response);
					}
					else {
						final RequestDispatcher dispatcher = request.getRequestDispatcher("barAnzeige.jsp");
						dispatcher.forward(request, response);
					}
				} else {
					final RequestDispatcher dispatcher = request.getRequestDispatcher("barExistiertNicht.jsp");
					dispatcher.forward(request, response);
				}

			} catch (Exception e) {
				throw new ServletException(e.getMessage());
			}
		} else {
			final RequestDispatcher dispatcher = request.getRequestDispatcher("barExistiertNicht.jsp");
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
