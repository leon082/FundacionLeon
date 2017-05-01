package modelo;
// Generated 21/05/2016 07:17:23 PM by Hibernate Tools 3.6.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tareas generated by hbm2java
 */
@Entity
@Table(name="tareas"
    ,catalog="proyecto"
)
public class Tareas  implements java.io.Serializable {


     private Integer idtareas;
     private ProyectoTrabajador proyectoTrabajador;
     private String descripcion;
     private Date fechaAsignacion;
     private Date fechaCulminacion;

    public Tareas() {
    }

	
    public Tareas(ProyectoTrabajador proyectoTrabajador) {
        this.proyectoTrabajador = proyectoTrabajador;
    }
    public Tareas(ProyectoTrabajador proyectoTrabajador, String descripcion, Date fechaAsignacion, Date fechaCulminacion) {
       this.proyectoTrabajador = proyectoTrabajador;
       this.descripcion = descripcion;
       this.fechaAsignacion = fechaAsignacion;
       this.fechaCulminacion = fechaCulminacion;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idtareas", unique=true, nullable=false)
    public Integer getIdtareas() {
        return this.idtareas;
    }
    
    public void setIdtareas(Integer idtareas) {
        this.idtareas = idtareas;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="Proyecto_trabajador_idProyecto_trabajador", nullable=false)
    public ProyectoTrabajador getProyectoTrabajador() {
        return this.proyectoTrabajador;
    }
    
    public void setProyectoTrabajador(ProyectoTrabajador proyectoTrabajador) {
        this.proyectoTrabajador = proyectoTrabajador;
    }

    
    @Column(name="descripcion", length=45)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaAsignacion", length=19)
    public Date getFechaAsignacion() {
        return this.fechaAsignacion;
    }
    
    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaCulminacion", length=19)
    public Date getFechaCulminacion() {
        return this.fechaCulminacion;
    }
    
    public void setFechaCulminacion(Date fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }




}


