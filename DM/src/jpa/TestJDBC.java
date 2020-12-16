package jpa;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import db.DB;

public class TestJDBC {

	public static void main(String[] args) {
		var db = new DB(Zugangsdaten.db, Zugangsdaten.user, Zugangsdaten.pass);
		ArrayList<LinkedHashMap<String, String>> daten;
		db.setSQL("SELECT * FROM Vorlesung;");
		daten = db.lesenJava();
		System.out.println(daten);
		db.setSQL("SELECT * FROM Professor;");
		daten = db.lesenJava();
		System.out.println(daten);
		db.close();
	}
}
