package db;

public class TestXML {
	public static void main(String[] args) {
		DB db = new DB(Zugangsdaten.db, Zugangsdaten.user, Zugangsdaten.pass);
		db.setSQL("SELECT * FROM produkt;");
		String daten = db.lesenXML();
		System.out.println(daten);
		db.close();
	}
}
