package jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student extends Person {
	@Id
	private int matrikelnummer;

	public Student() {
	}

	public Student(int personennummer, int matrikelnummer, String name) {
		super(personennummer, name);
		this.setMatrikelnummer(matrikelnummer);
	}

	public int getMatrikelnummer() {
		return this.matrikelnummer;
	}

	public void setMatrikelnummer(int matrikelnummer) {
		this.matrikelnummer = matrikelnummer;
	}
}
