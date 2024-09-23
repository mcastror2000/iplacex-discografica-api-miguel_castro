package org.iplacex.proyectos.discografia.discos;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepo;

    @Autowired
    private IArtistaRepository artistaRepo;

    //  insertar un Disco
    @PostMapping(
        value = "/disco",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> handlePostDiscoRequest(@RequestBody Disco disco) {
        if (!artistaRepo.existsById(disco.idArtista)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Artista no encontrado");
        }
        Disco temp = discoRepo.insert(disco);
        return ResponseEntity.status(HttpStatus.CREATED).body(temp);
    }

    // muestra todos los discos los Discos (ok funciona)
    @GetMapping(
        value = "/disco",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> handleGetDiscosRequest() {
        return ResponseEntity.ok(discoRepo.findAll());
    }

    // buscar  un Disco por ID
    @GetMapping(
        value = "/disco/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> handleGetDiscoRequest(@PathVariable("id") String id) {
        Optional<Disco> temp = discoRepo.findById(id);
        if (!temp.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado");
        }
        return ResponseEntity.ok(temp.get());
    }

    // aca deberia obtener Discos por idArtista-- chequear
    @GetMapping(
        value = "/artista/{id}/discos",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Disco>> handleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {
        List<Disco> discos = discoRepo.findDiscosByIdArtista(idArtista);
        return ResponseEntity.ok(discos);
    }
}
