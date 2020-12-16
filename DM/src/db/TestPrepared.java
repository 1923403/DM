package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestPrepared {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String kommando = "jdbc:mysql://localhost/" + Zugangsdaten.db + "?user=" + Zugangsdaten.user + "&password="
				+ Zugangsdaten.pass;
		Connection con = DriverManager.getConnection(kommando);
		PreparedStatement ps = con.prepareStatement("SELECT * FROM rechnungen WHERE kundennummer=?");
		ps.setString(1, "ABX050");
		ResultSet rs = ps.executeQuery();
		System.out.println(DB.konvertiereJava(rs));
	}
}
