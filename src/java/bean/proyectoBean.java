/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import Control.NegocioGeneral;
import dao.ProyectoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Comunidad;
import modelo.Proyecto;
import modelo.Temas;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class proyectoBean implements java.io.Serializable {

    /**
     * Creates a new instance of proyectoBean
     */
    private Proyecto proyecto;
    private NegocioGeneral Negocio;
    
    private List<Proyecto> listaProyecto;
    //Combos
    private ArrayList<SelectItem> itemsComunida;
    private ArrayList<SelectItem> itemsComTemas;
    private String v_select_comunidad;
    private String v_select_tema;
    
    public proyectoBean() {

      Negocio = new NegocioGeneral();
      proyecto = new Proyecto();
      itemsComTemas = Negocio.Consultar_Temas_combo();   
      itemsComunida = Negocio.Consultar_Comunida_combo();   
      listaProyecto = Negocio.Consultar_Proyectos();

    }
 
     public void limpiarForma(){
        proyecto = new Proyecto();
        listaProyecto = Negocio.Consultar_Proyectos();
        v_select_comunidad="";
        v_select_tema="";
     }
    
     public void modificar(RowEditEvent event) {
       
        Object ob= event.getObject();
        Proyecto pr = (Proyecto) ob ;
        
        //Se modifica el valor en la tabla
        for (SelectItem proyecto1 : itemsComTemas) {
            if(proyecto1.getValue() ==  pr.getTemas().getIdtemas()){
                pr.getTemas().setDescripcion(proyecto1.getLabel());
                break;
            }
        }
       
        //Se modifica el valor en la tabla 
        for (SelectItem proyecto1 : itemsComunida) {
            if(proyecto1.getValue() ==  pr.getComunidad().getIdcomunidad()){
                pr.getComunidad().setNombre(proyecto1.getLabel());
                break;
            }
        }
        
        ProyectoDaoImpl proyectoDao = new ProyectoDaoImpl();
        proyectoDao.update(pr);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El proyecto fue modificado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void crear(){
        
      ProyectoDaoImpl proyectoDao = new ProyectoDaoImpl();
      
      //Se crea el tema selecionado
      proyecto.setTemas(new Temas(Integer.parseInt(v_select_tema )));     
      //se crea la comunidad selecionada
      proyecto.setComunidad(new Comunidad(Integer.parseInt(v_select_comunidad)));  
     
      proyectoDao.create(proyecto);

      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El proyecto Fue creado con exito.");
      FacesContext.getCurrentInstance().addMessage(null, msg);
      
      limpiarForma();
      
    }
    
    public void eliminar(int IdProyecto){
        ProyectoDaoImpl proyectoDao = new ProyectoDaoImpl();

        for (Proyecto proyectoEliminar : listaProyecto) {
            if(proyectoEliminar.getIdProyecto() == IdProyecto){
               proyectoDao.delete(proyectoEliminar); 
               break;
            }
        }
        listaProyecto = Negocio.Consultar_Proyectos();      

       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El proyecto Fue eliminado con exito.");
       FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public ArrayList<SelectItem> getItemsComunida() {
        return itemsComunida;
    }

    public void setItemsComunida(ArrayList<SelectItem> itemsComunida) {
        this.itemsComunida = itemsComunida;
    }

    public ArrayList<SelectItem> getItemsComTemas() {
        return itemsComTemas;
    }

    public void setItemsComTemas(ArrayList<SelectItem> itemsComTemas) {
        this.itemsComTemas = itemsComTemas;
    }
    
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<Proyecto> getListaProyecto() {
        return listaProyecto;
    }

    public void setListaProyecto(List<Proyecto> listaProyecto) {
        this.listaProyecto = listaProyecto;
    }

    public String getV_select_comunidad() {
        return v_select_comunidad;
    }

    public void setV_select_comunidad(String v_select_comunidad) {
        this.v_select_comunidad = v_select_comunidad;
    }

    public String getV_select_tema() {
        return v_select_tema;
    }

    public void setV_select_tema(String v_select_tema) {
        this.v_select_tema = v_select_tema;
    }
 
    
}
