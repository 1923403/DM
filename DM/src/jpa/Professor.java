package jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Professor")
public class Professor extends Person {
	private String name;
	@Id
	private int personalnummer;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "gelesenVon", referencedColumnName = "personalnummer")
	private List<Vorlesung> vorlesungen = new ArrayList<>();

	public Professor() {
		// noetig, um das Objekt automatisiert vom ORM erzeugen zu lassen
	}

	public Professor(int personennummer, int personalnummer, String name) {
		super(personennummer, name);
		this.setPersonalnummer(personalnummer);
	}

	public void addVorlesung(Vorlesung v) {
		this.vorlesungen.add(v);
	}

	@Override
	public String getName() {
		return this.name;
	}

	public int getPersonalnummer() {
		return this.personalnummer;
	}

	public List<Vorlesung> getVorlesungen() {
		return this.vorlesungen;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public void setPersonalnummer(int personalnummer) {
		this.personalnummer = personalnummer;
	}

	public void setVorlesungen(List<Vorlesung> vorlesungen) {
		this.vorlesungen = vorlesungen;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Professor " + this.getName() + "\n";
		if (this.vorlesungen.isEmpty())
			s += "<keine Vorlesungen>";
		else
			for (Vorlesung v : this.vorlesungen)
				s += "- Vorlesung " + v.getTitel() + " mit " + v.getSws() + " SWS\n";
		return s;
	}
}
