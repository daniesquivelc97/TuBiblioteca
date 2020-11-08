package co.com.uco.tubiblioteca.dto;

import java.util.Date;

public class Book {
    int id;
    String Nombres;
    String Identificacion;
    String Celular;
    String Libro;
    String Fecha;

    public Book() {
    }

    public Book(int id, String nombres, String identificacion, String celular, String libro, String fecha) {
        this.id = id;
        Nombres = nombres;
        Identificacion = identificacion;
        Celular = celular;
        Libro = libro;
        Fecha = fecha;
    }

    public boolean isNull() {
        if(Nombres.equals("") && Identificacion.equals("") && Celular.equals("") && Libro.equals("") && Fecha.equals("")){
            return false;
        }
        else {
            return true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(String identificacion) {
        Identificacion = identificacion;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getLibro() {
        return Libro;
    }

    public void setLibro(String libro) {
        Libro = libro;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", Nombres='" + Nombres + '\'' +
                ", Identificacion='" + Identificacion + '\'' +
                ", Celular='" + Celular + '\'' +
                ", Libro='" + Libro + '\'' +
                ", Fecha='" + Fecha + '\'' +
                '}';
    }
}
