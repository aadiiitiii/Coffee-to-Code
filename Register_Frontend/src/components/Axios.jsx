/**
  * 1990-2019 Publicis Sapient Corporation. All rights reserved.   
*/

// List of urls this api is going 
import axios from 'axios';

const axcreate =  axios.create({
    baseURL: "http://localhost:8005/api/v1/users",
    responseType: "json"
})

export default{
    auth: {

        // to get all security questions from the database
        getSecurityQuestions(){
            return axcreate.get("/securityquestions");
        },

        //to post users data into the database
        postusers(data){
            return axcreate.post("/register",data);
        }
    }
}
