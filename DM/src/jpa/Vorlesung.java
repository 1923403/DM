package jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Vorlesung")
public class Vorlesung {
	@Id
	private int id;
	private String titel;
	private int sws;
	@Transient
	private Professor gelesenVon;

	public Vorlesung() {
		// noetig, um das Objekt automatisiert vom ORM erzeugen zu lassen
	}

	public Vorlesung(int id, String titel, int sws, Professor gelesenVon) {
		this.setId(id);
		this.setTitel(titel);
		this.setSws(sws);
		this.setGelesenVon(gelesenVon);
		this.gelesenVon.addVorlesung(this);
	}

	public Professor getGelesenVon() {
		return this.gelesenVon;
	}

	public int getId() {
		return this.id;
	}

	public int getSws() {
		return this.sws;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setGelesenVon(Professor gelesenVon) {
		this.gelesenVon = gelesenVon;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSws(int sws) {
		this.sws = sws;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
}
