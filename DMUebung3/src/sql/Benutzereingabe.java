package sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Benutzereingabe {
	public static String lese() {
		final var in = new BufferedReader(new InputStreamReader(System.in));
		var eingabe = "";

		try {
			eingabe = in.readLine();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return eingabe;
	}
}
