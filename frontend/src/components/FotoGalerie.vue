<template>
  <div class="container">
    <!-- Button zum Hinzufügen des nächsten Bildes -->
    <button class="button" v-on:click="gelickt()">
      Neues Bild hinzufügen
      <i class="fas fa-camera" />
    </button>
    <!-- Eingabefeld für inkrementelle Suche -->
    <section class="section">
      <input type="text" class="input" placeholder="Suche" />
    </section>
    <section class="section">
      <div class="columns is-multiline">
        <ul>
          <FotoGalerieBild :foto="f" v-for="f in fotos" :key="f.id"/> 
        </ul>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { defineComponent, Ref, ref } from "vue";
import FotoGalerieBild from "./FotoGalerieBild.vue";
import { Foto } from "../services/Foto";
import { fotoliste } from "../services/FotoListe";

export default defineComponent({
  name: "FotoGalerie",

  components:{
    FotoGalerieBild
  },
  setup(){
    
      const fotos: Ref<Foto[]> = ref(fotoliste);

    return{
      fotos
    }
  }

  
});
</script>


<style scoped>

</style>
