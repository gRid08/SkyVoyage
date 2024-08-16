import React from 'react';
import '../App.css';

function Loader() {
  return (
    <div className="loader-container">
      <div className="loader"></div>
      <p>Fetching Flight details...</p>
    </div>
  );
}

export default Loader;
