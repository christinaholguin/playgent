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
        <button type="submit" :disabled="isSubmitting">
        {{ isSubmitting ? 'Submitting...' : 'Register Agent' }}
      </button>
    </form>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
  </template>
  
  <script>
 import { resourceService } from "../services/ResourceService.js";

export default {
  data() {
    return {
      agentName: '',
      yearsOfExperience: '',
      teamAssociations: '',
      isSubmitting: false,  // To track submission state
      errorMessage: '',     // For error feedback
    };
  },
  methods: {
    async submitAgent() {
      this.isSubmitting = true;
      this.errorMessage = '';  // Clear previous errors

      try {
        const agentData = {
          agencyName: this.agentName,
          yearsOfExperience: this.yearsOfExperience,
          teamAssociations: this.teamAssociations,
        };

        const response = await resourceService.createAgency(agentData);

        console.log("Agent account created:", response.data);
        alert("Agent account created successfully!");

        this.resetForm();
      } catch (error) {
        console.error("Error creating agent account:", error);
        this.errorMessage = "Failed to create agent account. Please try again.";
      } finally {
        this.isSubmitting = false;  // Reset submitting state
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

<style scoped>
.error {
  color: red;
}
</style>