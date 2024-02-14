package jpaproject.jpashop.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpaproject.jpashop.model.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepo {

	private final EntityManager em;
	
	public void save(Item item) {
		if(item.getId()== null) {
			em.persist(item);
		}else {
			em.merge(item);
		}
	}
	
	public Item findOne(Long Id) {
		return em.find(Item.class, Id);
	}
	
	public List<Item> findAll(){
		return em.createQuery("select i from Item i",Item.class)
				.getResultList();
	}
}
