import React, { useState, useEffect } from 'react';

import {
    Card,
    CardGroup,
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

interface BreweryListProps {
    breweries: any[]
}

const BreweryList = ({ breweries }: BreweryListProps) => {
    const chunked = chunk(breweries, 3, true);
    
    return (
        <React.Fragment>
        { chunked.map((chunk, idx) => (
            <React.Fragment key={ idx }>
                <CardGroup>
                { chunk.map((brewery, idx) => (
                    brewery ? <Brewery key={ `brewery-${idx}` } brewery={ brewery } /> : <Card key={ `brewery-${idx}` } />
                ) ) }
                </CardGroup>
                <br />
            </React.Fragment>
        )) }
        </React.Fragment>
    );
    
};

const Breweries = () => {
    const [ { breweries, error, loading }, setState ] = useState({
        breweries: [],
        error: undefined,
        loading: true
    });

    // console.info(`Rendering brewery page; stack: ${new Error().stack}`);

    // const { breweries, loading } = state;

    console.debug(`Found breweries: ${JSON.stringify(breweries)}`);

    // const fetchBeers = async () => {
    //     const breweries = await retrieveBreweries();
    //     setState({ breweries, error, loading: false })
    // };

    useEffect(() => {
        retrieveBreweries()
            .then((breweries) => setState({ breweries, error, loading: false }))
            .catch((error) => setState({ breweries, error, loading: false }))
    }, [ ]);

    return (
        <>
            { loading && <div>Loading...</div> }
            { !loading && <BreweryList breweries={ breweries } /> }
        </>
    );
};

export default Breweries;
