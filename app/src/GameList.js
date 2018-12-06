import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import Menu from './Menu';
import Footer from './Footer';

class GameList extends Component {

    state = {pager: {pagesCount: 0, currentPage: 0, games: [], pageSize: 10, hasNextPage: '', hasPreviousPage: ''}};

    async remove(id) {
        if (window.confirm("Do you want to delete game?")) {
            await fetch(`/api/game/${id}`, {method: 'DELETE'}).then(async () => {
                const {pager} = this.state;
                await this.reload(pager.pageSize, pager.currentPage);
            });
        }

    }

    async reload(size, page) {
        console.log(page)
        const response = await fetch('/api/game?size=' + size + '&page=' + page);
        const body = await response.json();
        this.setState({pager: body});
    }

    async componentDidMount() {
        await this.reload(10, 0);
    }

    render() {

        const {pager} = this.state;
        const games = pager.games;

        console.log(pager);
        const gameList = games.map(game => {
            return (
                <tr>
                    <td>{game.id}</td>
                    <td>{game.name}</td>
                    <td>{game.year}</td>
                    <td>{game.genre}</td>
                    <td><Link style={{color: '#FFFFFF' }} to={{
                        pathname: "/company/" + ((game.company == null) ? '' : game.company.name),
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

        const pageList =  [];

        for (let i = 1; i <= pager.pagesCount; i++) {
            pageList.push(<Button onClick={() => this.reload(pager.pageSize, (i-1))}>{i}</Button>);
        }

        return (
            <div>
                <Menu/>
                <table style={{marginBottom: 0}} className="table table-dark">
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
                <div align="center" width="100%" className="bg-dark">
                    {previousButton}
                    {pageList}
                    {nextButton}
                </div>
                <Footer />
            </div>
        );
    }
}

export default GameList;
