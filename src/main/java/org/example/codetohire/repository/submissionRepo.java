package org.example.codetohire.repository;

import org.example.codetohire.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface submissionRepo  extends JpaRepository<Submission,Long> {
}
