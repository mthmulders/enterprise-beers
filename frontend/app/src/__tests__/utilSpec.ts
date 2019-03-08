import { chunk } from '../util';

describe('Utils', () => {
    describe('chunk()', () => {
        it('should break input into equal pieces', () => {
            // Arrange
            const input = [ 1, 2, 3, 4, 5, 6 ];

            // Act
            const output = chunk(input, 3);

            // Assert
            expect(output).toEqual([ [ 1, 2, 3 ], [ 4, 5, 6 ]]);
        });

        it('should put left-over items together', () => {
            // Arrange
            const input = [ 1, 2, 3, 4, 5 ];

            // Act
            const output = chunk(input, 3);

            // Assert
            expect(output).toEqual([ [ 1, 2, 3 ], [ 4, 5 ]]);
        });

        it('should chunk empty arrays', () => {
            // Arrange
            const input: any[] = [ ];

            // Act
            const output = chunk(input, 3);

            // Assert
            expect(output).toEqual([ ]);
        });

        describe('with fill=true', () => {
            it('should add items to fill the last chunk', () => {
                // Arrange
                const input = [ 1, 2, 3, 4, 5 ];
    
                // Act
                const output = chunk(input, 3, true);
    
                // Assert
                expect(output).toEqual([ [ 1, 2, 3 ], [ 4, 5, undefined ]]);
            });

            it('should not add an extra chunk', () => {
                // Arrange
                const input = [ 1, 2, 3, 4, 5, 6 ];
    
                // Act
                const output = chunk(input, 3, true);
    
                // Assert
                expect(output).toEqual([ [ 1, 2, 3 ], [ 4, 5, 6 ]]);
            });

            it('should chunk empty arrays', () => {
                // Arrange
                const input: any[] = [ ];
    
                // Act
                const output = chunk(input, 3, true);
    
                // Assert
                expect(output).toEqual([ ]);
            });
        });
    });
});