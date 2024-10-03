package springforum.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springforum.forum.dto.PostDto;
import springforum.forum.dto.PostSaveDto;
import springforum.forum.entity.Post;
import springforum.forum.repository.PostRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(PostSaveDto post) {
        postRepository.save(new Post(post.getMember(), post.getTitle(), post.getContent()));
    }

    public Page<PostDto> findAll(Pageable pageable) {

        return postRepository.findAllByOrderByIdDesc(pageable)
                .map(PostDto::new);
    }

    public Page<PostDto> findPage(int pageNum, Pageable pageable) {

        pageable = PageRequest.of(pageNum, 10);

        return postRepository.findAllByOrderByIdDesc(pageable)
                .map(PostDto::new);
    }

    public Post findPost(Long id) {
        Optional<Post> byId = postRepository.findById(id);

        if (!byId.isPresent()) {
            return null;
        }

        return byId.get();
    }

    public PostDto findPostDto(Long id) {
        Optional<Post> byId = postRepository.findById(id);

        if (!byId.isPresent()) {
            return null;
        }

        return new PostDto(byId.get());
    }

    public Post findPostForComment(Long id) {

        return postRepository.getReferenceById(id);
    }
}
