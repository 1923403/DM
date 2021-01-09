package sql;

import java.math.BigDecimal;
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
	private int positionPrepared = 1;

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
			try {
				this.preparedStatement.execute();
				System.out.println("Operation erfolgreich durchgeführt!");
				return new ArrayList<>();
			} catch (Exception e2) {
				throw new RuntimeException("Fehler beim Verarbeiten des Statements: " + e2.getMessage());
			}
		} finally {
			this.positionPrepared = 1;
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

	public void setString(char c) {
		try {
			this.setString("" + c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setString '" + c + "': " + e.getMessage());
		}
	}

	public void setString(String s) {
		try {
			this.preparedStatement.setString(this.positionPrepared++, s);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setString'" + s + "': " + e.getMessage());
		}
	}

	public void setInt(int i) {
		try {
			this.preparedStatement.setInt(this.positionPrepared++, i);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setInt '" + i + "': " + e.getMessage());
		}
	}

	public void setInt(String s) {
		try {
			this.setInt(Integer.parseInt(s));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setInt '" + s + "': " + e.getMessage());
		}
	}

	public void setDouble(double d) {
		try {
			this.preparedStatement.setDouble(this.positionPrepared++, d);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setDouble '" + d + "': " + e.getMessage());
		}
	}

	public void setDouble(String s) {
		try {
			this.setDouble(Double.parseDouble(s));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setDouble '" + s + "': " + e.getMessage());
		}
	}

	public void setDecimal(BigDecimal d) {
		try {
			this.preparedStatement.setBigDecimal(this.positionPrepared++, d);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setDecimal(String s) {
		try {
			this.setDecimal(BigDecimal.valueOf(Double.parseDouble(s)));
		} catch (Exception e) {
			// TODO: handle exception
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
