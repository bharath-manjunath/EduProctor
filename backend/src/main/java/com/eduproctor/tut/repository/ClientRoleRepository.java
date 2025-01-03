package com.eduproctor.tut.repository;

import com.eduproctor.tut.entity.Client;
import com.eduproctor.tut.entity.ClientRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//import java.lang.ScopedValue;

public interface ClientRoleRepository extends JpaRepository<ClientRole, Long> {
    void deleteByClient(Client client);

    Optional<ClientRole> findByClient(Client client);
//    <T> ScopedValue<T> findByClient(Client client);
}
