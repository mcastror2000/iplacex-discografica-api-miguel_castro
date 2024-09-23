package org.iplacex.proyectos.discografia.artistas;

import java.util.Optional;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepo;

    public ArtistaController() {}

    // Método para insertar un Artista- revisar proyecto vivero
    @PostMapping(
        value= "/artista",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object handleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista temp = artistaRepo.insert(artista);
        return temp;  // Devuelve el objeto insertado
    }

    // obtiene todos los Artistas
    @GetMapping(
        value = "/artista",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Artista> handleGetArtistasRequest() {
        return artistaRepo.findAll();  // muestra lista de artistas
    }

    //  para obtener un Artista por ID
    @GetMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object handleGetArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> temp = artistaRepo.findById(id);
        if (!temp.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
        }
        return temp.get();  // Devuelve el objeto encontrado
    }

    // Método para actualizar un Artista
    @PutMapping(
        value = "/artista/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object handleUpdateArtistaRequest(@PathVariable("id") String id, @RequestBody Artista artista) {
        if (!artistaRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
        }
        artista._id = id;
        Artista temp = artistaRepo.save(artista);
        return temp;  
    }

    // Método para eliminar un Artista por ID
    @DeleteMapping(
        value = "/artista/{id}"
    )
    public Object handleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!artistaRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Artista no encontrado");
        }
        Artista temp = artistaRepo.findById(id).get();
        artistaRepo.deleteById(id);
        return temp;  // Devuelve el objeto eliminado
    }
}

    
