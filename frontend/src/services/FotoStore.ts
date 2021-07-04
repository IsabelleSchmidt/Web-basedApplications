import { Client } from '@stomp/stompjs';
import{ reactive, readonly } from 'vue'
import { Foto } from './Foto'
import { FotoMessage } from './FotoMessage';

    const fotostate = reactive({
        fotos: Array<Foto>(), 
        errormessage: ""    
    })

    const wsurl = "ws://localhost:9090/messagebroker";
    const DEST = "/topic/foto";

    const stompclient = new Client({brokerURL: wsurl})
    
    async function updateFotos() {
        const fotoliste = new Array<Foto>();

        fetch(`/api/foto`,{
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
        
        fetch(`/api/foto/${id}`, {
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

    stompclient.onStompError = (frame) => {
        console.log(frame)
    }
    stompclient.onConnect = () => {
        stompclient.subscribe(DEST, (message) => {
            const f = JSON.parse(message.body) as FotoMessage

        if(f.operation == "FOTO_GESPEICHERT"){
            updateFotos();
        }else if(f.operation == "FOTO_GELOESCHT"){
            deleteFoto(f.id)
        }
    });
    };

    stompclient.activate();

    export function useFotoStore() {
        
        function addListeZeile(f: Foto): void{
            fotostate.fotos.push(f)
        }

        return{
            fotostate: readonly(fotostate),
            addListeZeile,
            updateFotos,
            deleteFoto,
            stompclient
        }
    }
    
    //functionaddListeZeile(s: string): void {state.liste.push(s)}
    
        