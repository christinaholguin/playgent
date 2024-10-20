<template>
  <div class="athlete-detail" v-if="athlete">
    <h2>{{ athlete.name }}</h2>
    <p><strong>Current Team:</strong> {{ getCurrentTeamName(athlete.currentTeamId) }}</p>
    <p><strong>Date of Birth:</strong> {{ formatDate(athlete.dob) }}</p>
    <p><strong>Height:</strong> {{ athlete.height }} m</p>
    <p><strong>Nationality:</strong> {{ athlete.nationality }}</p>
    <p><strong>Position:</strong> {{ athlete.positionPlayed }}</p>
    <p><strong>Contract Expiration:</strong> {{ formatDate(athlete.currentContractExpiration) }}</p>
    <p><strong>Visa Status:</strong> {{ athlete.visa }}</p>
  </div>
  <div v-else>
    <p>Loading athlete details...</p>
  </div>
</template>

<script>
export default {
computed: {
  athlete() {
    return this.$store.state.athletes.find((athlete) => athlete.athleteId == this.$route.params.athleteId);
  },
},
isAdmin() {
      return this.$store.state.user && 
        this.$store.state.user.authoritiesString &&
        this.$store.state.user.authoritiesString.includes("ROLE_ADMIN");
    },
methods: {
    formatDate(dateString) {
      const options = { year: "numeric", month: "long", day: "numeric" };
      return dateString ? new Date(dateString).toLocaleDateString(undefined, options) : "N/A";
    },
    getCurrentTeamName(teamId) {
      const team = this.$store.state.teams.find((team) => team.teamId === teamId);
      return team ? team.teamName : "Unknown Team";
    },
  },
};
</script>

<style>
.athlete-detail {
padding: 20px;
background-color: #ad0404;
border: 1px solid #ccc;
}
</style>
