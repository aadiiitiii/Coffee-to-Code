/**
  * 1990-2019 Publicis Sapient Corporation. All rights reserved.   
*/

import React from 'react';
import './App.css';
import RegisterButton from './components/RegisterButton';
import { Provider } from 'react-redux';
import store from './store/store';


function App() {
  return (
      <div className="App">
        <RegisterButton />
      </div>
  );
}

export default App;
