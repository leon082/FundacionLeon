/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author hernandario
 */
public class MiddelwareTrabajador {
    
    private int idTrabajador;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;     
    private String descripcion;
    private String clase_trabajador;

    public MiddelwareTrabajador(int idTrabajador, String nombres, String apellidos, String fechaNacimiento, String descripcion, String clase_trabajador) {
        this.idTrabajador = idTrabajador;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.descripcion = descripcion;
        this.clase_trabajador = clase_trabajador;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClase_trabajador() {
        return clase_trabajador;
    }

    public void setClase_trabajador(String clase_trabajador) {
        this.clase_trabajador = clase_trabajador;
    }
    
    
    

}
