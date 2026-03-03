package org.example.codetohire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String input;
    private String expectedOutput;

    private Boolean isHidden;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private  Question question;


}
