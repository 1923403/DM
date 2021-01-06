package sql;

public class Navigation {
	private Datenzugriff datenzugriff;

	public Navigation(Datenzugriff datenzugriff) {
		this.datenzugriff = datenzugriff;
	}

	public void zeigeStartmenue() {
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
				System.out.println("\nProgramm wird beendet...");
				System.exit(0);
				break;
			default:
				System.err.println("\nAktion nicht vorhanden, bitte erneut eingeben!");
				this.zeigeStartmenue();
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

		this.datenzugriff.verbindungHerstellen(datenbank, benutzername, passwort);

		if (this.datenzugriff.verbindungHergestellt()) {
			System.out.println("\n============================================");
			System.out.println("Verbindung erfolgreich hergestellt!");
			System.out.println("============================================\n");
			this.sqlStatementEingeben();
		} else {
			this.verbindungHerstellen();
		}
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
				System.out.println(sqlStatement);
				this.sqlStatementEingeben();
				break;
			case "2":
				this.datenzugriff.verbindungSchliessen();
				this.zeigeStartmenue();
				break;
			case "3":
				System.out.println("\nProgramm wird beendet...");
				System.exit(0);
				break;
			default:
				System.err.println("\nAktion nicht vorhanden, bitte erneut eingeben!");
				this.zeigeStartmenue();
				break;
		}
	}
}
