import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { MatchDetailCard } from '../components/MatchDetailCard';

export const MatchPage = () => {

    const [matchArray, setMatchArray] = useState([]);
    const { teamName, year } = useParams();

    const apiUrl = 'http://localhost:8765/v1/teams/' + teamName + '/matches/?year=' + year;

    useEffect(() => {
        const fetchMatchDetails = async () => {
            const response = await fetch(apiUrl);
            const matchArray = await response.json();
            console.log(matchArray);
            setMatchArray(matchArray);
        };
        fetchMatchDetails();
    }, []);
    return (
        <div className="MatchPage">
            {matchArray.map(match => <MatchDetailCard match={match} teamName={teamName} />)}
        </div>
    );
};