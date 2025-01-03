package com.eduproctor.tut.controller;


import com.eduproctor.tut.dto.ClientRequest;
import com.eduproctor.tut.entity.Client;
import com.eduproctor.tut.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    //public ResponseEntity<Client> saveClient(@RequestBody Client client)
    public ResponseEntity<Client>  saveClient(@Valid @RequestBody ClientRequest clientRequest) throws Exception {
        Client client = Client.builder().
                email(clientRequest.getEmail()).
                enabled(clientRequest.isEnabled()).
                firstName(clientRequest.getFirstName()).
                lastName(clientRequest.getLastName()).
                password(clientRequest.getPassword()).
                phone(clientRequest.getPhone()).
                userName(clientRequest.getUserName()).
                profile(clientRequest.getProfile()).
                build();

        Client savedClient = clientService.saveClient(client, clientRequest.getRoleName());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);

//        return clientService.saveClient(client, clientRequest.getRoleName());

//        Client createdClient = clientService.saveClient(client);
//        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

//    @PostMapping("/clients")
//    public Client saveClient(@Valid @RequestBody Client client){
//        return clientService.saveClient(client);
//    }


    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Client> getCientById(@PathVariable Long id){
        Client client = clientService.getCientById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/userName/{userName}")
    public ResponseEntity<Client> findByUserName(@PathVariable String userName){
        Client client = clientService.findByUserName(userName);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) throws Exception{
        clientService.deleteClient(id);
        return ResponseEntity.ok("Client with ID " + id + "has been deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable Long id,
            @RequestBody ClientRequest clientRequest
    )throws Exception{
        Client updatedClient = clientService.updateClient(id, clientRequest);
        return ResponseEntity.ok(updatedClient);
    }
}
