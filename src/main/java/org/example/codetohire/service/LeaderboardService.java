package org.example.codetohire.service;


import org.example.codetohire.dto.LeaderboardDTO;
import org.example.codetohire.repository.submissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardService {
    @Autowired
    private submissionRepo  submissionRepo;

    public List<LeaderboardDTO> leaderBoard(){
        return submissionRepo.getLeaderboard();
    }

}
