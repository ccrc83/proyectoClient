package com.app.clients.application.usecases;

import com.app.clients.application.utility.constants.Utilities;
import com.app.clients.domain.entities.ClientDTO;
import com.app.clients.domain.entities.Clients;
import com.app.clients.domain.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ClientsUseCase {
    static Logger logger = Logger.getLogger(ClientsUseCase.class.getName());
   private  final ClientRepository clientRepository;

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


}
