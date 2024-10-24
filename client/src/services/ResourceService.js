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
    createAgency(agent) {
      return axios.post('/agents', agent);
    },
    createAthlete(athlete) {
      return axios.post('/athletes', athlete);
    },
    updateAgent(agentId, agent){
      return axios.put(`/agents/${agentId}`, agent);
    }
  // getBrands(){
  //   return axios.get('/brands');
  // }
};

export { resourceService };
