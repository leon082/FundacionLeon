/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import Control.NegocioGeneral;
import dao.ProyectoDaoImpl;
import dao.ProyectoTrabajadorDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Proyecto;
import modelo.ProyectoTrabajador;
import modelo.Trabajador;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class ProyectoTrabajadorBean implements java.io.Serializable {

    /**
     * Creates a new instance of claseTrabajadorBean
     */

    private List<ProyectoTrabajador> listaProyectoTrabajador ;

    private ArrayList<SelectItem> itemsProyectos;
    private ArrayList<SelectItem> itemsTrabajadores;
    private String v_select_proyecto;
    private String v_select_trabajador;
    
    private NegocioGeneral Negocio;
  
    public ProyectoTrabajadorBean() {
 
        Negocio = new NegocioGeneral();
        
        itemsTrabajadores  =  Negocio.Consultar_Traba_combo();
        itemsProyectos     =  Negocio.Consultar_Proyectos_combo();
        listaProyectoTrabajador =  Negocio.Consultar_ProyectoTrabajador();
        
    }

    public void limpiarForma(){
        
        listaProyectoTrabajador =  Negocio.Consultar_ProyectoTrabajador();
        v_select_proyecto="";
        v_select_trabajador="";
    }

    public void crear(){
        
        ProyectoTrabajadorDaoImpl Proyectotrabajadordao = new ProyectoTrabajadorDaoImpl();
        
        ProyectoTrabajador proyectoTrabajador = new ProyectoTrabajador();
 
        proyectoTrabajador.setProyecto(new Proyecto(Integer.parseInt(v_select_proyecto)));
        proyectoTrabajador.setTrabajador(new Trabajador(Integer.parseInt(v_select_trabajador)));
        
        Proyectotrabajadordao.create(proyectoTrabajador);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito ");
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
       

        limpiarForma();     
    }
    
    public void modificar(RowEditEvent event) {
       
        Object ob= event.getObject();
        ProyectoTrabajador pr = (ProyectoTrabajador) ob ;
        
        //Se modifica el valor en la tabla
        for (SelectItem proyecto1 : itemsProyectos) {
            if(proyecto1.getValue() ==  pr.getProyecto().getIdProyecto()){
                pr.getProyecto().setDescripcion(proyecto1.getLabel());
                break;
            }
        }
       
        //Se modifica el valor en la tabla 
        for (SelectItem proyecto1 : itemsTrabajadores) {
            if(proyecto1.getValue() ==  pr.getTrabajador().getIdtrabajador()){
                pr.getTrabajador().setNombres(proyecto1.getLabel());
                break;
            }
        }
        
        ProyectoTrabajadorDaoImpl Proyectotrabajadordao = new ProyectoTrabajadorDaoImpl();
        Proyectotrabajadordao.update(pr);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    public void eliminar(int IdProyectoTrabajador){
        
         ProyectoTrabajadorDaoImpl Proyectotrabajadordao = new ProyectoTrabajadorDaoImpl();

        for (ProyectoTrabajador proyectoEliminar : listaProyectoTrabajador) {
            if(proyectoEliminar.getIdProyectoTrabajador() == IdProyectoTrabajador){
               Proyectotrabajadordao.delete(proyectoEliminar); 
               break;
            }
        }
        
        listaProyectoTrabajador =  Negocio.Consultar_ProyectoTrabajador();     
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ArrayList<SelectItem> getItemsProyectos() {
        return itemsProyectos;
    }

    public void setItemsProyectos(ArrayList<SelectItem> itemsProyectos) {
        this.itemsProyectos = itemsProyectos;
    }

    public ArrayList<SelectItem> getItemsTrabajadores() {
        return itemsTrabajadores;
    }

    public void setItemsTrabajadores(ArrayList<SelectItem> itemsTrabajadores) {
        this.itemsTrabajadores = itemsTrabajadores;
    }

    public String getV_select_proyecto() {
        return v_select_proyecto;
    }

    public void setV_select_proyecto(String v_select_proyecto) {
        this.v_select_proyecto = v_select_proyecto;
    }

    public String getV_select_trabajador() {
        return v_select_trabajador;
    }

    public void setV_select_trabajador(String v_select_trabajador) {
        this.v_select_trabajador = v_select_trabajador;
    }


    public List<ProyectoTrabajador> getListaProyectoTrabajador() {
        return listaProyectoTrabajador;
    }

    public void setListaProyectoTrabajador(List<ProyectoTrabajador> listaProyectoTrabajador) {
        this.listaProyectoTrabajador = listaProyectoTrabajador;
    }
    

}
