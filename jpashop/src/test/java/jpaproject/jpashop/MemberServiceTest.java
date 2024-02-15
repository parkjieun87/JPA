package jpaproject.jpashop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpaproject.jpashop.model.Member;
import jpaproject.jpashop.repo.MemberRepo;
import jpaproject.jpashop.service.MemberService;

@SpringBootTest
@Transactional
class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepo memberRepo;

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
	
	@Test()
	public void 중복_회원_예외()throws Exception {
		//given
		Member member1 = new Member();
		member1.setName("kim");
		
		Member member2 = new Member();
		member2.setName("kim");
    		
		 //When & Then
	    memberService.join(member1);
	    assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
	}
	
}
