<template>
  <div class="container">
    <!-- Button zum Hinzufügen des nächsten Bildes -->
    <button class="button" v-on:click="geklickt()">
      Neues Bild hinzufügen
      <i class="fas fa-camera" />
    </button>
    <!-- Eingabefeld für inkrementelle Suche -->
    <section class="section">
      <input type="text" class="input" v-model="suchbegriff" placeholder="Suche" />
    </section>
    <section class="section">
      <div class="columns is-multiline">
        <ul>
          <FotoGalerieBild :foto="f" v-for="f in fotos" :key="f.id" @entferne-zeile="delFoto($event)"/> 
        </ul>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, Ref, ref } from "vue";
import FotoGalerieBild from "./FotoGalerieBild.vue";
import { Foto } from "../services/Foto";
import { fotoliste } from "../services/FotoListe";

export default defineComponent({
  name: "FotoGalerie",

  components:{
    FotoGalerieBild
  },
  setup(props){
    
    const fotos: Ref<Foto[]> = ref([]);

    let i = 0;

    function geklickt() {
      if (i<fotoliste.length) {
        fotos.value.push(fotoliste[i])
        i++;
      }
      else alert("Keine Fotos mehr")
    }

    const suchbegriff = ref("")
    const displayfotos =  computed( () => {
      if (suchbegriff.value.length < 3) {
        return fotos.value;
      } else {
        return fotos.value.filter(e => e.ort.toLowerCase().includes(suchbegriff.value.toLowerCase()) );
      }
    })

    function delFoto(id: number): void{
      fotos.value = fotos.value.filter(ele => ele.id !== id);
    }


    return{
      fotos: displayfotos,
      geklickt,
      delFoto,
      suchbegriff
    }
  }

  
});
</script>


<style scoped>

</style>
