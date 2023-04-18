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
        <div id="premium-section" class="field">
                <label id="premium-text" for="checkbox">Premium Account?</label>
                <label id="switch"><input type="checkbox" v-model="user.role" /><span id="slider"></span></label>
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
        role: false,
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
        if(this.user.role){
          this.user.role = 'premium';
        } else {
          this.user.role = 'user';
        }
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

#premium-section {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.field {
    display: flex;
    justify-content: center;
    margin: 1rem;
}

#switch {
    position: relative;
    display: inline-block;
    width: 60px;
    height: 34px;
}

#switch input {
    opacity: 0;
    width: 0;
    height: 0;
}

#slider {
    position: absolute;
    cursor: pointer;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #ccc;
    -webkit-transition: .4s;
    transition: .4s;
}

#slider:before {
    position: absolute;
    content: "";
    height: 26px;
    width: 26px;
    left: 4px;
    bottom: 4px;
    background-color: white;
    -webkit-transition: .4s;
    transition: .4s;
}

input:checked + #slider {
    background-color: #23ce2b;
    box-shadow: 0 0 1px #23ce2b;
}

input:checked + #slider:before {
    -webkit-transform: translateX(26px);
    -ms-transform: translateX(26px);
    transform: translateX(26px);
}

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
  width: 9rem;
}

.form-input-group {
  margin-bottom: 1rem;
}

.form-input-group input {
  padding: .5rem;
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
  font-size: 1rem;
}

button:hover {
  color: white;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  background-color: rgba(236, 29, 36, 1);
}

</style>