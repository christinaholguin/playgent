<template>
  <div>
    <div id="home" v-if="isLoading">
      <loading-spinner id="spinner" :spin="true" />
    </div>
    <div id="main-div" v-else>
      <!-- Conditional rendering based on flags -->
      <div v-if="showAgentList" class="agent-list">
        <agent-list />
      </div>
      <div v-if="showAthleteList" class="athlete-table">
        <athlete-table />
      </div>
      <div v-if="showBrandList" class="brand-list">
        <brand-list />
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import AgentList from '../components/AgentList.vue';
import AthleteTable from '../components/AthleteTable.vue';
import BrandList from '../components/BrandList.vue';
import LoadingSpinner from '../components/LoadingSpinner.vue';
import { resourceService } from '../services/ResourceService.js';

export default {
  components: { AgentList, AthleteTable, BrandList, LoadingSpinner },

  data() {
    return {
      isLoading: true,
      showAgentList: false,
      showAthleteList: false,
      showBrandList: false,
    };
  },

  computed: {
    ...mapState({
      agents: (state) => state.agents,
      athletes: (state) => state.athletes,
      brands: (state) => state.brands,
    }),
  },

  created() {
    this.isLoading = true;

    // Fetch data from the service and commit to Vuex
    Promise.all([
      resourceService.getAgents(),
      resourceService.getAthletes(),
      resourceService.getBrands(),
    ])
      .then(([agentResponse, athleteResponse, brandResponse]) => {
        this.$store.commit('SET_AGENTS', agentResponse.data);
        this.$store.commit('SET_ATHLETES', athleteResponse.data);
        this.$store.commit('SET_BRANDS', brandResponse.data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      })
      .finally(() => {
        this.isLoading = false;
      });
  },

  methods: {
    showAgents() {
      this.showAgentList = true;
      this.showAthleteList = false;
      this.showBrandList = false;
    },
    showAthletes() {
      this.showAgentList = false;
      this.showAthleteList = true;
      this.showBrandList = false;
    },
    showBrands() {
      this.showAgentList = false;
      this.showAthleteList = false;
      this.showBrandList = true;
    },
  },
};
</script>

<style>
#main-div {
  grid-area: main;
  display: grid;
  grid-template-columns: 1fr 2fr;
  grid-template-areas:
    "agents athletes";
  gap: 20px; 
  padding-bottom: 50px;
  background-color: transparent;
}

.agent-list {
  grid-area: agents;
}

.athlete-table {
  grid-area: athletes;
}

.brand-list {
  
}

@media (max-width: 425px) {
  #main-div {
    grid-template-columns: 1fr; 
    grid-template-areas:
      "agents"
      "athletes";
  }
}
</style>