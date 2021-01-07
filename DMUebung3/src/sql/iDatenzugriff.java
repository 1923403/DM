package sql;

public interface iDatenzugriff {
	void verbindungHerstellen(String datenbank, String user, String passwort) throws Exception;

	void verbindungSchliessen();
}
