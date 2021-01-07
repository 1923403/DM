package sql;

import java.util.LinkedHashMap;

public class Datenzugriff implements iDatenzugriff {
	private Datenbank datenbank;

	@Override
	public void verbindungHerstellen(String datenbank, String benutzer, String passwort) throws Exception {
		this.datenbank = new Datenbank(datenbank, benutzer, passwort);
	}

	@Override
	public void anfrageAbsetzen(String sql) {
		this.datenbank.setSQL(sql);

		for (LinkedHashMap<String, String> datensatz : this.datenbank.lesenJava()) {
			System.out.println(datensatz);
		}
	}

	@Override
	public void verbindungSchliessen() {
		this.datenbank.schliessen();
	}
}
