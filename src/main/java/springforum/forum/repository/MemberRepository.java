package springforum.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springforum.forum.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);
}
