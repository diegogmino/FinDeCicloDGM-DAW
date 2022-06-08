import axios from "axios";

const user = axios.create({
  baseURL: "http://localhost:8080/usuarios/",
});

const usersApi = {
  login: async (email, password) => {
    const response = await user.get(`login/${email}/${password}`);
    return response.data;
  },

  signUp: async (body) => {
    const response = await user.post("guardar", body);
    return response.data;
  },

  getOrders: async (id) => {
    const response = await user.get(`buscarPedidos/${id}`);
    return response.data;
  },

  postOrder: async (films, userId) => {
    const response = await user.post(`nuevoPedido/${userId}`, films);
    return response.data;
  },

  postMessage: async (message) => {
    const response = await user.post("mailContacto", message);
    return response.data;
  },
};

export default usersApi;
