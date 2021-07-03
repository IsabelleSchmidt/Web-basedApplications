import { Client } from "@stomp/stompjs";
import { useFotoStore } from "./FotoStore";

const {  fotostate,updateFotos, deleteFoto } = useFotoStore()

export interface FotoMessage{

    FOTO_GESPEICHERT: String,
    FOTO_GELOESCHT: String,
    operation: String,
    id: number
}

const wsurl = "ws://localhost:9090/stompbroker";
const DEST = "topic/foto";

const stompclient = new Client({brokerURL: wsurl})

stompclient.onConnect = (frame) => {
    stompclient.subscribe(DEST, (message) => {
        const f = JSON.parse(message.body) as FotoMessage

    if(f.operation == "FOTO_GESPEICHERT"){
      updateFotos();
    }else if(f.operation == "FOTO_GELOESCHT"){
      deleteFoto(f.id)
    }
    });
};

stompclient.onDisconnect = () => {}

stompclient.activate();

// try{
//     stompclient.publish({destination: DEST, headers: {}, 
//         body: JSON.stringify(fotostate.fotos)});
// } catch(fehler){

// }