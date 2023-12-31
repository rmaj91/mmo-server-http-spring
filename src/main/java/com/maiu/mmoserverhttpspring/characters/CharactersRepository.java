package com.maiu.mmoserverhttpspring.characters;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CharactersRepository extends JpaRepository<CharacterEntity, UUID> {
    boolean existsByName(String name);

    List<CharacterEntity> findAllByAccountIdOrderByCreationDateAsc(UUID accountId);

    Optional<CharacterEntity> findByIdAndAccountId(UUID characterId, UUID accountId);
}
