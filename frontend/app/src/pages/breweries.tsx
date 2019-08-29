import React, { useState, useEffect } from 'react';

import {
    Card,
    CardDeck,
    CardHeader,
    CardText,
    CardBody,
} from 'reactstrap';

import { retrieveBreweries } from '../api';
import { chunk } from '../util';
import Flag from '../components/flag';

interface BreweryProps {
    brewery: any
}

const Brewery = ({ brewery }: BreweryProps) => (
    <Card>
        <CardHeader>{ brewery.name }</CardHeader>
        <CardBody>
            <CardText>
                Located in { brewery.location } &nbsp;
                <Flag country={ brewery.country } />
            </CardText>
        </CardBody>
    </Card>
);

const BreweryOrEmpty = ({ brewery }: BreweryProps) => (
    brewery ? <Brewery brewery={ brewery } /> : <Card />
);

const BreweryRow = ({ breweries }: BreweryListProps) => (
    <>
        <CardDeck>
            { breweries.map((brewery, idx) => <BreweryOrEmpty key={ idx } brewery={ brewery } /> )}
        </CardDeck>
        <br />
    </>
);

interface BreweryListProps {
    breweries: any[]
}

const BreweryList = ({ breweries }: BreweryListProps) => {
    const chunked = chunk(breweries, 3, true);
    
    return (
        <>
        { chunked.map((chunk, idx) => <BreweryRow key={ idx } breweries={ chunk } />) }
        </>
    );  
};

interface State {
    breweries?: any[],
    error?: Error,
    loading: boolean,
}

const initialState: State = {
    breweries: undefined,
    error: undefined,
    loading: false
}

const Breweries = () => {
    const [ { breweries, error, loading }, setState ] = useState<State>(initialState);
    
    useEffect(() => {
        setState({ loading: true });
        retrieveBreweries()
            .then((breweries) => setState({ breweries, loading: false }))
            .catch((error) => setState({ error, loading: false }))
    }, [ ]);

    return (
        <>
            { loading && <div>Loading...</div> }
            { error && <div>An error occured: { error.message }</div> }
            { breweries && <BreweryList breweries={ breweries } /> }
        </>
    );
};

export default Breweries;
