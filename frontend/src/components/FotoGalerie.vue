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
      <div class="columns">
        <ul id="foto">
          <FotoGalerieBild :foto="f" v-for="f in fotostate.fotos" :key="f.id" @entferne-zeile="delFoto($event)"/> 
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
      if (i<fotostate.fotos.length) {
        addListeZeile(FotoGalerieBild[i])
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
      geklickt,
      delFoto,
      suchbegriff,
      fotostate,
      anzahl
    }
  }

  
});
</script>

<style>

  #foto{
    display: flex;
    flex-direction: column;
    width: 100%;
  }

  .colums{
    display: flex;
    flex-direction: column;
    width: 100%;
  }
</style>

