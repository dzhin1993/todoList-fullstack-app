import React, {Component} from "react";
import { Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import {Form, Input} from 'reactstrap';

import {
    login, register
} from "../actions/auth";

class Login extends Component {

    state = {
        username: "",
        password: ""
    };

    onChange = e => {
        this.setState({[e.target.name]: e.target.value});
        console.log(this.state);
    }

    handleLogin = e => {
        e.preventDefault();
        const { login, history } = this.props;
        const { username, password } = this.state;

        login(username, password).then(
            () => {
                history.push("/todoList");
                window.location.reload();
            }
        );
    }

    moveToRegister = () => {
        const { history } = this.props;
        history.push("/register");
        window.location.reload();
    }

    render() {

        const { isLoggedIn, message } = this.props;

        if (isLoggedIn) {
            return <Redirect to="/todoList" />;
        }

        return (
            <div className="login-container">
                <div className="login-form-1">
                    <h3>Login</h3>
                    <Form onSubmit={this.handleLogin} ref={c => {
                        this.form = c;
                    }}>
                        <div className="form-group">
                            <Input type="text" className="form-control" name="username" placeholder="Your Email *"
                                   value={this.state.username} onChange={this.onChange}/>
                        </div>
                        <div className="form-group">
                            <Input type="password" className="form-control" name="password"
                                   placeholder="Your Password *"
                                   value={this.state.password} onChange={this.onChange}/>
                        </div>
                        <div className="form-group" style={{marginTop: "1%"}}>
                            <Input type="submit" className="btnSubmit"
                                   value="Login"/>
                        </div>
                        <div className="form-group" style={{marginTop: "1%"}}>
                                <Input type="button" className="btnSubmit" value={"register"}
                                       onClick={this.moveToRegister}/>
                        </div>
                        {message && (
                            <div style={{marginTop: "1%"}} className="form-group">
                                <div className="alert alert-danger" role="alert">
                                    {message}
                                </div>
                            </div>
                        )}
                    </Form>
                </div>
            </div>
        );
    }
}

function mapStateToProps(state) {
    const { isLoggedIn } = state.auth;
    const { message } = state.message;
    return { isLoggedIn, message };
}

export default connect(mapStateToProps, {
    login, register
})(Login);