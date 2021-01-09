package sql;

public class Datenbanknavigation {
	private Datenzugriff datenzugriff;

	public Datenbanknavigation() {
		this.datenzugriff = new Datenzugriff();
	}

	public void startbildschirm() {
		this.abgrenzungOben();
		System.out.println("[1] - Verbindung zu einer Datenbank herstellen");
		System.out.println("[2] - Programm beenden");
		this.abgrenzungUnten();

		switch (this.aktionWaehlen()) {
			case "1":
				this.verbindungHerstellen();
				break;
			case "2":
				this.programmBeenden();
				break;
			default:
				this.fehlermeldungAusgeben();
				this.startbildschirm();
				break;
		}
	}

	private void verbindungHerstellen() {
		this.abgrenzungOben();
		System.out.println("Verbindung zu einer Datenbank herstellen");
		this.abgrenzungUnten();

		var datenbank = this.eingabeDatenbank();
		var benutzername = this.eingabeBenutzername();
		var passwort = this.eingabePasswort();

		try {
			this.datenzugriff.verbindungHerstellen(datenbank, benutzername, passwort);
		} catch (Exception e) {
			System.out.println(
					"\nDer Zugriff auf die angegebene Datenbank ist nicht möglich!");
			if (this.erneutVersuchen())
				this.verbindungHerstellen();
			else
				this.startbildschirm();
		}

		this.abgrenzungOben();
		System.out.println("Verbindung erfolgreich hergestellt!");
		this.abgrenzungUnten();

		this.sqlAuswahl();
	}

	private void sqlAuswahl() {
		this.abgrenzungOben();
		System.out.println("[1] - SQL-Statement eingeben");
		System.out.println("[2] - Statements aus Aufgabe 3");
		System.out.println("[3] - Statements aus Aufgabe 4");
		System.out.println("[4] - Datenbankverbindung schließen");
		System.out.println("[5] - Programm beenden");
		this.abgrenzungUnten();

		switch (this.aktionWaehlen()) {
			case "1":
				this.sqlStatementEingeben();
				this.sqlAuswahl();
				break;
			case "2":
				this.statementsAufgabe3();
				break;
			case "3":
				this.statementsAufgabe4();
				break;
			case "4":
				this.verbindungSchliessen();
				this.startbildschirm();
				break;
			case "5":
				this.verbindungSchliessen();
				this.programmBeenden();
				break;
			default:
				this.fehlermeldungAusgeben();
				this.sqlAuswahl();
				break;
		}
	}

	private void statementsAufgabe3() {
		this.abgrenzungOben();
		System.out.println("[1] - Gib den Hauptwohnsitz von Mitarbeiter für die Personalnummer ? aus.");
		System.out.println("[2] - Gib die Anzahl der Versicherungen von Mitarbeiter für die Personalnummer ? aus.");
		System.out.println("[3] - Gib das Geschlecht von Mitarbeiter für die Personalnummer ? aus.");
		System.out.println("[4] - Gib die Konfession von Mitarbeiter für die Personalnummer ? aus.");
		System.out.println("[5] - Gib die medizinische Fachrichtung für Berufs-ID ? aus.");
		System.out.println("[6] - Zurück");
		this.abgrenzungUnten();

		switch (this.aktionWaehlen()) {
			case "1":
				System.out.println("\nBitte geben Sie eine Personalnummer ein:");
				this.datenzugriff.a1(this.benutzereingabe());
				this.nachEingabeFortsetzen();
				this.statementsAufgabe3();
				break;
			case "2":
				System.out.println("\nBitte geben Sie eine Personalnummer ein:");
				this.datenzugriff.a2(this.benutzereingabe());
				this.nachEingabeFortsetzen();
				this.statementsAufgabe3();
				break;
			case "3":
				System.out.println("\nBitte geben Sie eine Personalnummer ein:");
				this.datenzugriff.a3(this.benutzereingabe());
				this.nachEingabeFortsetzen();
				this.statementsAufgabe3();
				break;
			case "4":
				System.out.println("\nBitte geben Sie eine Personalnummer ein:");
				this.datenzugriff.a4(this.benutzereingabe());
				this.nachEingabeFortsetzen();
				this.statementsAufgabe3();
				break;
			case "5":
				System.out.println("\nBitte geben Sie eine Berufs-ID ein:");
				this.datenzugriff.a5(this.benutzereingabe());
				this.nachEingabeFortsetzen();
				this.statementsAufgabe3();
				break;
			case "6":
				this.sqlAuswahl();
				break;
			default:
				this.fehlermeldungAusgeben();
				this.statementsAufgabe3();
				break;
		}
	}

	private void statementsAufgabe4() {
		this.abgrenzungOben();
		System.out.println("[1] - Gib alle Gerätenamen aus, auf die Mitarbeiter ? eingewiesen ist.");
		System.out.println("[2] - Gib alle Mitarbeiter aus, die noch arbeiten.");
		System.out.println("[3] - Gib alle Mitarbeiter aus, die ? (Objekt) ? (Zugriffsart) dürfen.");
		System.out.println("[4] - Gib alle Mitarbeiter aus, die in ? wohnen und einen Stellenanteil von ? haben.");
		System.out.println(
				"[5] - Gib alle ? (Geschlecht), ? (Konfession) Mitarbeiter aus, die als ? (Rolle) arbeiten und bei ? (Versicherungsname) versichert sind.");
		System.out.println("[6] - Zurück");
		this.abgrenzungUnten();

		switch (this.aktionWaehlen()) {
			case "1":
				System.out.println("\nBitte geben Sie eine Personalnummer ein:");
				this.datenzugriff.b1(this.benutzereingabe());
				this.nachEingabeFortsetzen();
				this.statementsAufgabe4();
				break;
			case "2":
				this.datenzugriff.b2();
				this.nachEingabeFortsetzen();
				this.statementsAufgabe4();
				break;
			case "3":
				System.out.println("\nBitte geben Sie das Objekt an, für das Sie die Rechte einsehen wollen:");
				var objekt = this.benutzereingabe();

				System.out.println("Bitte geben Sie die Art der Berechtigung an:");
				var berechtigung = this.benutzereingabe();

				this.datenzugriff.b3(berechtigung, objekt);
				this.nachEingabeFortsetzen();
				this.statementsAufgabe4();
				break;
			case "4":
				System.out.println("\nBitte geben Sie eine Stadt ein:");
				var stadt = this.benutzereingabe();

				System.out.println("Bitte geben Sie einen Stellenanteil ein:");
				var stellenanteil = this.benutzereingabe();

				this.datenzugriff.b4(stadt, stellenanteil);
				this.nachEingabeFortsetzen();
				this.statementsAufgabe4();
				break;
			case "5":
				System.out.println("\nBitte geben Sie ein Geschlecht ein:");
				var geschlecht = this.benutzereingabe();

				System.out.println("Bitte geben Sie eine Konfession ein:");
				var konfession = this.benutzereingabe();

				System.out.println("Bitte geben Sie eine Rolle ein:");
				var rolle = this.benutzereingabe();

				System.out.println("Bitte geben Sie einen Versicherungsnamen ein:");
				var versicherungsname = this.benutzereingabe();

				this.datenzugriff.b5(geschlecht, konfession, rolle, versicherungsname);
				this.nachEingabeFortsetzen();
				this.statementsAufgabe4();
				break;
			case "6":
				this.sqlAuswahl();
				break;
			default:
				this.fehlermeldungAusgeben();
				this.statementsAufgabe4();
				break;
		}
	}

	private boolean erneutVersuchen() {
		this.abgrenzungOben();
		System.out.println("[1] - Erneute Eingabe");
		System.out.println("[2] - Vorgang abbrechen");
		this.abgrenzungUnten();

		switch (this.aktionWaehlen()) {
			case "1":
				return true;
			case "2":
				return false;
			default:
				this.fehlermeldungAusgeben();
				return this.erneutVersuchen();
		}
	}

	private void sqlStatementEingeben() {
		System.out.println("\nBitte geben Sie Ihr SQL-Statement ein:");
		var sqlStatement = this.benutzereingabe();

		this.datenzugriff.anfrageAbsetzen(sqlStatement);

		this.nachEingabeFortsetzen();
//		System.out.println("\n[Mit beliebiger Eingabe fortsetzen]");
//		this.benutzereingabe();
	}

	private void verbindungSchliessen() {
		System.out.println("\nVerbindung wird geschlossen...");
		this.datenzugriff.verbindungSchliessen();
	}

	private void programmBeenden() {
		System.out.println("\nProgramm wird beendet...");
		System.exit(0);
	}

	private void fehlermeldungAusgeben() {
		System.out.println("\nAktion nicht vorhanden, bitte erneut eingeben!");
	}

	private String aktionWaehlen() {
		System.out.println("Bitte wählen Sie eine Aktion:");
		return this.benutzereingabe();
	}

	private String eingabeDatenbank() {
		System.out.println("Bitte geben Sie den Namen der Datenbank ein:");
		return this.benutzereingabe();
	}

	private String eingabeBenutzername() {
		System.out.println("Bitte geben Sie den Benutzernamen ein:");
		return this.benutzereingabe();
	}

	private String eingabePasswort() {
		System.out.println("Bitte geben Sie das Passwort ein:");
		return this.benutzereingabe();
	}

	private String benutzereingabe() {
		return Benutzereingabe.lese();
	}

	private void nachEingabeFortsetzen() {
		System.out.println("\n[Mit beliebiger Eingabe fortsetzen]");
		this.benutzereingabe();
	}

	private void abgrenzungOben() {
		System.out.println("\n============================================");
	}

	private void abgrenzungUnten() {
		System.out.println("============================================\n");
	}
}
