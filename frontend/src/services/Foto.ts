
/*
 * Beispiel "Kommentar"
{
    "id": 2,
    "autor": "ab",
    "zeitpunkt": "2021-06-08T21:42:37.399333",
    "text": "Wat soll dat denn sein. So viel Grünzeuchs."
  }
*/

export interface Kommentar {
    id: number;
    autor: string;
    zeitpunkt: string;
    text: string;
}

export interface Foto {
    id: number;
    mimetype: string;
    dateiname: string;
    ort: string;
    zeitstempel: string;
    geolaenge: number;
    geobreite: number;
    kommentare: number[];
}