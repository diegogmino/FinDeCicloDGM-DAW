import axios from 'axios';
  
const film = axios.create({
  baseURL: 'http://localhost:8080/peliculas/',
});

const filmsApi = {

  get: async id => { 
    const response = await film.get(`buscar/${id}`); 
    return response.data;
  },

  getFiltered: async (filters, page, size) => { 
    let filterString = 'filtrar?';
    Object.keys(filters).forEach(
      key => {
        if(filters[key] !== '') {
          filterString += key + '=' + filters[key] + '&';
        }
      }
    );
    filterString += 'page=' + page + '&size=' + size;
    const response = await film.get(`${filterString}`); 
    return response.data;
  },
};

export default filmsApi;