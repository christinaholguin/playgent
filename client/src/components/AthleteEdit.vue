<template>
    <div id="edit-div">
      <form v-on:submit.stop.prevent="submitAthlete">
        <div>
          <label for="athleteName">Name</label>
          <input
            type="text"
            id="athleteName"
            name="athleteName"
            v-model="athlete.name"
          />
        </div>
  
        <div>
          <label for="dob">Date of Birth</label>
          <input
            type="date"
            id="dob"
            name="dob"
            v-model="athlete.dob"
          />
        </div>
  
        <div>
          <label for="height">Height (meters)</label>
          <input
            type="number"
            id="height"
            name="height"
            step="0.01"
            v-model="athlete.height"
          />
        </div>
  
        <div>
          <label for="nationality">Nationality</label>
          <input
            type="text"
            id="nationality"
            name="nationality"
            v-model="athlete.nationality"
          />
        </div>
  
        <div>
          <label for="visa">Visa</label>
          <select id="visa" v-model="athlete.visa">
            <option value="Yes">Yes</option>
            <option value="No">No</option>
          </select>
        </div>
  
        <div>
          <label for="positionPlayed">Position Played</label>
          <input
            type="text"
            id="positionPlayed"
            name="positionPlayed"
            v-model="athlete.positionPlayed"
          />
        </div>
  
        <div>
          <label for="currentTeam">Current Team</label>
          <select
            id="currentTeam"
            name="currentTeam"
            v-model="athlete.currentTeamId"
          >
            <option
              v-for="team in $store.state.teams"
              :key="team.id"
              :value="team.id"
            >
              {{ team.name }}
            </option>
          </select>
        </div>
  
        <div>
          <label for="contractExpiration">Contract Expiration</label>
          <input
            type="date"
            id="contractExpiration"
            name="contractExpiration"
            v-model="athlete.currentContractExpiration"
          />
        </div>
  
        <div>
          <label for="residingCountry">Residing Country</label>
          <select
            id="residingCountry"
            name="residingCountry"
            v-model="athlete.residingCountryId"
          >
            <option
              v-for="country in $store.state.countries"
              :key="country.id"
              :value="country.id"
            >
              {{ country.name }}
            </option>
          </select>
        </div>
  
        <div class="button-container">
          <button @click="cancel">Cancel</button>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  </template>
  
  <script>
  import { resourceService } from "../services/ResourceService.js";
  
  export default {
    props: ["athleteId"],
    data() {
      return {
        athlete: {
          name: "",
          dob: "",
          height: "",
          nationality: "",
          visa: "No",
          positionPlayed: "",
          currentTeamId: null,
          currentContractExpiration: "",
          residingCountryId: null,
        },
      };
    },
    methods: {
      submitAthlete() {
        if (this.athleteId) {
          // Update existing athlete
          resourceService.updateAthlete(this.athleteId, this.athlete).then(() => {
            this.$router.push({ name: "home" });
          }).catch((error) => {
            console.error(error);
          });
        } else {
          // Add a new athlete
          resourceService.addAthlete(this.athlete).then(() => {
            this.$router.push({ name: "home" });
          }).catch((error) => {
            console.error(error);
          });
        }
      },
      cancel() {
        this.$router.push({ name: "home" });
      },
    },
    created() {
      if (this.athleteId) {
        this.athlete = this.$store.state.athletes.find((athlete) => {
          return athlete.id == this.athleteId;
        });
      }
    },
  };
  </script>
  
  <style>
  #edit-div {
    height: 100%;
    overflow-y: auto;
  }
  </style>
  