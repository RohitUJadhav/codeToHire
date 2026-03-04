package org.example.codetohire.service;

import org.apache.coyote.Response;
import org.example.codetohire.repository.submissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private submissionRepo submissionRepo;

    public Map<String, Object> fetchSubmissionUser(Long userId) {

        Map<String, Object> results = new HashMap<>();

        Long totalSubmissions = submissionRepo.totalSubmissioon(userId);
        Long totalAccepted = submissionRepo.totalSubmissionAccept(userId);

        System.out.println("Total Submissions: " + totalSubmissions);
        System.out.println("Total Accepted: " + totalAccepted);

        double accuracy = 0.0;

        if (totalSubmissions != null && totalSubmissions > 0) {
            accuracy = ((double) totalAccepted / totalSubmissions) * 100;
        }

        List<Object[]> ls = submissionRepo.getDifficulty(userId);

        Long easy = 0L;
        Long medium = 0L;
        Long hard = 0L;

        for (Object[] row : ls) {
            String difficulty = (String) row[0];
            Number countNumber = (Number) row[1];

            if ("Easy".equalsIgnoreCase(difficulty)) {
                easy = countNumber.longValue();
            }
            else if ("Medium".equalsIgnoreCase(difficulty)) {
                medium = countNumber.longValue();
            }
            else if ("Hard".equalsIgnoreCase(difficulty)) {
                hard = countNumber.longValue();
            }
        }

        results.put("totalSubmissions", totalSubmissions);
        results.put("totalAccepted", totalAccepted);
        results.put("accuracy", accuracy);
        results.put("easy", easy);
        results.put("medium", medium);
        results.put("hard", hard);

        return results;
    }
}
