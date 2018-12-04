import React, {Component} from 'react';
import {Button} from 'reactstrap';
import {Link} from 'react-router-dom';
import Menu from './Menu';
import Footer from './Footer';

class CompanyList extends Component {

    state = {companies: []};

    async remove(id) {
        if (window.confirm("Do you want to delete company?")) {

            await fetch(`/api/company/${id}`, {method: 'DELETE'}).then((response) => {
                if (response.ok) {
                    let updatedCompanies = [...this.state.companies].filter(i => i.id !== id);
                    this.setState({companies: updatedCompanies});
                }
                else {
                    alert("Can`t delete this company: it has games");
                }
            });
        }
    }

    async componentDidMount() {
        const response = await fetch('/api/company');
        const body = await response.json();
        this.setState({companies: body});
    }

    render() {

        const {companies} = this.state;

        const companyList = companies.map(company =>
            <tr>
                <td>{company.id}</td>
                <td>{company.name}</td>
                <td>{company.country}</td>
                <td><Button color="primary" tag={Link} to={{
                    pathname: "/company/" + company.id,
                    state: {company: company}
                }}>Edit</Button></td>
                <td><Button color="danger" onClick={() => this.remove(company.id)}>Delete</Button></td>
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
                        <th>Country</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>

                    <tbody>
                    {companyList}
                    </tbody>
                </table>
                <Footer />
            </div>
        );
    }
}

export default CompanyList;
