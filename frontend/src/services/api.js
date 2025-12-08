import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8081/api',
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && (error.response.status === 403 || error.response.status === 401)) {
      localStorage.clear();
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export const loginUser = async (data) => {
  const response = await axios.post('http://localhost:8081/auth/login', data);
  return response.data;
};

export const registerUser = async (data) => {
  const response = await axios.post('http://localhost:8081/auth/register', data);
  return response.data;
};

export const getItens = async (endpoint) => {
  const response = await api.get(endpoint);
  if (response.data && typeof response.data.content !== 'undefined') {
    return response.data.content;
  }
  return response.data;
};

export const createItem = async (endpoint, data) => {
  const response = await api.post(endpoint, data);
  return response.data;
};

export const updateItem = async (endpoint, id, data) => {
  const response = await api.put(`${endpoint}/${id}`, data);
  return response.data;
};

export const deleteItem = async (endpoint, id) => {
  await api.delete(`${endpoint}/${id}`);
};

export default api;