import { React, useEffect, useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchRecentCard } from '../components/MatchRecentCard';
import { useParams } from 'react-router';


export const TeamPage = () => {

    const [team, setTeam] = useState({
        matchDataList: []
    });

    const { teamName } = useParams();

    const apiUrl = 'http://localhost:8765/v1/teams/' + teamName;

    // execute this, as soon as TeamPage component is loaded by react.
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(apiUrl);
                const team = await response.json();
                setTeam(team)
                console.log(team);
            }
            fetchMatches();
        },
        [teamName]
    );
    if (!team || !team.name) return (
        <h2>Team Not Found!</h2>
    );
    return (
        <div className="TeamPage">
            <h1>{team.name}</h1>
            <MatchDetailCard match={team.totalMatchesList[0]} teamName={teamName} />
            {team.totalMatchesList.slice(1).map(match => <MatchRecentCard match={match} teamName={teamName} />)}
        </div>
    );
};