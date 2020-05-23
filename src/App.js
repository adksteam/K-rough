import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import CustomerList from './CustomerList';
import CustomerEdit from './CustomerEdit';
import CustomerDetails from './CustomerDetails';
class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/Customers' exact={true} component={CustomerList} />
                    <Route path='/Customers/:id' exact={true} component={CustomerEdit} />
                    <Route path='/Customers/show/:id' exact={true} component={CustomerDetails} />
                </Switch>
            </Router>
        )
    }
}

export default App;