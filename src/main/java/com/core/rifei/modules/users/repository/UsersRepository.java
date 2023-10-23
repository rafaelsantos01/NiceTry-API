package com.core.rifei.modules.users.repository;

import com.core.rifei.modules.users.entityes.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    UserDetails findByLogin(String login);

    List<Users> findByCreateDateBetween(Date dataInicioMes, Date dataFimMes);
}
