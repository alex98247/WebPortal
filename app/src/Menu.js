import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';

class Menu extends Component {

    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <a className="navbar-brand" href="#">Menu</a>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div className="navbar-nav">
                        <Link tag={Link} to={"/"} className="nav-item nav-link">Game List</Link>
                        <Link tag={Link} to={"/companies"} className="nav-item nav-link">Company List</Link>
                        <Button tag={Link} to={"/games"} className="btn btn-success" style={{marginLeft: 20}}>Add Game</Button>
                        <Button tag={Link} to={"/company"} className="btn btn-success" style={{marginLeft: 10}}>Add Company</Button>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Menu;