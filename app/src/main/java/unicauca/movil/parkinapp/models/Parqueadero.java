package unicauca.movil.parkinapp.models;

/**
 * Created by Natic_000 on 15/12/2016.
 */

public class Parqueadero {

    long id;
    String nombre;
    String direccion;
    String precio;
    String longitud;
    String latitud;
    double calificacion;
    long cantidad;
    String imagen;
    long lugaresLibres;
    String horarioApertura;
    String horarioCerrado;
    String telefono;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public long getLugaresLibres() {
        return lugaresLibres;
    }

    public void setLugaresLibres(long lugaresLibres) {
        this.lugaresLibres = lugaresLibres;
    }

    public String getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(String horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public String getHorarioCerrado() {
        return horarioCerrado;
    }

    public void setHorarioCerrado(String horarioCerrado) {
        this.horarioCerrado = horarioCerrado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
