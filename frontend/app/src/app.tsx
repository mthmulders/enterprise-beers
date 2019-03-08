import React from 'react';

import { Route, Switch } from 'react-router-dom';
import { Container } from 'reactstrap';

import Navigation from './components/navigation';
import Breweries from './pages/breweries';
import Home from './pages/home';

const Main = (props: any) => <main { ...props }>{ props.children }</main>;

const App = () => (
  <>
    <Navigation />
    <Container tag={ Main } role='main'>
      <Switch>
        <Route exact={ true } path='/' component={ Home } />
        <Route path='/breweries' component={ Breweries } />
      </Switch>
    </Container>
  </>
);

export default App;
