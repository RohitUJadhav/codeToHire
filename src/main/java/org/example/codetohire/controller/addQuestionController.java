package org.example.codetohire.controller;


import lombok.RequiredArgsConstructor;
import org.example.codetohire.dto.AddQuestionRequestDTO;
import org.example.codetohire.dto.questionResponseDTO;
import org.example.codetohire.dto.testCaseDTO;
import org.example.codetohire.dto.testCaseResponseDTO;
import org.example.codetohire.entity.Question;
import org.example.codetohire.exception.QuestionNotFoundException;
import org.example.codetohire.repository.questionRepo;
import org.example.codetohire.repository.testCaseRepo;
import org.example.codetohire.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/admin/question")
@CrossOrigin (origins = "http://localhost:3000", allowCredentials = "true")
@RequiredArgsConstructor
public class addQuestionController {

    @Autowired
    private questionService questionService;

    @Autowired
    private questionRepo questionRepo;

    @PostMapping("/addQuestion")
    public questionResponseDTO  addQuestion(@RequestBody AddQuestionRequestDTO addQuestionRequestDTO) {

        try{
             return   questionService.addQuestion(addQuestionRequestDTO);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateQuestion/{id}")
    public questionResponseDTO updateQuestionTestCase(@PathVariable Long id, @RequestBody AddQuestionRequestDTO addQuestionRequestDTO)  {
            return  questionService.updateQuestion(id,addQuestionRequestDTO);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable Long id)  {
        Question question = questionRepo.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question not found"));
        questionRepo.delete(question);
    }

    @GetMapping("/{id}")
    public questionResponseDTO getQuestionById(@PathVariable Long id)  {
        return  questionService.getQuestionById(id);
    }

    @GetMapping("/questions")
    public List<questionResponseDTO> getQuestions()  {
        return  questionService.getQuestions();
    }
}
