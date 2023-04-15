<template>
  <div class="header">
    <div>
      <router-link v-bind:to="{ name: 'home' }"><img class="logo" src="..\public\Logo.png" alt="Manage My Comcis!" /></router-link>
    </div>
    <nav>
      <ul class="nav-bar">
        <li><router-link :to="{ name: 'browse' }" style="text-decoration: none;">Browse</router-link></li>
        <li class="newcomics"><router-link :to="{ name: 'newcomics' }" style="text-decoration: none;">New Comics</router-link></li>
        <li class="collections"><router-link :to="{ name: 'collections' }" style="text-decoration: none;">Collections</router-link></li>
      </ul>
    </nav>
    <div>
      <ul class="login-register">
        <li class="login" ><router-link class="nav-link" :to="{ name: 'login' }" v-if="$store.state.token.length <= 0" tag="button">SIGN IN</router-link></li>
        <li class="register" ><router-link class="nav-link" :to="{ name: 'register' }" v-if="$store.state.token.length <= 0" tag="button">REGISTER</router-link></li>
        <li class="logout" ><router-link class="nav-link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''" tag="button">LOGOUT</router-link></li>
      </ul>
    </div>
  </div>
</template>

<script>

export default {

    name: 'the-header',
}
</script>

<style scoped>

div .header {
  display: grid;
  grid-template-columns: 1fr 3fr 1fr;
  grid-template-areas:
    "logo nav-bar login-register";
  align-items: center;
  background-color: white;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  /* background-image: url("https://mightymega.com/wp-content/uploads/2013/05/marvel_panoramic_posters_2.jpg"); */
}

.logo {
  grid-area: logo;
  margin-left: 15%;
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
  font-family: 'Montserrat', Helvetica, sans-serif;
  font-weight: 800;
  font-size: 1.3em;


}

nav > ul {
  justify-content: space-evenly;
  text-transform: uppercase;
  grid-area: nav-bar;
  justify-self: end;
}

.nav-bar > li:hover {
  color: #ED1D24;
  font-weight: 1000;
  transition: all .15s ease-in-out;
}

div > ul {
  justify-content: flex-end;
  margin-right: 15%;
}

div > ul .login {
  margin-right: 5%;
}

div > ul .register {
  margin-right: 5%;
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

a, a:hover, a:visited, a:active {
  color: inherit;
  text-decoration: none;
 }

</style>