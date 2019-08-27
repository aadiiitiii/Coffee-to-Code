/**
  * 1990-2019 Publicis Sapient Corporation. All rights reserved.   
*/

// Registration Page

import React, { Component } from 'react';
import Recaptcha from 'react-recaptcha';
<<<<<<< HEAD
import Axios from './Axios';
import GoogleLogin from 'react-google-login';


class RegistrationPage extends Component {

  constructor() {
    super();
    this.state = {
      users: {
        firstName: "",
        lastName: "",
        emailID: "",
        phoneNo: "",

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
      firstnamereadonly: false,
      lasstnamereadonly: false,
      emailreadonly: false,

    };

  }

=======
import Axios from '../Axios';
import GoogleLogin from 'react-google-login';
import SimpleReactValidator from 'simple-react-validator';
import Form from './Form';
import PropTypes from 'prop-types';
import { Provider } from 'react-redux';
import store from '../store/store';
import { connect } from 'react-redux';
import { fetchSecurityQuestions, filterSecurityQuestions, filterSecurityQuestionOne, filterSecurityQuestionTwo } from '../actions/registerAction';

class RegistrationPage extends Component {

>>>>>>> 17227f083b00e705b9e3b8011e42d00eb5eaff5d
  componentDidMount() {
    this.props.fetchSecurityQuestions(this.filterQuestion);
  }
  
  filterQuestion = (questionID, question) => {


    let securityQuestions = this.props.state.fullList.filter((list) => {
      return list.questionID !== parseInt(questionID)
    }).map((list) => { return list });

    if (question === 1) {
      this.props.filterSecurityQuestionOne({
        questionID: parseInt(questionID),
        securityQuestions: securityQuestions
      });
    }

    else if (question === 2) {
      this.props.filterSecurityQuestionTwo({
        questionID: parseInt(questionID),
        securityQuestions: securityQuestions
      });
    }
    else {
      this.props.filterSecurityQuestions({
        questionID: parseInt(questionID),
        securityQuestions: securityQuestions
      });
    }

<<<<<<< HEAD
  // confirmnpassword enters in the input box this will be fired 
  ConfirmPasswordEventHandler = (event) => {
    if (this.state.users.passwordHistory.pwd1 === event.target.value)
      document.getElementById("conpasscheck").innerHTML = "";
    else
      document.getElementById("conpasscheck").innerHTML = "Password and Confirm Password should be same"
=======
>>>>>>> 17227f083b00e705b9e3b8011e42d00eb5eaff5d
  }

  // constructor() {
  //   super();
  //   this.state = {
  //     users: {
  //       firstName: "",
  //       lastName: "",
  //       emailID: "",
  //       phoneNo: "",

  //       passwordHistory: {
  //         pwd1: ""
  //       },
  //       securityAns: {
  //         securityQueID1: 1,
  //         securityQueID2: 2,
  //         securityAnsID1: "",
  //         securityAnsID2: ""
  //       }
  //     },
  //     allcorrect: true,
  //     captchaver: true,

<<<<<<< HEAD
  // on submit the form this will be called
  validate = (event) => {

    // // code to validate all input cases

    // first name checking 

    var ulen = this.state.users.firstName.length;
    if (ulen < 2 || ulen > 10) {
      this.state.allcorrect = false;
      console.log("Length should be between 2 to 10")
    }

    var fnameValid = this.state.users.firstName.match(/^[a-zA-Z]+$/)
    fnameValid ? alert("FirstName is valid") : alert("FirstName is not valid");



    //  last name checking


    var ulen = this.state.users.lastName.length;
    if (ulen < 2 || ulen > 10) {
      this.state.allcorrect = false;
    }
    var lnameValid = this.state.users.lastName.match(/^[a-zA-Z]+$/)
    lnameValid ? alert("LastName is  valid") : alert("LastName is not valid");

    //   password checking

    var plen = this.state.users.passwordHistory.pwd1.length;
    if (plen < 8 || plen > 15) {
      this.state.allcorrect = false;

      var pwdValid = newpass.pwd1.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d.*)(?=.*\W.*)[a-zA-Z0-9\S]{8,15}$/);
      if (pwdValid) {
        document.getElementById("passId").innerHTML = "";
      }
      else {
        document.getElementById("passId").innerHTML = "Password should contain atleast one upper case,one lower case,one special character and one number";
      }

      //  email checking

      var emailValid = this.state.users.emailID.match(/^([\w.%+-]+)@([\w-]+\.)+([\w]{2,})$/i);
      if (emailValid) {
        alert("email is  valid")
      }
      else {
        alert("email is not valid")
      }

      //   phone number

      var pholen = this.state.users.phoneNo.length;
      if (pholen !== 10) {
        this.state.allcorrect = false;
      }


    }

    //console.log(this.state);
    // this.props.history.push('/home');
    this.setState({
      allcorrect: true
    });

    // // code to validate all input cases
    if (this.state.allcorrect && this.state.captchaver) {
      console.log(this.state.users);
      Axios.auth.postusers(this.state.users)
        .then(response => {
          if (response.data.status === 400) {
            // User already exists
            this.props.history.push('/error');
          }
          else if (response.data.status === 200) {
            // User details has been succesfully entered into the database
            // Calling login API
            window.location.assign("http://localhost:8014/#");
          }
        });
=======
  //     fullList: [],
  //     securityQuestion1: [],
  //     securityQuestion2: [],
  //     firstnamereadonly: false,
  //     lasstnamereadonly: false,
  //     emailreadonly: false,
>>>>>>> 17227f083b00e705b9e3b8011e42d00eb5eaff5d

  //   };
  //   this.validator = new SimpleReactValidator();

  // }

<<<<<<< HEAD


  recaptchaLoaded = () => {
    console.log('captcha has loaded');
  }
=======
  // componentDidMount() {
  //   this.props.fetchSecurityQuestions();
  // }
>>>>>>> 17227f083b00e705b9e3b8011e42d00eb5eaff5d

  // // firstName / lastname / email / phonenumber enters in the input box this will be fired 
  // userDataEventHandler = (event) => {
  //   const newUsers = {};
  //   newUsers[event.target.name] = event.target.value;
  //   this.setState({ users: { ...this.state.users, ...newUsers } });
  // }

  // // password enters in the input box this will be fired 
  // PasswordEventHandler = (event) => {
  //   this.setState({ users: { ...this.state.users, passwordHistory: { pwd1: event.target.value } } });
  // }

  // // confirmnpassword enters in the input box this will be fired 
  // ConfirmPasswordEventHandler = (event) => {
  //   if (this.state.users.passwordHistory.pwd1 === event.target.value)
  //     document.getElementById("conpasscheck").innerHTML = "";
  //   else
  //     document.getElementById("conpasscheck").innerHTML = "Password and Confirm Password should be same"
  // }






  // recaptchaLoaded = () => {
  //   console.log('captcha has loaded');
  // }

  // verifyCallback = (response) => {
  //   if (response) {
  //     this.setState({ captchaver: true });
  //   }
  // }

<<<<<<< HEAD
  responseGoogleSuccess = (response) => {
    console.log(response.profileObj);
    this.setState({ users: { ...this.state.users, emailID: response.profileObj.email, firstName: response.profileObj.givenName, lastName: response.profileObj.familyName } }
      , () => {
        this.setState({ ...this.state, firstnamereadonly: true, lastnamereadonly: true, emailreadonly: true });
      })
      ;

  }

  responseGoogleFailure = (response) => {
    console.log(response);
  }


=======
  // // Scurity question changing thsi will be called
  // filterQuestion = (questionID, question) => {
>>>>>>> 17227f083b00e705b9e3b8011e42d00eb5eaff5d

  //   let securityQuestions = this.state.fullList.filter((list) => {
  //     return list.questionID !== parseInt(questionID)
  //   }).map((list) => { return list });

  //   if (question === 1) {
  //     this.setState({ users: { ...this.state.users, securityAns: { ...this.state.users.securityAns, securityQueID1: parseInt(questionID) } }, securityQuestion2: securityQuestions });
  //   }

  //   else if (question === 2)
  //     this.setState({ users: { ...this.state.users, securityAns: { ...this.state.users.securityAns, securityQueID2: parseInt(questionID) } }, securityQuestion1: securityQuestions });

  //   else {
  //     this.setState({ securityQuestion1: this.state.fullList });
  //     this.setState({ securityQuestion2: securityQuestions });
  //   }

  // }

<<<<<<< HEAD
      <div>
        <form onSubmit={(e) => { this.validate(); e.preventDefault(); }}>

          <div className="form-group row">
            <label className="col-sm-1 col-form-label" >FirstName</label>
            <div className="col-sm-4">
              <input type="text" className="form-control" placeholder="FirstName" required onChange={this.userDataEventHandler} name="firstName" value={this.state.users.firstName} />
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-1 col-form-label">LastName</label>
            <div className="col-sm-4">
              <input type="text" className="form-control" placeholder="LastName" name="lastName" required onChange={this.userDataEventHandler} value={this.state.users.lastName} />
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-1 col-form-label">EmailID</label>
            <div className="col-sm-4">
              <input type="email" className="form-control" placeholder="EmailID" name="emailID" required onChange={this.userDataEventHandler} value={this.state.users.emailID} />
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-1 col-form-label">PhoneNo</label>
            <div className="col-sm-4">
              <input type="number" className="form-control" placeholder="PhoneNo" name="phoneNo" required onChange={this.userDataEventHandler} value={this.state.users.phoneNo} />
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-1 col-form-label">Password</label>
            <div className="col-sm-4">
              <input type="password" className="form-control" placeholder="Password" name="pwd1" required onChange={this.PasswordEventHandler} value={this.state.users.passwordHistory.pwd1} />
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-1 col-form-label">Confirm Password</label>
            <div className="col-sm-4">
              <input type="password" className="form-control" placeholder="Confirm Password" name="confirmPassword" required onChange={this.ConfirmPasswordEventHandler} />
            </div>
            <span id="conpasscheck"></span>
          </div>


          <div className="form-group row">
            <label className="col-sm-1 col-form-label">Security Question 1</label>
            <div className="col-sm-3">
              <select className="security" name="securityQueID1" required onChange={(e) => this.filterQuestion(e.target.value, 1)}>
                {this.state.securityQuestion1.map(questions =>
                  <option key={questions.questionID} value={questions.questionID}> {questions.question} </option>
                )}
              </select>
            </div>

            <div className="col-sm-4">
              <input type="text" name="securityAnsID1" required value={this.state.users.securityAns.securityAnsID1} onChange={(e) => this.securityAnswers(e.target.value, 1)}></input>
            </div>
          </div>

          <div className="form-group row">
            <label className="col-sm-1 col-form-label">Security Question 2</label>
            <div className="col-sm-3">
              <select className="security" name="securityQueID2" required onChange={(e) => this.filterQuestion(e.target.value, 2)}>
                {this.state.securityQuestion2.map(questions =>
                  <option key={questions.questionID} value={questions.questionID}>{questions.question}</option>
                )}
              </select>
            </div>

            <div className="col-sm-4">
              <input type="text" name="securityAnsID2" required value={this.state.users.securityAns.securityAnsID2} onChange={(e) => this.securityAnswers(e.target.value, 2)}></input>
            </div>
          </div>

          <Recaptcha
            sitekey="6LcE97EUAAAAAJ-MJMDdEt2fX5KDN_pjsdbCQ-pJ"
            render="explicit"
            onloadCallback={this.recaptchaLoaded}
            verifyCallback={this.verifyCallback}
          />
          <br />
          <button className="btn btn-primary" type="submit">Submit</button>

        </form>

        <br></br>
        <GoogleLogin
          clientId={'850784983032-gk55huo5976bjbrtgvi403rufcvg579s.apps.googleusercontent.com'}
          onSuccess={this.responseGoogleSuccess}
          onFailure={this.responseGoogleFailure}
        >

          <span > Signup with Google</span>
        </GoogleLogin>
=======
  // // security answer enters in the input box this will be called
  // securityAnswers = (answer, answerID) => {

  //   if (answerID === 1)
  //     this.setState({ users: { ...this.state.users, securityAns: { ...this.state.users.securityAns, securityAnsID1: answer } } });

  //   else if (answerID === 2)
  //     this.setState({ users: { ...this.state.users, securityAns: { ...this.state.users.securityAns, securityAnsID2: answer } } });
  // }

  // responseGoogleSuccess = (response) => {
  //   console.log(response.profileObj);
  //   this.setState({ users: { ...this.state.users, emailID: response.profileObj.email, firstName: response.profileObj.givenName, lastName: response.profileObj.familyName } }
  //     , () => {
  //       this.setState({ ...this.state, firstnamereadonly: true, lastnamereadonly: true, emailreadonly: true });
  //     })
  //     ;

  // }

  // responseGoogleFailure = (response) => {
  //   console.log(response);
  // }


  render() {

    return (
      <div>
        <Form filterQuestion={this.filterQuestion}></Form>
      </div>
>>>>>>> 17227f083b00e705b9e3b8011e42d00eb5eaff5d

      </div>
    )
  }
}

RegistrationPage.propTypes = {
  fetchSecurityQuestions: PropTypes.func.isRequired,
  filterSecurityQuestionOne: PropTypes.func.isRequired,
  state: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  state: state.register
})

export default connect(mapStateToProps, { fetchSecurityQuestions, filterSecurityQuestions, filterSecurityQuestionOne, filterSecurityQuestionTwo})(RegistrationPage);
