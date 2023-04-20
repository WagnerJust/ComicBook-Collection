<template>
    <div class='site-stats'>
      <h2>{{comicStats.num_comics_siteWide}}  COMICS HAVE BEEN COLLECTED IN {{comicStats.num_collections_siteWide}} COLLECTIONS! </h2>
      <h4 v-if="$store.state.token === ''">~~~ Register and make an account to start collecting today! ~~~</h4>
    </div>
</template>

<script>
import StatsService from '../services/StatsService'

export default {
    data(){
        return{
            comicStats: {
              num_comics_siteWide: 0,
              num_collections_siteWide: 0
            }
        }
    },
    created(){
        StatsService.getSiteStats()
        .then(response => {
            this.comicStats = response.data;
        })
    }


}
</script>

<style scoped>

.site-stats {
  font-family: 'Montserrat', Helvetica, sans-serif;
  background-color: #a4e9d5;
  text-align: center;
  padding-top: 1rem;
}

h4 {
  padding-bottom: .5rem;
}

</style>