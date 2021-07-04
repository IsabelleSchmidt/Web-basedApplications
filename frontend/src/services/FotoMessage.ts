import { Client } from "@stomp/stompjs";
import { useFotoStore } from "./FotoStore";

export interface FotoMessage{

    FOTO_GESPEICHERT: String,
    FOTO_GELOESCHT: String,
    operation: String,
    id: number
}



// try{
//     stompclient.publish({destination: DEST, headers: {}, 
//         body: JSON.stringify(fotostate.fotos)});
// } catch(fehler){

// }