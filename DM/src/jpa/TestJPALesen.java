package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestJPALesen {

	public static void main(String[] args) {
		EntityManagerFactory factory = null;
		EntityManager db = null;

		try {
			factory = Persistence.createEntityManagerFactory("dm");
			db = factory.createEntityManager();
			Query query = db.createQuery("SELECT p FROM Professor p " + "WHERE p.name='Dopatka'");
			Professor p = (Professor) query.getSingleResult();
			System.out.println(p);
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
