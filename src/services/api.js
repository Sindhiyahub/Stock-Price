import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth';  // Make sure this is correct based on your API

// Login function to authenticate user and return JWT token
export const login = async ({ username, password }) => {
  const response = await axios.post(`${API_URL}/login`, { username, password });
  const token = response.data.token;  // Assuming the token is in the response data
  localStorage.setItem('authToken', token);  // Store token in localStorage
  return response;
};

// Register function to register a new user
export const register = async ({ username, password }) => {
  const response = await axios.post(`${API_URL}/register`, { username, password });
  return response;  // This might be the user object or a success message
};

// Buy Stock Function
export const buyStock = async (orderRequest) => {
  const jwtToken = localStorage.getItem('authToken');
  if (!jwtToken) {
    throw new Error('User not authenticated. Please log in.');
  }

  const response = await axios.post('http://localhost:8080/api/orders/buy', orderRequest, {
    headers: {
      Authorization: `Bearer ${jwtToken}`,
    },
  });

  return response.data;
};

// Sell Stock Function
export const sellStock = async (orderRequest) => {
  const jwtToken = localStorage.getItem('authToken');
  if (!jwtToken) {
    throw new Error('User not authenticated. Please log in.');
  }

  const response = await axios.post('http://localhost:8080/api/orders/sell', orderRequest, {
    headers: {
      Authorization: `Bearer ${jwtToken}`,
    },
  });

  return response.data;
};
