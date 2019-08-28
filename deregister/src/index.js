import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import history from './history'
import {Router, Route} from 'react-router-dom';
import Welcome from './components/DeregisterButton';
import DeactivationSuccess from './components/DeactivationSuccess';
import DisplayReviews from './components/DisplayReviews';
import ReviewButton from './components/ReviewButton';
import ForgetMeSuccess from './components/ForgetMeSuccess';

//ReactDOM.render(<DeregisterButton />, document.getElementById('root'));

const router = (
    <Router history = {history} >
        <div>
            <Route exact path ="/reviewsbutton" component={ReviewButton} /><br />
            <Route exact  path="/welcome/:id" component={Welcome} /> <br />
            <Route exact path="/deactivateSuccess" component={DeactivationSuccess} /> <br />
            <Route exact path="/forgetmeSuccess" component={ForgetMeSuccess} /> <br />
            <Route exact path="/reviews" component={DisplayReviews} /> <br /> 
        </div>
    </Router>
)
ReactDOM.render(
    router, 
    document.getElementById('root'));