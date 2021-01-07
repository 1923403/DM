package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Datenbank {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public Connection getConnection() {
		return this.connection;
	}

	public Datenbank(String datenbank, String benutzer, String passwort) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost/" + datenbank + "?user=" + benutzer + "&password=" + passwort;
		this.connection = DriverManager.getConnection(url);
	}

	public ArrayList<LinkedHashMap<String, String>> lesenJava() {
		try {
			return this.konvertiereZuJava(this.preparedStatement.executeQuery());
		} catch (SQLException e) {
			throw new RuntimeException("Fehler beim Verarbeiten des Statements: " + e.getMessage());
		}
	}

	private ArrayList<LinkedHashMap<String, String>> konvertiereZuJava(ResultSet resultSet) throws SQLException {
		var daten = new ArrayList<LinkedHashMap<String, String>>();
		var spaltenanzahl = resultSet.getMetaData().getColumnCount();

		if (spaltenanzahl == 0)
			return daten;

		while (resultSet.next()) {
			var datensatz = new LinkedHashMap<String, String>();

			for (var i = 1; i <= spaltenanzahl; i++) {
				var spaltenname = resultSet.getMetaData().getColumnName(i);
				var spaltenwert = resultSet.getString(spaltenname);

				if (spaltenwert != null)
					datensatz.put(spaltenname, spaltenwert);
				else
					datensatz.put(spaltenname, "");
			}

			daten.add(datensatz);
		}

		return daten;
	}

	public void setSQL(String sql) {
		try {
			this.preparedStatement = this.connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL-Fehler: " + e.getMessage());
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
