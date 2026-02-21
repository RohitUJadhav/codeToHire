package org.example.codetohire.service;

import lombok.RequiredArgsConstructor;
import org.example.codetohire.dto.AddQuestionRequestDTO;
import org.example.codetohire.dto.questionResponseDTO;
import org.example.codetohire.dto.testCaseDTO;
import org.example.codetohire.dto.testCaseResponseDTO;
import org.example.codetohire.entity.Question;
import org.example.codetohire.entity.TestCase;
import org.example.codetohire.enums.Difficulty;
import org.example.codetohire.exception.QuestionNotFoundException;
import org.example.codetohire.repository.questionRepo;
import org.example.codetohire.repository.testCaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class questionService {

    @Autowired
    private final questionRepo questionRepo;

    @Autowired
    private final testCaseRepo testCaseRepo;

    public questionResponseDTO addQuestion(AddQuestionRequestDTO addQuestionRequestDTO) {
        Question question = new Question();
        question.setTitle(addQuestionRequestDTO.getTitle());
        question.setDescription(addQuestionRequestDTO.getDescription());
        question.setDifficulty(addQuestionRequestDTO.getDifficulty());
        question.setInputFormat(addQuestionRequestDTO.getInputFormat());
        question.setOutputFormat(addQuestionRequestDTO.getOutputFormat());
        question.setConstraints(addQuestionRequestDTO.getConstraints());
        question.setSampleInput(addQuestionRequestDTO.getSampleInput());
        question.setSampleOutput(addQuestionRequestDTO.getSampleOutput());
        for (testCaseDTO dto : addQuestionRequestDTO.getTestCaseDTO()) {
            TestCase testCase = new TestCase();
            testCase.setInput(dto.getInput());
            testCase.setExpectedOutput(dto.getExpectedOutput());
            testCase.setIsHidden(dto.getIsHidden());
            testCase.setQuestion(question);
            question.getTestCases().add(testCase);
        }

        Question saveQuestion = questionRepo.save(question);
        return mapToResponseDTO(saveQuestion);

    }

    private questionResponseDTO mapToResponseDTO(Question question) {
        questionResponseDTO response = new questionResponseDTO();
        response.setId(question.getId());
        response.setTitle(question.getTitle());
        response.setDescription(question.getDescription());
        response.setDifficulty(question.getDifficulty());
        response.setInputFormat(question.getInputFormat());
        response.setOutputFormat(question.getOutputFormat());
        response.setConstraints(question.getConstraints());
        response.setSampleInput(question.getSampleInput());
        response.setSampleOutput(question.getSampleOutput());
        List<testCaseResponseDTO> testCaseDTOList = new ArrayList<>();
        for (TestCase testCase : question.getTestCases()) {
            testCaseResponseDTO testCaseResponseDTO1 = new testCaseResponseDTO();
            testCaseResponseDTO1.setId(testCase.getId());
            testCaseResponseDTO1.setInput(testCase.getInput());
            testCaseResponseDTO1.setExpectedOutput(testCase.getExpectedOutput());
            testCaseResponseDTO1.setIsHidden(testCase.getIsHidden());
            testCaseDTOList.add(testCaseResponseDTO1);
        }
        response.setTestCaseResponseDTO(testCaseDTOList);
        return response;
    }

    public questionResponseDTO updateQuestion(Long id, AddQuestionRequestDTO addQuestionRequestDTO) {

        Question question = questionRepo.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question doesn't not exist, please check question number"));
        question.setTitle(addQuestionRequestDTO.getTitle());
        question.setDescription(addQuestionRequestDTO.getDescription());
        question.setDifficulty(addQuestionRequestDTO.getDifficulty());
        question.setInputFormat(addQuestionRequestDTO.getInputFormat());
        question.setOutputFormat(addQuestionRequestDTO.getOutputFormat());
        question.setConstraints(addQuestionRequestDTO.getConstraints());
        question.setSampleInput(addQuestionRequestDTO.getSampleInput());
        question.setSampleOutput(addQuestionRequestDTO.getSampleOutput());
        question.getTestCases().clear();
        for (testCaseDTO dto : addQuestionRequestDTO.getTestCaseDTO()) {
            TestCase testCase = new TestCase();
            testCase.setInput(dto.getInput());
            testCase.setExpectedOutput(dto.getExpectedOutput());
            testCase.setIsHidden(dto.getIsHidden());
            testCase.setQuestion(question);
            question.getTestCases().add(testCase);
        }
        Question result = questionRepo.save(question);
        return mapToResponseDTO(result);

    }

    public questionResponseDTO getQuestionById(Long id) {
        questionResponseDTO response = new questionResponseDTO();
        Question question = questionRepo.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question doesn't not exist, please check question number"));
        response.setId(question.getId());
        response.setTitle(question.getTitle());
        response.setDescription(question.getDescription());
        response.setDifficulty(question.getDifficulty());
        response.setInputFormat(question.getInputFormat());
        response.setOutputFormat(question.getOutputFormat());
        response.setConstraints(question.getConstraints());
        response.setSampleInput(question.getSampleInput());
        response.setSampleOutput(question.getSampleOutput());

        List<testCaseResponseDTO> testCaseDTOList = new ArrayList<>();
        for (TestCase testCase : question.getTestCases()) {
            testCaseResponseDTO testCaseResponseDTO1 = new testCaseResponseDTO();
            testCaseResponseDTO1.setId(testCase.getId());
            testCaseResponseDTO1.setInput(testCase.getInput());
            testCaseResponseDTO1.setExpectedOutput(testCase.getExpectedOutput());
            testCaseResponseDTO1.setIsHidden(testCase.getIsHidden());
            testCaseDTOList.add(testCaseResponseDTO1);
        }
        response.setTestCaseResponseDTO(testCaseDTOList);
        return response;

    }
    public List<questionResponseDTO> getQuestions() {
        List<questionResponseDTO> responseList = new ArrayList<>();
        List<Question> questions = questionRepo.findAll();
        for (Question question : questions) {
            questionResponseDTO response = new questionResponseDTO();
            response.setId(question.getId());
            response.setTitle(question.getTitle());
            response.setDifficulty(question.getDifficulty());
            response.setCreatedAt(question.getCreatedAt());

            List<testCaseResponseDTO> testCaseDTOList = new ArrayList<>();
            if(question.getTestCases() != null && !question.getTestCases().isEmpty()) {
                for(TestCase testCase : question.getTestCases()) {
                testCaseResponseDTO testCaseResponseDTO1 = new testCaseResponseDTO();
                testCaseResponseDTO1.setId(testCase.getId());
                testCaseResponseDTO1.setInput(testCase.getInput());
                testCaseResponseDTO1.setExpectedOutput(testCase.getExpectedOutput());
                testCaseResponseDTO1.setIsHidden(testCase.getIsHidden());
                testCaseDTOList.add(testCaseResponseDTO1);
              }
            }
            response.setTestCaseResponseDTO(testCaseDTOList);
            responseList.add(response);
        }
        return responseList;

    }
}

