package de.hsrm.mi.web.projekt.foto;

import java.util.List;
import java.util.Optional;

public interface FotoService {
    public Foto fotoAbspeichern(Foto foto);
    public Optional<Foto> fotoAbfragenNachId(Long id);
    public List<Foto> alleFotosNachZeitstempelSortiert();
    public void loescheFoto(Long id);
    void fotoKommentieren(long id, String autor, String kommentar);
    List<Kommentar> alleKommentareFuerFoto(long fotoid);
    void fotoKommentarLoeschen(long fotoid, long kid);


}
