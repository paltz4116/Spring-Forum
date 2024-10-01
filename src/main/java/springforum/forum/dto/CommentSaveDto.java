package springforum.forum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentSaveDto {

    @NotBlank
    private String content;

    public CommentSaveDto(String content) {
        this.content = content;
    }
}
