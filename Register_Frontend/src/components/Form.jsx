import React from 'react'
import useForm from 'react-hook-form'
import Recaptcha from 'react-recaptcha';
import Axios from '../Axios';
import GoogleLogin from 'react-google-login';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { userDataEventHandler } from '../actions/registerAction';

function Form(props) {
    const { register, errors } = useForm({
        mode:'onBlur'
    })

    const validate = (event) => {
        console.log(errors);

        // // code to validate all input cases
        if (props.state.allcorrect && props.state.captchaver && errors.firstName === undefined) {
            Axios.auth.postusers(props.state.users)
                .then(response => {
                    if (response.data.status === 400) {
                        // User already exists
                        //props.history.push('/error');
                        console.log("Error")
                    }
                    else if (response.data.status === 200) {
                        // User details has been succesfully entered into the database
                        // Calling login API
                        // window.location.assign("http://localhost:8014/#");
                        console.log("success")
                    }
                });
        }
        else{
            console.log("Errors");
        }


    }

    // firstName / lastname / email / phonenumber enters in the input box will be fired 
    const userDataEventHandler = (event) => {
        console.log(props.state);
        let newUsers = {};
        props.state.users[event.target.name] = event.target.value;
        newUsers = {...props.state.users};
        props.userDataEventHandler(newUsers);
        // setState({ users: { ...state.users, ...newUsers } });
    }

    // password enters in the input box will be fired 
    const passwordEventHandler = (event) => {
        // setState({ users: { ...state.users, passwordHistory: { pwd1: event.target.value } } });
    }

    // confirmnpassword enters in the input box will be fired 
    const confirmPasswordEventHandler = (event) => {
        if (props.state.users.passwordHistory.pwd1 === event.target.value)
            document.getElementById("conpasscheck").innerHTML = "";
        else
            document.getElementById("conpasscheck").innerHTML = "Password and Confirm Password should be same"
    }






    const recaptchaLoaded = () => {
        console.log('captcha has loaded');
    }

    const verifyCallback = (response) => {
        // if (response) {
        //     setState({ captchaver: true });
        // }
    }

    // Scurity question changing thsi will be called

    // security answer enters in the input box will be called
    const securityAnswers = (answer, answerID) => {

        // if (answerID === 1)
        //     setState({ users: { ...state.users, securityAns: { ...state.users.securityAns, securityAnsID1: answer } } });

        // else if (answerID === 2)
        //     setState({ users: { ...state.users, securityAns: { ...state.users.securityAns, securityAnsID2: answer } } });
    }

    return (
        <form onSubmit={(e) => { validate(); e.preventDefault(); }}>

            <div className="form-group row">
                <label className="col-sm-1 col-form-label" >FirstName</label>
                <div className="col-sm-4">
                    <input type="text" className="form-control" placeholder="FirstName" name="firstName" 
                    value={props.state.users.firstName} 
                    onChange={userDataEventHandler} 
                    ref={register({ required: true })} 
                    />
                </div>
                {errors.firstName && 'First name is required'}
            </div>

            <div className="form-group row">
                <label className="col-sm-1 col-form-label">LastName</label>
                <div className="col-sm-4">
                    <input type="text" className="form-control" placeholder="LastName" name="lastName" required onChange={userDataEventHandler} value={props.state.users.lastName} />
                </div>
            </div>

            <div className="form-group row">
                <label className="col-sm-1 col-form-label">EmailID</label>
                <div className="col-sm-4">
                    <input type="email" className="form-control" placeholder="EmailID" name="emailID" required onChange={userDataEventHandler} value={props.state.users.emailID} />
                </div>
            </div>

            <div className="form-group row">
                <label className="col-sm-1 col-form-label">PhoneNo</label>
                <div className="col-sm-4">
                    <input type="number" className="form-control" placeholder="PhoneNo" name="phoneNo" required onChange={userDataEventHandler} value={props.state.users.phoneNo} />
                </div>
            </div>

            <div className="form-group row">
                <label className="col-sm-1 col-form-label">Password</label>
                <div className="col-sm-4">
                    <input type="password" className="form-control" placeholder="Password" name="pwd1" required onChange={passwordEventHandler}  />
                </div>
            </div>

            <div className="form-group row">
                <label className="col-sm-1 col-form-label">Confirm Password</label>
                <div className="col-sm-4">
                    <input type="password" className="form-control" placeholder="Confirm Password" name="confirmPassword" required onChange={confirmPasswordEventHandler} />
                </div>
                <span id="conpasscheck"></span>
            </div>


            <div className="form-group row">
                <label className="col-sm-1 col-form-label">Security Question 1</label>
                <div className="col-sm-3">
                    <select className="security" name="securityQueID1" required onChange={(e) => props.filterQuestion(e.target.value, 1)}>
                        {props.state.securityQuestion1.map(questions =>
                            <option key={questions.questionID} value={questions.questionID}> {questions.question} </option>
                        )}
                    </select>
                </div>

                <div className="col-sm-4">
                    <input type="text" name="securityAnsID1" required  onChange={(e) => securityAnswers(e.target.value, 1)}></input>
                </div>
            </div>

            <div className="form-group row">
                <label className="col-sm-1 col-form-label">Security Question 2</label>
                <div className="col-sm-3">
                    <select className="security" name="securityQueID2" required onChange={(e) => props.filterQuestion(e.target.value, 2)}>
                        {props.state.securityQuestion2.map(questions =>
                            <option key={questions.questionID} value={questions.questionID}>{questions.question}</option>
                        )}
                    </select>
                </div>

                <div className="col-sm-4">
                    <input type="text" name="securityAnsID2" required onChange={(e) => securityAnswers(e.target.value, 2)}></input>
                </div>
            </div>

            <Recaptcha
                sitekey="6LcE97EUAAAAAJ-MJMDdEt2fX5KDN_pjsdbCQ-pJ"
                render="explicit"
                onloadCallback={recaptchaLoaded}
                verifyCallback={verifyCallback}
            />
            <br />
            <button className="btn btn-primary" type="submit">Submit</button>

        </form>
    );
}

Form.propTypes = {
    userDataEventHandler: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    state: state.register
})

export default connect(mapStateToProps, { userDataEventHandler })(Form);
