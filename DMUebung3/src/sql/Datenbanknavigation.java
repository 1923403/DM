package sql;

public class Datenbanknavigation {
	private Datenzugriff datenzugriff;

	public Datenbanknavigation() {
		this.datenzugriff = new Datenzugriff();
	}

	public void startbildschirm() {
		System.out.println("\n============================================");
		System.out.println("[1] - Verbindung zu einer Datenbank herstellen");
		System.out.println("[2] - Programm beenden");
		System.out.println("============================================\n");

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
		System.out.println("\n============================================");
		System.out.println("Verbindung zu einer Datenbank herstellen");
		System.out.println("============================================\n");

		var datenbank = this.eingabeDatenbank();
		var benutzername = this.eingabeBenutzername();
		var passwort = this.eingabePasswort();

		try {
			this.datenzugriff.verbindungHerstellen(datenbank, benutzername, passwort);
		} catch (Exception e) {
			System.err.println(
					"\nDer Zugriff auf die angegebene Datenbank ist nicht möglich!");
			if (this.erneutVersuchen())
				this.verbindungHerstellen();
			else
				this.startbildschirm();
		}

		System.out.println("\n============================================");
		System.out.println("Verbindung erfolgreich hergestellt!");
		System.out.println("============================================\n");

		this.sqlAuswahl();
	}

	private void sqlAuswahl() {
		System.out.println("\n============================================");
		System.out.println("[1] - SQL-Statement eingeben");
		System.out.println("[2] - Datenbankverbindung schließen");
		System.out.println("[3] - Programm beenden");
		System.out.println("============================================\n");

		switch (this.aktionWaehlen()) {
			case "1":
				this.sqlStatementEingeben();
				this.sqlAuswahl();
				break;
			case "2":
				this.verbindungSchliessen();
				this.startbildschirm();
				break;
			case "3":
				this.verbindungSchliessen();
				this.programmBeenden();
				break;
			default:
				this.fehlermeldungAusgeben();
				this.sqlAuswahl();
				break;
		}
	}

	private boolean erneutVersuchen() {
		System.out.println("\n============================================");
		System.out.println("[1] - Erneute Eingabe");
		System.out.println("[2] - Vorgang abbrechen");
		System.out.println("============================================\n");

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

		System.out.println();

		this.datenzugriff.anfrageAbsetzen(sqlStatement);

		System.out.println("\n[Mit beliebiger Eingabe fortsetzen]");
		this.benutzereingabe();
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
		System.err.println("\nAktion nicht vorhanden, bitte erneut eingeben!");
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
}
