package com.maiu.mmoserverhttpspring.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<SessionEntity, UUID> {

    void deleteAllByAccountId(UUID accountId);
}
