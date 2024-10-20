<template>
  <section id="brands">
    <h2>Sports Brands</h2>
    <LoadingSpinner v-if="loading" :spin="loading" />
    <p v-if="!loading && brands.length === 0">No brands found.</p>
    <div v-if="brands.length">
      <div class="brand-display">
        <button @click="prevBrand" class="nav-button" :disabled="currentIndex === 0">&lt;</button>
        <div class="brand-card">
          <h3>{{ currentBrand.name }}</h3>
          <p>{{ currentBrand.description }}</p>
        </div>
        <button @click="nextBrand" class="nav-button" :disabled="currentIndex === brands.length - 1">&gt;</button>
      </div>
    </div>
  </section>
</template>

<script>
import LoadingSpinner from './LoadingSpinner.vue'; 

export default {
  components: {
    LoadingSpinner,
  },
  data() {
    return {
      currentIndex: 0, // Track the currently displayed brand
      loading: false,
      brands: [
        {
          id: 1,
          name: 'ADIDAS',
          image: 'https://via.placeholder.com/100?text=Adidas',
          description: 'TRAILBLAZER in sportswear, merging cutting-edge technology with stylish design. Known for its iconic three stripes, Adidas empowers athletes to perform at their best while making a bold fashion statement. With a commitment to sustainability and innovation, the brand inspires individuals to "Unleash Your Potential." Whether on the field or in everyday life, Adidas is the ultimate choice for those who strive for excellence.',
        },
        {
          id: 2,
          name: 'NIKE',
          image: 'https://via.placeholder.com/100?text=Nike',
          description: 'JUST DO IT. A powerhouse in athletic footwear and apparel. Renowned for its innovative designs and cutting-edge technology, Nike embodies the spirit of competition and personal achievement. The iconic Swoosh logo symbolizes movement, speed, and agility, inspiring athletes to push their limits. Whether youre training for a marathon or seeking everyday comfort, Nike empowers you to achieve greatness and embrace your athletic journey',
        },
        {
          id: 3,
          name: 'PUMA',
          image: 'https://via.placeholder.com/100?text=Puma',
          description: 'Puma is a German multinational company that designs and manufactures athletic and casual footwear, apparel, and accessories.',
        },
        {
          id: 4,
          name: 'ELECTROLIT',
          image: 'https://via.placeholder.com/100?text=Electrolit',
          description: 'Electrolit is a brand of electrolyte solution that helps with hydration.',
        },
        {
          id: 5,
          name: 'HYPERICE',
          image: 'https://via.placeholder.com/100?text=Hyperice',
          description: 'Hyperice develops recovery and movement enhancement technology for athletes.',
        },
        {
          id: 6,
          name: 'VITAMIN WATER',
          image: 'https://via.placeholder.com/100?text=Vitamin+Water',
          description: 'Vitamin Water is a brand of flavored water beverages with added vitamins and minerals.',
        },
      ],
    };
  },
  computed: {
    currentBrand() {
      return this.brands[this.currentIndex]; // Get the currently displayed brand
    },
  },
  methods: {
    nextBrand() {
      if (this.currentIndex < this.brands.length - 1) {
        this.currentIndex++;
      }
    },
    prevBrand() {
      if (this.currentIndex > 0) {
        this.currentIndex--;
      }
    },
  },
};
</script>

<style>
#brands {
    padding: 20px;
    background-color: #8d0707;
}

.brand-display {
  display: flex;
  align-items: center; /* Center items vertically */
}

.brand-card {
  display: flex; /* Use flexbox to align image and text */
  align-items: center; /* Center items vertically */
  background-color: white; /* Background for brand cards */
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  margin: 0 20px; /* Space between buttons and card */
  min-width: 350px; /* Minimum width for cards */
}
.brand-image {
  width: 100px; /* Set a fixed width for the image */
  height: auto; /* Maintain aspect ratio */
  margin-right: 20px; /* Space between image and text */
}
.nav-button {
  background-color: #333; /* Background for navigation buttons */
  color: white; /* Text color */
  border: none; /* Remove border */
  border-radius: 5px; /* Rounded corners */
  padding: 10px; /* Padding for button */
  cursor: pointer; /* Pointer cursor on hover */
  font-size: 1.5em; /* Increase font size */
}

.nav-button:disabled {
  background-color: #aaa; /* Color for disabled buttons */
  cursor: not-allowed; /* Change cursor to indicate it's disabled */
}
</style>
