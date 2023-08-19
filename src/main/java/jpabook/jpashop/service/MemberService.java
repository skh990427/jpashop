package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
//@AllArgsConstructor
@RequiredArgsConstructor //@AllArgsConstructor 보다 더 나은 방법 final로 선언된것만 생성자를 만들어준다.
public class MemberService {

    private final MemberRepository memberRepository;

//    @AllArgsConstructor   어노테이션을 사용해면 얘를 만들어줌
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional //얘가 우선권을 가짐 위에 선언된 @Transactional 보다
    public Long join(Member member) {
        validateDuplicateMember(member); //중복회원검증, 중복회원이 있으면 예외를 터뜨린다.

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
