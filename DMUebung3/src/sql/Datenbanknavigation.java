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

		System.out.println("Bitte wählen Sie eine Aktion:");
		var eingabe = Benutzereingabe.lese();

		switch (eingabe) {
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

		System.out.println("Bitte geben Sie den Namen der Datenbank ein:");
		var datenbank = Benutzereingabe.lese();

		System.out.println("Bitte geben Sie den Benutzernamen ein:");
		var benutzername = Benutzereingabe.lese();

		System.out.println("Bitte geben Sie das Passwort ein:");
		var passwort = Benutzereingabe.lese();

		try {
			this.datenzugriff.verbindungHerstellen(datenbank, benutzername, passwort);
		} catch (Exception e) {
			System.err.println(
					"\nDer Zugriff auf die angegebene Datenbank ist nicht möglich, bitte überprüfen Sie Ihre Eingaben!");
			if (this.erneutVersuchen())
				this.verbindungHerstellen();
			else
				this.startbildschirm();
		}

		System.out.println("\n============================================");
		System.out.println("Verbindung erfolgreich hergestellt!");
		System.out.println("============================================\n");

		this.sqlStatementEingeben();
	}

	private void sqlStatementEingeben() {
		System.out.println("\n============================================");
		System.out.println("[1] - SQL-Statement eingeben");
		System.out.println("[2] - Datenbankverbindung schließen");
		System.out.println("[3] - Programm beenden");
		System.out.println("============================================\n");

		System.out.println("Bitte wählen Sie eine Aktion:");
		var eingabe = Benutzereingabe.lese();

		switch (eingabe) {
			case "1":
				System.out.println("\nBitte geben Sie Ihr SQL-Statement ein:");
				var sqlStatement = Benutzereingabe.lese();
				System.out.println();
				this.datenzugriff.anfrageAbsetzen(sqlStatement);
				this.sqlStatementEingeben();
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
				this.sqlStatementEingeben();
				break;
		}
	}

	private boolean erneutVersuchen() {
		System.out.println("\n============================================");
		System.out.println("[1] - Erneute Eingabe");
		System.out.println("[2] - Vorgang abbrechen");
		System.out.println("============================================\n");

		System.out.println("Bitte wählen Sie eine Aktion:");
		var eingabe = Benutzereingabe.lese();

		switch (eingabe) {
			case "1":
				return true;
			case "2":
				return false;
			default:
				this.fehlermeldungAusgeben();
				return this.erneutVersuchen();
		}
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
}
