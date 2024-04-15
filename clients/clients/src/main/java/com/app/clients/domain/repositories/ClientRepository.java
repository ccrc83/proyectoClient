package com.app.clients.domain.repositories;

import com.app.clients.domain.entities.Clients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends ListCrudRepository<Clients, String> {
    Optional<Clients> findBySharedKey(String sharedKey);
    Page<Clients> findAll(Pageable pageable);
}
