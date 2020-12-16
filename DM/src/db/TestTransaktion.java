package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestTransaktion {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String kommando = "jdbc:mysql://localhost/" + Zugangsdaten.db + "?user=" + Zugangsdaten.user + "&password="
				+ Zugangsdaten.pass;
		Connection con = DriverManager.getConnection(kommando);
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement("SELECT * FROM kreditkarten WHERE inhaber=?");
			ps.setString(1, "Max Mustermann");
			ResultSet rs = ps.executeQuery();
			String kartennummer = DB.konvertiereJava(rs).get(0).get("kartennummer");
			ps.close();
			ps = con.prepareStatement(
					"INSERT INTO RECHNUNGEN "
							+ "(rechnungnummer, kundennummer, betrag, kartennummer) VALUES (?,?,?,?)");
			ps.setInt(1, 27);
			ps.setString(2, "ABX040");
			ps.setDouble(2, 43.24);
			ps.setInt(4, Integer.parseInt(kartennummer));
			ps.execute();
			con.commit();
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {

			}
		} finally {
			try {
				ps.close();
			} catch (Exception e2) {
			}
			try {
				con.close();
			} catch (Exception e) {
			}
		}
	}
}
