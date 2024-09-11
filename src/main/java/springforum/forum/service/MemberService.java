package springforum.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springforum.forum.entity.Member;
import springforum.forum.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }

    public boolean checkMember(String loginId, String password) {

        Optional<Member> member = memberRepository.findByLoginId(loginId);

        if (!member.isPresent() || !password.equals(member.get().getPassword())) {
            return false;
        }

        return true;
    }
}
