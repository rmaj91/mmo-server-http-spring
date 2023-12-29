package com.maiu.mmoserverhttpspring.commons.dtos.accounts;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.maiu.mmoserverhttpspring.commons.dtos.Constants.USERNAME_VALIDATION_REGEXP;
import static com.maiu.mmoserverhttpspring.commons.dtos.Constants.PASSWORD_VALIDATION_REGEXP;

@Getter
@Setter
public class AccountCreateRequest {

    @Size(min = 4, max = 20)
    @Pattern(regexp = USERNAME_VALIDATION_REGEXP, message = "Username can contain only letters and digits.")
    private String username;

    @Size(min = 4, max = 20)
    @Pattern(regexp = PASSWORD_VALIDATION_REGEXP, message = "Password can contains only letters, digits and @$!%*?& characters.")
    private String password;

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }
}
