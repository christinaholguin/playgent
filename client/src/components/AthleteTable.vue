<template>
  <section id="athletes">
    <h2>ATHLETES</h2>
    <LoadingSpinner :spin="loading" v-if="loading" />
    <table id="athlete-table" v-if="!loading && athletes.length > 0">
      <thead>
        <tr>
          <th>Name</th>
          <th>Position</th>
          <!-- <th>Team</th> -->
          <th>Nationality</th>
          <th v-if="isAdmin">Actions</th> <!-- Only show actions for admins -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="athlete in athletes" :key="athlete.athleteId">
          <td>
            <div class="athlete-name">
              <div>
                <router-link
                  v-bind:to="{
                    name: 'athleteDetail', 
                    params: { athleteId: athlete.athleteId }
                  }"
                >
                  {{ athlete.name }}
                </router-link>
              </div>
              <span class="icon-container" v-if="isAdmin">
                <router-link
                  v-bind:to="{
                    name: 'editAthlete', 
                    params: { athleteId: athlete.athleteId }
                  }"
                >
                  <font-awesome-icon
                    :icon="['fas', 'edit']"
                    class="icon"
                    title="edit athlete"
                  />
                </router-link>
              </span>
            </div>
          </td>
          <td>{{ athlete.positionPlayed }}</td>
          <!-- <td>{{ getCurrentTeamName(athlete.currentTeamId) }}</td> -->
          <td>{{ athlete.nationality }}</td>
          <td v-if="isAdmin">
            <button @click="deleteAthlete(athlete.athleteId)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
    <p v-if="!loading && athletes.length === 0">No athletes available.</p>
  </section>
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
    };
  },
  computed: {
    ...mapState(['athletes']), // Using Vuex to get athletes directly
    isAdmin() {
      return (
        this.$store.state.user &&
        this.$store.state.user.authoritiesString &&
        this.$store.state.user.authoritiesString.includes('ROLE_ADMIN')
      );
    },
  },
  created() {
    this.fetchAthletes();
  },
  methods: {
    async fetchAthletes() {
      try {
        const response = await fetch('http://localhost:9000/athletes');
        const data = await response.json();
        console.log('Fetched Athletes:', data); 
        this.$store.commit('SET_ATHLETES', data); // Commit to Vuex store
      } catch (error) {
        console.error('Error fetching athletes:', error);
      } finally {
        this.loading = false; // Set loading to false when done
      }
    },
    // getCurrentTeamName(teamId) {
    //   const team = this.$store.state.teams.find((team) => {
    //     return team.teamId === teamId;
    //   });
    //   return team ? team.teamName : 'Unknown Team';
    // },
    deleteAthlete(athleteId) {
      // Logic to delete an athlete
      console.log('Delete athlete with ID:', athleteId);
      // Optionally refresh the list after deletion
    },
  },
};
</script>

<style>
#athletes {
  padding: 20px;
  background-color: #ad0404;
  border: 1px solid #ccc;
}

.hidden {
  display: none;
}

#athlete-table {
  width: 100%;
  border-collapse: collapse;
}

#athlete-table th,
#athlete-table td {
  border: 1px solid #8d0707;
  padding: 8px;
  background-color: white;
}

#athlete-table th {
  background-color: white;
}
</style>
