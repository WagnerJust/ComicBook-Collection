<template>
  <div class="character">
        <img class="character-image" v-bind:src="'http://i.annihil.us/u/prod/marvel/i/mg/' + character.image + '/standard_xlarge.jpg'" />
        <h3 class="character-alias">{{ character.alias }}</h3>
  </div>
</template>

<script>
export default {
    name: 'character-card',
    props: {
        character: Object
    }
}
</script>

<style scoped>
.character {
    width: 18%;
    height: 25%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-bottom: 30px;
}

.character:hover {
    color: #ED1D24;
}

.character-image {
    width: 100%;
    box-shadow: 0 6px 10px 0 rgba(0, 0, 0, 0.2), 0 10px 30px 0 rgba(0, 0, 0, 0.19);
    object-fit: cover;
    position: relative;
    transition: transform 250ms;
}

.character-image:hover {
    transform: translateY(-10px);
}

.character-alias {
    margin-top: 1.5rem;
    text-align: center;
    font-family: 'Montserrat', Helvetica, sans-serif;
    font-size: 1.2rem;
    font-weight: 600;
}
</style>