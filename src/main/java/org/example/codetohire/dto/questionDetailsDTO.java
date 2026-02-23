package org.example.codetohire.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.codetohire.enums.Difficulty;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class questionDetailsDTO {
    private Long id;
    private String title;
    private String description;
    private String  difficulty;
    private String constraint;
    private String sampleInput;
    private String sampleOutput;
    List<testCaseDTO> testCaseDTOS;
}
