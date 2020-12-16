package db;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestDB {
	public static void main(String[] args) {
		DB db = new DB();
		db.setSQL("SELECT * FROM mitarbeiter;");
		ArrayList<LinkedHashMap<String, String>> daten = db.lesenJava();
		for (LinkedHashMap<String, String> datensatz : daten) {
			System.out.println(datensatz);
		}
		db.close();
	}
}
