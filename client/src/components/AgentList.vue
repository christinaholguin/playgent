<template>
  <div id="agents">
    <h2>Agents</h2>
    <LoadingSpinner :spin="loading" v-if="loading" />

    <!-- Display current agent details -->
    <div v-if="!loading && agents.length > 0" class="agent-card">
      <h3>{{ currentAgent.agencyName }} (Rating: {{ currentAgent.rating }})</h3>
      <p><strong>Years of Experience:</strong> {{ currentAgent.yearsOfExperience }}</p>
      <p><strong>Players:</strong> {{ currentAgent.playersCurrentlyRepresenting.join(', ') }}</p>
      <p><strong>Teams:</strong> {{ currentAgent.teamAssociations.join(', ') }}</p>

      <div class="navigation">
        <button @click="prevAgent" :disabled="isFirstAgent">← Previous</button>
        <button @click="nextAgent" :disabled="isLastAgent">Next →</button>
      </div>
    </div>

    <p v-if="!loading && agents.length === 0">No agents available.</p>
  </div>
</template>

<script>
import LoadingSpinner from './LoadingSpinner.vue';
import { mapState } from 'vuex';

export default {
  components: {
    LoadingSpinner,
  },
  data() {
    return {
      loading: true,
      currentIndex: 0, // Track the index of the currently displayed agent
    };
  },
  computed: {
    ...mapState({
      agents: (state) => state.agents, // Get agents from Vuex state
    }),
    currentAgent() {
      return this.agents[this.currentIndex];
    },
    isFirstAgent() {
      return this.currentIndex === 0;
    },
    isLastAgent() {
      return this.currentIndex === this.agents.length - 1;
    },
  },
  created() {
    this.fetchAgents();
  },
  methods: {
    async fetchAgents() {
      try {
        const response = await fetch('http://localhost:9000/agents');
        const data = await response.json();
        this.$store.commit('SET_AGENTS', data); // Commit to Vuex store
      } catch (error) {
        console.error('Error fetching agents:', error);
      } finally {
        this.loading = false;
      }
    },
    nextAgent() {
      if (this.currentIndex < this.agents.length - 1) {
        this.currentIndex++;
      }
    },
    prevAgent() {
      if (this.currentIndex > 0) {
        this.currentIndex--;
      }
    },
  },
};
</script>
<style>
#agents {
  grid-area: agents;
  border-right: 1px solid black;
  border-bottom: none;
}

.agent-card {
  background-color: white;
  padding: 15px;
  border-radius: 5px;
  margin-bottom: 20px;
}

.navigation {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
}

button {
  padding: 10px 20px;
  background-color: #8d0707;
  color: white;
  border: none;
  cursor: pointer;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

button:hover:not(:disabled) {
  background-color: #6b0505;
}

h2 {
  margin-top: 0;
  margin-bottom: 0;
  background-color: slategray;
  color: white;
}
</style>
