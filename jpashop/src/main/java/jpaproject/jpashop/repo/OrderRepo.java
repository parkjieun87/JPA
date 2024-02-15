package jpaproject.jpashop.repo;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jpaproject.jpashop.model.Order;
import jpaproject.jpashop.model.OrderSearch;
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
	
	 public List<Order> findAllByCriteria(OrderSearch orderSearch) {
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
	        Root<Order> o = cq.from(Order.class);
	        Join<Object, Object> m = o.join("member", JoinType.INNER);

	        List<Predicate> criteria = new ArrayList<>();

	        //주문 상태 검색
	        if (orderSearch.getOrderStatus() != null) {
	            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
	            criteria.add(status);
	        }
	        //회원 이름 검색
	        if (org.springframework.util.StringUtils.hasText(orderSearch.getMemberName())) {
	            Predicate name =
	                    cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
	            criteria.add(name);
	        }

	        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
	        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
	        return query.getResultList();
	    }
}
