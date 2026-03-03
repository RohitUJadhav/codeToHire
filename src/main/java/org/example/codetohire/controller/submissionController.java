package org.example.codetohire.controller;

import lombok.RequiredArgsConstructor;
import org.example.codetohire.dto.SubmissionResultDTO;
import org.example.codetohire.entity.Submission;
import org.example.codetohire.repository.submissionRepo;
import org.example.codetohire.service.submissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/code")
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true")
@RequiredArgsConstructor
public class submissionController {

    private  final  submissionService submissionService;

    @PostMapping("/submit")
    public SubmissionResultDTO submit(@RequestBody Submission submission){
       return submissionService.saveSubmission(submission);
    }
}
