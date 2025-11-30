import axios from 'axios';

// Configuração base da API
const api = axios.create({
  baseURL: 'http://localhost:8081/api', // A porta do seu Spring Boot
});

/**
 * Função genérica para buscar dados
 * @param {string} endpoint - O endpoint da API (ex: '/funcionarios')
 * @returns {Promise<Array>} - Uma promessa que resolve para a lista de itens
 */
export const getItens = async (endpoint) => {
  const response = await api.get(endpoint);
  // A API de paginação retorna os dados em 'content'
  if (response.data && typeof response.data.content !== 'undefined') {
    return response.data.content;
  }
  // Retorno padrão para APIs não paginadas
  return response.data;
};

/**
 * Função genérica para criar um item
 * @param {string} endpoint - O endpoint da API
 * @param {object} data - O objeto a ser criado
 * @returns {Promise<object>} - O item criado
 */
export const createItem = async (endpoint, data) => {
  const response = await api.post(endpoint, data);
  return response.data;
};

/**
 * Função genérica para atualizar um item
 * @param {string} endpoint - O endpoint da API
 * @param {number|string} id - O ID do item
 * @param {object} data - O objeto com as atualizações
 * @returns {Promise<object>} - O item atualizado
 */
export const updateItem = async (endpoint, id, data) => {
  const response = await api.put(`${endpoint}/${id}`, data);
  return response.data;
};

/**
 * Função genérica para excluir um item
 * @param {string} endpoint - O endpoint da API
 * @param {number|string} id - O ID do item
 * @returns {Promise<void>}
 */
export const deleteItem = async (endpoint, id) => {
  await api.delete(`${endpoint}/${id}`);
};