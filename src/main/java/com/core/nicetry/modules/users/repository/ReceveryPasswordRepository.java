package com.core.nicetry.modules.users.repository;

import com.core.nicetry.modules.users.entityes.ReceveryPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReceveryPasswordRepository extends JpaRepository<ReceveryPassword, UUID> {
  ReceveryPassword findByTokenAndStatusFalse(String token);
}
