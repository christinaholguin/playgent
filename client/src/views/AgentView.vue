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
  display: flex; /* Display agents in a row */
  overflow-x: auto; /* Enable horizontal scrolling */
  scroll-snap-type: x mandatory; /* Enable scroll snapping */
  padding: 10px 0; /* Optional padding */
}

.agent-card {
  background-color: white; /* Background for agent cards */
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  margin-right: 10px; /* Space between cards */
  min-width: 250px; /* Minimum width for cards */
  scroll-snap-align: start; /* Align cards to the start when scrolling */
  flex: 0 0 auto; /* Prevent cards from growing */
}

.agent-card strong {
  display: block; /* Display name in a block */
  margin-bottom: 5px; /* Space below name */
}

.agent-card p {
  margin: 0; /* Reset margin for paragraphs */
}
</style>