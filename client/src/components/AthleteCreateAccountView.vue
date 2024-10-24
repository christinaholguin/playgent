<template>
    <div>
      <h1>Create Athlete Account</h1>
      <form v-on:submit.stop.prevent="submitForm">
        <div>
          <label for="athleteName">Athlete Name:</label>
          <input type="text" v-model="athleteName" required />
        </div>
        <div>
          <label for="dob">Date of Birth:</label>
          <input type="date" v-model="dob" required />
        </div>
        <button type="submit" :disabled="isSubmitting">
        {{ isSubmitting ? 'Submitting...' : 'Register Athlete' }}
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
      athleteName: '',
      dob: '',
      isSubmitting: false,  // tracking submission state
      errorMessage: '',     //  error messages if submitting goes wrong
    };
  },
  methods: {
    async submitAthlete() {
      this.isSubmitting = true;
      this.errorMessage = '';  // Reset previous error messages

      try {
        // Prepare athlete data
        const athleteData = {
          name: this.athleteName,
          dob: this.dob,
        };

        // Calling API to create an athlete account
        const response = await resourceService.createAthlete(athleteData);

        // Handle successful creation
        console.log("Athlete account created:", response.data);
        alert("Athlete account created successfully!");

        //  reset the form
        this.resetForm();
      } catch (error) {
        console.error("Error creating athlete account:", error);
        this.errorMessage = "Failed to create athlete account. Please try again.";
      } finally {
        this.isSubmitting = false;  // Reset the loading state
      }
    },
    resetForm() {
      this.athleteName = '';
      this.dob = '';
    }
  }
};
</script>

<style scoped>
.error {
  color: red;
}
</style>