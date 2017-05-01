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
public class ProyectoXTemaBean implements java.io.Serializable {

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
    
    public ProyectoXTemaBean() {

      Negocio = new NegocioGeneral();
      proyecto = new Proyecto();
      itemsComTemas = Negocio.Consultar_Temas_combo();   
      itemsComunida = Negocio.Consultar_Comunida_combo();   
      listaProyecto = Negocio.Consultar_Proyectos();

    }
 
     
    public void updatetable(){   
        
        ProyectoDaoImpl proyectoDao = new ProyectoDaoImpl();
        listaProyecto = proyectoDao.findByIdComunidad(Integer.parseInt(v_select_comunidad ));    
      
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
