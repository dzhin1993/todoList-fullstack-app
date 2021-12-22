import { combineReducers } from "redux";

import tasks from "./tasks"
import auth from "./auth";
import message from "./message";

const rootReducer = combineReducers({ auth, message, tasks });

export default rootReducer;
