package org.example.codetohire.controller;

import org.example.codetohire.service.judge0Service;
import org.example.codetohire.util.languageIdUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/code")
public class Judge0Controller {

    private final judge0Service judge0Service;

    public  Judge0Controller(judge0Service judge0Service1){
        this.judge0Service=judge0Service1;
    }
    @PostMapping("/run")
    public Map<String,Object> run(@RequestBody  Map<String,String> request){
        int languageId = languageIdUtil.getLanguageId(request.get("language"));
        Map output = judge0Service.execute(
                request.get("source_code"),
                languageId,
                request.get("input")
        );

        return output;
    }
}
