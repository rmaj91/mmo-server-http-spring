package com.maiu.mmoserverhttpspring.accounts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNTS")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "USERNAME", nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "IS_GUEST_ACCOUNT", nullable = false)
    private boolean isGuestAccount;

    @Column(name = "CREATION_DATE", updatable = false, insertable = false)
    private Date creationDate;

    @UpdateTimestamp(source = SourceType.DB)
    @Column(name = "UPDATE_DATE")
    private Date updatedDate;
}
