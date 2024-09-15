package springforum.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springforum.forum.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {


}
