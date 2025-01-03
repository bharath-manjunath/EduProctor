package com.eduproctor.tut.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Column(length = 255)
    private String description;

    @Column(length = 255)
    private String title;


    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Examination> examinations = new LinkedHashSet<>();
}
