/**
  * 1990-2019 Publicis Sapient Corporation. All rights reserved.   
*/
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import {Router, Route} from 'react-router-dom';
import history from './history';
import RegistrationPage from './components/RegistrationPage';
import Register from './components/Register';
import Home from './components/Home';
import Error from './components/Error';
import { Provider } from 'react-redux';
import store from './store/store';

// All routing details
const router = (
   <Provider store={store}>
        <Router history = {history}>
        <ul>
            <Route exact path="/" component={App} /> <br />
            <Route exact path="/register" component={RegistrationPage} /> <br />
            <Route exact path="/home" component={Home} /> <br />
            <Route exact path="/error" component={Error} /> <br />
        </ul>
    </Router>
   </Provider>
)


ReactDOM.render(
    router, 
    document.getElementById('root'));
