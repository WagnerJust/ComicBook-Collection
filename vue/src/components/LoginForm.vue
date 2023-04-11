<template>
  <div id="loginForm">
    <form @submit.prevent="login">
      <h1>LOGIN</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <input type="text" id="username" placeholder="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <input type="password" id="password" placeholder="password" v-model="user.password" required />
      </div>
      <button type="submit">SIGN IN</button>
    </form>
  </div>
</template>


<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
}
</script>


<style scoped>

#loginForm {
  display: flex;
  background-color: white;
  height: 80%;
  width: 55%;
  grid-area: loginForm;
  border: 5px solid black;
  align-self: center;
  justify-self: center;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.form-input-group {
  margin-bottom: 1rem;
}

</style>