package vn.hoidanit.jobhunter.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank(message = "username cannot be left blank")
    private String username;

    @NotBlank(message = "password cannot be left blank")
    private String password;

}
