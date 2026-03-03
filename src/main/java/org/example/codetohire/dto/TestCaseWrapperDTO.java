package org.example.codetohire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.codetohire.entity.TestCase;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestCaseWrapperDTO {

    List<TestCase> visibleTestCases;
    List<TestCase> invisibleTestCases;
}
