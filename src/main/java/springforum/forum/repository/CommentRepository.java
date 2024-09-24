package springforum.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springforum.forum.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
