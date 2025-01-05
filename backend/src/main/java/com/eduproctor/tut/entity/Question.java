package com.eduproctor.tut.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Integer version;


    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private Examination examination;

    @ManyToOne(fetch = FetchType.EAGER)
    private QuestionType type;

    public abstract String getAnswer();
}
