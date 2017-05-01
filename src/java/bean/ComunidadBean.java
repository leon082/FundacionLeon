/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Control.NegocioGeneral;
import dao.ComunidadDaoImpl;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Comunidad;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class ComunidadBean {

    /**
     * Creates a new instance of claseTrabajadorBean
     */
    private Comunidad comunidad;
    private List<Comunidad> listaComunidad;
    private List<Comunidad> listaComunidadEliminar;
    private NegocioGeneral Negocio;

    public ComunidadBean() {
        comunidad = new Comunidad();
        Negocio = new NegocioGeneral();
        listaComunidad = Negocio.Consultar_Comunidades();

    }

  

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public List<Comunidad> getListaComunidad() {
        return listaComunidad;
    }

    public void setListaComunidad(List<Comunidad> listaComunidad) {
        this.listaComunidad = listaComunidad;
    }

    public List<Comunidad> getListaComunidadEliminar() {
        return listaComunidadEliminar;
    }

    public void setListaComunidadEliminar(List<Comunidad> listaComunidadEliminar) {
        this.listaComunidadEliminar = listaComunidadEliminar;
    }
 public void limpiarForma(){
        comunidad = new Comunidad();
        listaComunidad = Negocio.Consultar_Comunidades();
        
        
     }
    public void crear() {
        ComunidadDaoImpl claseDao = new ComunidadDaoImpl();
        claseDao.create(comunidad);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        limpiarForma();
    }

          public void modificar(RowEditEvent event) {
       
        Object ob= event.getObject();
        Comunidad tb = (Comunidad) ob ;
        
       ComunidadDaoImpl ocupacionDao = new ComunidadDaoImpl();
        ocupacionDao.update(tb);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminar(int IdComunidad) {
        ComunidadDaoImpl claseDao = new ComunidadDaoImpl();
       for (Comunidad comunidadEliminar : listaComunidad) {
            if(comunidadEliminar.getIdcomunidad()== IdComunidad){
               claseDao.delete(comunidadEliminar); 
               break;
            }
       }
             listaComunidad = Negocio.Consultar_Comunidades();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    
       }
}

