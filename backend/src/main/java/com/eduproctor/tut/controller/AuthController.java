package com.eduproctor.tut.controller;


import com.eduproctor.tut.dto.AuthenticationRequest;
import com.eduproctor.tut.dto.AuthenticationResponse;
import com.eduproctor.tut.entity.Client;
import com.eduproctor.tut.error.ResourceNotFoundException;
import com.eduproctor.tut.repository.ClientRepository;
import com.eduproctor.tut.service.ClientService;
import com.eduproctor.tut.service.CustomUserDetailsService;
import com.eduproctor.tut.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest){
        Client client = clientService.findByUserName(authenticationRequest.getUserName());

        if(client == null || !passwordEncoder.matches(authenticationRequest.getPassword(), client.getPassword())){
            throw new ResourceNotFoundException("Invalid username or Password");
        }

        String jwtToken = jwtUtils.generateJwtToken(authenticationRequest.getUserName());
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }


    @GetMapping("/currentClient")
    public UserDetails getCurrentClient(Principal principal){
        return customUserDetailsService.loadUserByUsername(principal.getName());
    }

    @GetMapping("/loggedInUser")
    public Client getLoggedInClient(Principal principal){
        if(principal == null){
            throw new IllegalStateException("Principal is nulll");
        }
        return clientRepository.findByUserName(principal.getName())
                .orElseThrow(() -> new RuntimeException("Client not found: "+ principal.getName()));
    }

}
