<template>
    <athlete-edit :athleteId="$route.params.athleteId"/>
  </template>
  
  <script>
  import AthleteEdit from '../components/AthleteEdit.vue';
  
  export default {
      components: { AthleteEdit },
  };
  </script>
  
  <style>
  </style>
  