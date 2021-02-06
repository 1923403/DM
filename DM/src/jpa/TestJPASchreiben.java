package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestJPASchreiben {

	public static void main(String[] args) {
		EntityManagerFactory factory = null;
		EntityManager db = null;

		try {
			Professor p = new Professor(1, 65, "Dopatka");
			Student s = new Student(2, 567675, "Wurst");
			Vorlesung v1 = new Vorlesung(17, "Datenmanagement", 5, p);
			Vorlesung v2 = new Vorlesung(32, "Programmieren 2", 5, p);
			factory = Persistence.createEntityManagerFactory("dm");
			db = factory.createEntityManager();
			db.getTransaction().begin();
			db.persist(p);
			db.persist(s);
			db.persist(v1);
			db.persist(v2);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception e2) {
			}
		}
	}
}
