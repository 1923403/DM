package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TestTransaktion {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
//		String kommando = "jdbc:mysql://localhost/" + Zugangsdaten.db + "?user=" + Zugangsdaten.user + "&password="
//				+ Zugangsdaten.pass;
		String kommando = "jdbc:mysql://localhost/kis?user=root&password=";
		Connection con = DriverManager.getConnection(kommando);
		PreparedStatement ps = null;
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement("SELECT * FROM mitarbeiter WHERE vorname=?");
			ps.setString(1, "Mohammed");
//			ResultSet rs = ps.executeQuery();
//			String personalnummer = DB.konvertiereJava(rs).get(0).get("personalnummer");
			ps.close();
			ps = con.prepareStatement(
					"INSERT INTO mitarbeiter "
							+ "(personalnummer, name, vorname, geburtsdatum, anstelldatum, austrittsdatum, steuerId) VALUES (?,?,?,?,?,?,?)");
			ps.setInt(1, 27);
			ps.setString(2, "Wurst");
			ps.setString(3, "Hans");
			ps.setDate(4, new Date(1970, 06, 20));
			ps.setDate(5, new Date(1990, 10, 20));
			ps.setDate(6, null);
			ps.setLong(7, 1745639582);
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
