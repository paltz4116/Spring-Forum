package springforum.forum.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import springforum.forum.entity.Member;
import springforum.forum.entity.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    //Page<Post> findByMemberOrderByIdDesc(Member member, Pageable pageable);

    Page<Post> findAllByOrderByIdDesc(Pageable pageable);



    @Override
    @EntityGraph(attributePaths = {"member", "comments"})
    Optional<Post> findById(Long id);
}
