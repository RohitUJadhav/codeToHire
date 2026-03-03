package org.example.codetohire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicTestCaseResultDTO {

        private int testCaseId;

        private String input;

        private String expectedOutput;

        private String actualOutput;

        private boolean passed;

        private String error;

        private String executionTime;

        private String memory;
}
