package modelo;
// Generated 21/05/2016 07:17:23 PM by Hibernate Tools 3.6.0


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

/**
 * Ocupacion generated by hbm2java
 */
@Entity
@Table(name="ocupacion"
    ,catalog="proyecto"
)
public class Ocupacion  implements java.io.Serializable {


     private Integer idocupacion;
     private ClaseTrabajador claseTrabajador;
     private String descripcion;
     private Set trabajadors = new HashSet(0);

    public Ocupacion() {
    }
   
    public Ocupacion(Integer idocupacion) {
        this.idocupacion = idocupacion;
    }
    
    public Ocupacion(ClaseTrabajador claseTrabajador) {
        this.claseTrabajador = claseTrabajador;
    }
    public Ocupacion(ClaseTrabajador claseTrabajador, String descripcion, Set trabajadors) {
       this.claseTrabajador = claseTrabajador;
       this.descripcion = descripcion;
       this.trabajadors = trabajadors;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idocupacion", unique=true, nullable=false)
    public Integer getIdocupacion() {
        return this.idocupacion;
    }
    
    public void setIdocupacion(Integer idocupacion) {
        this.idocupacion = idocupacion;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="clase_trabajador_idclase_trabajador", nullable=false)
    public ClaseTrabajador getClaseTrabajador() {
        return this.claseTrabajador;
    }
    
    public void setClaseTrabajador(ClaseTrabajador claseTrabajador) {
        this.claseTrabajador = claseTrabajador;
    }

    
    @Column(name="descripcion", length=45)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="ocupacion")
    public Set getTrabajadors() {
        return this.trabajadors;
    }
    
    public void setTrabajadors(Set trabajadors) {
        this.trabajadors = trabajadors;
    }




}


