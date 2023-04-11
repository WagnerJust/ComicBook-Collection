<template>
    <div id="login-banner">
      <img src="..\public\Logo.png">
    </div>
</template>

<style scoped>

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

img {
  backdrop-filter: blur(10px);
}

</style>
