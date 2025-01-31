import {
  FETCH_SECURITY_QUESTIONS, FILTER_SECURITY_QUESTIONS,
  FILTER_SECURITY_QUESTION_ONE, FILTER_SECURITY_QUESTION_TWO,
  USER_BASIC_DATA_EVENT_HANDLER, PASSWORD_EVENT_HANDLER,
  SECURITY_ANSWER_ONE, SECURITY_ANSWER_TWO
} from '../actions/types';

const initialState = {
  users: {
    firstName: "",
    lastName: "",
    emailID: "",
    phoneNo: "",
    gender:"Male",
    maritalStatus:"Single",
	  profession:"Developer",
	  dateOfBirth:"1997-10-29",

    passwordHistory: {
      pwd1: ""
    },
    securityAns: {
      securityQueID1: 1,
      securityQueID2: 2,
      securityAnsID1: "",
      securityAnsID2: ""
    }
  },
  allcorrect: true,
  captchaver: true,

  fullList: [],
  securityQuestion1: [],
  securityQuestion2: [],
  

};

export default function (state = initialState, action) {
  switch (action.type) {
    case FETCH_SECURITY_QUESTIONS:
      return {
        ...state,
        fullList: action.payload
      }
    case FILTER_SECURITY_QUESTIONS:
      return {
        ...state,
        securityQuestion1: state.fullList,
        securityQuestion2: action.payload.securityQuestions
      }
    case FILTER_SECURITY_QUESTION_ONE:
      return {
        ...state,
        securityAns: { ...state.users.securityAns, securityQueID1: parseInt(action.payload.questionID) },
        securityQuestion2: action.payload.securityQuestions
      }
    case FILTER_SECURITY_QUESTION_TWO:
      return {
        ...state,
        securityAns: { ...state.users.securityAns, securityQueID1: parseInt(action.payload.questionID) },
        securityQuestion1: action.payload.securityQuestions
      }

    case USER_BASIC_DATA_EVENT_HANDLER:
      return {
        ...state,
        users: { ...state.users }
      }
    case PASSWORD_EVENT_HANDLER:
      return {
        ...state,
        users: { ...state.users, passwordHistory: { pwd1: action.payload } }
      }
    case SECURITY_ANSWER_ONE:
      return {
        ...state,
        users: { ...state.users, securityAns: { ...state.users.securityAns, securityAnsID1: action.payload } }
      }
    case SECURITY_ANSWER_TWO:
      return {
        ...state,
        users: { ...state.users, securityAns: { ...state.users.securityAns, securityAnsID2: action.payload } }
      }

    default:
      return state;
  }
}