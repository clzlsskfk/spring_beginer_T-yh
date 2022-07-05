package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member); // 회원 중복 검증

        memberRepository.save(member);
        return member.getId();
    }

    //멤버 서비스가 멤버 레포지토리의 메서드를 사용하기 위해 멤버 레포지토리를  멤버서비스에 연결했다.

    /**
     * 회원 중복 확인
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                   throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * ID 조회
     */
    public Optional<Member> findOne(Long member) {
        return memberRepository.findById(member);
    }
    
}
