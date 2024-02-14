package jpaproject.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpaproject.jpashop.model.Member;
import jpaproject.jpashop.repo.MemberRepo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepo memberRepo;
	
	//회원가입
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member); //중복 회원 검증
		memberRepo.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findName = memberRepo.findName(member.getName());
		if(!findName.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 이름입니다");
		}
	}
	
	//회원 전체 조회
	public List<Member> findMembers(){
		return memberRepo.findAll();
	}
	
	//회원 아이디 조회
	public Member findOne(Long MemberId) {
		return memberRepo.findOne(MemberId);
	}
	

}
