package sql;

public interface iDatenzugriff {
	void verbindungHerstellen(String datenbank, String user, String passwort);

	void verbindungSchliessen();
}
