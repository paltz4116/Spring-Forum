package springforum.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springforum.forum.dto.PostResponseDto;
import springforum.forum.entity.Post;
import springforum.forum.repository.PostRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(Post post) {
        postRepository.save(post);
    }

    public Page<PostResponseDto> findAll(Pageable pageable) {

        return postRepository.findAllByOrderByIdDesc(pageable)
                .map(PostResponseDto::new);
    }

    public Page<PostResponseDto> findPage(int pageNum, Pageable pageable) {

        pageable = PageRequest.of(pageNum, 10);

        return postRepository.findAllByOrderByIdDesc(pageable)
                .map(PostResponseDto::new);
    }

    public Post findPost(Long id) {
        Optional<Post> byId = postRepository.findById(id);

        if (!byId.isPresent()) {
            return null;
        }

        return byId.get();
    }

    public PostResponseDto findPostDto(Long id) {
        Optional<Post> byId = postRepository.findById(id);

        if (!byId.isPresent()) {
            return null;
        }

        return new PostResponseDto(byId.get());
    }

    public Post findPostForComment(Long id) {

        return postRepository.getReferenceById(id);
    }
}
