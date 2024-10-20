<template>
  <div id="playgent-app">
    <header>
      <h1>
            <img src="./assets/Playgent.jpg" alt="Playgent Logo">
      </h1>
    </header>
    <nav>

      <router-link :to="{ name: 'agents' }">
        <button>AGENCIES</button>
      </router-link>
      <router-link :to="{ name: 'athletes' }">
        <button>ATHLETES</button>
      </router-link>
      <router-link :to="{ name: 'brands' }">
        <button>BRANDS</button>
      </router-link>
      <button @click="openRegisterView" >JOIN US</button>
      <LoginView ref="loginView" />
      <router-link :to="{ name: 'logout' }">Logout</router-link>
</nav>
<main>
      <router-view />
    </main>
    <footer>
      <div class="footer-content">
        <span>&copy; 2024 All Rights Reserved</span>
        <div class="social-media">
          <a href="#" class="social-icon">
            <font-awesome-icon :icon="['fab', 'tiktok']" />
          </a>
          <a href="#" class="social-icon">
            <font-awesome-icon :icon="['fab', 'instagram']" />
          </a>
          <a href="#" class="social-icon">
            <font-awesome-icon :icon="['fab', 'twitter']" />
          </a>
          <a href="#" class="social-icon">
            <font-awesome-icon :icon="['fab', 'facebook-f']" />
          </a>
        </div>
      </div>
    </footer>
    <RegisterView ref="registerView" />
  </div>
</template>

<script>
import LoginView from './views/LoginView.vue'; // Importing LoginView component
import RegisterView from './views/RegisterView.vue'; // Import the RegistrationModal

export default {
  components: {
    LoginView,
    RegisterView
  },
  methods: {
    showLoginModal() {
      this.$refs.loginView.showModal = true; // Open modal
    },
    closeLoginModal() {
      this.$refs.loginView.showModal = false; // Close modal
    },
    openRegisterView() {
      this.$refs.registerView.showTypeModal = true; // Open registration type selection modal
    }
  }
};
</script>


<style>
html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
  background-color: #333;
}

#playgent-app {
  font-family: 'Helvetica Neue', sans-serif;
  background-color: #333;
  display: grid;
  grid-template-rows: auto 1fr auto;
  grid-template-areas:
    "header"
    "main"
    "footer";
  width: 100vw;
  max-width: 1200px;
  margin: 0 auto;
  overflow-y: auto;
}

header {
  grid-area: header;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #333;
}

header h1 {
  margin: 0;
}

nav {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 15px;
  margin-top: 20px;
  margin-bottom: 10px;
}
.logout-button {
  background-color: red;
}
nav button {
  font-size: 1.2em;
  background-color: #333;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  transition: transform 0.3s ease, background-color 0.3s ease;
  width: 200px;
}

nav button:hover {
  background-color: #a00;
  transform: scale(1.05);
}

main {
  grid-area: main;
  display: grid;
  gap: 20px;
  padding: 20px;
  background-color: transparent;
  overflow-y: auto;
}

footer {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  background-color: transparent;
  width: 100%;
}

.footer-content {
  color: white;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  margin: auto;
}

.social-media {
  display: flex;
  justify-content: center;
  gap: 8px;
}

.social-icon {
  color: #0f35f3;
  margin: 10px;
  text-decoration: none;
  font-size: 20px;
}

.social-icon:hover {
  color: #555;
}

@media (max-width: 425px) {
  body {
    font-size: 14px;
  }

  header h1 {
    font-size: 2em;
  }

  main {
    grid-template-columns: 1fr;
  }

  nav {
    flex-direction: column;
    align-items: center;
  }

  .footer-content {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }

  .social-media {
    margin-top: 10px;
  }
}
</style>