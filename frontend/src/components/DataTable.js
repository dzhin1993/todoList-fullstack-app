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
                    <div style={{width: "110px"}}>
                        <button className="delete-button" onClick={() => deleteTask(id)}>
                            <i className="material-icons delete-icon">&#xE5C9;</i>
                        </button>
                    </div>
                </td>
                <td>
                    <div className="edit-button" style={{width: "110px"}}>
                        <ModalForm buttonLabel="Edit" item={item} updateTask={updateTask}>
                           {/* <i className="material-icons">&#xE8B8;</i>*/}
                        </ModalForm>
                    </div>
                </td>
            </tr>
        )
    })

    return (
        <div className="table-wrapper">
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