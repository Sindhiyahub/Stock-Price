
import React, { useEffect, useState } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Login from "./Auth/Login";
import Register from "./Auth/Register";
import StockDetails from "./components/StockDetails";
import StockSearch from "./components/StockSearch";
const App = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem("authToken"));

  // Update `isAuthenticated` whenever the token changes
  useEffect(() => {
    const token = localStorage.getItem("authToken");
    setIsAuthenticated(!!token);
    console.log(localStorage.getItem("authToken")) 
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login setAuth={setIsAuthenticated} />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/StockSearch"
          element={isAuthenticated ? <StockSearch /> : <Navigate to="/login" />}
        />
        <Route
          path="/StockDetails"
          element={isAuthenticated ? <StockDetails /> : <Navigate to="/login" />}
        />
        <Route path="/" element={<Navigate to="/login" />} />
      </Routes>
    </Router>
  );
};

export default App;
