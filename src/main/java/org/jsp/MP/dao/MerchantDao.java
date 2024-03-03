package org.jsp.MP.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.MP.dto.Merchant;

public class MerchantDao {

	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction t=manager.getTransaction();
	
	public Merchant saveMerchant(Merchant m) {
		manager.persist(m);
		t.begin();
		t.commit();
		return m;
	}
	
	public Merchant updateMerchant(Merchant m) {
		Merchant merchant=manager.find(Merchant.class, m.getId());
		if(merchant!=null) {
			merchant.setName(merchant.getName());
			merchant.setPhone(merchant.getPhone());
			merchant.setEmail(merchant.getEmail());
			merchant.setGst(merchant.getGst());
			merchant.setPassword(merchant.getPassword());
			t.begin();
			t.commit();
			return merchant;
		}else {
			return null;
		}
	}
	
	public Merchant findBYId(int id) {
		return manager.find(Merchant.class, id);
	}
	
	public Merchant verifyMerchant(long phone, String password) {
		String jpql="select m from Merchant m where m.phone=:phone and m.password=:password";
		Query q=manager.createQuery(jpql);
		q.setParameter("phone", phone);
		q.setParameter("password", password);
		try {
			return (Merchant) q.getSingleResult();
		}catch(Exception e) {
			return null;
		}
	}
	
	public List<Merchant> fetchAll() {
		String jpql="select m from Merchant m";
		Query q=manager.createQuery(jpql);
		List<Merchant> merchants=q.getResultList();
		return merchants;
	}
}
