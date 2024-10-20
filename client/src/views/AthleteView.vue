<template>
  <div>
    <AthleteTable />
  </div>
</template>

<script>
import AthleteTable from '../components/AthleteTable.vue';

export default {
  components: {
    AthleteTable
  },
};
</script>


<!-- <style>
.athlete-card {
  display: flex;
  flex-direction: column; 
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin: 20px;
  padding: 20px;
  transition: transform 0.2s; 
}

.athlete-card:hover {
  transform: translateY(-5px); 
}

.athlete-name {
  font-size: 1.5em;
  margin-bottom: 10px;
}

.athlete-info {
  margin-bottom: 20px; 
}

.athlete-image {
  display: flex; 
  justify-content: center; 
  margin-top: 10px; 
}

.athlete-image img {
  max-width: 100%; 
  height: auto; 
  border-radius: 8px;
}

@media (max-width: 768px) {
  .athlete-card {
    text-align: center; 
  }
}
</style> -->
