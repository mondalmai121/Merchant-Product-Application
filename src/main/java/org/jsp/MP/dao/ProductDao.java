package org.jsp.MP.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.MP.dto.Merchant;
import org.jsp.MP.dto.Product;

public class ProductDao {

	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction t=manager.getTransaction();
	
	public Product saveProduct(Product p, int merchant_id) {
		Merchant m=manager.find(Merchant.class, merchant_id);
		if(m!=null) {
			m.getProducts().add(p);
			p.setMerchant(m);
			manager.persist(p);
			t.begin();
			t.commit();
			return p;
		}else {
			return null;
		}
	}
	
	public Product updateProduct(Product p) {
		Product products=manager.find(Product.class, p.getId());
		if(products!=null) {
			products.setName(p.getName());
			products.setBrand(p.getBrand());
			products.setCategory(p.getCategory());
			products.setDescription(p.getDescription());
			products.setCost(p.getCost());
			products.setImage_url(p.getImage_url());
			t.begin();
			t.commit();
			return products;
		}else {
			return null;
		}
	}
	
	public List<Product> findByMerchantId(int merchant_id) {
		String jpql="select m.products from Merchant m where m.id=:id";
		Query q=manager.createQuery(jpql);
		q.setParameter("id", merchant_id);
		return q.getResultList();
	}
	
	public List<Product> filter() {
		String jpql="FROM Product p ORDER BY p.cost ASC";
		Query q=manager.createQuery(jpql);
		return q.getResultList();
	}
	
	public List<Product> fetchAll(){
		String jpql="select p from Product p";
		Query q=manager.createQuery(jpql);
		List<Product> products=q.getResultList();
		return products;
	}
	
	public String exit() {
		System.out.print("Exiting");
		for(int i=0;i<7;i++) {
			System.err.println(".");
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return "\nThank you visit again........!!!";
	}
}
