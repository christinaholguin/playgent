<template>
    <div>
      <h1>Create Athlete Account</h1>
      <form @submit.prevent="submitForm">
        <div>
          <label for="athleteName">Athlete Name:</label>
          <input type="text" v-model="athleteName" required />
        </div>
        <div>
          <label for="dob">Date of Birth:</label>
          <input type="date" v-model="dob" required />
        </div>
        <button type="submit">Register Athlete</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        athleteName: '',
        dob: ''
      };
    },
    methods: {
      submitForm() {
        // Add logic to submit athlete account creation (e.g., API call)
        console.log("Athlete account created:", this.athleteName, this.dob);
      }
    }
  };
  </script>
  