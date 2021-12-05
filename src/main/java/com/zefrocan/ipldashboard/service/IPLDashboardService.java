package com.zefrocan.ipldashboard.service;

import com.zefrocan.ipldashboard.dao.MatchRepository;
import com.zefrocan.ipldashboard.dao.TeamRepository;
import com.zefrocan.ipldashboard.model.Match;
import com.zefrocan.ipldashboard.model.Team;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class IPLDashboardService {

    private MatchRepository matchDataRepo;
    private TeamRepository teamRepository;

    public IPLDashboardService(MatchRepository matchDataRepo, TeamRepository teamRepository) {
        this.matchDataRepo = matchDataRepo;
        this.teamRepository = teamRepository;
    }

    public Team fetchTeamInformation(String teamName) {
        final Team team = teamRepository.findTeamByName(teamName);
        if (Objects.nonNull(team)) {
            team.setTotalMatchesList(matchDataRepo.findMatchDataByTeamName(team.getName()));
        }
        return team;
    }

    public List<Team> fetchAllTeams() {
        final List<Team> allTeamsList = new ArrayList<>();
        teamRepository.findAll().forEach(team -> allTeamsList.add(team));
        return allTeamsList;
    }

    public List<Match> fetchMatchesForTeam(String teamName, int year) {
        if (year >= 2006 && year <= 2022) {
            final LocalDate startDate = LocalDate.of(year, 1, 1);
            final LocalDate endDate = LocalDate.of(year, 12, 31);
            return matchDataRepo.fetchMatchesForTeamByNameAndYear(teamName, startDate, endDate);
        }
        return null;
    }
}
