import React from 'react'
import {Table, Button, Input} from 'reactstrap';

import ModalForm from "./Modal";

function DataTable({ items, deleteTask, updateTask, completeTask }) {
    let count = 0;
    const itemsList = items.map(item => {
        const { id, name, dateTime, completed } = item;
        return (
            <tr key={id} style={ item.completed ? {textDecoration: "line-through"} : {textDecoration: "none"}}>
                <td>{++count}</td>
                <td>{name}</td>
                <td>{dateTime.replace("T", " ").substr(0, 16)}</td>
                <td><Input type={"checkbox"}
                           checked={completed}
                           onChange={(e) => completeTask(id, e.target.checked)}/>
                </td>
                <td>
                    <div>
                        <button className="delete-button" onClick={() => deleteTask(id)}>
                            <i className="material-icons delete-icon">&#xE5C9;</i>
                        </button>
                    </div>
                </td>
                <td>
                    <ModalForm buttonLabel="Edit" item={item} updateTask={updateTask}/>
                </td>
            </tr>
        )
    })

    return (
        <div className="table-wrapper">
            <div className="table-title">
                <div className="row">
                    <div className="col-sm-5">
                        <h2>Todo list <b>Management</b></h2>
                    </div>
                    <div className="col-sm-7">
                        <button  className="btn btn-secondary"><i className="material-icons">&#xE147;</i> <span>Add New Item</span></button>
                    </div>
                </div>
            </div>
            <table className="table table-striped table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Datetime</th>
                    <th>Completed</th>
                    <th/>
                    <th/>
                </tr>
                </thead>
                <tbody>
                {itemsList}
                </tbody>
            </table>
        </div>
    )
}

export default DataTable;