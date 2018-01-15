package inbar.servlets;

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

import inbar.beans.UserBean;

/**
 * Servlet implementation class Suchservlet
<<<<<<< HEAD
 * @author sabine
=======
 * @author Sabine
>>>>>>> refs/remotes/origin/master
 */

//04.01 Sabine kopiert von BarSuchServlet und angepasst auf User
@WebServlet("/UserSuchServlet")
public class UserSuchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSuchServlet() {
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

		String username = request.getParameter("username");
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");

		if (!username.isEmpty() || !vorname.isEmpty() || !nachname.isEmpty()) {
			List<UserBean> userList = new ArrayList<UserBean>();
		
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * FROM benutzer WHERE benutzername LIKE ? AND vorname LIKE ? AND nachname LIKE ?"))
			{

					if (!username.isEmpty() && username != null) {
						statement.setString(1, "%" + username + "%");	
					}
					else {
						statement.setString(1, "%");						
					}
					 
					if (!vorname.isEmpty() && vorname != null) {
						statement.setString(2, "%" + vorname + "%");
					}
					else {
						statement.setString(2, "%");
					}
					if (!nachname.isEmpty() && nachname != null) {
						statement.setString(3, "%" + nachname + "%");
					}
					else {
						statement.setString(3, "%");
					}
					
				
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {	
					UserBean user = new UserBean();
					user.setUserid(rs.getInt("userid"));
					user.setUsername(rs.getString("benutzername"));
					user.setVorname(rs.getString("vorname"));
					user.setNachname(rs.getString("nachname"));
					user.setEmail(rs.getString("email"));
					userList.add(user);}
				
				
				if (!userList.isEmpty()) {
					request.setAttribute("suchergebnisse", userList);

					RequestDispatcher dispatcher = request.getRequestDispatcher("usersuchergebnisse.jsp");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("keinSuchergebnis.jsp");
					dispatcher.forward(request, response);
				}
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.out);
			}
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("userSucheKeineEingabe.jsp");
			dispatcher.forward(request, response);
		}
		
				/*
				 * Suchservlet und suchergebnisse.jsp teilweise erstellt mit
				 * Hilfe von
				 * https://stackoverflow.com/questions/23492233/arraylist-of-
				 * bean-how-to-access-the-property-of-a-bean-using-el-in-jsp-
				 * throw
				 */

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
