<template>
     <div class='site-stats'>
            <table class='stats'>
                <thead>
                    <tr>
                        <th># Comics Collected</th>
                        <th># Collections Made</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{comicsStat.num_comics_siteWide}}</td>
                        <td>{{comicStat.num_collections_siteWide}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
</template>

<script>
import StatsService from '../services/StatsService'

export default {
    data(){
        return{
            comicsStat: {}
        }
    },
    created(){
        StatsService.getSiteStats()
        .then(response => {
            this.comicsStat = response.data;
            console.log("stats", this.comicsStat);
        })
    }


}
</script>

<style>
table.stats {
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
}


</style>