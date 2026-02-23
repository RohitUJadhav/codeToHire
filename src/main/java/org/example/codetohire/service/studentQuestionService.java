package org.example.codetohire.service;

import lombok.RequiredArgsConstructor;
import org.example.codetohire.dto.questionDetailsDTO;
import org.example.codetohire.dto.questionListDTO;
import org.example.codetohire.dto.questionResponseDTO;
import org.example.codetohire.dto.testCaseDTO;
import org.example.codetohire.entity.Question;
import org.example.codetohire.entity.TestCase;
import org.example.codetohire.repository.questionRepo;
import org.example.codetohire.repository.testCaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class studentQuestionService {
    @Autowired
    private final questionRepo questionRepo;
    @Autowired
    private testCaseRepo testCaseRepo;

    public List<questionListDTO> questionList(){
       return  questionRepo.findAllQuestions();
    }

    public List<questionListDTO> getAllQuestionByFilter(String difficulty){
        return  questionRepo.findAllQuestionsByDifficulty(difficulty);
    }
    public questionDetailsDTO questionDetails(Long questionId){
         Question question = questionRepo.findById(questionId).get();
         questionDetailsDTO questionDetailsDTO = new questionDetailsDTO();
         questionDetailsDTO.setId(question.getId());
         questionDetailsDTO.setTitle(question.getTitle());
         questionDetailsDTO.setDescription(question.getDescription());
         questionDetailsDTO.setDifficulty(question.getDifficulty().toString());
         questionDetailsDTO.setSampleInput(question.getSampleInput());
         questionDetailsDTO.setSampleOutput(question.getSampleOutput());
         questionDetailsDTO.setConstraint(questionDetailsDTO.getConstraint());
         List<testCaseDTO> testCaseDTOS = new ArrayList<>();
         for(TestCase testCase:question.getTestCases()){
             if(testCase.getIsHidden() != true) {
                 testCaseDTO response = new testCaseDTO();
                 response.setInput(testCase.getInput());
                 response.setExpectedOutput(testCase.getExpectedOutput());
                 testCaseDTOS.add(response);
             }
         }
        questionDetailsDTO.setTestCaseDTOS(testCaseDTOS);
         return questionDetailsDTO;

    }
}
