package org.example.codetohire.controller;


import org.example.codetohire.dto.LeaderboardDTO;
import org.example.codetohire.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
    @Autowired
    private LeaderboardService leaderboardService;
    @GetMapping
    public List<LeaderboardDTO> leaderBoard(){
        return  leaderboardService.leaderBoard();

    }
}
