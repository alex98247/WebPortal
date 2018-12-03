import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import Menu from './Menu';

class GameList extends Component {

    state = {pager: {currentPage: 0, games: [], pageSize: 5, hasNextPage: '', hasPreviousPage: ''}};

    async remove(id) {
        if (window.confirm("Do you want to delete game?")) {
            await fetch(`/api/game/${id}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(async () => {
                const {pager} = this.state;
                await this.reload(pager.pageSize, pager.currentPage);
            });
        }
    }

    async reload(size, page) {
        const response = await fetch('/api/game?size=' + size + '&page=' + page);
        const body = await response.json();
        this.setState({pager: body});
    }

    async componentDidMount() {
        await this.reload(5, 0);
    }

    render() {

        const {pager} = this.state;
        const games = pager.games;

        console.log(pager);
        const gameList = games.map(game => {
            return(
            <tr>
                <td>{game.id}</td>
                <td>{game.name}</td>
                <td>{game.year}</td>
                <td>{game.genre}</td>
                <td><Link to={{
                    pathname: "/company/" + (game.company == null) ? '' : game.company.name,
                    state: {company: game.company}
                }}>{(game.company == null) ? '-' : game.company.name}</Link></td>
                <td><Button color="primary" tag={Link} to={{
                    pathname: "/games/" + game.id,
                    state: {game: game}
                }}>Edit</Button></td>
                <td><Button color="danger" onClick={() => this.remove(game.id)}>Delete</Button></td>
            </tr>);
        });

        const nextButton = (pager.hasNextPage) ? <Button variant="contained" component="span"
                                                         onClick={() => this.reload(pager.pageSize, pager.currentPage + 1)}>
            next
        </Button> : '';
        const previousButton = (pager.hasPreviousPage) ? <Button variant="contained" component="span"
                                                                 onClick={() => this.reload(pager.pageSize, pager.currentPage - 1)}>
            previous
        </Button> : '';

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
                <div>
                    {previousButton}
                    {nextButton}
                </div>
            </div>
        );
    }
}

export default GameList;
