const defaultOptions: RequestInit = {
    credentials: 'same-origin',
};

interface ErrorMapping {
    [key: number]: (response: Response) => Promise<string>;
}

const checkStatus = (response: Response, errorMapping: ErrorMapping): Promise<Response> => {
    const status = response.status;
    if (status >= 200 && status < 300) {
        return Promise.resolve(response);
    } else {
        const mapping = errorMapping[status];
        const message = mapping ? mapping(response) : Promise.resolve(`HTTP error ${response.status}: ${response.statusText}`);
        return new Promise((resolve, reject) => {
            message.then((msg) => reject(new Error(msg)));
        });
    }
};

const parseJSON = (response: Response): any => response.json();

const get = (input: RequestInfo, init?: RequestInit, errorMapping: ErrorMapping = {}): Promise<any> => {
    const options = { ...defaultOptions, ...init };
    return fetch(input, options)
        .then((res) => checkStatus(res, errorMapping))
        .then(parseJSON);
};

export const retrieveBreweries = () => get('/api/brewery');
