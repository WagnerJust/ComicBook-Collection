<template>
    <div id="sign-in-box">
        <div>
            <div id="welcome">Hello, Friend!</div>
            <p>Have an account?</p>
            <router-link :to="{ name: 'login' }" tag="button">SIGN IN</router-link>
        </div>
    </div>
</template>


<script>
export default {
    
}
</script>


<style scoped>

#sign-in-box {
  display: flex;
  background-color: #BC545E;
  height: 300px;
  width: 300px;
  align-self: center;
  justify-self: center;
  grid-area: signInBox;
  border: 5px solid black;
  justify-content: center;
  align-items: center;
  text-align: center;
  margin: 3rem;
}

#welcome {
  font-size: 3rem;
}

</style>