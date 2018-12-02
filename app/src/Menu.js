import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';

class Menu extends Component {

    render() {
        return (
            <nav className="navbar navbar-dark bg-dark pull-left">
                <Button tag={Link} to={"/games"} className="btn btn-success">Add Game</Button>
                <Button tag={Link} to={"/companies"} className="btn btn-success">Add Company</Button>
                <span className="navbar-text">Navbar text with an inline element</span>
            </nav>
        );
    }
}

export default Menu;