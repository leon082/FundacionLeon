/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import Control.NegocioGeneral;
import dao.ClaseTrabajadorDaoImpl;
import dao.objetivoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Objetivo;
import modelo.Proyecto;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class ObjetivoBean {

    /**
     * Creates a new instance of claseTrabajadorBean
     */
    private Objetivo objetivo;
    private List<Objetivo> listaobjetivo ;
    private List<Objetivo> listaObjetivoEliminar ;
    private String v_select_Proyecto;
    private ArrayList<SelectItem> itemsProyectos;
    private Boolean cumplimiento;
     private NegocioGeneral Negocio;
    
    public ObjetivoBean() {
        objetivo = new Objetivo();
        Negocio=new NegocioGeneral();
        itemsProyectos=Negocio.Consultar_Proyectos_comboLAL();
        listaobjetivo=Negocio.Consultar_Objetivos();    
 
    }

    public ArrayList<SelectItem> getItemsProyectos() {
        return itemsProyectos;
    }

    public void setItemsProyectos(ArrayList<SelectItem> itemsProyectos) {
        this.itemsProyectos = itemsProyectos;
    }

    public NegocioGeneral getNegocio() {
        return Negocio;
    }

    public void setNegocio(NegocioGeneral Negocio) {
        this.Negocio = Negocio;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public List<Objetivo> getListaobjetivo() {
        return listaobjetivo;
    }

    public void setListaobjetivo(List<Objetivo> listaobjetivo) {
        this.listaobjetivo = listaobjetivo;
    }

    public List<Objetivo> getListaObjetivoEliminar() {
        return listaObjetivoEliminar;
    }

    public void setListaObjetivoEliminar(List<Objetivo> listaObjetivoEliminar) {
        this.listaObjetivoEliminar = listaObjetivoEliminar;
    }

    public Boolean getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(Boolean cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public String getV_select_Proyecto() {
        return v_select_Proyecto;
    }

    public void setV_select_Proyecto(String v_select_Proyecto) {
        this.v_select_Proyecto = v_select_Proyecto;
    }
      
     public void limpiarForma(){
        objetivo = new Objetivo();
        listaobjetivo = Negocio.Consultar_Objetivos();
        v_select_Proyecto="";
        
     }
        
    public void crear(){
        objetivoDaoImpl objetivoDao = new objetivoDaoImpl();
        
        //Se crea el proyecto selecionado
        objetivo.setProyecto(new Proyecto(Integer.parseInt(v_select_Proyecto )));
     
        objetivoDao.create(objetivo);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        limpiarForma();
    }
  
    public void modificar(RowEditEvent event) {
       
        Object ob= event.getObject();
        Objetivo pr = (Objetivo) ob ;
        
        //Se modifica el valor en la tabla
        for (SelectItem proyecto1 : itemsProyectos) {
            if(proyecto1.getValue() ==  pr.getProyecto().getIdProyecto()){
                pr.getProyecto().setTituloProyecto(proyecto1.getLabel());
                break;
            }
        }     
      
        objetivoDaoImpl objetivoDao = new objetivoDaoImpl();  
        objetivoDao.update(pr);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El proyecto fue modificado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    
    public void eliminar(){
         objetivoDaoImpl objetivoDao = new objetivoDaoImpl(); 
        for (Objetivo objetivoEliminar: listaobjetivo) {
            objetivoDao.delete(objetivoEliminar);
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void eliminar(int Idobj){
        objetivoDaoImpl objetivoDao = new objetivoDaoImpl();

         for (Objetivo objetivoEliminar: listaobjetivo) {
            if(objetivoEliminar.getIdobjetivo() == Idobj){
               objetivoDao.delete(objetivoEliminar); 
               break;
            }
        }
        listaobjetivo = Negocio.Consultar_Objetivos();      

       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El proyecto Fue eliminado con exito.");
       FacesContext.getCurrentInstance().addMessage(null, msg);
    }

  
}
