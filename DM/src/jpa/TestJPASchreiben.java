package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestJPASchreiben {

	public static void main(String[] args) {
		EntityManagerFactory factory = null;
		EntityManager db = null;

		try {
			Professor p = new Professor(65, "Dopatka");
			Vorlesung v1 = new Vorlesung(17, "Datenmanagement", 5, 65);
			Vorlesung v2 = new Vorlesung(32, "Programmieren 2", 5, 65);
			factory = Persistence.createEntityManagerFactory("dm");
			db = factory.createEntityManager();
			db.getTransaction().begin();
			db.persist(p);
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
