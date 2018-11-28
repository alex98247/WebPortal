import React, {Component} from 'react';
import {Button} from 'reactstrap';

class GameRow extends Component{

    constructor(props) {
        super(props);
        this.state = {games: []};
        this.remove = this.remove.bind(this);
    }

    async remove(id) {

        await fetch(`/api/game/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
    }

    render() {
        return (
            <tr>
                <td>{this.props.game.id}</td>
                <td>{this.props.game.name}</td>
                <td>{this.props.game.year}</td>
                <td>{this.props.game.genre}</td>
                <td>{this.props.game.company.name}</td>
                <td><Button color="primary" tag={Link} to={"api/games/" + game.id}>Edit</Button></td>
                <td><Button color="danger" onClick={() => this.remove(this.props.game.id)}>Delete</Button></td>
            </tr>
        );
    }
}

export default GameRow;