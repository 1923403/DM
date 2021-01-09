package sql;

public interface iDatenzugriff {
	void verbindungHerstellen(String datenbank, String user, String passwort) throws Exception;

	void anfrageAbsetzen(String sql);

	void a1(String personalnummer);

	void a2(String personalnummer);

	void a3(String personalnummer);

	void a4(String personalnummer);

	void a5(String berufsId);

	void b1(String personalnummer);

	void b2();

	void b3(String berechtigung, String berechtigungsobjekt);

	void b4(String stadtname, String stellenanteil);

	void b5(String geschlecht, String konfession, String rolle, String versicherungsname);

	void anfrageAuswerten();

	void verbindungSchliessen();
}
