package org.example.codetohire.dto;

import lombok.*;
import org.example.codetohire.enums.Difficulty;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddQuestionRequestDTO {

    private String title;
    private String description;
    private Difficulty difficulty;
    private  String inputFormat;
    private  String outputFormat;
    private  String constraints;
    private  String sampleInput;
    private  String sampleOutput;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    List<testCaseDTO> testCaseDTO;


}
