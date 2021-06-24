<template>
  <div class="card column is-3 has-background-grey-lighter m-3">
    <div class="card-header">
      <p class="card-header-title is-centered">
        {{foto.dateiname}} 
      </p>
      <!-- Lösch-Button -->
      <button class="button card-header-icon has-background-grey-light">
        <i class="fa fa-times" />
      </button>
    </div>
    <div class="card-content has-text-centered">
      <!-- Bild anzeigen -->
      <figure class="image is-inline-block">
        <img :src="url" />
      </figure>
      <div class="content">
        <foto-star-rating :maxsterne="5" />
      </div>
      <div class="content">Ort: {{foto.ort}}</div>
      <div class="has-text-grey">Zeitstempel: {{foto.zeitstempel}}</div>
    </div>
  </div>
</template>


<script lang="ts">
import { defineComponent, PropType, ref } from "vue";
import FotoStarRating from "./FotoStarRating.vue";
import { Foto } from "../services/Foto";
import { fotoliste } from "../services/FotoListe";

export default defineComponent({
  components: { FotoStarRating },
  name: "FotoGalerieBild",
  props:{
    foto: {type: Object as PropType<Foto>,required: true}
  },
  setup(props, context) {

    return {
      url: require("../assets/thumbnails/DerTupel.png")
    };
  }
});
</script>
