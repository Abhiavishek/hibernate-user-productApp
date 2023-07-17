package org.jsp.userproductApp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userproductApp.dto.Product;
import org.jsp.userproductApp.dto.User;

public class UserDao {
	
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public User saveUser(User user) {
		EntityTransaction t = manager.getTransaction();
		manager.persist(user);
		t.begin();
		t.commit();
		return user;
	}
	
	public User updateUser(int id, long phone, String email) {
		User u = manager.find(User.class, id);
		if(u!=null) {
			u.setPhone(phone);
			u.setEmail(email);
			EntityTransaction t = manager.getTransaction();
			manager.merge(u);
			t.begin();
			t.commit();
			return u;
		}
		return null;
	}
	
	public User FindUserById(int id) {
		return manager.find(User.class, id);
	}
	
	public User deleteUser(int id) {
		String qry = "select u.products from User u where u.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, id);
		List<Product>p = q.getResultList();
		User u = FindUserById(id);
		if(u!=null) {
			EntityTransaction t = manager.getTransaction();
			try {
				if(p.size()>0) {
					for(Product pro: p) {manager.remove(pro);}
	                t.begin();
	                t.commit();
				}
			}
			catch(NoResultException e) {
				System.out.println("No foreign key attached ");
			}
			
			manager.remove(u);
			t.begin();
			t.commit();
			return u;
		}
		return null;
	}
	
	
	public User verifyUser(long phone, String password) {
		String qry = "select u from User u where u.phone=?1 and u.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (User)q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
		
		
	}
	
	public User verifyUser(String email, String password) {
		String qry = "select u from User u where u.email=?1 and u.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User)q.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}

}
