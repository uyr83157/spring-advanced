package org.example.expert.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordRequest {

    @NotBlank
    private String oldPassword;
    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{8,}$", message = "새 비밀번호는 8자 이상이어야 하고, 숫자와 대문자를 포함해야 합니다.")
    private String newPassword;

    // 이렇게하면 (?=.*\d)(?=.*[A-Z])만 만족하면 어떤 글자든 8자 이상 채우기하면 통과됨.
    // => ^(?=.*\d)(?=.*[A-Z])[A-Za-z\\d]{8,}$ 이렇게 제한 걸면 [A-Za-z\\d] 중에 8글자 이상 채우게 됨.
}
