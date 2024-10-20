import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      agents: [],
      athletes: [],
      brands: [],
      teams: [],
      currentAgentId: 1,
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {

      SET_CURRENT_AGENT_ID(state, id) {
        state.currentAgentId = id;
      },

      SET_AGENTS(state, agents) {
        state.agents = agents;
      },

      SET_LOADING(state, loading) {
        state.loading = loading;  // Mutation to update loading state
      },

      SET_CURRENT_AGENT_DETAILS(state, agentDetails) {
        state.currentAgentDetails = agentDetails;
      },

      SET_ATHLETES(state, athletes) {
        state.athletes = athletes;
      },

      SET_TEAMS(state, teams) { // Mutation for teams
        state.teams = teams;
      },

      SET_BRANDS(state, brands) {
        state.brands = brands;
      },

      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    },
    actions: {
      async fetchAgents({ commit }) {
        commit('SET_LOADING', true);  // Set loading true while fetching
        try {
          const response = await axios.get('http://localhost:9000/agents');
          commit('SET_AGENTS', response.data);  // Commit data to Vuex state
        } catch (error) {
          console.error('Error fetching agents:', error);
        } finally {
          commit('SET_LOADING', false);  // Stop loading after fetching
        }
      },
    },
    async fetchTeams() {
  try {
    const response = await fetch('http://localhost:9000/teams');
    const teamsData = await response.json();
    console.log('Fetched Teams:', teamsData); // Log for debugging
    this.$store.commit('SET_TEAMS', teamsData); // Commit teams data to Vuex
  } catch (error) {
    console.error('Error fetching teams:', error);
  }
},
  });
  return store;
}