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
        let toggle = this.toggle;
        const closeBtn = <Button color={"danger"} className="close" onClick={toggle}>&times;</Button>

        let addButton = <button className="btn btn-secondary" onClick={toggle}>
            <i className="material-icons">&#xE147;</i> <span>Add New Item</span>
        </button>

        let editButton = <button className="edit-delete-button" onClick={toggle}>
            <i className="material-icons">&#xE8B8;</i>
        </button>

        const {addTask, updateTask, item} = this.props;

        return (
            <div>
                {item ? editButton : addButton}
                <Modal isOpen={this.state.modal} toggle={toggle}>
                    <ModalHeader toggle={toggle} close={closeBtn}>Edit item</ModalHeader>
                    <ModalBody>
                        <AddOrEditForm
                            addTask={addTask}
                            updateTask={updateTask}
                            toggle={toggle}
                            item={item}/>
                    </ModalBody>
                </Modal>
            </div>
        )
    }
}

export default ModalForm