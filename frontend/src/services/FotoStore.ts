import{ computed, reactive, readonly } from 'vue'
import { Foto } from './Foto'
// Gemeinsame State-Variable(n) auf oberster Ebene,
// also ausserhalb der use-Funktion (dürfen nur je
// einmal und nicht nicht je use-Aufruf angelegt werden)
// Oft auch mit einem reactive()-Objekt gelöst

    const fotostate = reactive({
        liste: Array<Foto>(), 
        errormessage: ""
    })
    // Composition-Function zur Bereitstellung
    // von State-Abfrage- und Bearbeitungsmoeglichkeiten
    
    export function useFotoStore() {
        
        function addListeZeile(f: Foto): void{
            fotostate.liste.push(f)
        }

        return{
            fotostate: readonly(fotostate),
            addListeZeile
        }
    }
        //functionaddListeZeile(s: string): void {state.liste.push(s)}
    
        