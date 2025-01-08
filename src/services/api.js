import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth';  // Make sure this is correct based on your API

// Login function to authenticate user and return JWT token
export const login = async ({ username, password }) => {
  const response = await axios.post(`${API_URL}/login`, { username, password });
  return response;  // Assuming the API returns a token
};

// Register function to register a new user
export const register = async ({ username, password }) => {
  const response = await axios.post(`${API_URL}/register`, { username, password });
  return response;  // This might be the user object or a success message
};

// You can also add other API functions like buyStock, sellStock here if needed
// Example for buyStock and sellStock:
export const buyStock = async (orderRequest) => {
  // Implement buyStock functionality here
  // For example:
  const response = await axios.post('http://localhost:8080/api/orders/buy', orderRequest);
  return response.data;
};

export const sellStock = async (orderRequest) => {
  // Implement sellStock functionality here
  // For example:
  const response = await axios.post('http://localhost:8080/api/orders/sell', orderRequest);
  return response.data;
};
