import React, { Component } from 'react'
import Recaptcha from 'react-recaptcha';
import Axios from '../Axios';
import GoogleLogin from 'react-google-login';
import history from '../history';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { userDataEventHandler, passwordEventHandler, securityAnswers } from '../actions/registerAction';

const formValid = formErrors => {
    let valid = true;

    Object.values(formErrors).forEach(value => {
        value.length > 0 && (valid = false);
    });

    return valid;
}

const emailRegex = RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
const phoneNoRegex = RegExp(/^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/);
const passwordRegex = RegExp(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*_#?&])[A-Za-z\d@$!%_#*?&]{8,15}$/);


class Form extends Component {

    constructor(props) {
        super(props);
        this.state = {
            confirmPassword: "",
            formErrors: {
                firstName: "",
                lastName: "",
                emailID: "",
                phoneNo: "",
                password: "",
                confirmPassword: "",
                securityAnsID1: "",
                securityAnsID2: ""
            }

        }
    }

    validate = (event) => {

        // // code to validate all input cases
        if (true) {
            Axios.auth.postusers(this.props.state.users)
                .then(response => {
                    console.log(response);
                    if (response.status === 200) {
                        // User already exists
                        history.push('/error');
                        console.log("Error")
                    }
                    else if (response.status === 200) {
                        // User details has been succesfully entered into the database
                        // Calling login API
                        // window.location.assign("http://localhost:8014/#");
                        console.log("success")
                    }
                }).catch(error => {
                    if (error.response)
                        console.log(error.response);
                });
        }
        else {
            console.log("Errors");
        }


    }

    // firstName / lastname / email / phonenumber enters in the input box will be fired 
    userDataEventHandler = (event) => {
        let newUsers = {};
        this.props.state.users[event.target.name] = event.target.value;
        newUsers = { ...this.props.state.users };
        this.props.userDataEventHandler(newUsers);
        const { name, value } = event.target;
        let formErrors = this.state.formErrors;

        switch (name) {
            case 'firstName':
                formErrors.firstName = (value.length < 5 || value.length > 15) ?
                    "FirstName should be between 5 to 15 characters" : "";
                break;
            case 'lastName':
                formErrors.lastName = (value.length < 5 || value.length > 15) ?
                    "LastName should be between 5 to 15 characters" : "";
                break;
            case 'emailID':
                formErrors.emailID = emailRegex.test(value) && value.length > 0 ?
                    '' : "Invalid email address";
                break;
            case 'phoneNo':
                formErrors.phoneNo = phoneNoRegex.test(value) && value.length > 0 ?
                    '' : "Invalid Phone Numeber";
                break;
            default:
                break;

        }
        this.setState({ formErrors })

    }

    // password enters in the input box will be fired 
    passwordEventHandler = (event) => {
        const { value } = event.target;

        this.props.passwordEventHandler(value);
        let formErrors = this.state.formErrors;
        formErrors.password = passwordRegex.test(value) && value.length > 0 ?
            '' : "Password should be minimum eight and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character";
        formErrors.confirmPassword = value === this.state.confirmPassword ?
            '' : "Both Password and Confirm Password Should match";
        this.setState({ formErrors })
    }

    // confirmnpassword enters in the input box will be fired 
    confirmPasswordEventHandler = (event) => {
        const { value } = event.target;
        let formErrors = this.state.formErrors;
        formErrors.confirmPassword = this.props.state.users.passwordHistory.pwd1 === value ?
            '' : "Both Password and Confirm Password Should match";
        this.setState({ confirmPassword: value, formErrors });
    }

    // security answer enters in the input box will be called
    securityAnswers = (answer, answerID) => {

        let data = {
            answerID: answerID,
            answer: answer
        }
        let formErrors = this.state.formErrors;

        switch (answerID) {
            case 1:
                formErrors.securityAnsID1 = answer.length === 0 ?
                    "Answer the Security Question One" : ''
                break;
            case 2:
                formErrors.securityAnsID2 = answer.length === 0 ?
                    "Answer the Security Question Two" : ''
                break;

            default:
                break;

        }
        this.props.securityAnswers(data);
        this.setState({ formErrors })

    }

    recaptchaLoaded = () => {
        console.log('captcha has loaded');
    }

    verifyCallback = (response) => {
        // if (response) {
        //     setState({ captchaver: true });
        // }
    }


    render() {
        const { formErrors } = this.state;
        return (
            <form onSubmit={(e) => { this.validate(); e.preventDefault(); }}>

                <div className="form-group row">
                    <label className="col-sm-1 col-form-label" >FirstName</label>
                    <div className="col-sm-4">
                        <input type="text" className="form-control" placeholder="FirstName" name="firstName"
                            noValidate
                            onChange={this.userDataEventHandler}
                            value={this.props.state.users.firstName}
                        />
                        {formErrors.firstName.length > 0 && (
                            <span>{formErrors.firstName}</span>
                        )}
                    </div>
                </div>

                <div className="form-group row">
                    <label className="col-sm-1 col-form-label">LastName</label>
                    <div className="col-sm-4">
                        <input type="text" className="form-control" placeholder="LastName" name="lastName"
                            required
                            onChange={this.userDataEventHandler}
                            value={this.props.state.users.lastName}
                        />
                        {formErrors.lastName.length > 0 && (
                            <span>{formErrors.lastName}</span>
                        )}
                    </div>
                </div>

                <div className="form-group row">
                    <label className="col-sm-1 col-form-label">EmailID</label>
                    <div className="col-sm-4">
                        <input type="email" className="form-control" placeholder="EmailID" name="emailID"
                            required
                            onChange={this.userDataEventHandler}
                            value={this.props.state.users.emailID}
                        />
                        {formErrors.emailID.length > 0 && (
                            <span>{formErrors.emailID}</span>
                        )}
                    </div>
                </div>

                <div className="form-group row">
                    <label className="col-sm-1 col-form-label">PhoneNo</label>
                    <div className="col-sm-4">
                        <input type="number" className="form-control" placeholder="PhoneNo" name="phoneNo"
                            required
                            onChange={this.userDataEventHandler}
                            value={this.props.state.users.phoneNo}
                        />
                        {formErrors.phoneNo.length > 0 && (
                            <span>{formErrors.phoneNo}</span>
                        )}
                    </div>
                </div>

                <div className="form-group row">
                    <label className="col-sm-1 col-form-label">Gender</label>
                    <div className="col-sm-3">
                        <select className="gender" name="gender" required>
                            {this.props.state.securityQuestion1.map(questions =>
                                <option key={questions.questionID} value={questions.questionID}> {questions.question} </option>
                            )}
                        </select>
                    </div>
                </div>




                <div className="form-group row">
                    <label className="col-sm-1 col-form-label">Password</label>
                    <div className="col-sm-4">
                        <input type="password" className="form-control" placeholder="Password" name="pwd1"
                            required
                            onChange={this.passwordEventHandler}
                            value={this.props.state.users.passwordHistory.pwd1}
                        />
                        {formErrors.password.length > 0 && (
                            <span>{formErrors.password}</span>
                        )}
                    </div>
                </div>

                <div className="form-group row">
                    <label className="col-sm-1 col-form-label">Confirm Password</label>
                    <div className="col-sm-4">
                        <input type="password" className="form-control" placeholder="Confirm Password" name="confirmPassword"
                            required
                            onChange={this.confirmPasswordEventHandler}
                        />
                        {formErrors.confirmPassword.length > 0 && (
                            <span>{formErrors.confirmPassword}</span>
                        )}
                    </div>
                </div>


                <div className="form-group row">
                    <label className="col-sm-1 col-form-label">Security Question 1</label>
                    <div className="col-sm-3">
                        <select className="security" name="securityQueID1" required
                            onChange={(e) => this.props.filterQuestion(e.target.value, 1)}>
                            {this.props.state.securityQuestion1.map(questions =>
                                <option key={questions.questionID} value={questions.questionID}> {questions.question} </option>
                            )}
                        </select>
                    </div>

                    <div className="col-sm-4">
                        <input type="text" name="securityAnsID1"
                            required
                            onChange={(e) => this.securityAnswers(e.target.value, 1)}
                            value={this.props.state.users.securityAns.securityAnsID1}
                        />
                        {formErrors.securityAnsID1.length > 0 && (
                            <span>{formErrors.securityAnsID1}</span>
                        )}
                    </div>
                </div>

                <div className="form-group row">
                    <label className="col-sm-1 col-form-label">Security Question 2</label>
                    <div className="col-sm-3">
                        <select className="security" name="securityQueID2" required
                            onChange={(e) => this.props.filterQuestion(e.target.value, 2)}>
                            {this.props.state.securityQuestion2.map(questions =>
                                <option key={questions.questionID} value={questions.questionID}>{questions.question}</option>
                            )}
                        </select>
                    </div>

                    <div className="col-sm-4">
                        <input type="text" name="securityAnsID2"
                            required
                            onChange={(e) => this.securityAnswers(e.target.value, 2)}
                            value={this.props.state.users.securityAns.securityAnsID2}
                        />
                        {formErrors.securityAnsID2.length > 0 && (
                            <span>{formErrors.securityAnsID2}</span>
                        )}
                    </div>
                </div>

                {/* <Recaptcha
                    sitekey="6LcE97EUAAAAAJ-MJMDdEt2fX5KDN_pjsdbCQ-pJ"
                    render="explicit"
                    onloadCallback={this.recaptchaLoaded}
                    verifyCallback={this.verifyCallback}
                /> */}
                <br />
                <button className="btn btn-primary" type="submit">Submit</button>

            </form>
        );
    }



}

Form.propTypes = {
    userDataEventHandler: PropTypes.func.isRequired,
    passwordEventHandler: PropTypes.func.isRequired,
    securityAnswers: PropTypes.func.isRequired,
    state: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    state: state.register
})

export default connect(mapStateToProps, { userDataEventHandler, passwordEventHandler, securityAnswers })(Form);
