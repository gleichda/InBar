package inbar.datenbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import inbar.beans.BildBean;

public class BildHandler {

	BildBean bild;

	public BildHandler(BildBean bild) {
		this.bild = bild;
	}

	public BildBean bildSpeichern(DataSource ds) throws ServletException {
		System.out.println("Bild speichern aufgerufen");

		String[] generatedKeys = new String[] { "bildid" };

		try (Connection con = ds.getConnection();
				PreparedStatement bildStatement = con.prepareStatement("INSERT INTO bild(bild) VALUES (?)",
						generatedKeys)) {
			System.out.println("Bild in Datenbank speichern");
			bildStatement.setBytes(1, bild.getBild());
			bildStatement.executeUpdate();

			ResultSet rs = bildStatement.getGeneratedKeys();

			while (rs.next()) {
				bild.setBildid((int) rs.getLong(1));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
		
		return bild;
	}

}
