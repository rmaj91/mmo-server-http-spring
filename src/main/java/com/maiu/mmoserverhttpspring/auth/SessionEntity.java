package com.maiu.mmoserverhttpspring.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "SESSIONS")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private UUID accountId;

    @Column(name = "CREATION_DATE", updatable = false, insertable = false)
    private Date creationDate;
}
