import React, {Component} from 'react';
import { connect } from 'react-redux';
import {Form, Input} from 'reactstrap';

import {
    register
} from "../actions/auth";

import {Redirect} from "react-router-dom";

class Register extends Component {

    state = {
        username: "",
        password: "",
        passwordConfirm: ""
    };

    onChange = e => {
        this.setState({[e.target.name]: e.target.value});
        console.log(this.state);
    }

    handleRegister = e => {
        e.preventDefault();
        const { register, history } = this.props;
        const { username, password, passwordConfirm } = this.state;

        register(username, password, passwordConfirm).then(
            () => {
                history.push("/login");
                window.location.reload();
            }
        );
    }

    moveToLogin = () => {
        const { history } = this.props;
        history.push("/login");
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
                    <h3>Register</h3>
                    <Form onSubmit={this.handleRegister} ref={c => {
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
                        <div className="form-group">
                            <Input type="password" className="form-control" name="passwordConfirm"
                                   placeholder="Confirm Password *"
                                   value={this.state.passwordConfirm} onChange={this.onChange}/>
                        </div>
                        <div className="form-group" style={{marginTop: "1%"}}>
                            <Input type="submit" className="btnSubmit"
                                   value="Register"/>
                        </div>
                        <div className="form-group" style={{marginTop: "1%"}}>
                            <Input type="button" className="btnSubmit" value={"Login"}
                                   onClick={this.moveToLogin}/>
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
    register
})(Register);