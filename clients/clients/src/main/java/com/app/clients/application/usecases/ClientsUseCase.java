package com.app.clients.application.usecases;

import com.app.clients.application.utility.constants.Utilities;
import com.app.clients.domain.entities.ClientDTO;
import com.app.clients.domain.entities.Clients;
import com.app.clients.domain.repositories.ClientRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ClientsUseCase {
    static Logger logger = Logger.getLogger(ClientsUseCase.class.getName());
   private  final ClientRepository  clientRepository;

    public ClientsUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public Page<Clients> getAllClients(int page, int size) {
        logger.info("getAllClients");
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable);
    }

    public Optional<Clients> getClientBySharedKey(String sharedKey) {
        logger.info("getClientBySharedKey");
        return clientRepository.findBySharedKey(sharedKey);
    }

    public Clients createClient(ClientDTO client) {
        logger.info("createClient");
        if (client.getSharedKey()==null){
            client.setSharedKey(Utilities.getUsername(client.getEmail()));
        }
        return clientRepository.save(ClientDTO.toEntity(client));

    }

    public void deleteClient(String sharedKey) {
        clientRepository.deleteById(sharedKey);
    }

    public Page<Clients> advancedFilter(ClientDTO clientDTO, int page, int size) {
        logger.info("advancedFilter");

        // Crear especificación (Specification) para la consulta
        Specification<Clients> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtrar por sharedKey si está presente en el objeto clientDTO
            if (Objects.nonNull(clientDTO.getSharedKey()) && !clientDTO.getSharedKey().isEmpty()) {
                predicates.add(cb.equal(root.get("sharedKey"), clientDTO.getSharedKey()));
            }

            // Filtrar por businessId si está presente en el objeto clientDTO
            if (Objects.nonNull(clientDTO.getBusinessId()) && !clientDTO.getBusinessId().isEmpty()) {
                predicates.add(cb.equal(root.get("businessId"), clientDTO.getBusinessId()));
            }

            // Filtrar por email si está presente en el objeto clientDTO
            if (Objects.nonNull(clientDTO.getEmail()) && !clientDTO.getEmail().isEmpty()) {
                predicates.add(cb.equal(root.get("email"), clientDTO.getEmail()));
            }

            // Filtrar por phone si está presente en el objeto clientDTO
            if (Objects.nonNull(clientDTO.getPhone()) && !clientDTO.getPhone().isEmpty()) {
                predicates.add(cb.equal(root.get("phone"), clientDTO.getPhone()));
            }

            // Filtrar por dataAdded si está presente en el objeto clientDTO
            if (Objects.nonNull(clientDTO.getDataAdded())) {
                predicates.add(cb.equal(root.get("dataAdded"), clientDTO.getDataAdded()));
            }

            // Combinar todas las condiciones con AND
            Predicate combinedPredicate = cb.and(predicates.toArray(new Predicate[0]));

            return combinedPredicate;
        };

        // Ejecutar la consulta con la especificación y la paginación
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(spec, pageable);
    }



}
