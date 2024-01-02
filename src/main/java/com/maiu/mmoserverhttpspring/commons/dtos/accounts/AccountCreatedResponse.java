package com.maiu.mmoserverhttpspring.commons.dtos.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreatedResponse {
    private String id;
    private String username;
}
