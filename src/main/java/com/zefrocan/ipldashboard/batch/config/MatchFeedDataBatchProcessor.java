package com.zefrocan.ipldashboard.batch.config;

import com.zefrocan.ipldashboard.model.Match;
import com.zefrocan.ipldashboard.batch.model.MatchFeedData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MatchFeedDataBatchProcessor implements ItemProcessor<MatchFeedData, Match> {

    @Override
    public Match process(MatchFeedData matchFeedData) throws Exception {
        final Match processedData = new Match();
        processedData.setId(Long.parseLong(matchFeedData.getId()));
        processedData.setCity(matchFeedData.getCity());
        processedData.setDate(LocalDate.parse(matchFeedData.getDate()));
        processedData.setPlayerOfMatch(matchFeedData.getPlayer_of_match());
        processedData.setVenue(matchFeedData.getVenue());
        processedData.setTossWinner(matchFeedData.getToss_winner());
        processedData.setTossDecision(matchFeedData.getToss_decision());

        // Below is the logic to ensure that we are assigning firstInningsTeam to Team 1
        // and secondInningsTeam to Team 2.
        if (StringUtils.equalsIgnoreCase(matchFeedData.getToss_decision(), "bat")) {
            processedData.setTeamFirst(matchFeedData.getToss_winner());
            processedData.setTeamSecond(StringUtils.equalsIgnoreCase(matchFeedData.getToss_winner(),
                    matchFeedData.getTeam1()) ? matchFeedData.getTeam2() : matchFeedData.getTeam1());
        } else {
            processedData.setTeamFirst(StringUtils.equalsIgnoreCase(matchFeedData.getToss_winner(),
                    matchFeedData.getTeam1()) ? matchFeedData.getTeam2() : matchFeedData.getTeam1());
            processedData.setTeamSecond(matchFeedData.getToss_winner());
        }

        processedData.setWinner(matchFeedData.getWinner());
        processedData.setResult(matchFeedData.getResult());
        processedData.setResultMargin(matchFeedData.getResult_margin());
        processedData.setEliminator(matchFeedData.getEliminator());
        processedData.setMethod(matchFeedData.getMethod());
        processedData.setUmpireFirst(matchFeedData.getUmpire1());
        processedData.setUmpireSecond(matchFeedData.getUmpire2());
        return processedData;
    }
}
