package com.eduproctor.tut.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
//import org.springframework.data.annotation.Id;


@Entity
//@Table(name = "\"user\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //    @Column(nullable = false,unique = true)
    private String email;

    //    @Column(nullable = false,unique = true)
    private boolean enabled;

    //    @Column(name = "f_name",nullable = false)
    private String firstName;

    //    @Column(name = "l_name",nullable = false)
    private  String lastName;

    //    @Column(nullable = false)
    private  String password;

    //    @Column(nullable = false,unique = true)
    private String phone;

    //    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;

    //    @Column(nullable = false)
    private String profile;

//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private String clientRole;
}
