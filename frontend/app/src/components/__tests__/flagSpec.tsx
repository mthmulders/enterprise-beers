import { shallow } from 'enzyme';
import * as React from 'react';

import Flag from '../flag';

describe('Flag component', () => {
    it('should render an image with the appropriate country code', () => {
        // Act
        const container = shallow(<Flag country='NL' />);

        // Assert
        const src = container.find('img').props().src;
        expect(src).toContain('nl.png');
    });

    it('should render an image with an appropriate alt text', () => {
        // Act
        const container = shallow(<Flag country='NL' />);

        // Assert
        const alt = container.find('img').props().alt;
        expect(alt).toEqual('NL flag');
    });
});
