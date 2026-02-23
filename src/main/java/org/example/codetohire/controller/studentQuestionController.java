package org.example.codetohire.controller;


import lombok.RequiredArgsConstructor;
import org.example.codetohire.dto.questionDetailsDTO;
import org.example.codetohire.dto.questionListDTO;
import org.example.codetohire.service.studentQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/questions")
@CrossOrigin (origins = "http://localhost/3000" , allowCredentials = "true")
@RequiredArgsConstructor
public class studentQuestionController {

    @Autowired
    private final studentQuestionService studentQuestionService;

    @GetMapping
    public List<questionListDTO> questionList(){
        return studentQuestionService.questionList();
    }

    @GetMapping("/{id}")
    public questionDetailsDTO questionDetails(@PathVariable Long id){
        return studentQuestionService.questionDetails(id);
    }

    @GetMapping("/filter")
    List<questionListDTO>getAllQuestionByFilter(@RequestParam(required = false) String difficulty){
        return  studentQuestionService.getAllQuestionByFilter(difficulty);
    }
}
