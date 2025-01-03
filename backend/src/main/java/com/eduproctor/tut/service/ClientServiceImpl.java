package com.eduproctor.tut.service;

import com.eduproctor.tut.dto.ClientRequest;
import com.eduproctor.tut.entity.Client;
import com.eduproctor.tut.entity.ClientRole;
import com.eduproctor.tut.entity.Role;
import com.eduproctor.tut.error.ResourceNotFoundException;
import com.eduproctor.tut.repository.ClientRepository;
import com.eduproctor.tut.repository.ClientRoleRepository;
import com.eduproctor.tut.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ClientRoleRepository clientRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public Client createClient(Client client) {
//        return clientRepository.save(client);
//    }

    @Transactional
    public Client saveClient(Client client, String roleName) throws Exception {

//        ensureRolesExist();


        if(clientRepository.findByUserName(client.getUserName()).isPresent()){
            throw new Exception("Client already exists with username: "+ client.getUserName());
        }

        Role role ;

        if(roleName.equalsIgnoreCase("admin")) {
            role = roleRepository.findById(1)
                    .orElseThrow(() -> new Exception("Admin role not found"));
        }else if(roleName.equalsIgnoreCase("user")){
            role = roleRepository.findById(2)
                    .orElseThrow(()-> new Exception("User role not found"));
        }else {
            throw new Exception("Invalid role name. Please specify 'admin' or 'user'.");
        }

        ClientRole clientRole = ClientRole.builder().
                client(client).
                role(role).
                build();

//        HashSet<ClientRole> clientRoles = new HashSet<>();
//        clientRoles.add(clientRole);
        client.setClientRole(roleName);

        clientRepository.save(client);
        clientRoleRepository.save(clientRole);

        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return client;
    }

//    private void ensureRolesExist() {
//        // Check if the 'admin' role exists, otherwise create it
//        if (!roleRepository.existsById(1)) {
//            Role adminRole = new Role();
//            adminRole.setId(1);
//            adminRole.setRoleName("admin");
//            roleRepository.save(adminRole);
//        }
//
//        // Check if the 'user' role exists, otherwise create it
//        if (!roleRepository.existsById(2)) {
//            Role userRole = new Role();
//            userRole.setId(2);
//            userRole.setRoleName("user");
//            roleRepository.save(userRole);
//        }
//    }

    @Override
    public Client getCientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findByUserName(String userName) {
        return clientRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with username: " + userName));
    }

    @Override
    @Transactional
    public void deleteClient(Long id) throws Exception {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client not found with id:"+id));

        clientRoleRepository.deleteByClient(client);
        clientRepository.delete(client);
    }

    @Override
    @Transactional
    public Client updateClient(Long id, ClientRequest clientRequest) throws Exception {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: "+id));

        client.setEmail(clientRequest.getEmail());
        client.setEnabled(clientRequest.isEnabled());
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setPassword(clientRequest.getPassword()); // Ensure to hash password if necessary
        client.setPhone(clientRequest.getPhone());
        client.setUserName(clientRequest.getUserName());
        client.setProfile(clientRequest.getProfile());

        String roleName = clientRequest.getRoleName();

        Role role;
        if ("admin".equalsIgnoreCase(roleName)) {
            role = roleRepository.findById(1)
                    .orElseThrow(() -> new Exception("Admin role not found"));
        } else if ("user".equalsIgnoreCase(roleName)) {
            role = roleRepository.findById(2)
                    .orElseThrow(() -> new Exception("User role not found"));
        } else {
            throw new Exception("Invalid role name. Please specify 'admin' or 'user'.");
        }

        client.setClientRole(clientRequest.getRoleName());

        ClientRole clientRole = clientRoleRepository.findByClient(client)
                .orElseThrow(() -> new Exception("Client role not found"));

        clientRole.setRole(role);
        clientRoleRepository.save(clientRole);

        return clientRepository.save(client);

    }
}
