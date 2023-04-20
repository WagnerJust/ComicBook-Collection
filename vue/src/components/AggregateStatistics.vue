<template>
    <div class='site-stats'>
      <h2>{{comicStats.num_comics_siteWide}}  COMICS HAVE BEEN COLLECTED IN {{comicStats.num_collections_siteWide}} COLLECTIONS! </h2>
      <h4 v-if="$store.state.token === ''">~ Register and make an account to start collecting today! ~</h4>
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
/* table.stats {
  font-family: "Comic Sans MS", cursive, sans-serif;
  border: 1px solid#ed1d24;
  background-color: #EEEEEE;
  display: inline-flexbox;
  align-content: center;
  justify-content: center;
  
  text-align: left;
  border-collapse: collapse;
}
table.stats td, table.stats th {
  border: 1px solid #AAAAAA;
  padding: 5px 10px;
}
table.stats tbody td {
  font-size: 20px;
  color: #333333;
}
table.stats tr:nth-child(even) {
  background: #F5F5F5;
}
table.stats thead {
  background: #ed1d24;
  border-bottom: 2px solid #444444;
}
table.stats thead th {
  font-size: 20px;
  font-weight: bold;
  color: #FFFFFF;
  border-left: 3px solid #D0E4F5;
}
table.stats thead th:first-child {
  border-left: none;
} */


</style>