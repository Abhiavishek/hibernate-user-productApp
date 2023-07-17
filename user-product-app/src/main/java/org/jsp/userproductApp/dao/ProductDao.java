package org.jsp.userproductApp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userproductApp.dto.Product;
import org.jsp.userproductApp.dto.User;

public class ProductDao {
	
	EntityManager m = Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public Product SaveProduct(Product product, int uid) {
		User u = m.find(User.class, uid);
		if(u!=null) {
			u.getProducts().add(product);
			product.setUser(u);
			EntityTransaction t = m.getTransaction();
			m.persist(product);
			t.begin();
			t.commit();
			return product;
		}
		return null;
	}
	
	public Product UpdateProduct( int uid,String category) {
		User u = m.find(User.class, uid);
		if(u!=null) {
			String qry ="select p.products from User p where p.id=?1";
			Query q = m.createQuery(qry);
			q.setParameter(1, uid);
			Product p = (Product)q.getSingleResult();
//			Product p = m.find(Product.class, uid);
			u.getProducts().add(p);
			p.setUser(u);
			if(p!=null) {
//				p.setBrand(brand);
//				p.setPrice(price);
				p.setCategory(category);
				EntityTransaction t = m.getTransaction();
				m.merge(p);
				t.begin();
				t.commit();
				return p;
			}
		}
		return null;
	}
	
	public Product FindProductById(int id) {
		return m.find(Product.class, id);
	}
	
	public Product DeleteProduct(int id) {
		Product p = FindProductById(id);
		if(p!=null) {
			m.remove(p);
			EntityTransaction t = m.getTransaction();
			t.begin();
			t.commit();
			return p;
		}
		return null;
	}
	
	public List<Product> FindProductByUserId(int userid){
		String qry = "select p.products from User p where p.id=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, userid);
		return q.getResultList();
	}
	
	public List<Product> FindProductByCategory(String category){
		String qry = "select p from Product p where p.category=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, category);
		return q.getResultList();
	}
	
	public List<Product> FindProductByBrand(String brand){
		String qry = "select p from Product p where p.brand=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, brand);
		return q.getResultList();
	}
	
	public List <Product>FindProductByPrice(double price){
		String qry = "select p from Product p where p.price=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, price);
		return q.getResultList();
		
	}


}
