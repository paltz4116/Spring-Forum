package springforum.forum.dto;

import lombok.Data;
import springforum.forum.entity.Comment;
import springforum.forum.entity.Member;
import springforum.forum.entity.Post;

import java.util.List;

@Data
public class PostDto {

    private final Long id;
    private Member member;
    private final String title;
    private final String content;
    private final List<Comment> comments;

    public PostDto(Post post) {
        this.id = post.getId();
        this.member = post.getMember();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = post.getComments();
    }
}
