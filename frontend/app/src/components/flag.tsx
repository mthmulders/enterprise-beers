import React from 'react';

const Flag = ({ country }: { country: string }) => {
    const src = `/img/famfam-flag-icons/${country.toLowerCase()}.png`;
    return (<img className='flag' src={ src } alt={ `${country} flag` } />);
};

export default Flag;
