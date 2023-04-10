<template>
  <div id="container">
    <div id="login-banner">
      <img src="..\public\manageMyComics.png">
    </div>
    <div id="register" class="text-center">
      <form @submit.prevent="register">
        <h1>Create Account</h1>
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <label for="username">Username</label>
          <input type="text" id="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <label for="password">Password</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div class="form-input-group">
          <label for="confirmPassword">Confirm Password</label>
          <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
        </div>
        <button type="submit">Create Account</button>
        <p></p>
      </form>
    </div>
    <div id="create-user">
      <div>
        <div id="welcome">Hello, Friend!</div>
        <p>Have an account?</p>
        <router-link :to="{ name: 'login' }" tag="button">SIGN IN</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>

#container {
  background-color: #F5DE87;
  display: grid;
  grid-template-areas: 
  "banner banner"
  "register login"
  "register login";
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

#register {
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
