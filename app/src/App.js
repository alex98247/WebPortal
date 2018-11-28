import React, {Component} from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import GameList from './GameList'
import EditGame from './EditGame'

class App extends Component {

    render() {

        return(
        <Router>
            <Switch>
                <Route path='/' exact={true} component={GameList}/>
                <Route path='/games/:id' component={EditGame}/>
            </Switch>
        </Router>
        );
    }
}

export default App;
