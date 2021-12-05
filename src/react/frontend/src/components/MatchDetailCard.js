import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetailCard = ({ match, teamName }) => {
    if (!match) return null;
    const otherTeam = match.teamFirst === teamName ? match.teamSecond : match.teamFirst;
    const otherTeamRoute = `/teams/${otherTeam}`;
    return (
        <div className="MatchDetailCard">
            <h3>Latest Matches</h3>
            <h3>vs <Link to={otherTeamRoute}>{otherTeam}</Link></h3>
            <h3>on {match.date}</h3>
            <h3>at {match.venue}</h3>
        </div>
    );
};