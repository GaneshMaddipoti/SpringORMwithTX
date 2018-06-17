package spring.orm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Component;

import spring.orm.model.Product;

@Component
public class ProductDao {

	@PersistenceContext
	private EntityManager em;

	public void persist(Product product) {
		em.persist(product);
	}
	
	public List<Product> get(Product product) {
		CriteriaQuery<Product> criteriaQuery = em.getCriteriaBuilder().createQuery(Product.class);
		return em.createQuery(criteriaQuery).getResultList();
	}

	public List<Product> findAll() {
		return em.createQuery("SELECT p FROM Products p").getResultList();
	}

}
