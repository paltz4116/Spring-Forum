package springforum.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springforum.forum.entity.Post;
import springforum.forum.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(Post post) {
        postRepository.save(post);
    }
}
