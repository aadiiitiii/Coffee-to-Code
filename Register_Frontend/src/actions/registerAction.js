import {
    FETCH_SECURITY_QUESTIONS, FILTER_SECURITY_QUESTIONS,
    FILTER_SECURITY_QUESTION_ONE, FILTER_SECURITY_QUESTION_TWO,
    USER_BASIC_DATA_EVENT_HANDLER
} from './types';
import Axios from '../Axios';

export const fetchSecurityQuestions = (filterQuestions) => dispatch => {
    Axios.auth.getSecurityQuestions()
        .then(response => {
            dispatch({
                type: FETCH_SECURITY_QUESTIONS,
                payload: response.data
            })
        }).then(() => { filterQuestions(1, 3) })
}

export const filterSecurityQuestions = (data) => (dispatch) => {
    dispatch({
        type: FILTER_SECURITY_QUESTIONS,
        payload: data
    })
}

export const filterSecurityQuestionOne = (data) => (dispatch) => {
    dispatch({
        type: FILTER_SECURITY_QUESTION_ONE,
        payload: data
    })
}

export const filterSecurityQuestionTwo = (data) => (dispatch) => {
    dispatch({
        type: FILTER_SECURITY_QUESTION_TWO,
        payload: data
    })
}

export const userDataEventHandler = (data) => (dispatch) => {
    return dispatch({
        type: USER_BASIC_DATA_EVENT_HANDLER,
        payload: data
    })
}

