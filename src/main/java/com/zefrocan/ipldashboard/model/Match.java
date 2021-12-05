package com.zefrocan.ipldashboard.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * This table has all the processed data for Match.
 * Data has been provided by Kaggle.
 * Kaggle data is present in file kaggle-match-data-2008-2020.csv.
 */
@Entity
@Table(name = "T_MD_MATCHES")
@SequenceGenerator(name="match_id_seq", initialValue = 1000)
public class Match {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="match_id_seq")
    private Long id;
    private String city;
    private LocalDate date;
    private String playerOfMatch;
    private String venue;
    private String teamFirst;
    private String teamSecond;
    private String tossWinner;
    private String tossDecision;
    private String winner;
    private String result;
    private String resultMargin;
    private String eliminator;
    private String method;
    private String umpireFirst;
    private String umpireSecond;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPlayerOfMatch() {
        return playerOfMatch;
    }

    public void setPlayerOfMatch(String playerOfMatch) {
        this.playerOfMatch = playerOfMatch;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTeamFirst() {
        return teamFirst;
    }

    public void setTeamFirst(String teamFirst) {
        this.teamFirst = teamFirst;
    }

    public String getTeamSecond() {
        return teamSecond;
    }

    public void setTeamSecond(String teamSecond) {
        this.teamSecond = teamSecond;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultMargin() {
        return resultMargin;
    }

    public void setResultMargin(String resultMargin) {
        this.resultMargin = resultMargin;
    }

    public String getEliminator() {
        return eliminator;
    }

    public void setEliminator(String eliminator) {
        this.eliminator = eliminator;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUmpireFirst() {
        return umpireFirst;
    }

    public void setUmpireFirst(String umpireFirst) {
        this.umpireFirst = umpireFirst;
    }

    public String getUmpireSecond() {
        return umpireSecond;
    }

    public void setUmpireSecond(String umpireSecond) {
        this.umpireSecond = umpireSecond;
    }

    @Override
    public String toString() {
        return "MatchData{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", matchDate=" + date +
                ", playerOfMatch='" + playerOfMatch + '\'' +
                ", matchVenue='" + venue + '\'' +
                ", teamFirst='" + teamFirst + '\'' +
                ", teamSecond='" + teamSecond + '\'' +
                ", tossWinner='" + tossWinner + '\'' +
                ", tossDecision='" + tossDecision + '\'' +
                ", matchWinner='" + winner + '\'' +
                ", matchResult='" + result + '\'' +
                ", resultMargin='" + resultMargin + '\'' +
                ", eliminator='" + eliminator + '\'' +
                ", method='" + method + '\'' +
                ", umpireFirst='" + umpireFirst + '\'' +
                ", umpireSecond='" + umpireSecond + '\'' +
                '}';
    }
}
