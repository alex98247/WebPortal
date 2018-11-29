import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import Menu from './Menu';

class GameList extends Component {

    state = {games: []};

    async remove(id) {

        await fetch(`/api/game/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedGames = [...this.state.games].filter(i => i.id !== id);
            this.setState({games: updatedGames});
        });
    }

    async componentDidMount() {
        const response = await fetch('/api/game');
        const body = await response.json();
        this.setState({games: body});
    }

    render() {

        const {games} = this.state;

        const gameList = games.map(game =>
            <tr>
                <td>{game.id}</td>
                <td>{game.name}</td>
                <td>{game.year}</td>
                <td>{game.genre}</td>
                <td><Link to={{
                    pathname: "/companies/" + game.company.id,
                    state: {company: game.company}
                }}>{game.company.name}</Link></td>
                <td><Button color="primary" tag={Link} to={{
                    pathname: "/games/" + game.id,
                    state: {game: game}
                }}>Edit</Button></td>
                <td><Button color="danger" onClick={() => this.remove(game.id)}>Delete</Button></td>
            </tr>
        );

        return (
            <div>
                <Menu/>
                <table className="table table-dark">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Year</th>
                        <th>Genre</th>
                        <th>Company</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>

                    <tbody>
                    {gameList}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default GameList;
