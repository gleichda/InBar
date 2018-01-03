package inbar.servlets.bar;

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
import javax.sql.DataSource;

/**
 * Servlet implementation class BarBearbeitenServlet
 */
@WebServlet("/BarBearbeiten")
public class BarBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource (lookup = "jdbc/InBar")
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarBearbeitenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Barid: " + request.getParameter("barid"));
		int barid = Integer.parseInt(request.getParameter("barid"));
		System.out.println("Barid: " + request.getParameter("barid"));		
		String barname = request.getParameter("barname");
		String vorname = request.getParameter("vorname");
		String nachname = request.getParameter("nachname");
		String chefmail = request.getParameter("chefmail");
		String strasse = request.getParameter("strasse");
		String hausnummer = request.getParameter("hausnummer");
		String plz = request.getParameter("plz");
		String ort = request.getParameter("ort");
		String barmail = request.getParameter("barmail");
		String bbeschreibung = request.getParameter("bbeschreibung");
		String mbeschreibung = request.getParameter("mbeschreibung");
		String lbeschreibung = request.getParameter("lbeschreibung");
		
		//TODO: verify user
		
		try(Connection con = ds.getConnection();
				PreparedStatement statement = con.prepareStatement("UPDATE bar SET barname = ?, vorname = ?, nachname = ?, chefmail = ?, strasse = ?, hausnummer = ?, "
						+ "plz = ?, ort = ?, barmail = ?, bbeschreibung = ?, mbeschreibung = ?, lbeschreibung = ? WHERE barid = ? "))
		{
			statement.setString(1, barname);
			statement.setString(2, vorname);
			statement.setString(3, nachname);
			statement.setString(4, chefmail);
			statement.setString(5, strasse);
			statement.setString(6, hausnummer);
			statement.setString(7, plz);
			statement.setString(8, ort);
			statement.setString(9, barmail);
			statement.setString(10, bbeschreibung);
			statement.setString(11, mbeschreibung);
			statement.setString(12, lbeschreibung);
			statement.setInt(13, barid);
			statement.executeUpdate();
		}catch  (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("erfolgreichGeaendert.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
