package com.eduproctor.tut.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EssayQuestion  extends Question{
    private String answer;

    private int wordLimit;

    @Override
    public String getAnswer(){
        return answer;
    }
}
