package org.example.codetohire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResultDTO {

    private Long submissionId;

    private String status;

    private int totalTestCases;
    private int totalPassed;

    private int publicTestCases;
    private int publicPassed;

    private int hiddenTestCases;
    private int hiddenPassed;

    private String compileError;
    private String runtimeError;

    private String totalExecutionTime;
    private double memoryUsed;

    private List<PublicTestCaseResultDTO> publicTestCaseResults;
}