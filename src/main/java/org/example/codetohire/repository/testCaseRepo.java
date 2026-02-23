package org.example.codetohire.repository;

import lombok.RequiredArgsConstructor;
import org.example.codetohire.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface testCaseRepo  extends JpaRepository <TestCase, Long> {

    @Override
    Optional<TestCase> findById(Long aLong);

    List<TestCase> id(Long id);

}
