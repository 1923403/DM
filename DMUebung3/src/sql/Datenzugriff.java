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

		this.anfrageAuswerten();
	}

	@Override
	public void a1(String personalnummer) {
		this.datenbank.setSQL("SELECT * FROM hauptwohnsitz WHERE hauptwohnsitz.personalnummer=?;");
		this.datenbank.setInt(personalnummer);

		this.anfrageAuswerten();
	}

	@Override
	public void a2(String personalnummer) {
		this.datenbank.setSQL("SELECT COUNT(versicherungsnummer) FROM versicherung WHERE personalnummer=?;");
		this.datenbank.setInt(personalnummer);

		this.anfrageAuswerten();
	}

	@Override
	public void a3(String personalnummer) {
		this.datenbank.setSQL("SELECT geschlecht FROM geschlecht WHERE geschlecht.personalnummer=?;");
		this.datenbank.setInt(personalnummer);

		this.anfrageAuswerten();
	}

	@Override
	public void a4(String personalnummer) {
		this.datenbank.setSQL("SELECT konfession FROM konfession WHERE konfession.personalnummer=?;");
		this.datenbank.setInt(personalnummer);

		this.anfrageAuswerten();
	}

	@Override
	public void a5(String berufsId) {
		this.datenbank.setSQL("SELECT medfachrichtung FROM medfachrichtung WHERE medFachrichtung.berufsId=?");
		this.datenbank.setInt(berufsId);

		this.anfrageAuswerten();
	}

	@Override
	public void b1(String personalnummer) {
		this.datenbank.setSQL("SELECT geraetename.geraetename, mitarbeiter.personalnummer, mitarbeiter.name\r\n"
				+ "FROM mitarbeiter\r\n"
				+ "INNER JOIN geraeteeinweisung ON geraeteeinweisung.personalnummer = mitarbeiter.personalnummer\r\n"
				+ "INNER JOIN geraet ON geraet.geraeteId = geraeteeinweisung.geraeteId\r\n"
				+ "INNER JOIN geraetename ON geraetename.geraeteId = geraet.geraeteId\r\n"
				+ "WHERE mitarbeiter.personalnummer = ?;");
		this.datenbank.setInt(personalnummer);

		this.anfrageAuswerten();
	}

	@Override
	public void b2() {
		this.datenbank.setSQL(
				"SELECT mitarbeiter.personalnummer, mitarbeiter.name, arbeitszeit.schichtId, arbeitszeit.dienstende\r\n"
						+ "FROM mitarbeiter\r\n"
						+ "INNER JOIN stundenuebersicht on stundenuebersicht.personalnummer = mitarbeiter.personalnummer\r\n"
						+ "INNER JOIN arbeitszeit ON arbeitszeit.personalnummer = stundenuebersicht.personalnummer\r\n"
						+ "WHERE arbeitszeit.dienstende IS NULL;");

		this.anfrageAuswerten();
	}

	@Override
	public void b3(String berechtigung, String berechtigungsobjekt) {
		this.datenbank.setSQL(
				"SELECT mitarbeiter.personalnummer, mitarbeiter.name, berechtigungen.darfEditieren, berechtigungsname.berechtigungsname\r\n"
						+ "FROM mitarbeiter\r\n"
						+ "INNER JOIN hat ON hat.personalnummer = mitarbeiter.personalnummer\r\n"
						+ "INNER JOIN beruf ON beruf.berufsId = hat.berufsId\r\n"
						+ "INNER JOIN berechtigungen ON berechtigungen.berufsId = beruf.berufsId\r\n"
						+ "INNER JOIN berechtigungsname ON berechtigungsname.berechtigungsId = berechtigungen.berechtigungsId\r\n"
						+ "WHERE berechtigungen." + berechtigung + " = 1\r\n"
						+ "AND berechtigungsname.berechtigungsname = ?;");
		this.datenbank.setString(berechtigungsobjekt);

		this.anfrageAuswerten();
	}

	@Override
	public void b4(String stadtname, String stellenanteil) {
		this.datenbank.setSQL(
				"SELECT mitarbeiter.personalnummer, mitarbeiter.name, stadtname.stadtname ,stundenuebersicht.stellenanteil\r\n"
						+ "FROM mitarbeiter\r\n"
						+ "INNER JOIN hauptwohnsitz ON hauptwohnsitz.personalnummer = mitarbeiter.personalnummer\r\n"
						+ "INNER JOIN stadt ON stadt.plz = hauptwohnsitz.plz\r\n"
						+ "INNER JOIN stadtname ON stadtname.plz = stadt.plz\r\n"
						+ "INNER JOIN stundenuebersicht ON stundenuebersicht.personalnummer = mitarbeiter.personalnummer\r\n"
						+ "WHERE stadtname.stadtname=?\r\n"
						+ "AND stundenuebersicht.stellenanteil=?;");
		this.datenbank.setString(stadtname);
		this.datenbank.setDecimal(stellenanteil);

		this.anfrageAuswerten();
	}

	@Override
	public void b5(String geschlecht, String konfession, String rolle, String versicherungsname) {
		this.datenbank.setSQL(
				"SELECT mitarbeiter.personalnummer, mitarbeiter.name, konfession.konfession, geschlecht.geschlecht, rolle.rolle, versicherungsname.versicherungsname\r\n"
						+ "FROM mitarbeiter\r\n"
						+ "INNER JOIN geschlecht ON geschlecht.personalnummer = mitarbeiter.personalnummer\r\n"
						+ "INNER JOIN konfession ON konfession.personalnummer = mitarbeiter.personalnummer\r\n"
						+ "INNER JOIN versicherung ON versicherung.personalnummer = mitarbeiter.personalnummer\r\n"
						+ "INNER JOIN versicherungsname ON versicherungsname.versicherungsnummer = versicherung.versicherungsnummer\r\n"
						+ "INNER JOIN hat ON hat.personalnummer = mitarbeiter.personalnummer\r\n"
						+ "INNER JOIN beruf ON beruf.berufsId = hat.berufsId\r\n"
						+ "INNER JOIN rolle ON rolle.berufsId = beruf.berufsId\r\n"
						+ "WHERE geschlecht = ?\r\n"
						+ "AND konfession = ?\r\n"
						+ "AND rolle = ?\r\n"
						+ "AND versicherungsname = ?;");
		this.datenbank.setString(geschlecht);
		this.datenbank.setString(konfession);
		this.datenbank.setString(rolle);
		this.datenbank.setString(versicherungsname);

		this.anfrageAuswerten();
	}

	@Override
	public void anfrageAuswerten() {
		System.out.println();

		if (!this.datenbank.lesenJava().isEmpty())
			for (LinkedHashMap<String, String> datensatz : this.datenbank.lesenJava()) {
				System.out.println(datensatz);
			}
		else
			System.out.println("Das gewählte SQL-Statement liefert keine Datenbankeinträge.");
	}

	@Override
	public void verbindungSchliessen() {
		this.datenbank.schliessen();
	}
}
