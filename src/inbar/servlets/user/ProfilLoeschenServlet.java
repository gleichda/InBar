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
 * Servlet implementation class ProfilLoeschenServlet
 */
@WebServlet("/ProfilLoeschen")
public class ProfilLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilLoeschenServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession();
		UserBean selfUser = (UserBean) session.getAttribute("selfUser");
		
		try (Connection con = ds.getConnection();
				PreparedStatement statement = con.prepareStatement("DELETE FROM benutzer WHERE userid = ?")){
			statement.setInt(1, selfUser.getUserid());
			statement.executeUpdate();
			session.removeAttribute("selfUser");
			RequestDispatcher dispatcher = request.getRequestDispatcher("profilGeloescht.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
