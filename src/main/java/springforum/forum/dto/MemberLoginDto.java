package springforum.forum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberLoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    public MemberLoginDto() {
    }

    public MemberLoginDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
