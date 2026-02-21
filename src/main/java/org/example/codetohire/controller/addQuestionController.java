package org.example.codetohire.controller;


import lombok.RequiredArgsConstructor;
import org.example.codetohire.dto.AddQuestionRequestDTO;
import org.example.codetohire.dto.questionResponseDTO;
import org.example.codetohire.dto.testCaseDTO;
import org.example.codetohire.dto.testCaseResponseDTO;
import org.example.codetohire.exception.QuestionNotFoundException;
import org.example.codetohire.repository.testCaseRepo;
import org.example.codetohire.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/admin/question")
@CrossOrigin (origins = "http://localhost:3000", allowCredentials = "true")
@RequiredArgsConstructor
public class addQuestionController {

    @Autowired
    private questionService questionService;

    @Autowired
    private testCaseRepo testCaseRepos;

    @PostMapping("/addQuestion")
    public questionResponseDTO  addQuestion(@RequestBody AddQuestionRequestDTO addQuestionRequestDTO) {

        try{
             return   questionService.addQuestion(addQuestionRequestDTO);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public testCaseResponseDTO updateQuestionTestCase(@PathVariable Long id, @RequestBody testCaseDTO testCase) {
        try{
            if(this.testCaseRepos.findById(id).isEmpty()){
                throw new QuestionNotFoundException("Question doesn't not exist, please check question number");
            }
            return  questionService.updateQuestion(testCase,id);
        }catch (Exception e){

        }
        return null;
    }
}
