package com.eduproctor.tut.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class QuizQuestion extends Question{

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;

    @Override
    public String getAnswer(){
        return answer;
    }

}
