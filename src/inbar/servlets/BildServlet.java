package inbar.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class BildServlet
 */
@WebServlet("/Bild")
public class BildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup = "jdbc/InBar")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BildServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @see Bild11aServlet aus dem Demo Paket
	 * Gibt das Bild fuer die Anzeige im BarProfil aus.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bildIdParameter = request.getParameter("id");
		if (bildIdParameter != "") {
			int bildId = Integer.parseInt(bildIdParameter);
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con.prepareStatement("SELECT bild FROM bild where bildid = ?")) {
				statement.setInt(1, bildId);
				ResultSet rs = statement.executeQuery();
				if (rs != null && rs.next()) {
					Blob bild = rs.getBlob("bild");
					response.reset();
					long length = bild.length();
					response.setHeader("Content-Length", String.valueOf(length));

					try (InputStream in = bild.getBinaryStream()) {
						final int bufferSize = 256;
						byte[] buffer = new byte[bufferSize];

						ServletOutputStream out = response.getOutputStream();
						while ((length = in.read(buffer)) != -1) {
							out.write(buffer, 0, (int) length);
						}
						out.flush();
					}
				}
				//start 04.01 Sabine Default-Profilbild
				else {
					final RequestDispatcher dispatcher = request.getRequestDispatcher("./img/default-avatar.jpg");
					dispatcher.forward(request, response);
				}
				//ende 04.01 Sabine Default-Profilbild
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

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
