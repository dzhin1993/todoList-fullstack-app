import { ADD_TASK, REMOVE_TASK, GET_TASKS, UPDATE_TASK, COMPLETE_TASK } from "../constants";

const tasks = (state = [], action) => {
    const { type, payload } = action;
    switch (type) {
        case GET_TASKS:
            return payload;
        case REMOVE_TASK:
            return [...state].filter(task => task.id !== payload.id);
        case ADD_TASK:
            return [...state, payload];
        case COMPLETE_TASK:
            return state.map((item) => {
                if (item.id === payload.id) {
                    item.completed = !item.completed;
                }
                return item;
            });
        case UPDATE_TASK:
            return state.map((item) => {
                if (item.id === payload.id) {
                    return {
                        ...item,
                        ...payload,
                    };
                } else {
                    return item;
                }
            });
        default:
            return state;
    }
}

export default tasks;