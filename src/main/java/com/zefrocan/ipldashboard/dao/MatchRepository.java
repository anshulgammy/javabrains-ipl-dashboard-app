package com.zefrocan.ipldashboard.dao;

import com.zefrocan.ipldashboard.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    @Query("select match.teamFirst, count(*) from Match match group by match.teamFirst")
    public List<Object[]> getTeamsFirstNameAndCount();

    @Query("select match.teamSecond, count(*) from Match match group by match.teamSecond")
    public List<Object[]> getTeamsSecondNameAndCount();

    @Query("select match.winner, count(*) from Match match group by match.winner")
    public List<Object[]> getTeamsAndTotalWins();

    public List<Match> findMatchesByTeamFirstOrTeamSecondOrderByDateDesc(String teamFirst,
                                                                         String teamSecond,
                                                                         Pageable pageable);

    @Query("select match from Match match where (match.teamFirst = :teamName or match.teamSecond = :teamName) and "
            + "(match.date between :startDate and :endDate) order by match.date desc")
    public List<Match> fetchMatchesForTeamByNameAndYear(@Param("teamName") String teamName,
                                                        @Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate);

    public default List<Match> findMatchDataByTeamName(String teamName) {
        return findMatchesByTeamFirstOrTeamSecondOrderByDateDesc(teamName,
                teamName, PageRequest.of(0, 4));
    }

}
