package org.example.codetohire.repository;

import org.example.codetohire.dto.LeaderboardDTO;
import org.example.codetohire.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface submissionRepo  extends JpaRepository<Submission,Long> {

    @Query (value = "select count( distinct question_id) from Submission where user_id =:userId" , nativeQuery = true)
    public Long totalSubmissioon(@Param("userId") Long userId);

    @Query (value = "select count(distinct question_id)  from Submission where user_id =:userId and status ='ACCEPTED' " , nativeQuery = true)
    public Long totalSubmissionAccept(@Param("userId") Long userId);

    @Query (value = "select q.difficulty, count(distinct(s.question_id))  from  question q \n" +
            " inner join submission s \n" +
            "on q.id = s.question_id \n" +
            "where s.user_id =:userId \n" +
            "and s.status = 'ACCEPTED' group by q.difficulty", nativeQuery = true)
    public List<Object[]> getDifficulty(@Param("userId") Long userId);



    @Query(value = "\n" +
            "select \n" +
            "Username,\n" +
            "totalSolved,\n" +
            "totalScore ,\n" +
            "dense_rank () over(order by totalScore  desc ) as rankPosition \n" +
            "from(\n" +
            "select  count(*) as totalSolved, u.name as Username, u.id,\n" +
            "sum(\n" +
            "CASE q.difficulty\n" +
            "when 'EASY' then  10\n" +
            "when 'MEDIUM' then  20\n" +
            "when 'HARD' then  30\n" +
            "else 0\n" +
            "end\n" +
            " ) as totalScore\n" +
            "from (select distinct user_id, question_id\n" +
            "     from submission\n" +
            "     where status = 'ACCEPTED' ) s\n" +
            "join question q on q.id = s.question_id\n" +
            "join user u on u.id = s.user_id\n" +
            "group by  u.name,u.id \n" +
            ")\n" +
            "leaderboard order by totalScore DESC", nativeQuery = true)
    List<LeaderboardDTO> getLeaderboard();


}
