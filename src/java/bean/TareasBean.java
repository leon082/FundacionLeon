/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Control.NegocioGeneral;
import dao.ProyectoTrabajadorDaoImpl;
import dao.TareasDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.ProyectoTrabajador;
import modelo.Tareas;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Cesar
 */
@ManagedBean
@ViewScoped
public class TareasBean {

    /**
     * Creates a new instance of proyectoBean
     */
    private Tareas tareas;
    private List<Tareas> listaTareas;
    private List<Tareas> listaTareasEliminar;
    private NegocioGeneral Negocio;
    private String v_selectProyecto;
    private int v_selectTrabajadores;
    //Combos
    private ArrayList<SelectItem> itemsProyectos;
    private ArrayList<SelectItem> itemsTrabajadores;

    public int getV_selectTrabajadores() {
        return v_selectTrabajadores;
    }

    public void setV_selectTrabajadores(int v_selectTrabajadores) {
        this.v_selectTrabajadores = v_selectTrabajadores;
    }

    public ArrayList<SelectItem> getItemsTrabajadores() {
        return itemsTrabajadores;
    }

    public void setItemsTrabajadores(ArrayList<SelectItem> itemsTrabajadores) {
        this.itemsTrabajadores = itemsTrabajadores;
    }

    public NegocioGeneral getNegocio() {
        return Negocio;
    }

    public void setNegocio(NegocioGeneral Negocio) {
        this.Negocio = Negocio;
    }

    public String getV_selectProyecto() {
        return v_selectProyecto;
    }

    public void setV_selectProyecto(String v_selectProyecto) {
        this.v_selectProyecto = v_selectProyecto;
    }

    public ArrayList<SelectItem> getItemsProyectos() {
        return itemsProyectos;
    }

    public void setItemsProyectos(ArrayList<SelectItem> itemsProyectos) {
        this.itemsProyectos = itemsProyectos;
    }

    public Tareas getTareas() {
        return tareas;
    }

    public void setTareas(Tareas tareas) {
        this.tareas = tareas;
    }

    public List<Tareas> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(List<Tareas> listaTareas) {
        this.listaTareas = listaTareas;
    }

    public List<Tareas> getListaTareasEliminar() {
        return listaTareasEliminar;
    }

    public void setListaTareasEliminar(List<Tareas> listaTareasEliminar) {
        this.listaTareasEliminar = listaTareasEliminar;
    }

    public TareasBean() {
        tareas = new Tareas();
        Negocio = new NegocioGeneral();
        itemsProyectos = Negocio.Consultar_Proyectos_combo();
        
    }
    
    public void cargarTrabajadores(){
        itemsTrabajadores = Negocio.Consultar_Proyectos_Trabajador_combo(Integer.parseInt(v_selectProyecto));
    }

    @PostConstruct
    public void init() {
        listaTareas = Negocio.Consultar_All_Tareas();

    }
    
    public void limpiarforma(){
       tareas = new Tareas();
       listaTareas = Negocio.Consultar_All_Tareas();
       v_selectProyecto = "";
       v_selectTrabajadores = 0;
    }

    public void crear() {
        TareasDaoImpl tareasDao = new TareasDaoImpl();
        ProyectoTrabajadorDaoImpl proyectoTrabajadorDao = new ProyectoTrabajadorDaoImpl();
        ProyectoTrabajador proyectoTrabajador = proyectoTrabajadorDao.findById(v_selectTrabajadores);
        //ProyectoTrabajador proyectoTrabajador = proyectoTrabajadorDao.findById(Integer.parseInt("1"));
        tareas.setProyectoTrabajador(proyectoTrabajador);
        tareasDao.create(tareas);

        limpiarforma();
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void modificar(RowEditEvent event) {
        
        Object ob= event.getObject();
        Tareas pr = (Tareas) ob ;
        TareasDaoImpl tareasDao = new TareasDaoImpl();
        tareasDao.update(pr);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void eliminar(Tareas tarea) {
        TareasDaoImpl tareasDao = new TareasDaoImpl();
        tareasDao.delete(tarea);
        listaTareas = Negocio.Consultar_All_Tareas();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
