/**
 * Split an input array into arrays (chunks) of equal size. Optionally fill the last of them until the desired length.
 * @param input The array to split into chunks
 * @param size The size of each chunk.
 * @param fill Should the last chunk be filled to the desired length.
 * @return An array of arrays.
 */
function chunk<T>(input: T[], size: number, fill: boolean = false): T[][] {
    const result: T[][] = [];
    let index = 0;
    while (index < input.length) {
        result.push(input.slice(index, size + index));
        index += size;
    }
    if (fill && result.length > 0) {
        const last = result[result.length - 1];
        while (last.length < size) {
            last.push(undefined as unknown as T);
        }
        result[result.length - 1] = last;
    }
    return result;
}

export {
    chunk
}
