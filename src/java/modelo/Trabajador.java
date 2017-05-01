package modelo;
// Generated 21/05/2016 07:17:23 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Trabajador generated by hbm2java
 */
@Entity
@Table(name="trabajador"
    ,catalog="proyecto"
)
public class Trabajador  implements java.io.Serializable {


     private Integer idtrabajador;
     private Ocupacion ocupacion;
     private String nombres;
     private String apellidos;
     private Date fechaNacimiento;
     private Set proyectoTrabajadors = new HashSet(0);
     private Set usuarios = new HashSet(0);

    public Trabajador() {
    }

    public Trabajador(Integer idtrabajador) {
         this.idtrabajador = idtrabajador;
    }	

    public Trabajador(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }
    public Trabajador(Ocupacion ocupacion, String nombres, String apellidos, Date fechaNacimiento, Set proyectoTrabajadors, Set usuarios) {
       this.ocupacion = ocupacion;
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.fechaNacimiento = fechaNacimiento;
       this.proyectoTrabajadors = proyectoTrabajadors;
       this.usuarios = usuarios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idtrabajador", unique=true, nullable=false)
    public Integer getIdtrabajador() {
        return this.idtrabajador;
    }
    
    public void setIdtrabajador(Integer idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ocupacion_idocupacion", nullable=false)
    public Ocupacion getOcupacion() {
        return this.ocupacion;
    }
    
    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    
    @Column(name="nombres", length=45)
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    
    @Column(name="apellidos", length=45)
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaNacimiento", length=19)
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="trabajador")
    public Set getProyectoTrabajadors() {
        return this.proyectoTrabajadors;
    }
    
    public void setProyectoTrabajadors(Set proyectoTrabajadors) {
        this.proyectoTrabajadors = proyectoTrabajadors;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="trabajador")
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}


