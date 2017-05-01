package modelo;
// Generated 21/05/2016 07:17:23 PM by Hibernate Tools 3.6.0



import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.model.SelectItem;
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
 * Proyecto generated by hbm2java
 */
@Entity
@Table(name="proyecto"
    ,catalog="proyecto"
)
public class Proyecto  implements java.io.Serializable {


     private Integer idProyecto;
     private Temas temas;
     private Comunidad comunidad;
     private String tituloProyecto;
     private String descripcion;
     private String alcance;
     private Long presupuesto;
     private Date fechaInicio;
     private Date fechaFinalizacion;
     private Set objetivos = new HashSet(0);
     private Set proyectoTrabajadors = new HashSet(0);
     
    public Proyecto() {
    }
    
    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }
    
    public Proyecto(Temas temas, Comunidad comunidad) {
        this.temas = temas;
        this.comunidad = comunidad;
    }

    public Proyecto(Temas temas, Comunidad comunidad, String tituloProyecto, String descripcion, String alcance, Long presupuesto, Date fechaInicio, Date fechaFinalizacion, Set objetivos, Set proyectoTrabajadors) {
       this.temas = temas;
       this.comunidad = comunidad;
       this.tituloProyecto = tituloProyecto;
       this.descripcion = descripcion;
       this.alcance = alcance;
       this.presupuesto = presupuesto;
       this.fechaInicio = fechaInicio;
       this.fechaFinalizacion = fechaFinalizacion;
       this.objetivos = objetivos;
       this.proyectoTrabajadors = proyectoTrabajadors;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idProyecto", unique=true, nullable=false)
    public Integer getIdProyecto() {
        return this.idProyecto;
    }
    
    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="temas_idtemas", nullable=false)
    public Temas getTemas() {
        return this.temas;
    }
    
    public void setTemas(Temas temas) {
        this.temas = temas;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="comunidad_idcomunidad", nullable=false)
    public Comunidad getComunidad() {
        return this.comunidad;
    }
    
    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    
    @Column(name="tituloProyecto", length=45)
    public String getTituloProyecto() {
        return this.tituloProyecto;
    }
    
    public void setTituloProyecto(String tituloProyecto) {
        this.tituloProyecto = tituloProyecto;
    }

    
    @Column(name="descripcion", length=45)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="alcance", length=45)
    public String getAlcance() {
        return this.alcance;
    }
    
    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    
    @Column(name="presupuesto", precision=10, scale=0)
    public Long getPresupuesto() {
        return this.presupuesto;
    }
    
    public void setPresupuesto(Long presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaInicio", length=19)
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaFinalizacion", length=19)
    public Date getFechaFinalizacion() {
        return this.fechaFinalizacion;
    }
    
    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="proyecto")
    public Set getObjetivos() {
        return this.objetivos;
    }
    
    public void setObjetivos(Set objetivos) {
        this.objetivos = objetivos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="proyecto")
    public Set getProyectoTrabajadors() {
        return this.proyectoTrabajadors;
    }
    
    public void setProyectoTrabajadors(Set proyectoTrabajadors) {
        this.proyectoTrabajadors = proyectoTrabajadors;
    }



}


