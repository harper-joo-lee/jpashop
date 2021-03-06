package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

//    1. @Autowired
//    private MemberRepository memberRepository;

    // 2. with @AllArgsConstructor
//    private final MemberRepository memberRepository;
//
//    public MemberSerivce(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    private final MemberRepository memberRepository;

    /* 회원 가입
    */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    private void validateDuplicateMember(Member member) {
        // EXCEPTION
       List<Member> findMembers = memberRepository.findByName(member.getName());
       if(!findMembers.isEmpty()) {
           throw new IllegalStateException("이미 존재하는 회원입니다.");
       }
    }

    // 회원 전체 조회
    // 읽기에는 가급적이면 readOnly = true
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
