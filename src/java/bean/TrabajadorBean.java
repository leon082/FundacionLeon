/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import Control.NegocioGeneral;
import dao.TrabajadorDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Ocupacion;
import modelo.Trabajador;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class TrabajadorBean implements java.io.Serializable  {
    
    private NegocioGeneral Negocio;

    /**
     * Creates a new instance of proyectoBean
     */
    private Trabajador trabajador;
    private List<Trabajador> listaTrabajador;

      //Combos
    private ArrayList<SelectItem> itemsOcupacion;
    private String v_select_ocupacion;
    public Trabajador getTrabajador() {
        return trabajador;
    }

    

    public TrabajadorBean() {
        trabajador = new Trabajador();  
        Negocio = new NegocioGeneral();
        itemsOcupacion = Negocio.Consultar_Ocupacion_combo();
        listaTrabajador = Negocio.Consultar_Trabajadores();
    }
    
      public void limpiarForma(){
        trabajador = new Trabajador();
        listaTrabajador = Negocio.Consultar_Trabajadores();
        v_select_ocupacion="";
        
     }
    
    public void crear(){
        TrabajadorDaoImpl trabajadorDao = new TrabajadorDaoImpl();
        
          //Se crea la ocupacion seleccionada selecionado
        trabajador.setOcupacion(new Ocupacion(Integer.parseInt(v_select_ocupacion)));
        
        trabajadorDao.create(trabajador);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        limpiarForma();
        
        
    }
      public void modificar(RowEditEvent event) {
       
        Object ob= event.getObject();
        Trabajador tb = (Trabajador) ob ;
        
        //Se modifica el valor en la tabla
        for (SelectItem proyecto1 : itemsOcupacion) {
            if(proyecto1.getValue() ==  tb.getOcupacion().getIdocupacion()){
                tb.getOcupacion().setDescripcion(proyecto1.getLabel());
                break;
            }
        }

        TrabajadorDaoImpl trabajadorDao = new TrabajadorDaoImpl();
        trabajadorDao.update(tb);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
      
 
    
    public void eliminar( int IdTrabajador){
        TrabajadorDaoImpl trabajadorDao = new TrabajadorDaoImpl();
      for (Trabajador trabajadorEliminar : listaTrabajador) {
            if(trabajadorEliminar.getIdtrabajador()== IdTrabajador){
               trabajadorDao.delete(trabajadorEliminar); 
               break;
            }
        }
        listaTrabajador = Negocio.Consultar_Trabajadores();

       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El proyecto Fue eliminado con exito.");
       FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public NegocioGeneral getNegocio() {
        return Negocio;
    }

    public void setNegocio(NegocioGeneral Negocio) {
        this.Negocio = Negocio;
    }

    public ArrayList<SelectItem> getItemsOcupacion() {
        return itemsOcupacion;
    }

    public void setItemsOcupacion(ArrayList<SelectItem> itemsOcupacion) {
        this.itemsOcupacion = itemsOcupacion;
    }

    public String getV_select_ocupacion() {
        return v_select_ocupacion;
    }

    public void setV_select_ocupacion(String v_select_ocupacion) {
        this.v_select_ocupacion = v_select_ocupacion;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public List<Trabajador> getListaTrabajador() {
        return listaTrabajador;
    }

    public void setListaTrabajador(List<Trabajador> listaTrabajador) {
        this.listaTrabajador = listaTrabajador;
    }

    
}
