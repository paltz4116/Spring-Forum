package springforum.forum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import springforum.forum.entity.Comment;
import springforum.forum.entity.Member;
import springforum.forum.entity.Post;

import java.util.List;

@Data
public class PostSaveDto {

    private Member member;

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    private final List<Comment> comments;
}
