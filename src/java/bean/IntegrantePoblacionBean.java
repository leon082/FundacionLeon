/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import Control.NegocioGeneral;
import dao.IntegrantePoblaDaoImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Comunidad;
import modelo.Integrantepoblacion;
import org.primefaces.event.RowEditEvent;


/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class IntegrantePoblacionBean {
    
    /**
     * Creates a new instance of proyectoBean
     */
    private Integrantepoblacion integrantepoblacion;
    private List<Integrantepoblacion> listaIntegrantepoblacion;
    
    private NegocioGeneral Negocio;

    //Combos
    private ArrayList<SelectItem> itemsComunida;
    private String v_select_comunidad;
    
    public IntegrantePoblacionBean(){
        
        Negocio = new NegocioGeneral();

        integrantepoblacion = new Integrantepoblacion();
        itemsComunida = Negocio.Consultar_Comunida_combo();
        listaIntegrantepoblacion = Negocio.Consultar_Integrante_poblacion();

    }

    public void limpiarForma(){
        integrantepoblacion = new Integrantepoblacion();
        listaIntegrantepoblacion = Negocio.Consultar_Integrante_poblacion();
        v_select_comunidad="";
     }
    
    
    public void crear(){
        
        IntegrantePoblaDaoImpl integrantepoblaDao = new IntegrantePoblaDaoImpl();
        
        //se crea la comunidad selecionada

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = format.format(integrantepoblacion.getFecha_select());
        integrantepoblacion.setFechaNacimiento(fecha);
        integrantepoblacion.setComunidad(new Comunidad(Integer.parseInt(v_select_comunidad)));  
        
        integrantepoblaDao.create(integrantepoblacion);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        limpiarForma();
 
    }
    
    public void modificar(RowEditEvent event){
        
        Object ob= event.getObject();
        Integrantepoblacion obj = (Integrantepoblacion) ob ;
        
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String fecha = format.format(obj.getFecha_select());
        obj.setFechaNacimiento(fecha);
        
        //Se modifica el valor en la tabla 
        for (SelectItem proyecto1 : itemsComunida) {
            if(proyecto1.getValue() ==  obj.getComunidad().getIdcomunidad()){
                obj.getComunidad().setNombre(proyecto1.getLabel());
                break;
            }
        }

        IntegrantePoblaDaoImpl integrantepoblaDao = new IntegrantePoblaDaoImpl();
        integrantepoblaDao.update(obj);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminar(int Id){
        
        IntegrantePoblaDaoImpl integrantepoblaDao = new IntegrantePoblaDaoImpl();
         
        for (Integrantepoblacion integrantepoblacionEliminar : listaIntegrantepoblacion) {
            if(integrantepoblacionEliminar.getIdIntegrante() == Id){
               integrantepoblaDao.delete(integrantepoblacionEliminar); 
               break;
            }
        }
        
        listaIntegrantepoblacion = Negocio.Consultar_Integrante_poblacion();
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

      
    public Integrantepoblacion getIntegrantepoblacion() {
        return integrantepoblacion;
    }

    public void setIntegrantepoblacion(Integrantepoblacion integrantepoblacion) {
        this.integrantepoblacion = integrantepoblacion;
    }

    public List<Integrantepoblacion> getListaIntegrantepoblacion() {
        return listaIntegrantepoblacion;
    }

    public void setListaIntegrantepoblacion(List<Integrantepoblacion> listaIntegrantepoblacion) {
        this.listaIntegrantepoblacion = listaIntegrantepoblacion;
    }

    public ArrayList<SelectItem> getItemsComunida() {
        return itemsComunida;
    }

    public void setItemsComunida(ArrayList<SelectItem> itemsComunida) {
        this.itemsComunida = itemsComunida;
    }

    public String getV_select_comunidad() {
        return v_select_comunidad;
    }

    public void setV_select_comunidad(String v_select_comunidad) {
        this.v_select_comunidad = v_select_comunidad;
    }

    public NegocioGeneral getNegocio() {
        return Negocio;
    }

    public void setNegocio(NegocioGeneral Negocio) {
        this.Negocio = Negocio;
    }

 }
