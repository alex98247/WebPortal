import React, {Component} from 'react';
import {Button, Form, FormGroup, Input, Label} from 'reactstrap';

class EditGame extends Component {

    state = {game: {}};

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    async componentDidMount() {
        this.setState({game: this.props.location.state.game});
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let game = {...this.state.game};
        game[name] = value;
        this.setState({game});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {game} = this.state;

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
        const {game} = this.state;

        return (
            <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                    <Label for="name">Name</Label>
                    <Input type="text" name="name" id="name" value={game.name || ''} onChange={this.handleChange} />
                </FormGroup>
                <FormGroup>
                    <Label for="description">description</Label>
                    <Input type="text" name="description" id="description" value={game.description || ''} onChange={this.handleChange} />
                </FormGroup>
                <FormGroup>
                    <Label for="year">year</Label>
                    <Input type="text" name="year" id="year" value={game.year || ''} onChange={this.handleChange} />
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Save</Button>
                </FormGroup>
            </Form>
        )
    }
}

export default EditGame;