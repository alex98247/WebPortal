import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';

class Menu extends Component {

    render() {
        return (
            <div>
                <Button tag={Link} to={"/games"} className="btn btn-success">Add Game</Button>
                <Button tag={Link} to={"/companies"} className="btn btn-success">Add Company</Button>
            </div>
        );
    }
}

export default Menu;