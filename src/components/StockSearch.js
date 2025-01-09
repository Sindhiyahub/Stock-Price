// src/components/StockSearch.js
import React, { useState } from 'react';
import StockDetails from './StockDetails';

const StockSearch = () => {
  const [symbol, setSymbol] = useState(''); // Manage the stock symbol state
  const [submitted, setSubmitted] = useState(false); // Track if the user has submitted the search

  const handleChange = (e) => {
    setSymbol(e.target.value); // Update symbol as user types
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (symbol) {
      setSubmitted(true); // Submit the form and show stock details
    }
  };

  return (
    <div>
      <h2>Search for Stock</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={symbol}
          onChange={handleChange}
          placeholder="Enter stock symbol (e.g., AAPL)"
          required
        />
        <button type="submit">Search</button>
      </form>

      {submitted && <StockDetails symbol={symbol} />} {/* Pass symbol to StockDetails only when submitted */}
    </div>
  );
};

export default StockSearch;
