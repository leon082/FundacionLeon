/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Control.NegocioGeneral;
import dao.IntegrantePoblaDaoImpl;
import dao.RepresentanteDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Integrantepoblacion;
import modelo.Representante;
import modelo.RepresentanteId;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class RepresentanteBean {

    /**
     * Creates a new instance of proyectoBean
     */
    private Representante representante;
    private List<Representante> listaRepresentante;
    private List<Representante> listaRepresentanteEliminar;

    private NegocioGeneral Negocio;
    private String v_select_representante;
    private ArrayList<SelectItem> itemsPersonaComunidad;

    public RepresentanteBean() {
        Negocio = new NegocioGeneral();
        representante = new Representante();
        itemsPersonaComunidad = Negocio.Consultar_Personas_Comunidad();
    }

    @PostConstruct
    public void init() {
        listaRepresentante = Negocio.Consultar_All_Representante();

    }

    public String getV_select_representante() {
        return v_select_representante;
    }

    public void setV_select_representante(String v_select_representante) {
        this.v_select_representante = v_select_representante;
    }

    public ArrayList<SelectItem> getItemsPersonaComunidad() {
        return itemsPersonaComunidad;
    }

    public void setItemsPersonaComunidad(ArrayList<SelectItem> itemsPersonaComunidad) {
        this.itemsPersonaComunidad = itemsPersonaComunidad;
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }

    public List<Representante> getListaRepresentante() {
        return listaRepresentante;
    }

    public void setListaRepresentante(List<Representante> listaRepresentante) {
        this.listaRepresentante = listaRepresentante;
    }

    public List<Representante> getListaRepresentanteEliminar() {
        return listaRepresentanteEliminar;
    }

    public void setListaRepresentanteEliminar(List<Representante> listaRepresentanteEliminar) {
        this.listaRepresentanteEliminar = listaRepresentanteEliminar;
    }

    public void crear() {
        try {
            RepresentanteDaoImpl representanteDao = new RepresentanteDaoImpl();
            IntegrantePoblaDaoImpl integranteDao = new IntegrantePoblaDaoImpl();
            Integrantepoblacion integrante = integranteDao.findById(Integer.parseInt(v_select_representante));
            representante.setIntegrantepoblacion(integrante);

            RepresentanteDaoImpl repDao = new RepresentanteDaoImpl();
            List<Representante> rep = repDao.findLastId();
            //autoincremental
            int val = 1;
            if (!rep.isEmpty()) {
                val = rep.get(0).getId().getIdrepresentante() + 1;
            }
            RepresentanteId repId = new RepresentanteId(val, Integer.parseInt(v_select_representante));
            representante.setId(repId);

            representanteDao.create(representante);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
            limpiarForma();
            
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "" + e);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
 
        
    }
    
    public void limpiarForma(){
         representante = new Representante();
         listaRepresentante = Negocio.Consultar_All_Representante();
         v_select_representante="";
    }

    public void modificar(RowEditEvent event) {
        
        Object ob= event.getObject();
        Representante pr = (Representante) ob ;
        
        //Se modifica el valor en la tabla 
        for (SelectItem proyecto1 : itemsPersonaComunidad) {
            if(proyecto1.getValue() ==  pr.getIntegrantepoblacion().getIdIntegrante()){
                pr.getIntegrantepoblacion().setNombres(proyecto1.getLabel());
                break;
            }
        }
         
        RepresentanteDaoImpl representanteDao = new RepresentanteDaoImpl();
        representanteDao.update(pr);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminar(Representante representanteEliminar) {
        RepresentanteDaoImpl representanteDao = new RepresentanteDaoImpl();
        representanteDao.delete(representanteEliminar);
        listaRepresentante = Negocio.Consultar_All_Representante();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
