package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datenbank {
	private Connection connection = null;

	public Connection getConnection() {
		return this.connection;
	}

	public Datenbank(String datenbank, String benutzer, String passwort) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/" + datenbank + "?user=" + benutzer + "&password=" + passwort;
			this.connection = DriverManager.getConnection(url);
		} catch (Exception e) {
			System.err.println(
					"\nDer Zugriff auf die angegebene Datenbank ist nicht möglich, bitte überprüfen Sie Ihre Eingaben!");
		}
	}

	public void schliessen() {
		this.finalize();
	}

	@Override
	public void finalize() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection = null;
	}
}
