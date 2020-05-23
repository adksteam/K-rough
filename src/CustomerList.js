import React, { Component } from 'react';
import { Button, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import './App.css';

class CustomerList extends Component {

    constructor(props) {
        super(props);
        this.state = {customers: [], isLoading: true };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({ isLoading: true });

        fetch('/Customers')
            .then(response => response.json())
            .then(data => this.setState({ customers: data, isLoading: false }));
    }

    async remove(id) {
        await fetch(`/Customers/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedCustomers = [...this.state.customers].filter(i => i.id !== id);
            this.setState({ customers: updatedCustomers });
        });
    }

    render() {
        const {customers, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const customerList = customers.map(customer => {
           
            return <tr key={customer.id}>
                <td>{customer.id}</td>
                <td style={{ whiteSpace: 'nowrap' }}>{customer.name}</td>
                <td>{customer.email}</td>
                
                <td>
                    
                        <Button size="sm" color="info" tag={Link} to={"/Customers/show/" + customer.id}>Details</Button>
                </td>   
                <td>
                    <Button size="sm" color="warning" tag={Link} to={"/Customers/" + customer.id}>Edit</Button>
                </td>
                <td>
                    <Button size="sm" color="danger" onClick={() => this.remove(customer.id)}>Delete</Button>
                  
                </td>
            </tr>
        });
        
        return (
            <div>
                <AppNavbar />
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/Customers/new" >Add</Button>
                    </div>
                    <h3>Customer List</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="20%">Id</th>
                                <th width="20%">Name</th>
                                <th width="20%">Email</th>
                                <th width="10%">Details</th>
                                <th width="10%">Edit</th>
                                <th width="10%">Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {customerList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default CustomerList;