import React, {Component} from 'react';
import {Button, Form, FormGroup, Input, Label} from 'reactstrap';

class EditCompany extends Component {

    emptyCompany = {
        name: '',
        country: ''
    };

    state = {company: this.emptyCompany};

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    async componentDidMount() {
        const company = (this.props.location.state == null) ? this.emptyCompany : this.props.location.state.company;
        this.setState({company: company});
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let company = {...this.state.company};
        company[name] = value;
        this.setState({company: company});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {company} = this.state;

        await fetch('/api/company', {
            method: (company.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(company),
            credentials: 'include'
        }).then(()=>{
            setTimeout(() => window.location.href = '/companies' , 1 * 1000);
        });
    }

    render() {
        const {company} = this.state;

        return (
            <Form onSubmit={this.handleSubmit}>
                <FormGroup>
                    <Label for="name">Name</Label>
                    <Input type="text" name="name" id="name" value={company.name || ''} onChange={this.handleChange}/>
                </FormGroup>
                <FormGroup>
                    <Label for="country">Country</Label>
                    <Input type="text" name="country" id="country" value={company.country || ''}
                           onChange={this.handleChange}/>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" className="btn" type="submit">Save</Button>
                </FormGroup>
            </Form>
        )
    }
}

export default EditCompany;