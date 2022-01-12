import React, {Component} from 'react'
import { connect } from 'react-redux';
import {Container, Row, Col, Button} from 'reactstrap';

import {
    getTasks, deleteTask, addTask, updateTask, completeTask
} from "../actions/todo";

import {
    logout
} from "../actions/auth";

import DataTable from '../components/DataTable';

class TodoListContainer extends Component {

    componentDidMount() {
        this.props.getTasks();
    }

    logout = () => {
        this.props.logout();
        this.props.history.push("/login");
        window.location.reload();
    }

    render() {
        const { items, deleteTask, addTask, updateTask, completeTask } = this.props;
        return (
            <Container className="App">
                <header>
                    <Button style={{float: "right"}} color={"danger"} className="close" onClick={this.logout}>
                        Log out
                    </Button>
                </header>
                <Row>
                    <Col>
                        <h1 style={{margin: "20px 0"}}>Todo List</h1>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <DataTable items={items}
                                   updateTask={updateTask}
                                   deleteTask={deleteTask}
                                   addTask={addTask}
                                   completeTask={completeTask}/>
                    </Col>
                </Row>
            </Container>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        items: state.tasks
    };
};

export default connect(mapStateToProps, {
    getTasks, deleteTask, addTask, updateTask, completeTask, logout
})(TodoListContainer);