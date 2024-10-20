<template>
  <div id="agents">
    <h2>Agents</h2>
    <!-- Display loading spinner if data is still being fetched -->
    <LoadingSpinner :spin="loading" v-if="loading" />
    
    <!-- Display agents once they are loaded -->
    <ul v-if="!loading && agents.length > 0">
      <li v-for="agent in agents" :key="agent.agentId" @click="selectAgent(agent.agentId)">
        {{ agent.agencyName }} (Rating: {{ agent.rating }})
        <br />
        Years of Experience: {{ agent.yearsOfExperience }}
        <br />
        Players: {{ agent.playersCurrentlyRepresenting.join(', ') }}
        <br />
        Teams: {{ agent.teamAssociations.join(', ') }}
      </li>
    </ul>
    
    <!-- Message if no agents are available -->
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
      loading: true, // Start with loading state
    };
  },
  computed: {
    ...mapState({
      agents: (state) => state.agents, // Get agents from Vuex state
    }),
  },
  created() {
    this.fetchAgents();
  },
  methods: {
      async fetchAgents() {
      try {
        const response = await fetch('http://localhost:9000/agents');
        const data = await response.json();
        this.agents = data;
      } catch (error) {
        console.error('Error fetching agents:', error);
      } finally {
        this.loading = false;
      }
    },
    selectAgent(agentId) {
      this.$emit('select-agent', agentId); // Emit event to parent for agent selection
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

#agents ul {
  margin-top: 2px;
  list-style: none;
  padding: 2px;
}

#agents li {
  cursor: pointer;
}

#agents li:hover {
  background-color: lightsteelblue;
}

#agents h2 {
  margin-top: 0;
  margin-bottom: 0;
  background-color: slategray;
  color: white;
}

.agent-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.icon {
  padding: 2px;
  width: 1em;
  cursor: pointer;
}

.selected {
  background-color: steelblue;
  color: white;
}
</style>
