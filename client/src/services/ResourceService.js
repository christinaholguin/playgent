import axios from 'axios'
const API_URL = '/localhost:9000';
const resourceService = {
  getCountries() {
    return axios.get('/countries');
  },
  getLeagues(){
    return axios.get('/leagues');
  },
  getTeams(){
    return axios.get('/teams');
  },
  getAthletes(){
    return axios.get('/athletes');
  },
  getAgents(){
    return axios.get('/agents');
  },
  createAgency(agentData) {  // Add this method
    return axios.post(API_URL, agentData);
  },
  getBrands(){
    return axios.get('/brands');
  }
};

export { resourceService };
