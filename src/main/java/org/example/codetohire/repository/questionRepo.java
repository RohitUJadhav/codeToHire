package org.example.codetohire.repository;

import org.example.codetohire.dto.questionDetailsDTO;
import org.example.codetohire.dto.questionListDTO;
import org.example.codetohire.entity.Question;
import org.example.codetohire.enums.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface questionRepo extends JpaRepository<Question,Long> {


    @Query( value = "Select id, title , difficulty from question where difficulty = :difficulty", nativeQuery = true)
    List<questionListDTO> findAllQuestionsByDifficulty(@Param("difficulty") String difficulty);



    @Query(value = "Select id, title , difficulty from question ", nativeQuery = true)
    List<questionListDTO> findAllQuestions();


}
