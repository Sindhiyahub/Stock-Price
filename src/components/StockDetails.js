import React, { useEffect, useState } from "react";
import axios from "axios";

const StockDetails = () => {
  const [stockData, setStockData] = useState(null); // State for stock data
  const [loading, setLoading] = useState(true); // State for loading indicator

  useEffect(() => {
    const fetchStockData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/stocks", {
          params: { symbol: "AAPL" },
        });
        console.log("API Response:", response.data); // Debug response
        setStockData(response.data); // Update stock data
        setLoading(false); // Stop loading
      } catch (error) {
        console.error("Error fetching stock data:", error.message); // Log errors
        setLoading(false); // Stop loading on error
      }
    };

    fetchStockData();
  }, []);

  // Render stock data or loading indicator
  if (loading) {
    return <p>Loading stock data...</p>;
  }

  if (!stockData) {
    return <p>Failed to load stock data.</p>;
  }

  return (
    <div>
      <h2>Stock Details</h2>
      <p><strong>Symbol:</strong> {stockData.symbol}</p>
      <p><strong>Current Price:</strong> ${stockData.currentPrice}</p>
      <p><strong>Last Updated:</strong> {new Date(stockData.lastUpdated).toLocaleString()}</p>
    </div>
  );
};

export default StockDetails;
