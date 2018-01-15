package inbar.datenbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import inbar.beans.BildBean;

/**
 * @author david
 *Klasse zum Speichern des Bilds in der Datenbank
 */
public class BildHandler {

	BildBean bild;

	public BildHandler(BildBean bild) {
		this.bild = bild;
	}

	public BildBean bildSpeichern(DataSource ds) throws ServletException {

		String[] generatedKeys = new String[] { "bildid" };

		try (Connection con = ds.getConnection();
				PreparedStatement bildStatement = con.prepareStatement("INSERT INTO bild(bild) VALUES (?)",
						generatedKeys)) {
			bildStatement.setBytes(1, bild.getBild());
			bildStatement.executeUpdate();

			ResultSet rs = bildStatement.getGeneratedKeys();

			while (rs.next()) {
				bild.setBildid((int) rs.getLong(1));
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		return bild;
	}

}
