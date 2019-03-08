import * as React from 'react';
import * as ReactDOM from 'react-dom';

import 'bootstrap/dist/css/bootstrap.css';

import { HashRouter } from 'react-router-dom';

import App from './app';

const routes = (
    <HashRouter>
        <App />
    </HashRouter>
);

ReactDOM.render(routes, document.getElementById('root') as HTMLElement);
