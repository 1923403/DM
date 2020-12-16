package db;

public class TestXML {
	public static void main(String[] args) {
		DB db = new DB();
		db.setSQL("SELECT * FROM mitarbeiter;");
		String daten = db.lesenXML();
		System.out.println(daten);
		db.close();
	}
}
