// import React, { useState } from "react";
// import axios from "axios";

// function StockOrderForm() {
//   const [symbol, setSymbol] = useState(""); // Stock symbol
//   const [quantity, setQuantity] = useState(""); // Quantity of stock
//   const [orderType, setOrderType] = useState("BUY"); // Type of order (BUY/SELL)
//   const [stockPrice, setStockPrice] = useState(null); // Stock price fetched
//   const [error, setError] = useState(null); // Error handling
//   const [message, setMessage] = useState({ type: "", text: "" });
//   // Function to fetch stock price from backend
//   const fetchStockData = async () => {
//     const jwtToken = localStorage.getItem("authToken"); // Retrieve token from localStorage
//     if (!jwtToken) {
//       setMessage({ type: "error", text: "User not authenticated. Please log in." });
//       return;
//     }
  
//     try {
//       const response = await axios.get(`http://localhost:8080/api/stocks?symbol=${symbol}`, {
//         headers: { Authorization: `Bearer ${jwtToken}` },
//       });
//       setStockPrice(response.data.price); // Assuming the response contains a `price` field
//       setMessage({ type: "success", text: "Stock price fetched successfully!" });
//     } catch (err) {
//       setMessage({ type: "error", text: "Failed to fetch stock data." });
//       console.error("Error fetching stock data: ", err);
//     }
//   };
  

//   // Function to handle placing orders
//   const placeOrder = async () => {
//     const orderRequest = {
//       userId: "user123", // You can replace this with actual user info
//       symbol,
//       quantity: parseInt(quantity),
//     };

//     try {
//       const response = await axios.post(
//         `http://localhost:8080/api/orders/${orderType.toLowerCase()}`,
//         orderRequest
//       );
//       if (response.status === 200) {
//         alert(`${orderType} order placed successfully for ${symbol}`);
//       }
//     } catch (err) {
//       setError("Failed to place order.");
//       console.error("Error placing order: ", err);
//     }
//   };

//   return (
//     <div>
//       <h2>Place Stock Order</h2>
//       <div>
//         <label>Stock Symbol:</label>
//         <input
//           type="text"
//           value={symbol}
//           onChange={(e) => setSymbol(e.target.value)}
//         />
//       </div>
//       <div>
//         <label>Quantity:</label>
//         <input
//           type="number"
//           value={quantity}
//           onChange={(e) => setQuantity(e.target.value)}
//         />
//       </div>
//       <div>
//         <label>Order Type:</label>
//         <select
//           value={orderType}
//           onChange={(e) => setOrderType(e.target.value)}
//         >
//           <option value="BUY">Buy</option>
//           <option value="SELL">Sell</option>
//         </select>
//       </div>
//       <div>
//         <button onClick={fetchStockData}>Fetch Stock Price</button>
//       </div>
//       {stockPrice && (
//         <div>
//           <h3>Stock Price: {stockPrice}</h3>
//         </div>
//       )}
//       {error && <p style={{ color: "red" }}>{error}</p>}
//       <div>
//         <button onClick={placeOrder}>Place Order</button>
//       </div>
//     </div>
//   );
// }

// export default StockOrderForm;
import React, { useState } from "react";
import axios from "axios";

function StockOrderForm() {
  const [symbol, setSymbol] = useState(""); // Stock symbol
  const [quantity, setQuantity] = useState(""); // Quantity of stock
  const [orderType, setOrderType] = useState("BUY"); // Type of order (BUY/SELL)
  const [stockPrice, setStockPrice] = useState(null); // Stock price fetched
  const [error, setError] = useState(null); // Error handling
  const [message, setMessage] = useState({ type: "", text: "" }); // Message state for success/error feedback

  const fetchStockData = async () => {
    const jwtToken = localStorage.getItem("authToken"); // Retrieve token from localStorage
    console.log("JWT Token:", jwtToken);
    if (!jwtToken) {
      setMessage({ type: "error", text: "User not authenticated. Please log in." });
      return;
    }

    try {
      const response = await axios.get(`http://localhost:8080/api/stocks?symbol=${symbol}`, {
        headers: { Authorization: `Bearer ${jwtToken}` },
      });
      setStockPrice(response.data.price); // Assuming response contains a `price` field
      setMessage({ type: "success", text: "Stock price fetched successfully!" });
    console.log(response.data);
    } catch (err) {
      setMessage({ type: "error", text: "Failed to fetch stock data." });
      console.error("Error fetching stock data: ", err);
    }
  };

  const placeOrder = async () => {
    const orderRequest = {
      userId: "user123", // Replace with actual user info
      symbol,
      quantity: parseInt(quantity),
    };

    try {
      const jwtToken = localStorage.getItem("authToken");
      if (!jwtToken) {
        setMessage({ type: "error", text: "User not authenticated. Please log in." });
        return;
      }

      const response = await axios.post(
        `http://localhost:8080/api/orders/${orderType.toLowerCase()}`,
        orderRequest,
        { headers: { Authorization: `Bearer ${jwtToken}` } }
      );
      if (response.status === 200) {
        setMessage({ type: "success", text: `${orderType} order placed successfully for ${symbol}` });
      }
    } catch (err) {
      setMessage({ type: "error", text: "Failed to place order." });
      console.error("Error placing order: ", err);
    }
  };

  return (
    <div>
      <h2>Place Stock Order</h2>
      <div>
        <label>Stock Symbol:</label>
        <input type="text" value={symbol} onChange={(e) => setSymbol(e.target.value)} />
      </div>
      <div>
        <label>Quantity:</label>
        <input type="number" value={quantity} onChange={(e) => setQuantity(e.target.value)} />
      </div>
      <div>
        <label>Order Type:</label>
        <select value={orderType} onChange={(e) => setOrderType(e.target.value)}>
          <option value="BUY">Buy</option>
          <option value="SELL">Sell</option>
        </select>
      </div>
      <div>
        <button onClick={fetchStockData}>Fetch Stock Price</button>
      </div>
      {stockPrice && (
        <div>
          <h3>Stock Price: {stockPrice}</h3>
        </div>
      )}
      {message.text && (
        <p style={{ color: message.type === "error" ? "red" : "green" }}>{message.text}</p>
      )}
      <div>
        <button onClick={placeOrder}>Place Order</button>
      </div>
    </div>
  );
}

export default StockOrderForm;
