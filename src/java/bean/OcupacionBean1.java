/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import Control.NegocioGeneral;
import dao.OcupacionDaoImpl;
import dao.ProyectoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.ClaseTrabajador;
import modelo.Ocupacion;
import modelo.Proyecto;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class OcupacionBean1 implements java.io.Serializable {

    /**
     * Creates a new instance of proyectoBean
     */
    private Ocupacion ocupacion;
    private List<Ocupacion> listaOcupacion;
    private List<Ocupacion> listaOcupacionEliminar;
    private ArrayList<SelectItem> itemsclaseTrabajador;
    private NegocioGeneral Negocio;
    private String v_select_ocupacion;
    
  
  
    public OcupacionBean1(){
        ocupacion = new Ocupacion();
        Negocio = new NegocioGeneral();         
        itemsclaseTrabajador = Negocio.Consultar_claseTrabajador_combox();
        listaOcupacion = Negocio.Consultar_Ocupaciones();
        
    }

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Ocupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    public List<Ocupacion> getListaOcupacion() {
        return listaOcupacion;
    }

    public void setListaOcupacion(List<Ocupacion> listaOcupacion) {
        this.listaOcupacion = listaOcupacion;
    }

    public List<Ocupacion> getListaOcupacionEliminar() {
        return listaOcupacionEliminar;
    }

    public NegocioGeneral getNegocio() {
        return Negocio;
    }

    public void setNegocio(NegocioGeneral Negocio) {
        this.Negocio = Negocio;
    }

    public ArrayList<SelectItem> getItemsclaseTrabajador() {
        return itemsclaseTrabajador;
    }

    public void setItemsclaseTrabajador(ArrayList<SelectItem> itemsclaseTrabajador) {
        this.itemsclaseTrabajador = itemsclaseTrabajador;
    }

    public String getV_select_ocupacion() {
        return v_select_ocupacion;
    }

    public void setV_select_ocupacion(String v_select_ocupacion) {
        this.v_select_ocupacion = v_select_ocupacion;
    }

    public void setListaOcupacionEliminar(List<Ocupacion> listaOcupacionEliminar) {
        this.listaOcupacionEliminar = listaOcupacionEliminar;
    }
    public void limpiarForma(){
        ocupacion = new Ocupacion();
        listaOcupacion = Negocio.Consultar_Ocupaciones();
        v_select_ocupacion="";
        
     }
    public void crear(){
        OcupacionDaoImpl ocupacionDao = new OcupacionDaoImpl();
        ocupacion.setClaseTrabajador(new ClaseTrabajador(Integer.parseInt(v_select_ocupacion)));
        
        ocupacionDao.create(ocupacion);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        limpiarForma();
    }
    
         public void modificar(RowEditEvent event) {
       
        Object ob= event.getObject();
        Ocupacion tb = (Ocupacion) ob ;
        
        //Se modifica el valor en la tabla
        for (SelectItem proyecto1 : itemsclaseTrabajador) {
            if(proyecto1.getValue() ==  tb.getClaseTrabajador().getIdclaseTrabajador()){
                tb.getClaseTrabajador().setDescripcion(proyecto1.getLabel());
                break;
            }
        }

         OcupacionDaoImpl ocupacionDao = new OcupacionDaoImpl();
        ocupacionDao.update(tb);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
   
      public void eliminar( int IdOcupacion){
        OcupacionDaoImpl ocupacionDao = new OcupacionDaoImpl();
      for (Ocupacion ocupacionEliminar : listaOcupacion) {
            if(ocupacionEliminar.getIdocupacion()== IdOcupacion){
               ocupacionDao.delete(ocupacionEliminar); 
               break;
            }
        }
        listaOcupacion = Negocio.Consultar_Ocupaciones();

       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El proyecto Fue eliminado con exito.");
       FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
