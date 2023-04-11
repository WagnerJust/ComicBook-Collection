<template>
    <div id="registrationForm" class="text-center">
      <form @submit.prevent="register">
        <h1>Create Account</h1>
        <div role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div class="form-input-group">
          <input type="text" id="username" placeholder="username" v-model="user.username" required autofocus />
        </div>
        <div class="form-input-group">
          <input type="password" id="password" placeholder="password" v-model="user.password" required />
        </div>
        <div class="form-input-group">
          <input type="password" id="confirmPassword" placeholder="re-type password" v-model="user.confirmPassword" required />
        </div>
        <button type="submit">START COLLECTING</button>
      </form>
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

#registrationForm {
  display: flex;
  background-color: white;
  height: 80%;
  width: 50%;
  grid-area: registrationForm;
  border: 5px solid black;
  margin-top: 1rem;
  margin-bottom: -1rem;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.form-input-group {
  margin-bottom: 1rem;
}

</style>