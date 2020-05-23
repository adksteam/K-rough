import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';



class CustomerEdit extends Component {
   
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
        const { cookies } = props;
        this.state = {
            item: this.emptyItem,
           
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {   
        if (this.props.match.params.id !== 'new') {
            try {
                const customer = await (await fetch(`/Customers/${this.props.match.params.id}`, { credentials: 'include' })).json();
                this.setState({ item: customer });
            } catch (error) {
                this.props.history.push('/Customers');
            }
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = { ...this.state.item };
        item[name] = value;
        this.setState({ item });
    }

    async handleSubmit(event) {
        event.preventDefault();
        const { item } = this.state;

        await fetch('/Customers' + (item.id ? '/' + item.id : '/new'), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
           
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
            credentials: 'include'
        });
        this.props.history.push('/Customers');
    }

    render() {
        const { item } = this.state;
        const title = <h2>{item.id ? ('Edit Customer with Id: '+item.id) : 'Add New Customer'}</h2>;

        return (<div>
            <AppNavbar />
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="name">Name</Label>
                        <Input type="text" name="name" id="name" value={item.name || ''}
                            onChange={this.handleChange} autoComplete="name" />
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email" value={item.email || ''}
                            onChange={this.handleChange} autoComplete="email" />
                    </FormGroup>
                    <FormGroup>
                        <Label for="address">Address</Label>
                        <Input type="text" name="address" id="address" value={item.address || ''}
                            onChange={this.handleChange} autoComplete="address" />
                    </FormGroup>
                    <div className="row">
                        <FormGroup className="col-md-4 mb-3">
                            <Label for="accountType">Account Type</Label>
                            <Input type="text" name="accountType" id="accountType" value={item.accountType || ''}
                                onChange={this.handleChange} autoComplete="accountType" />
                        </FormGroup>
                        <FormGroup className="col-md-5 mb-3">
                            <Label for="accountNo">Account No.</Label>
                            <Input type="text" name="accountNo" id="accountNo" value={item.accountNo || ''}
                                onChange={this.handleChange} autoComplete="accountNo" />
                        </FormGroup>
                        <FormGroup className="col-md-3 mb-3">
                            <Label for="accountBalance">Account Balance</Label>
                            <Input type="number" step="0.01" name="accountBalance" id="accountBalance" value={item.accountBalance || ''}
                                onChange={this.handleChange} autoComplete="accountBalance" />
                        </FormGroup>
                    </div>
                    <FormGroup>
                        <Button color="primary" type="Submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/Customers">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>);
    }
}

export default withRouter(CustomerEdit);