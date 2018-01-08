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
 */

//04.01 Sabine kopiert von Suchservlet und angepasst auf User
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
		System.out.println("Suche: Username: " + username + " Vorname: " + vorname);

		if (!username.isEmpty() || !vorname.isEmpty() || !nachname.isEmpty()) {
			List<UserBean> userList = new ArrayList<UserBean>();
		
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT * FROM benutzer WHERE benutzername LIKE ? AND vorname LIKE ? AND nachname LIKE ?"))
			{
				
				/*if ((username != "" && username != null)|| (vorname != "" && vorname != null) || (nachname != "" && nachname != null)){ 
				 * */
					if (username != "" && username != null) {
						statement.setString(1, "%" + username + "%");	
					}
					else {
						statement.setString(1, "%");						
					}
					 
					if (vorname != "" && vorname != null) {
						statement.setString(2, "%" + vorname + "%");
					}
					else {
						statement.setString(2, "%");
					}
					if (nachname != "" && nachname != null) {
						statement.setString(3, "%" + nachname + "%");
					}
					else {
						statement.setString(3, "%");
					}
					
					System.out.println("test1");
				/*}
				else {
					statement.setString(1, "%");
					statement.setString(2, "%");
					statement.setString(3, "%");
					System.out.println("test2");
				}
				*/
				
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {	
					System.out.println("whileSchleife");
					UserBean user = new UserBean();
					user.setUserid(rs.getInt("userid"));
					user.setUsername(rs.getString("benutzername"));
					user.setVorname(rs.getString("vorname"));
					user.setNachname(rs.getString("nachname"));
					user.setEmail(rs.getString("email"));
					userList.add(user);}
				
				System.out.println(username);
				
				System.out.println("UserList Size:" + userList.size());
				if (!userList.isEmpty()) {
					System.out.println("Ergebnisse enthalten");
					request.setAttribute("suchergebnisse", userList);

					RequestDispatcher dispatcher = request.getRequestDispatcher("usersuchergebnisse.jsp");
					dispatcher.forward(request, response);
				} else {
					System.out.println("Nichts gefunden.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("keinSuchergebnis.jsp");
					dispatcher.forward(request, response);
				}
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace(System.out);
			}
		}
		else {
			System.out.println("Keine Eingabe");
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
