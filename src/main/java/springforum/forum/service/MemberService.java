package springforum.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springforum.forum.dto.SignupDto;
import springforum.forum.entity.Member;
import springforum.forum.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(SignupDto member) {
        memberRepository.save(new Member(member.getLoginId(),
                member.getName(), member.getPassword()));
    }

    public Optional<Member> findByLoginID(String loginId) {

        return memberRepository.findByLoginId(loginId);
    }

    public boolean checkMember(String loginId, String password) {

        Optional<Member> member = memberRepository.findByLoginId(loginId);

        if (!member.isPresent() || !password.equals(member.get().getPassword())) {
            return false;
        }

        return true;
    }
}
