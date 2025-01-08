// src/components/Login.js
import React, { useState } from "react";
import { login } from "../services/api";
import { useNavigate } from "react-router-dom";


const Login = ({ setAuth }) => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await login(formData);
      console.log(response)
      setSuccessMessage("Login successful!");
      setErrorMessage("");
      console.log(response)
      console.log("JWT Token:", response.data.token); // Save token in localStorage if needed
      localStorage.setItem("authToken", response.data.token); // Save token for future requests
      setAuth(true);
    // Redirect to StockOrderForm
    navigate("/StockOrderForm");} 
    
    catch (error) {
      console.log(error)
      setErrorMessage("Invalid credentials. Please try again.");
      setSuccessMessage("");
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Login</button>
      </form>
      {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
      {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}
    </div>
  );
};

export default Login;
