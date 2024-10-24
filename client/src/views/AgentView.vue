<template>
       <div class="agent-view">
    <h1>AGENCIES</h1>

    <div class="agent-container">
      <div class="agent-card" v-for="agent in agents" :key="agent.agent_id">
        <strong>{{ agent.agencyName }}</strong><br>
        <p><strong>Years of Experience:</strong> {{ agent.yearsOfExperience }}</p>
        <p><strong>Players Currently Managing:</strong> {{ agent.playersCurrentlyRepresenting }}</p>
        <p><strong>Team Associations:</strong> {{ agent.teamAssociations }}</p>
        <p><strong>Overall Rating:</strong> {{ agent.rating }}</p>
      </div>
    </div>

    <!-- No agents available message -->
    <p v-if="!loading && agents.length === 0">No agents available.</p>

    <LoadingSpinner :spin="loading" />
  </div>
</template>

<script>
import { mapState } from 'vuex';
import LoadingSpinner from '../components/LoadingSpinner.vue';

export default {
  components: {
    LoadingSpinner,
  },
  computed: {
    ...mapState({
      agents: (state) => state.agents,  // Map agents from Vuex state
      loading: (state) => state.loading, // Map loading from Vuex state
    }),
  },
  created() {
    // Fetch agents from the Vuex store
    this.$store.dispatch('fetchAgents');
  },
};
</script>

<style scoped>
.agent-view {
  padding: 20px;
  background-color:  #ad0404;
  border: 1px solid #ccc
}.agent-container {
  display: flex; 
  overflow-x: auto; 
  scroll-snap-type: x mandatory; 
  padding: 10px 0; 
}

.agent-card {
  background-color: white; 
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  margin-right: 10px; 
  min-width: 250px; 
  scroll-snap-align: start; 
  flex: 0 0 auto; 
}

.agent-card strong {
  display: block; 
  margin-bottom: 5px; 
}

.agent-card p {
  margin: 0; 
}
</style>