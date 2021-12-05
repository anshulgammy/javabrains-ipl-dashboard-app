package com.zefrocan.ipldashboard.batch.config;

import com.zefrocan.ipldashboard.dao.MatchRepository;
import com.zefrocan.ipldashboard.dao.TeamRepository;
import com.zefrocan.ipldashboard.model.Match;
import com.zefrocan.ipldashboard.model.Team;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class MatchFeedDataBatchNotificationListener extends JobExecutionListenerSupport {

    private static final Logger LOG = LoggerFactory.getLogger(MatchFeedDataBatchNotificationListener.class);

    private MatchRepository matchDataRepo;
    private TeamRepository teamRepository;

    public MatchFeedDataBatchNotificationListener(MatchRepository matchDataRepo, TeamRepository teamRepository) {
        this.matchDataRepo = matchDataRepo;
        this.teamRepository = teamRepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.info("BatchStatus.COMPLETED");
            final Optional<Match> matchDataLastRow = matchDataRepo.findById(816L);
            if (matchDataLastRow.isPresent()) {
                LOG.info("Last Data Row Ingested is: {}", matchDataLastRow.get());
            }
            persistTeamData();
        }
    }

    /**
     * This method persists Team data in T_MD_TEAMS
     */
    private void persistTeamData() {
        final Map<String, Team> teamHashMap = new HashMap<>();
        matchDataRepo.getTeamsFirstNameAndCount()
                .stream()
                .map(resultSet -> {
                    final Team team = new Team();
                    team.setName((String) resultSet[0]);
                    team.setTotalMatches((Long) resultSet[1]);
                    return team;
                }).forEach(team -> {
                    teamHashMap.put(team.getName(), team);
                });
        matchDataRepo.getTeamsSecondNameAndCount()
                .stream()
                .map(resultSet -> {
                    if (Objects.isNull(teamHashMap.get((String) resultSet[0]))) {
                        final Team team = new Team();
                        team.setName((String) resultSet[0]);
                        team.setTotalMatches((Long) resultSet[1]);
                        return team;
                    } else {
                        final Team team = teamHashMap.get((String) resultSet[0]);
                        final Long totalMatches = team.getTotalMatches() + (Long) resultSet[1];
                        team.setTotalMatches(totalMatches);
                        return team;
                    }
                }).forEach(team -> {
                    teamHashMap.put(team.getName(), team);
                });
        matchDataRepo.getTeamsAndTotalWins()
                .stream().forEach(resultSet -> {
                    if (!StringUtils.equalsIgnoreCase("NA", (String) resultSet[0])) {
                        teamHashMap.get((String) resultSet[0]).setTotalWins((Long) resultSet[1]);
                    }
                });
        teamHashMap.forEach((teamName, team) -> {
            teamRepository.save(team);
        });
    }
}