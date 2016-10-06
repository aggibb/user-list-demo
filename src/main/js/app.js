// This code and the other javascript modules are borrowed from the following tutorial:
// https://spring.io/guides/tutorials/react-and-spring-data-rest/
// Only the code below has been customized for the current app
'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const follow = require('./follow');
const root = '/api';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {users: [], attributes: [], links: {}};
        this.onCreate = this.onCreate.bind(this);
    }

    componentDidMount() {
        this.loadFromServer();
    }

    loadFromServer() {
        follow(client, root, ['users']
        ).then(userCollection => {
            return client({
                method: 'GET',
                path: userCollection.entity._links.profile.href,
                headers: {'Accept': 'application/schema+json'}
            }).then(schema => {
                this.schema = schema.entity;
                return userCollection;
            });
        }).done(userCollection => {
            this.setState({
                users: userCollection.entity._embedded.users,
                attributes: Object.keys(this.schema.properties),
                links: userCollection.entity._links});
        });
    }

    onCreate(newUser) {
        follow(client, root, ['users']
        ).then(userCollection => {
            return client({
                method: 'POST',
                path: userCollection.entity._links.self.href,
                entity: newUser,
                headers: {'Content-Type': 'application/json'}
            })
        }).then(response => {
            return follow(client, root, [
                {rel: 'users'}]);
        }).done(response => {this.loadFromServer()});
    }

    render() {
        return (
            <div>
            <CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
            <UserList users={this.state.users}/>
            </div>
    )
    }
}

function formatDate(dateString) {
    var date = new Date(dateString);
    var options = {year: 'numeric', month: 'short', day: 'numeric', timeZone: 'UTC'};
    var formattedDate = new Intl.DateTimeFormat('en', options).format(date);
    return formattedDate;
}

class CreateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        var newUser = {};
        this.props.attributes.forEach(attribute => {
            newUser[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onCreate(newUser);

        // clear out the dialog's inputs
        this.props.attributes.forEach(attribute => {
            ReactDOM.findDOMNode(this.refs[attribute]).value = '';
        });
    }

    render() {
        return (
            <div className="row">
                <div>
                    <h3>Add a new user</h3>
                    <form className="form-inline">
                        <div className="form-group">
                        <input type="text" placeholder="First name" ref="firstName" className="form-control" /></div>
                        <div className="form-group">
                        <input type="text" placeholder="Last name" ref="lastName" className="form-control" /></div>
                        <div className="form-group">
                        <input type="date" placeholder="Date of birth" ref="dateOfBirth" className="form-control" /></div>
                        <button className="btn btn-primary" onClick={this.handleSubmit}>Add new user</button>
                    </form>
                </div>
            </div>
        )
    }
}

class UserList extends React.Component {
    render() {
        var users = this.props.users.map(user =>
            <User key={user._links.self.href} user={user}/>
        );

        return (
            <div className="row">
                <h3>User list</h3>
            <table className="table table-condensed">
                <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Birth</th>
                </tr>
                {users}
                </tbody>
            </table>
            </div>
        )
    }
}

class User extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.user.firstName}</td>
                <td>{this.props.user.lastName}</td>
                <td>{formatDate(this.props.user.dateOfBirth)}</td>
            </tr>
        )
    }
}


ReactDOM.render(<App />, document.getElementById('react'))
