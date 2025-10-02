package com.back2.Instagram.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class SignupRequest {

    @NotBlank(message = "사용자 이름은 필수 입력 값입니다.")
    @Size(min = 4, max = 50, message = "사용자 이름은 4자 이상 50자 이하로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}