package db;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestDB {
	public static void main(String[] args) {
//		DB db = new DB("krankenhaus-informationssystem mit fokus auf beschäftigte");
//		db.setSQL("SELECT * FROM mitarbeiter;");
		DB db = new DB("test");
		db.setSQL("SELECT * FROM product;");
		ArrayList<LinkedHashMap<String, String>> daten = db.lesenJava();
		for (LinkedHashMap<String, String> datensatz : daten) {
			System.out.println(datensatz);
		}
		db.close();
	}
}
