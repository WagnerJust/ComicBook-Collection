<template>
    <section class="container">
        <h2>{{collection.collectionName}}</h2>
        <div class="cards">
            <comic-card v-for="comic in this.comics" :key="comic.comicId" :comic="comic" />
        </div>
        <h2> Statistics </h2>
            <p>&nbsp;</p>
            <table class="stats">
                <thead>
                    <tr>
                        <th>Character</th>
                        <th># Comics ft. character</th>
                        <th>% Comics ft. character</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="character in charactersInCollection" :key="character.characterId">
                        <td>{{character.characterAlias}}</td>
                        <td>{{getStats(character.characterId)}}</td>
                        <td>cell3_1</td>
                    </tr>
                </tbody>
            </table>
    </section>


    
</template>


<script>
import ComicCard from '../components/ComicCard.vue';
import comicService from '../services/ComicService.js'
import collectionService from "../services/CollectionsService.js"
import characterService from '../services/CharacterService.js'

export default {
    name: 'collection',
    components: { 
        ComicCard
    },
    data() {
        return {
            comics: [],
            collection: [],
            charactersInCollection: [],
            count: 0
        }
    },
    methods:{
        getStats(id){
            this.count = collectionService.numberComicsInCollectionWithCharacter(id);
        }

    },
    created() {
        /* This call can be seen in the console. It is requesting the comics from collectionId: 1 */
        comicService.getComicsByCollectionId(this.$route.params.id).then(response => {
            this.comics = response.data;
            console.log("COMICS");
            console.log(this.comics);
        });
        collectionService.getCollectionByCollectionId(this.$route.params.id).then(response => {
            this.collection = response.data;
        });
        characterService.getCharactersByCollectionId(this.$route.params.id)
        .then(response => {
            this.charactersInCollection = response.data;
        });
    }
}
</script>


<style scoped>

.container {
    margin-left: 12%;
    margin-right: 12%;
}

.cards {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    column-gap: 2.2%;
    margin-top: 3rem;
}

h2 {
    text-align: center;
    margin: 1.5rem;
    font-size: 2.5rem;
    font-family: 'Montserrat', Helvetica, sans-serif;
}


table.stats {
  font-family: "Comic Sans MS", cursive, sans-serif;
  border: 1px solid #1C6EA4;
  background-color: #EEEEEE;
  width: 30%;
  text-align: left;
  border-collapse: collapse;
}
table.stats td, table.stats th {
  border: 1px solid #AAAAAA;
  padding: 5px 10px;
}
table.stats tbody td {
  font-size: 13px;
  color: #333333;
}
table.stats tr:nth-child(even) {
  background: #F5F5F5;
}
table.stats thead {
  background: #1C6EA4;
  background: -moz-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);
  background: -webkit-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);
  background: linear-gradient(to bottom, #5592bb 0%, #327cad 66%, #1C6EA4 100%);
  border-bottom: 2px solid #444444;
}
table.stats thead th {
  font-size: 20px;
  font-weight: bold;
  color: #FFFFFF;
  border-left: 2px solid #D0E4F5;
}
table.stats thead th:first-child {
  border-left: none;
}

table.stats tfoot td {
  font-size: 8px;
}
table.stats tfoot .links {
  text-align: right;
}
table.stats tfoot .links a{
  display: inline-block;
  background: #1C6EA4;
  color: #FFFFFF;
  padding: 2px 8px;
  border-radius: 5px;
}

</style>

