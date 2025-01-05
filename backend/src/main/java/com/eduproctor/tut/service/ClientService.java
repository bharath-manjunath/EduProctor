package com.eduproctor.tut.service;

import com.eduproctor.tut.dto.ClientRequest;
import com.eduproctor.tut.entity.Client;

import java.util.List;

public interface ClientService {
//    public Client createClient(Client client) ;

    public Client saveClient( Client client, String roleName) throws Exception;

    public Client getCientById(Long id);

    public List<Client> getAllClients();

    public Client findByUserName(String userName);

    public void deleteClient(Long id) throws Exception;

    public Client updateClient(Long id, ClientRequest clientRequest) throws Exception;
}
