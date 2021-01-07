package sql;

public class Datenzugriff implements iDatenzugriff {
	private Datenbank datenbank;

	@Override
	public void verbindungHerstellen(String datenbank, String benutzer, String passwort) throws Exception {
		this.datenbank = new Datenbank(datenbank, benutzer, passwort);
	}

	@Override
	public void verbindungSchliessen() {
		this.datenbank.schliessen();
	}
}
