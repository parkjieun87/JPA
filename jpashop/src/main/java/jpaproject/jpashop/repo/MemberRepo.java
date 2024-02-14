package jpaproject.jpashop.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpaproject.jpashop.model.Member;

@Repository
public class MemberRepo {
	
	@PersistenceContext
	private EntityManager em;

	//저장
	public void save(Member member) {
		em.persist(member);
	}

	//id로 조회
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	//리스트조회
	public List<Member> findAll(){
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	//이름으로 조회
	public List<Member> findName(String name){
		return em.createQuery("select m from Member m where m.name = :name",Member.class)
				.setParameter("name", name).getResultList(); 
	}
}
