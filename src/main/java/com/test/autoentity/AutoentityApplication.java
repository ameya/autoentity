package com.test.autoentity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.orm.jpa.EntityManagerHolder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@SpringBootApplication
public class AutoentityApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AutoentityApplication.class, args);

		EntityManager entityManager = getJpaEntityManager();
		User user = entityManager.find(User.class, 1);

		entityManager.getTransaction().begin();
		user.setName("John");
		user.setEmail("john@domain.com");
		entityManager.merge(user);
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		entityManager.persist(new User("Monica", "monica@domain.com"));
		entityManager.getTransaction().commit();
	}





	private static class EntityManagerHolder {
		private static final EntityManager ENTITY_MANAGER = new JpaEntityManagerFactory(
				new Class[]{User.class})
				.getEntityManager();
	}

	public static EntityManager getJpaEntityManager() {
		return EntityManagerHolder.ENTITY_MANAGER;
	}

}
