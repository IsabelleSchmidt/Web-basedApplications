import{ computed, reactive, readonly } from 'vue'
import { Foto } from './Foto'
// Gemeinsame State-Variable(n) auf oberster Ebene,
// also ausserhalb der use-Funktion (dürfen nur je
// einmal und nicht nicht je use-Aufruf angelegt werden)
// Oft auch mit einem reactive()-Objekt gelöst

    const fotostate = reactive({
        fotos: Array<Foto>(), 
        errormessage: ""
    })
    // Composition-Function zur Bereitstellung
    // von State-Abfrage- und Bearbeitungsmoeglichkeiten
    
    async function updateFotos() {
        const fotoliste = new Array<Foto>();

        fetch(`http://localhost:8080/api/foto`,{
            method: 'GET'
        })
        .then((response)=>{
            if(!response.ok){
                throw new Error(response.statusText);
            }
            fotostate.errormessage ="";
            return response.json();
        })
        .then((jsondata: Array<Foto>)=>{
            for(let i=0; i < jsondata.length; i++){
                fotoliste.push(jsondata[i]);
            }
            fotostate.fotos = fotoliste;
        })
        .catch((fehler)=>{
            fehler.fotostate.errormessage ="Fehler bei der Serverkommunikation";
        });
    }

    async function deleteFoto(id: number) {
        
        fetch(`http://localhost:8080/api/foto/${id}`, {
            method:'DELETE'
          })
          .then((response)=>{
            if(!response.ok){
              throw new Error(fotostate.errormessage);
            }
            fotostate.errormessage = "";
            updateFotos();
          })
          .catch((fehler)=>{
            fotostate.errormessage = fehler;
          })
    }

    export function useFotoStore() {
        
        function addListeZeile(f: Foto): void{
            fotostate.fotos.push(f)
        }

        return{
            fotostate: readonly(fotostate),
            addListeZeile,
            updateFotos,
            deleteFoto
        }
    }
    
    //functionaddListeZeile(s: string): void {state.liste.push(s)}
    
        