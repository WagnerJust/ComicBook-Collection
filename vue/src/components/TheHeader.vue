<template>
  <div class="header">
    <div>
      <router-link v-bind:to="{ name: 'home' }"><img class="logo" src="..\public\Logo.png" alt="Manage My Comcis!" /></router-link>
    </div>
    <nav>
      <ul class="nav-bar">
        <li>Browse</li>
        <li>New Comics</li>
        <li class="collections"><router-link :to="{ name: 'my-collections' }" >Collections</router-link></li>
      </ul>
    </nav>
    <div>
      <ul class="login-register">
        <li class="login" ><router-link :to="{ name: 'login' }" v-if="$store.state.token.length <= 0" tag="button">SIGN IN</router-link></li>
        <li class="register" ><router-link :to="{ name: 'register' }" v-if="$store.state.token.length <= 0" tag="button">REGISTER</router-link></li>
        <li class="logout" ><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" tag="button">LOGOUT</router-link></li>
      </ul>
    </div>
  </div>
</template>

<script>

export default {

    name: 'the-header',
}
</script>

<style scope>
div .header {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas:
    "logo nav-bar login-register";
  align-items: center;
  background-color: white;
  width: 100%;
  /* background-image: url("https://mightymega.com/wp-content/uploads/2013/05/marvel_panoramic_posters_2.jpg"); */
}

.logo {
  grid-area: logo;
  margin-left: 10%;
  width: 80%;
  margin-top: 5%;
  margin-bottom: 5%;
}

ul .login-register {
  grid-area: login-register;
}

ul {
  display: flex;
  list-style: none;
}

nav > ul {
  justify-content: space-evenly;
  text-transform: uppercase;
  grid-area: nav-bar;
  justify-self: end;
}

div > ul {
  justify-content: flex-end;
  margin-right: 10%;
}

div > ul .login {
  margin-right: 5%;
}

div > ul .register {
  margin-right: 5%;
}

</style>