package org.example.codetohire.dto;

import lombok.*;
import org.example.codetohire.enums.Difficulty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class questionResponseDTO {

    private  Long id;
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

    List<testCaseResponseDTO> testCaseResponseDTO;
}
