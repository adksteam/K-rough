import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';


class CustomerDetails extends Component {

    emptyItem = {
        name: '',
        email: '',
        address: '',
        accountType: '',
        accountNo: '',
        accountBalance: ''
    };

    constructor(props) {
        super(props);
       
        this.state = {
            item: this.emptyItem,

        };
       
    }

    async componentDidMount() {
        
            try {
                const customer = await (await fetch(`/Customers/show/${this.props.match.params.id}`, { credentials: 'include' })).json();
                this.setState({ item: customer });
            } catch (error) {
                this.props.history.push('/Customers');
            }
        
    }
    
    render() {
        const { item } = this.state;
        const title = <h2>{('Customer Id: ' + item.id)}</h2>;

        return (<div>
            <AppNavbar />
            <Container>
                {title}
                <Form>
                    <FormGroup>
                        <Label for="name">Name:</Label>
                        <td class='text-primary'>{item.name}</td>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <td class='text-primary'>{item.email}</td>
                    </FormGroup>
                    <FormGroup>
                        <Label for="address">Address</Label>
                        <td class='text-primary'>{item.address}</td>
                    </FormGroup>
                    <div className="row">
                        <FormGroup className="col-md-4 mb-3">
                            <Label for="accountType">Account Type</Label>
                            <td class='text-danger'>{item.accountType}</td>
                          </FormGroup>
                        <FormGroup className="col-md-5 mb-3">
                            <Label for="accountNo">Account No.</Label>
                            <td class='text-danger'>{item.accountNo}</td>
                        </FormGroup>
                        <FormGroup className="col-md-3 mb-3">
                            <Label for="accountBalance">Account Balance</Label>
                            <td class='text-danger'>{item.accountBalance}</td>
                        </FormGroup>
                    </div>
                    <FormGroup>
                        <Button color="warning" tag={Link} to={"/Customers/" + item.id}>Edit</Button>{' '}
                        <Button color="secondary" tag={Link} to="/Customers">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>);
    }
}

export default withRouter(CustomerDetails);