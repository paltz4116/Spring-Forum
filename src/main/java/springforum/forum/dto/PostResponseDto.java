package springforum.forum.dto;

import lombok.Data;
import springforum.forum.entity.Comment;
import springforum.forum.entity.Post;

import java.util.List;

@Data
public class PostResponseDto {

    private final Long id;
    private final String name;
    private final String title;
    private final String content;
    private final List<Comment> comments;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.name = post.getMember().getName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = post.getComments();
    }
}
