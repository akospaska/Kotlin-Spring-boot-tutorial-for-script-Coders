import React, { useState } from 'react';
import ReactDOM from 'react-dom';

function InputComponent(props) {
  const [inputValue, setInputValue] = useState('');

  const addNewElement = (newElementName) => {
    console.log(newElementName);
  };

  return (
    <div key={1}>
      <div className="ui input">
        <input type="text" placeholder="Search..." value={inputValue} onChange={(e) => setInputValue(e.target.value)} />
      </div>
      <button
        className="ui button"
        onClick={() => {
          props.refreshPage();
          addNewElement(inputValue);
        }}
      >
        add
      </button>
    </div>
  );
}

export default InputComponent;
