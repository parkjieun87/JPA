package jpaproject.jpashop;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jpaproject.jpashop.model.Member;
import jpaproject.jpashop.repo.MemberRepo;
import jpaproject.jpashop.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepo memberRepo;
	@Autowired EntityManager em;

	@Test
	public void 회원가입()throws Exception {
		//given
		Member member = new Member();
		member.setName("jieun");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		assertEquals(member, memberRepo.findOne(saveId));
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복검사()throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
    		
		//when
		memberService.join(member1);
		memberService.join(member2);
		
		//then
		fail("예외가 발생해야 한다.");
		
	}
}
