package jpaproject.jpashop.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpaproject.jpashop.model.Order;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepo {

	private final EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	
	public Order findOne(Long Id) {
		return em.find(Order.class, Id);
	}
	
//	public List<Order> findAll(OrderSearch orderSearch){
//		
//	}
}
