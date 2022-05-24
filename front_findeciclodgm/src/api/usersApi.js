import axios from 'axios';
  
const user = axios.create({
  baseURL: 'http://localhost:8080/usuarios/',
});

const usersApi = {

  login: async (email, password) => { 
    const response = await user.get(`login/${email}/${password}`); 
    return response.data;
  },

  signUp: async (body) => {
    const response = await user.post('guardar', body);
    return response.data;
  },

}  

export default usersApi;