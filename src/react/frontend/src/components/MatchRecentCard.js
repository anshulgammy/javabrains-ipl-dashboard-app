import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchRecentCard = ({ match, teamName }) => {
    const otherTeam = match.teamFirst === teamName ? match.teamSecond : match.teamFirst;
    const otherTeamRoute = `/teams/${otherTeam}`;
    return (
        <div className="MatchRecentCard">
            <p>vs <Link to={otherTeamRoute}>{otherTeam}</Link> on {match.date}</p>
        </div>
    );
};