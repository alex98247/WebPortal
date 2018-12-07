import React, {Component} from 'react';
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';
import {Link} from 'react-router-dom';
import Menu from './Menu';
import Footer from './Footer';

class GameList extends Component {

    state = {
        pager: {
            pagesCount: 0,
            games: [],
            pageAndSort: {sort: 'id', currentPage: 0, pageSize: 10, find: ""},
            hasNextPage: '',
            hasPreviousPage: ''
        }
    };

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async remove(id) {
        if (window.confirm("Do you want to delete game?")) {
            await fetch(`/api/game/${id}`, {method: 'DELETE'}).then(async () => {
                const {pager} = this.state;
                await this.reload(pager.pageAndSort.pageSize, pager.pageAndSort.currentPage);
            });
        }

    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let pager = {...this.state.pager};
        let pageAndSort = pager.pageAndSort;
        pageAndSort[name] = value;
        pager.pageAndSort = pageAndSort;
        this.setState({pager: pager});
        console.log(pager);
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {pageAndSort} = this.state.pager;
        console.log(pageAndSort);

        const response = await fetch('/api/game/find', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(pageAndSort),
            credentials: 'include'
        });
        const body = await response.json();
        this.setState({pager: body});
    }

    async reload(size, page) {
        const response = await fetch('/api/game?size=' + size + '&page=' + page);
        const body = await response.json();
        this.setState({pager: body});
    }

    async componentDidMount() {
        await this.reload(10, 0);
    }

    render() {

        const {pager} = this.state;
        const {sort} = this.state;
        const games = pager.games;
        console.log(pager);

        const gameList = games.map(game => {
            return (
                <tr>
                    <td>{game.id}</td>
                    <td>{game.name}</td>
                    <td>{game.year}</td>
                    <td>{game.genre}</td>
                    <td><Link style={{color: '#FFFFFF'}} to={{
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

        const nextButton = (pager.hasNextPage) ? <Button style={{marginLeft: 5}} variant="contained" component="span"
                                                         onClick={() => this.reload(pager.pageAndSort.pageSize, pager.pageAndSort.currentPage + 1, sort)}>
            next
        </Button> : '';
        const previousButton = (pager.hasPreviousPage) ? <Button variant="contained" component="span"
                                                                 onClick={() => this.reload(pager.pageAndSort.pageSize, pager.pageAndSort.currentPage - 1, sort)}>
            previous
        </Button> : '';

        const pageList = [];

        for (let i = 1; i <= pager.pagesCount; i++) {
            pageList.push(<Button style={{marginLeft: 5}}
                                  onClick={() => this.reload(pager.pageAndSort.pageSize, (i - 1), sort)}>{i}</Button>);
        }

        return (
            <div>
                <Menu/>
                <Form className={"container"} onSubmit={this.handleSubmit}>
                        <FormGroup className={"row"}>
                            <Label className={"col-md-4"} for="sort">Sort by</Label>
                            <Input className={"col-md-4"} type="select" name="sort" id="sort"
                                   onChange={this.handleChange}>
                                <option>id</option>
                                <option>name</option>
                                <option>year</option>
                            </Input>
                        </FormGroup>
                        <FormGroup className={"row"}>
                            <Label className={"col-md-4"} for="find">Find</Label>
                            <Input className={"col-md-4"} type="text" name="find" id="find"
                                   onChange={this.handleChange}/>
                        </FormGroup>
                        <FormGroup>
                            <Button color="primary" className="btn" type="submit">Sort and Find</Button>
                        </FormGroup>
                </Form>
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
                <Footer/>
            </div>
        );
    }
}

export default GameList;
