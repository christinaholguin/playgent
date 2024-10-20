<template>
    <div>
      <h1>Create Agency Account</h1>
      <form v-on:submit.stop.prevent="submitAgent">
        <div>
          <label for="agentName">Agency Name:</label>
          <input type="text" v-model="agentName" required />
        </div>
        <div>
          <label for="yearsOfExperience">Years of Experience:</label>
          <input type="text" v-model="yearsOfExperience" required />
        </div>
        <div>
          <label for="teamAssociations">Team Associations:</label>
          <input type="text" v-model="teamAssociations" required />
        </div>
        <button type="submit">Register Agent</button>

        <div class="button-container">
        <!-- <button type="submit">Submit</button> -->
      </div>
      </form>
    </div>
  </template>
  
  <script>
 import { resourceService } from "../services/ResourceService.js";

export default {
  data() {
    return {
      agentName: '',
      yearsOfExperience: '',
      teamAssociations: ''
    };
  },
  methods: {
    async submitAgent() {
      try {
        // Prepare the data to send
        const agentData = {
          agencyName: this.agentName,
          yearsOfExperience: this.yearsOfExperience,
          teamAssociations: this.teamAssociations,
        };

        // Call the API to create an agency
        const response = await resourceService.createAgency(agentData);
        
        // Handle the response as needed
        console.log("Agent account created:", response.data);
        alert("Agent account created successfully!");
        
        // Optionally, reset the form
        this.resetForm();
      } catch (error) {
        console.error("Error creating agent account:", error);
        alert("Failed to create agent account.");
      }
    },
    resetForm() {
      this.agentName = '';
      this.yearsOfExperience = '';
      this.teamAssociations = '';
    }
  }
};
</script>