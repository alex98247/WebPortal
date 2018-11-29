import React, {Component} from 'react';
import {Button, Form, FormGroup, Input, Label} from 'reactstrap';

class EditGame extends Component {

    emptyGame = {
        name: '',
        genre: '',
        company:{}
    };

    state = {game: this.emptyGame, genres: [], companies: []};

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    async componentDidMount() {
        const responseGenre = await fetch('/api/genre');
        const bodyGenre = await responseGenre.json();

        const responseCompany = await fetch('/api/company');
        const bodyCompany = await responseCompany.json();

        this.emptyGame.company = bodyCompany[0];
        this.emptyGame.genre = bodyGenre[0];

        const game = (this.props.location.state == null) ? this.emptyGame : this.props.location.state.game;

        this.setState({
            game: game,
            genres: bodyGenre,
            companies: bodyCompany
        });
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        const {companies} = this.state;
        let game = {...this.state.game};
        if(name == "company")
        game[name] = companies.find(company => company.id == value);
        else game[name] = value;
        this.setState({game});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {game} = this.state;

        console.log(game)

        await fetch('/api/game', {
            method: (game.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(game),
            credentials: 'include'
        });
    }

    render() {
        const {game, genres, companies} = this.state;

        const genreList = genres.map(genre =>  <option>{genre}</option>);
        const companyList = companies.map(company => <option value={company.id}>{company.name}</option>);

        return (
            <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                    <Label for="name">Name</Label>
                    <Input type="text" name="name" id="name" value={game.name || ''} onChange={this.handleChange}/>
                </FormGroup>
                <FormGroup>
                    <Label for="description">Description</Label>
                    <Input type="text" name="description" id="description" value={game.description || ''}
                           onChange={this.handleChange}/>
                </FormGroup>
                <FormGroup>
                    <Label for="year">Year</Label>
                    <Input type="text" name="year" id="year" value={game.year || ''} onChange={this.handleChange}/>
                </FormGroup>
                <FormGroup>
                    <Label for="genre">Genre</Label>
                    <Input type="select" name="genre" id="genre" value={game.genre || ''} onChange={this.handleChange}>
                        {genreList}
                    </Input>
                </FormGroup>
                <FormGroup>
                    <Label for="company">Company</Label>
                    <Input type="select" name="company" id="company" value={game.company.id || ''} onChange={this.handleChange}>
                        {companyList}
                    </Input>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Save</Button>
                </FormGroup>
            </Form>
        )
    }
}

export default EditGame;