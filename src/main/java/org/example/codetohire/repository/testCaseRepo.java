package org.example.codetohire.repository;

import lombok.RequiredArgsConstructor;
import org.example.codetohire.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface testCaseRepo  extends JpaRepository <TestCase, Long> {

    @Override
    Optional<TestCase> findById(Long aLong);

    List<TestCase> id(Long id);

    @Query(value =  "SELECT  * from test_case where question_id =:questionId  AND is_hidden = 1" ,  nativeQuery = true)
    List<TestCase> findByQuestionIdAndIsHiddenTrue( @Param("questionId") Long questionId);

    @Query(value =  "SELECT  * from test_case where question_id =:questionId AND is_hidden = 0" ,  nativeQuery = true)
    List<TestCase> findByQuestionIdAndIsHiddenFalse( @Param("questionId") Long questionId);
}
