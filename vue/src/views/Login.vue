<template>
  <div id="container">
    <div id="login-banner">
      <img src="..\public\manageMyComics.png">
    </div>
    <div id="login">
      <form @submit.prevent="login">
        <h1 >LOGIN</h1>
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
    <div id="create-user">
      <div>
        <div id="welcome">Welcome Back!</div>
        <p>Not Registered?</p>
        <router-link :to="{ name: 'register' }" tag="button">SIGN UP</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
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
};
</script>

<style scoped>

#container {
  background-color: #F5DE87;
  display: grid;
  grid-template-areas: 
  "banner banner"
  "login register"
  "login register";
  gap: 1rem;
  border: 5px solid black;
  height: 95vh;
}

#login-banner {
  grid-area: banner;
  display: flex;
  height: 30vh;
  width: 95%;
  background-image: url("https://cdn.pixabay.com/photo/2016/03/06/04/14/comics-1239698_960_720.jpg");
  border-style: solid;
  justify-self: center;
  margin-top: 1rem;
  justify-content: center;
  align-items: center;
}

#login-banner > img {
  display: flex;
  height: 80%;
}

#login {
  display: flex;
  background-color: white;
  height: 25rem;
  width: 25rem;
  grid-area: login;
  border: 5px solid black;
  align-self: center;
  justify-self: center;
  justify-content: center;
  align-items: center;
  text-align: center;
}

#login > form {

}

#welcome {
  font-size: 3rem;
}

#create-user {
  display: flex;
  background-color: #BC545E;
  height: 25rem;
  width: 25rem;
  align-self: center;
  justify-self: center;
  grid-area: register;
  border: 5px solid black;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>