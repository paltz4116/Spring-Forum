package springforum.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springforum.forum.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
