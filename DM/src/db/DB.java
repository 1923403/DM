package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DB {
	public static ArrayList<LinkedHashMap<String, String>> konvertiereJava(ResultSet rs) throws SQLException {
		ArrayList<LinkedHashMap<String, String>> daten = new ArrayList<>();
		int anz_spalten = rs.getMetaData().getColumnCount();
		if (anz_spalten == 0)
			return daten;
		while (rs.next()) {
			LinkedHashMap<String, String> datensatz = new LinkedHashMap<>();
			for (int i = 1; i < anz_spalten; i++) {
				String name = rs.getMetaData().getColumnName(i);
				String wert = rs.getString(name);
				if (wert != null)
					datensatz.put(name, wert);
				else
					datensatz.put(name, "");
			}
			daten.add(datensatz);
		}
		return daten;
	}

	private Connection con = null;
	private int counterPrepared = 1;

	private PreparedStatement ps = null;

//	public DB(String db, String user, String pass) {
//		try {
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			String kommando = "jdbc:mysql://localhost/" + db + "?user=" + user + "&password=" + pass;
//			this.con = DriverManager.getConnection(kommando);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException("Der DB-Zugang ist nicht vorhanden!");
//		}
//	}

	public DB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String kommando = "jdbc:mysql://localhost/kis?user=root&password=";
			this.con = DriverManager.getConnection(kommando);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Der DB-Zugang ist nicht vorhanden!");
		}
	}

	public void close() {
		this.finalize();
	}

	@Override
	public void finalize() {
		try {
			this.ps.close();
		} catch (SQLException e) {
		}
		this.ps = null;
		try {
			this.con.close();
		} catch (SQLException e) {
		}
		this.con = null;
	}

	public ArrayList<LinkedHashMap<String, String>> lesenJava() {
		try {
			return DB.konvertiereJava(this.ps.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB lesenJava: " + e.getMessage());
		}
	}

	public String lesenXML() {
		try {
			return this.konvertiereXML(this.ps.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB lesenXML: " + e.getMessage());
		}
	}

	public void schreiben() {
		try {
			this.ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB schreiben: " + e.getMessage());
		}
	}

	public void setDouble(double d) {
		try {
			this.ps.setDouble(this.counterPrepared++, d);
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

	public void setInt(char c) {
		try {
			this.setInt(Integer.parseInt("" + c));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setInt '" + c + "': " + e.getMessage());
		}
	}

	public void setInt(int i) {
		try {
			this.ps.setInt(this.counterPrepared++, i);
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

	public void setSQL(String sql) {
		try {
			this.ps = this.con.prepareStatement(sql);
		} catch (Exception e) {
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
			this.ps.setString(this.counterPrepared++, s);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("DB setString'" + s + "': " + e.getMessage());
		}
	}

	private String konvertiereXML(ResultSet rs) throws SQLException {
		StringBuffer sb = new StringBuffer();
		int anz_spalten = rs.getMetaData().getColumnCount();
		if (anz_spalten == 0)
			return "<daten/>\n";
		sb.append("<daten>\n");
		while (rs.next()) {
			sb.append("<datensatz>\n");
			for (int i = 1; i <= anz_spalten; i++) {
				String name = rs.getMetaData().getColumnName(i);
				String wert = rs.getString(name);
				if (wert != null)
					sb.append("<" + name + ">" + wert + "</" + name + ">\n");
				else
					sb.append("<" + name + "></" + name + ">\n");
			}
			sb.append("</datensatz>\n");
		}
		sb.append("</daten>\n");
		return sb.toString();
	}
}
