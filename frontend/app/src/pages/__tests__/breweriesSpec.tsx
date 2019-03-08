import React from 'react';
import { mount } from 'enzyme';

jest.mock('../../api');
import { retrieveBreweries } from '../../api';

import Breweries from '../breweries';

describe('Breweries page', () => {
    afterEach(() => (retrieveBreweries as jest.Mock).mockReset());

    it('should retrieve breweries from the server', (done) => {
        // Arrange
        (retrieveBreweries as jest.Mock).mockImplementation(() => Promise.resolve([]));

        // Act
        mount(<Breweries />);

        // Assert
        setTimeout(() => {
            expect(retrieveBreweries).toHaveBeenCalled();
            done();
        }, 250);
    });
});
