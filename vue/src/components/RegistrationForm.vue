<template>
  <div class="registrationFormContainer">
    <div class="welcome">Hello, Friend!</div>
    <div id="registrationForm" class="text-center">
      <form @submit.prevent="register">
        <h1>CREATE ACCOUNT</h1>
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <input type="text" id="username" placeholder=" username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <input type="password" id="password" placeholder=" password" v-model="user.password" required />
        </div>
        <div class="form-input-group">
          <input type="password" id="confirmPassword" placeholder=" re-type password" v-model="user.confirmPassword" required />
        </div>
        <button type="submit" class="submit-button">START COLLECTING</button>
      </form>
    </div>
    <div class="existing-user">
      <p>Have an account?</p>
      <router-link :to="{ name: 'login' }" tag="button" class="register">SIGN IN</router-link>
    </div>
  </div>
</template>


<script>
import authService from '../services/AuthService';

export default {
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
}
</script>


<style scoped>

.registrationFormContainer {
  display: flex;
  flex-direction: column;
  background-color: white;
  height: 600px;
  width: 400px;
  grid-area: registrationForm;
  border: 1px solid black;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin: 3rem;
  font-family: 'Montserrat', Helvetica, sans-serif;
}

h1 {
  position: relative;
  top: -25px;
  font-size: 2rem;
}

.welcome {
  position: relative;
  top: -60px;
}

.existing-user {
  position: relative;
  bottom: -75px;
}

.existing-user > p {
  margin-bottom: 1rem;
}

.submit-button {
  position: relative;
  bottom: -30px;
}

.form-input-group {
  margin-bottom: 1rem;
}

input {
  height: 2rem;
  width: 15rem;
}

button {
  cursor: pointer;
  border: 0;
  border-radius: 4px;
  font-weight: 600;
  margin: 1 10px;
  width: 7rem;
  padding: 10px 0;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
  transition: 0.4s;
  color: rgb(236, 29, 36);
  background-color: rgba(255, 255, 255, 1);
  border: 1px solid rgba(236, 29, 36, 1);
  font-family: 'Montserrat', Helvetica, sans-serif;
}

button:hover {
  color: white;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  background-color: rgba(236, 29, 36, 1);
}

</style>