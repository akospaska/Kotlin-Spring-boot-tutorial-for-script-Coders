import React from 'react';
import ReactDOM from 'react-dom';

function InventoryElement(props) {
  const countPlus = (id) => {
    console.log(id);
  };
  const countMinus = (id) => {
    console.log(id);
  };
  const itemDelete = (id) => {
    console.log(id);
  };

  return (
    <div key={props.id} className="four wide column">
      <div className="ui segment">
        <h4 className="ui right floated header">
          {props.name} <span>Count:{props.count}</span>{' '}
          <div className="buttonContainer">
            <button className="ui button" onClick={() => countPlus(props.id)}>
              +
            </button>{' '}
            <button className="ui button" onClick={() => countMinus(props.id)}>
              -
            </button>
            <button className="ui button" onClick={() => itemDelete(props.id)}>
              Delete
            </button>
          </div>
        </h4>
        <div className="ui clearing divider"></div>
      </div>
    </div>
  );
}

export default InventoryElement;
