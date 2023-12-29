package com.maiu.mmoserverhttpspring.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountsRepository extends JpaRepository<AccountEntity, UUID> {
    boolean existsByUsername(String name);

    Optional<AccountEntity> findByUsername(String username);
}
