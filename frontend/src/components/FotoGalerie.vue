<template>
  <div class="container">
    <div class="notification is-danger" v-if="fotostate.errormessage != '' ">
      {{fotostate.errormessage}}
    </div>
    <button class="button" v-on:click="geklickt()">
      Neues Bild hinzufügen
      <i class="fas fa-camera" />
    </button>
    <section class="section">
      <input type="text" class="input" v-model="suchbegriff" placeholder="Suche" />
    </section>
    <section class="section">
      <div class="columns is-multiline">
        <ul>
          <FotoGalerieBild :foto="f" v-for="f in fotos" :key="f.id" @entferne-zeile="delFoto($event)"/> 
        </ul>
      </div>
      <span> Insgesamt {{anzahl}} Bilder</span>
    </section>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted, Ref, ref } from "vue";
import FotoGalerieBild from "./FotoGalerieBild.vue";
import { Foto } from "../services/Foto";
import { fotoliste } from "../services/FotoListe";
import {useFotoStore} from "../services/FotoStore";

export default defineComponent({
  name: "FotoGalerie",

  components:{
    FotoGalerieBild
  },
  setup(props){

    const {fotostate, addListeZeile, updateFotos, deleteFoto} = useFotoStore();

    // const fotos: Ref<Foto[]> = ref([]);
    const suchbegriff = ref("")
    const anzahl = computed(() => fotostate.fotos.length)
    let i = 0;

    function geklickt() {
      if (i<fotoliste.length) {
        addListeZeile(fotoliste[i])
        i++;
      }
      else alert("Keine Fotos mehr")
    }


    const displayfotos =  computed( () => {
      if (suchbegriff.value.length < 3) {
        return fotostate.fotos;
      } else {
        return fotostate.fotos.filter(e => e.ort.toLowerCase().includes(suchbegriff.value.toLowerCase()) );
      }
    })

    function delFoto(id: number): void{
      deleteFoto(id);
    }

    onMounted(async () => {
      await updateFotos();
    });

    return{
      fotos: displayfotos,
      geklickt,
      delFoto,
      suchbegriff,
      // fotoListe,
      fotostate,
      anzahl
    }
  }

  
});
</script>


<style scoped>

</style>

function useFotoStore(): { liste: any; update: any; errormessage: any; remove: any; } {
  throw new Error("Function not implemented.");
}
