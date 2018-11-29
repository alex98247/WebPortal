import React, {Component} from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import GameList from './GameList'
import EditGame from './EditGame'
import EditCompany from './EditCompany'

class App extends Component {

    render() {

        return(
        <Router>
            <Switch>
                <Route path='/' exact={true} component={GameList}/>
                <Route path='/games/:id' component={EditGame}/>
                <Route path='/games' component={EditGame}/>
                <Route path='/companies' component={EditCompany}/>
                <Route path='/companies/:id' component={EditCompany}/>
            </Switch>
        </Router>
        );
    }
}

export default App;
