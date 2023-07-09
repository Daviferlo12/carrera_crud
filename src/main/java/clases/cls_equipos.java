/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author davif
 */
public class cls_equipos {
    
    int id_equipo;
    String marca_patro;
    String nombre_tecnico;

    public cls_equipos(int id_equipo, String marca_patro, String nombre_tecnico) {
        this.id_equipo = id_equipo;
        this.marca_patro = marca_patro;
        this.nombre_tecnico = nombre_tecnico;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getMarca_patro() {
        return marca_patro;
    }

    public void setMarca_patro(String marca_patro) {
        this.marca_patro = marca_patro;
    }

    public String getNombre_tecnico() {
        return nombre_tecnico;
    }

    public void setNombre_tecnico(String nombre_tecnico) {
        this.nombre_tecnico = nombre_tecnico;
    }
    
    
    
}
