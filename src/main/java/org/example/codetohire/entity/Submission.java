package org.example.codetohire.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long questionId;

    @Column(columnDefinition = "Text")
    private String code;

    private String language;
    private String status;

    private LocalDate submittedAt;

    private Long total_testCase;
    private Long pass_testCase;

}
