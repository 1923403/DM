package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestPrepared {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
//		String kommando = "jdbc:mysql://localhost/" + Zugangsdaten.db + "?user=" + Zugangsdaten.user + "&password="
//				+ Zugangsdaten.pass;
		String kommando = "jdbc:mysql://localhost/kis?user=root&password=";
		Connection con = DriverManager.getConnection(kommando);
		PreparedStatement ps = con.prepareStatement("SELECT * FROM mitarbeiter WHERE vorname=?");
		ps.setString(1, "Dieter");
		ResultSet rs = ps.executeQuery();
		System.out.println(DB.konvertiereJava(rs));
	}
}
