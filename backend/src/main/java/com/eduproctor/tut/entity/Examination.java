package com.eduproctor.tut.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eid;

    private String title;

    private String description;

//    private int maxMarks;
//
//    private int noOfQuestions;

    private boolean active = false;

    private LocalDateTime examinationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "examination", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    public int getNumOfQuestions() {
        return questions.size();
    }

    public int getMaxMarks(){
        return getNumOfQuestions()*10;
    }
}
