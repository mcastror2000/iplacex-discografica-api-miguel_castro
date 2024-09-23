package org.iplacex.proyectos.discografia.discos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "discos")
public class Disco {

    @Id
    public String _id;

    public String idArtista;
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;

  
    public Disco() {}

    
    public Disco(String idArtista, String nombre, int anioLanzamiento, List<String> canciones) {
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.anioLanzamiento = anioLanzamiento;
        this.canciones = canciones;
    }

    
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "_id='" + _id + '\'' +
                ", idArtista='" + idArtista + '\'' +
                ", nombre='" + nombre + '\'' +
                ", anioLanzamiento=" + anioLanzamiento +
                ", canciones=" + canciones +
                '}';
    }
}
