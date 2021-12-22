import React, {Component} from 'react'
import {Button, Modal, ModalHeader, ModalBody} from 'reactstrap'

import AddOrEditForm from "./AddOrEditForm";

class ModalForm extends Component {
    constructor(props) {
        super(props)
        this.state = {
            modal: false
        }
    }

    toggle = () => {
        this.setState(prevState => ({
            modal: !prevState.modal
        }))
    }

    render() {
        const closeBtn = <Button color={"danger"} className="close" onClick={this.toggle}>&times;</Button>

        let button =
            <Button color="warning" onClick={this.toggle} style={{float: "left", marginRight: "10px"}}>
                {this.props.buttonLabel}
            </Button>

        const {addTask, updateTask, item} = this.props;

        return (
            <div>
                {button}
                <Modal isOpen={this.state.modal} toggle={this.toggle}>
                    <ModalHeader toggle={this.toggle} close={closeBtn}>Edit item</ModalHeader>
                    <ModalBody>
                        <AddOrEditForm
                            addTask={addTask}
                            updateTask={updateTask}
                            toggle={this.toggle}
                            item={item}/>
                    </ModalBody>
                </Modal>
            </div>
        )
    }
}

export default ModalForm