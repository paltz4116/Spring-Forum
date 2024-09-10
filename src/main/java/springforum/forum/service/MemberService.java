package springforum.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springforum.forum.entity.Member;
import springforum.forum.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }
}
