import { ADD_TASK, UPDATE_TASK, REMOVE_TASK, GET_TASKS, COMPLETE_TASK } from "../constants";

import TodoService from '../services/TodoService';

export const getTasks = () => (dispatch) => {
    TodoService.getAll().then(res => {
        dispatch({
            type: GET_TASKS,
            payload: res.data,
        });
    });
};

export const addTask = (task) => (dispatch) => {
    TodoService.save(task).then(res => {
        dispatch({
            type: ADD_TASK,
            payload: res.data,
        });
    });
};

export const updateTask = (task) => (dispatch) => {
    TodoService.save(task).then(res => {
        dispatch({
            type: UPDATE_TASK,
            payload: res.data,
        });
    });
};

export const deleteTask = (id) => (dispatch) => {
    TodoService.delete(id).then(() => {
        dispatch({
            type: REMOVE_TASK,
            payload: {id},
        });
    });
};

export const completeTask = (id, completed) => (dispatch) => {
    TodoService.complete(id, completed).then(() => {
        dispatch({
            type: COMPLETE_TASK,
            payload: {id},
        });
    });
};