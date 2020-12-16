package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	public String getName() {
		return this.name;
	}

	public int getPersonalnummer() {
		return this.personalnummer;
	}

	public List<Vorlesung> getVorlesungen() {
		return this.vorlesungen;
	}

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
