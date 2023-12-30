package com.maiu.mmoserverhttpspring.characters;

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
@Table(name = "CHARACTERS")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ACCOUNT_ID", nullable = false, updatable = false, unique = true)
    private UUID accountId;

    @Column(name = "NAME", nullable = false, updatable = false, unique = true)
    private String name;

    @Column(name = "CREATION_DATE", updatable = false, insertable = false)
    private Date creationDate;

    @UpdateTimestamp(source = SourceType.DB)
    @Column(name = "UPDATE_DATE")
    private Date updatedDate;
}
