import React from "react";
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import PrivateRoute from "./components/PrivateRoute";
import {Provider} from "react-redux";
import store from "./store";

import './App.css';

import ListTasks from './containers/TodoListContainer'
import Login from "./components/Login";
import Register from "./components/Register";


function App() {
    return (
        <Provider store={store}>

                <Router>
                    <Switch>
                        <Route exact path={["/", "/login"]} component={Login}/>
                        <Route exact path={["/register"]} component={Register}/>
                        <PrivateRoute exact path={["/todoList"]} component={ListTasks}/>
                    </Switch>
                </Router>

        </Provider>
    );
}

export default App;
