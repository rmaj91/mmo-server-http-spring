package com.maiu.mmoserverhttpspring.commons.dtos.accounts;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import static com.maiu.mmoserverhttpspring.commons.dtos.Constants.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCreateRequest {

    @Size(min = MIN_USERNAME_NAME_LENGTH, max = MAX_USERNAME_NAME_LENGTH)
    @Pattern(regexp = USERNAME_VALIDATION_REGEXP, message = "Username can contain only letters and digits.")
    private String username;

    @Size(min = MIN_PASSWORD_LENGTH, max = MAX_PASSWORD_LENGTH)
    @Pattern(regexp = PASSWORD_VALIDATION_REGEXP, message = "Password can contains only letters, digits and @$!%*?& characters.")
    private String password;

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }
}
