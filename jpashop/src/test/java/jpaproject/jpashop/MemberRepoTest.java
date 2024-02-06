package jpaproject.jpashop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import jpaproject.jpashop.model.Member;
import jpaproject.jpashop.repo.MemberRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepoTest {

	@Autowired MemberRepo memberRepo;
	
	@Test
	public void testMember()throws Exception {
		//given
		Member member = new Member();
		member.setUsername("cloud");
		
		//when
		Long save = memberRepo.save(member);
		Member findId = memberRepo.find(save);
		
		//then
		assertThat(findId.getId()).isEqualTo(member.getId());
		assertThat(findId.getUsername()).isEqualTo(member.getUsername());
	}
	

}
