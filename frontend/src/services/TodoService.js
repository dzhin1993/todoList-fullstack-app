import axios from 'axios';

import authService from "./AuthService";

const TASK_API_BASE_URL = "http://localhost:8080/api/todoList/"

class TodoService {
    static getAll() {
        let token = authService.getCurrentUser();
        return axios.get(TASK_API_BASE_URL, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        });
    }

    static delete(id) {
        let token = authService.getCurrentUser();
        return axios.delete(TASK_API_BASE_URL + id, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        });
    }

    static getById(id) {
        let token = authService.getCurrentUser();
        return axios.get(TASK_API_BASE_URL + id, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        });
    }

    static save(task) {
        let token = authService.getCurrentUser();
        return axios.post(TASK_API_BASE_URL, task, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        });
    }

    static complete(id, completed) {
        let token = authService.getCurrentUser();
        let request = {params: {id: id, completed: completed}, headers: {Authorization: 'Bearer ' + token}};
        return axios.put(TASK_API_BASE_URL + "complete", null, request);
    }
}

export default TodoService;