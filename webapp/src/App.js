import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PriceList from './PriceList';


class App extends Component {

  
render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/quotes' exact={true} component={PriceList}/>
        </Switch>
      </Router>
    )
  }
}



export default App;
