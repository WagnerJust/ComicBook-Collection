<template>
  <div class="footer">
      <img class="dev-logo" src="..\public\devengers-white.png">
      <div class="devs">
        <!-- <h2>Meet the Developers</h2> -->
        <ul>
            <li>Herman Grewal</li>
            <li>Robin Kim</li>
            <li>Maxwell MacFarlane</li>
            <li>David Sensendorf</li>
            <li>Justin Wagner</li>
        </ul>
      </div>


  </div>
</template>

<script>
export default {
    name: 'the-footer'
}
</script>

<style>

.footer {
    display: grid;
    grid-template-areas: "dev-logo devs";
    grid-template-columns: 1fr 1fr;
    background-color: #012136;
    bottom: 0;
    position: sitcky;
    height: auto;
    padding: 30px 30px 30px 30px;
}

.dev-logo {
    grid-area: dev-logo;
    justify-self: end;
}

.devs {
    grid-area: devs;
    color: white;
    margin-left: 30px;
    padding-left: 30px;
    border-left: 1px solid white;
    font-family: 'Montserrat', Helvetica, sans-serif;
}

.devs ul {
    list-style: none;
    font-size: .9rem;
}

.devs ul li {
    margin: 3px 0;
}

h2 {
    padding-bottom: 5px;
    font-size: 1.2rem;
    margin-top: -.5%;
}

</style>