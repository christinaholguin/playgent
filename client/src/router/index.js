import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import RegisterView from '../views/RegisterView.vue'
import AgentView from '../views/AgentView.vue'
import AthleteView from '../views/AthleteView.vue';
import BrandView from '../views/BrandView.vue';
import AthleteDetail from '../components/AthleteDetail.vue';
import EditAthleteView from '../views/EditAthleteView.vue'

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      // meta: {
      //   requiresAuth: false
      // }
    },
    {
      path: "/agents",
      name: "agents",
      component: AgentView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/athletes",
      name: "athletes",
      component: AthleteView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/athlete/edit/:athleteId',
      name: 'editAthlete',
      component: EditAthleteView
    },
    {
      path: '/athletes/:athleteId', // Route for athlete detail
      name: 'athleteDetail',
      component: AthleteDetail,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/brands",
      name: "brands",
      component: BrandView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/unauthorized',
      name: 'Unauthorized',
      component: () => import('../services/AuthService.js'),
    },
    {
      path: "/login",
      name: "login",
      component: LoginView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: LogoutView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: RegisterView,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/create-account/agent',
      name: 'AgentCreateAccount',
      component: () => import('../components/AgentCreateAccountView.vue'),
    },
    {
      path: '/create-account/athlete',
      name: 'athleteCreateAccount',
      component: () => import('../components/AthleteCreateAccountView.vue'),
    },
    // {
    //   path: '/create-account/brand',
    //   name: 'brandCreateAccount',
    //   component: BrandCreateAccountView, // brand account creation. WILL CREATE LATER AFTER ADDING MOCK DATA
    // }
  ];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
