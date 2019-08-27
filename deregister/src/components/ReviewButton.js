import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import Axios from './Axios';

export default class ReviewButton extends Component {

    render() {
        return (
          <div>
            <br /><br />
            <Link to="/reviews">
              <button className="btn btn-primary"> Reivews</button></Link>
          </div>
        )
      }
    }
