package com.app.clients.domain.entities;


import lombok.Data;


import java.time.LocalDate;

@Data
public class ClientDTO {
    private String sharedKey;
    private String businessId;
    private String email;
    private String phone;
    private LocalDate dataAdded;

    public static ClientDTO fromEntity(Clients client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setSharedKey(client.getSharedKey());
        clientDTO.setBusinessId(client.getBusinessId());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhone(client.getPhone());
        clientDTO.setDataAdded(client.getDataAdded());
        return clientDTO;
    }

    public static Clients toEntity(ClientDTO clientDTO) {
        Clients client = new Clients();
        client.setSharedKey(clientDTO.getSharedKey());
        client.setBusinessId(clientDTO.getBusinessId());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setDataAdded(clientDTO.getDataAdded());
        return client;
    }
}
