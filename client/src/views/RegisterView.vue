<template>
  <div id="register">
    <!-- Main Registration Type Popup -->
    <div v-if="showTypeModal" class="overlay" @click="closeTypeModal"></div>
    <div v-if="showTypeModal" class="modal">
      <h1>Register as an:</h1>
      <div id="role-buttons">
        <button @click="openCreateAccount('agent')">AGENT</button>
        <button @click="openCreateAccount('athlete')">ATHLETE</button>
        <button v-if="isAdmin" @click="openCreateAccount('brand')">BRAND</button>
      </div>
      <button @click="closeTypeModal">Close</button>
    </div>

    <!-- Create Account Popup -->
    <div v-if="showModal" class="overlay" @click="closeModal"></div>
    <div v-if="showModal" class="modal">
      <component :is="`${accountType}CreateAccountView`"></component>
      <button @click="closeModal">Close</button>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import AgentCreateAccountView from '../components/AgentCreateAccountView.vue';
import AthleteCreateAccountView from '../components/AthleteCreateAccountView.vue';
// import BrandCreateAccountView from './BrandCreateAccountView.vue';

export default {
  components: {
    AgentCreateAccountView,
    AthleteCreateAccountView,
    // BrandCreateAccountView,
  },
  data() {
    return {
      user: {
        username: "",
        name: "",
        password: "",
        confirmPassword: "",
        address: "",
        city: "",
        stateCode: "",
        zip: "",
        role: "user",
      },
      showModal: false,
      showTypeModal: false, // For the main registration type selection
      accountType: '', // To hold the selected account type
    };
  },
  computed: {
    isAdmin() {
      return this.$store.state.user && this.$store.state.user.role === 'admin';
    },
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    closeModal() { 
      this.showModal = false; 
    },
    closeTypeModal() {
      this.showTypeModal = false; 
    },
    openCreateAccount(type) {
      this.accountType = type; 
      this.showModal = true; 
      this.closeTypeModal(); 
    },
    goBackToHome() {
      this.$router.push('/home');
    },
    register() {
      if (this.user.password !== this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.success("Thank you for registering, please sign in.");
              this.closeModal();
              this.$router.push({
                path: "/login",
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}':${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 1000;
}
.modal {
  z-index: 2000;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  padding: 20px;
  z-index: 1001;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  border-radius: 8px;
  max-width: 400px;
  width: 100%;
  max-height: 80vh; 
  overflow-y: auto;
}
#register {
  display: flex;
  flex-direction: column;
}
#role-buttons {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
button {
  background-color: #4CAF50;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin: 5px;
}
button:hover {
  background-color: #45a049;
}
</style>
