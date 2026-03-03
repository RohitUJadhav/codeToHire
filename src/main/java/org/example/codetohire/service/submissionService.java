package org.example.codetohire.service;

import org.example.codetohire.dto.PublicTestCaseResultDTO;
import org.example.codetohire.dto.SubmissionResultDTO;
import org.example.codetohire.dto.TestCaseWrapperDTO;
import org.example.codetohire.entity.Submission;
import org.example.codetohire.entity.TestCase;
import org.example.codetohire.repository.submissionRepo;
import org.example.codetohire.repository.testCaseRepo;
import org.example.codetohire.util.languageIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class submissionService {

    @Autowired
    private submissionRepo submissionRepo;

    @Autowired
    private testCaseRepo testCaseRepo;

    @Autowired
    private judge0Service judge0Service;


    public SubmissionResultDTO saveSubmission(Submission submission) {

        submission.setStatus("PENDING");
        submission.setSubmittedAt(LocalDate.now());
        submissionRepo.save(submission);

        submission.setStatus("PROCESSING");
        submissionRepo.save(submission);

        int publicPassed = 0;
        int hiddenPassed = 0;
        double memoryUsed = 0.0;
        double totalExecutionTime = 0.0;

        TestCaseWrapperDTO testCase = fetchTestCase(submission.getQuestionId());
        int languageId = languageIdUtil.getLanguageId(submission.getLanguage());
        List<PublicTestCaseResultDTO> publicResults = new ArrayList<>();

        // execute public test cases
        for (TestCase t : testCase.getVisibleTestCases()) {

            Map<String, Object> result = judge0Service.execute(
                    submission.getCode(),
                    languageId,
                    t.getInput());

            String stdout = (String) result.get("stdout");
            String stderr = (String) result.get("stderr");
            String compileError = (String) result.get("compile_output");
            Object memoryObj = result.get("memory");
            String memory = memoryObj == null ? null : memoryObj.toString();
            Object timeObj = result.get("time");

            if (compileError != null) {
                submission.setStatus("COMPILE_ERROR");
                submissionRepo.save(submission);
                return new SubmissionResultDTO(
                        submission.getId(),
                        "COMPILE_ERROR",
                        0,0,0,0,0,0,
                        compileError,
                        null,
                        null,
                        0.0,
                        new ArrayList<>()
                );
            }
            String actual = stdout == null ? "" : stdout.trim();
            String expectedOutput = t.getExpectedOutput() == null ? "" : t.getExpectedOutput().trim();
            boolean passed = actual.equals(expectedOutput);
                PublicTestCaseResultDTO resultDTO = new PublicTestCaseResultDTO();
                resultDTO.setInput(t.getInput());
                resultDTO.setExpectedOutput(expectedOutput);
                resultDTO.setActualOutput(actual);
                resultDTO.setPassed(passed);
                resultDTO.setError(stderr);
                resultDTO.setMemory(memory);
                if(memoryObj instanceof Number){
                    memoryUsed += ((Number)memoryObj).doubleValue();
                }
                resultDTO.setExecutionTime(timeObj == null ? null : timeObj.toString());
                totalExecutionTime += ( timeObj == null ? 0.0 : Double.parseDouble(timeObj.toString()));
                publicResults.add(resultDTO);
            if (passed) {
                publicPassed++;
            }
        }


        for (TestCase t : testCase.getInvisibleTestCases()) {
            Map<String, Object> result = judge0Service.execute(
                    submission.getCode(),
                    languageId,
                    t.getInput());

            String stdout = (String) result.get("stdout");
            String stderr = (String) result.get("stderr");
            String compileError = (String) result.get("compile_output");
            Object timeObj = result.get("time");
            Object memoryObj = result.get("memory");


            if (compileError != null) {
                submission.setStatus("COMPILE_ERROR");
                submissionRepo.save(submission);
                break;
            }
            String actual = stdout == null ? "" : stdout.trim();
            String expectedOutput = t.getExpectedOutput() == null ? "" : t.getExpectedOutput().trim();
            totalExecutionTime += (timeObj == null ? 0.0 : Double.parseDouble(timeObj.toString()));
            if(memoryObj instanceof Number) {
                memoryUsed += ((Number) memoryObj).doubleValue();
            }

            if (actual.equals(expectedOutput)) {
                hiddenPassed++;
            }
        }

            int totalPublic = testCase.getVisibleTestCases().size();
            int totalHidden = testCase.getInvisibleTestCases().size();
            int total = totalPublic + totalHidden;
            int totalPassed = publicPassed  +  hiddenPassed;

            String finalResult = totalPassed == total ? "ACCEPTED" : "WRONG_ANSWER";
            submission.setStatus(finalResult);
            submission.setTotal_testCase((long)total);
            submission.setPass_testCase((long) totalPassed);
            submissionRepo.save(submission);

        SubmissionResultDTO resultDTO = new SubmissionResultDTO();
        resultDTO.setSubmissionId(submission.getId());
        resultDTO.setStatus(finalResult);
        resultDTO.setTotalTestCases(total);
        resultDTO.setTotalPassed(totalPassed);
        resultDTO.setPublicTestCases(totalPublic);
        resultDTO.setPublicPassed(publicPassed);
        resultDTO.setHiddenTestCases(totalHidden);
        resultDTO.setHiddenPassed(hiddenPassed);
        resultDTO.setMemoryUsed(memoryUsed);
        String TLE = String.format("%.3f", totalExecutionTime );
        resultDTO.setTotalExecutionTime(TLE);
        resultDTO.setPublicTestCaseResults(publicResults);

        return resultDTO;
    }

    public TestCaseWrapperDTO fetchTestCase(Long questionId) {
        List<TestCase> visibleTestCases = testCaseRepo.findByQuestionIdAndIsHiddenFalse(questionId);
        List<TestCase> inVisibleTestCases = testCaseRepo.findByQuestionIdAndIsHiddenTrue(questionId);
        TestCaseWrapperDTO testCaseWrapperDTO = new TestCaseWrapperDTO();
        if (inVisibleTestCases.size() > 0 || visibleTestCases.size() > 0) {
            testCaseWrapperDTO.setVisibleTestCases(visibleTestCases);
            testCaseWrapperDTO.setInvisibleTestCases(inVisibleTestCases);
        } else {
            throw new RuntimeException("No test cases found");
        }
        return testCaseWrapperDTO;
    }
}


