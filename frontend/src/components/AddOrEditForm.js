import React, {Component} from 'react'
import {Button, Form, FormGroup, Label, Input} from 'reactstrap';

class AddOrEditForm extends Component {
    state = {
        id: null,
        name: '',
        dateTime: '',
        completed: false
    }

    onChange = e => {
        this.setState({[e.target.name]: e.target.value});
        console.log(this.state);
    }

    submitFormEdit = e => {
        e.preventDefault();
        let task = this.state;
        this.props.updateTask(task);
        this.props.toggle();
    }

    submitFormAdd = e => {
        e.preventDefault();
        let task = this.state;
        this.props.addTask(task);
        this.props.toggle();
    }

    componentDidMount() {
        if (this.props.item) {
            const {id, name, dateTime, completed} = this.props.item
            this.setState({id, name, dateTime, completed})
        }
    }

    render() {
        const item = this.props.item;
        const { name, dateTime } = this.state;
        return (
            <Form onSubmit={item ? this.submitFormEdit : this.submitFormAdd}>
                <FormGroup>
                    <Label for="name">Name</Label>
                    <Input type="text" name="name" id="name" onChange={this.onChange}
                           value={name == null ? '' : name}/>
                </FormGroup>
                <FormGroup>
                    <Label for="dateTime">DateTime</Label>
                    <Input type="datetime-local" name="dateTime" id="dateTime" onChange={this.onChange}
                           value={dateTime == null ? '' : dateTime}/>
                </FormGroup>
                <Button color={"warning"}>Submit</Button>
            </Form>
        );
    }
}

export default AddOrEditForm