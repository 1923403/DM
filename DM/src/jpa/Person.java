package jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
	private String name;
	@Id
	private int personennummer;

	public Person() {
	}

	public Person(int personennummer, String name) {
		this.personennummer = personennummer;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
