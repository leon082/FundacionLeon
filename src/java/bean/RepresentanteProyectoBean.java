/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Control.NegocioGeneral;
import dao.ProyectoDaoImpl;
import dao.ProyectoTrabajadorDaoImpl;
import dao.RepresentanteProyectoDaoImpl;
import dao.TrabajadorDaoImpl;
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
import modelo.RepresentanteProyecto;
import modelo.RepresentanteProyectoId;
import modelo.Trabajador;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class RepresentanteProyectoBean {

    /**
     * Creates a new instance of proyectoBean
     */
    private RepresentanteProyecto representanteProyecto;
    private List<RepresentanteProyecto> listaRepresentanteProyecto;
    private List<RepresentanteProyecto> listaRepresentanteProyectoEliminar;

    private NegocioGeneral Negocio;
    private String v_select_representante;
    private String v_select_proyecto;
    private ArrayList<SelectItem> itemsComRepresentante;
    private ArrayList<SelectItem> itemsComProyectos;

    public RepresentanteProyectoBean() {
        representanteProyecto = new RepresentanteProyecto();
        Negocio = new NegocioGeneral();
        itemsComRepresentante = Negocio.Consultar_Traba_combo();
        itemsComProyectos = Negocio.Consultar_List_Proyectos();
    }

    @PostConstruct
    public void init() {

        listaRepresentanteProyecto = Negocio.Consultar_All_Representante_Proyectos();

    }

    public String getV_select_representante() {
        return v_select_representante;
    }

    public void setV_select_representante(String v_select_representante) {
        this.v_select_representante = v_select_representante;
    }

    public String getV_select_proyecto() {
        return v_select_proyecto;
    }

    public void setV_select_proyecto(String v_select_proyecto) {
        this.v_select_proyecto = v_select_proyecto;
    }

    public ArrayList<SelectItem> getItemsComRepresentante() {
        return itemsComRepresentante;
    }

    public void setItemsComRepresentante(ArrayList<SelectItem> itemsComRepresentante) {
        this.itemsComRepresentante = itemsComRepresentante;
    }

    public ArrayList<SelectItem> getItemsComProyectos() {
        return itemsComProyectos;
    }

    public void setItemsComProyectos(ArrayList<SelectItem> itemsComProyectos) {
        this.itemsComProyectos = itemsComProyectos;
    }

    public RepresentanteProyecto getRepresentanteProyecto() {
        return representanteProyecto;
    }

    public void setRepresentanteProyecto(RepresentanteProyecto representanteProyecto) {
        this.representanteProyecto = representanteProyecto;
    }

    public List<RepresentanteProyecto> getListaRepresentanteProyecto() {
        return listaRepresentanteProyecto;
    }

    public void setListaRepresentanteProyecto(List<RepresentanteProyecto> listaRepresentanteProyecto) {
        this.listaRepresentanteProyecto = listaRepresentanteProyecto;
    }

    public List<RepresentanteProyecto> getListaRepresentanteProyectoEliminar() {
        return listaRepresentanteProyectoEliminar;
    }

    public void setListaRepresentanteProyectoEliminar(List<RepresentanteProyecto> listaRepresentanteProyectoEliminar) {
        this.listaRepresentanteProyectoEliminar = listaRepresentanteProyectoEliminar;
    }

    public void crear() {
        RepresentanteProyectoDaoImpl representanteProyectoDao = new RepresentanteProyectoDaoImpl();
        ProyectoTrabajadorDaoImpl reProyTrabDao = new ProyectoTrabajadorDaoImpl();
        ProyectoDaoImpl proyectoDao = new ProyectoDaoImpl();
        TrabajadorDaoImpl trabDao = new TrabajadorDaoImpl();
        List<RepresentanteProyecto> listaRepresentantes = representanteProyectoDao.findAll();
        boolean existeRegistro = false;
        for (RepresentanteProyecto listaRepresentante : listaRepresentantes) {
            if (listaRepresentante.getProyectoTrabajador().getTrabajador().getIdtrabajador() == Integer.parseInt(v_select_representante)
                    && listaRepresentante.getProyectoTrabajador().getProyecto().getIdProyecto() == Integer.parseInt(v_select_proyecto)) {
                existeRegistro = true;

            }
        }
        //List<ProyectoTrabajador> listaProyectoTrabajador = reProyTrabDao.findByIdProyectoIdTrabajador(Integer.parseInt(v_select_proyecto), Integer.parseInt(v_select_representante));
        if (existeRegistro) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El registro ya existe");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            Proyecto proyecto = proyectoDao.findById(Integer.parseInt(v_select_proyecto));
            Trabajador trabajador = trabDao.findById(Integer.parseInt(v_select_representante));
            ProyectoTrabajador proyectoTrabajador = new ProyectoTrabajador(trabajador, proyecto);
            reProyTrabDao.create(proyectoTrabajador);
            RepresentanteProyectoId repId = new RepresentanteProyectoId(Integer.parseInt(v_select_representante), proyectoTrabajador.getIdProyectoTrabajador());

            representanteProyecto.setProyectoTrabajador(proyectoTrabajador);
            representanteProyecto.setId(repId);

            representanteProyectoDao.create(representanteProyecto);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void modificar() {
        try {
            ProyectoDaoImpl proyectoDao = new ProyectoDaoImpl();
            ProyectoTrabajadorDaoImpl proyectoTrabDao = new ProyectoTrabajadorDaoImpl();
            TrabajadorDaoImpl trabDao = new TrabajadorDaoImpl();
            Proyecto proyecto = proyectoDao.findById(Integer.parseInt(v_select_proyecto));
            Trabajador trabajador = trabDao.findById(Integer.parseInt(v_select_representante));

            List<ProyectoTrabajador> proyecto_trabajador = proyectoTrabDao.findByIdProyectoIdTrabajador(Integer.parseInt(v_select_proyecto), Integer.parseInt(v_select_proyecto));
            ProyectoTrabajador proyectoTrabajador;
            if (!proyecto_trabajador.isEmpty()) {
                proyectoTrabajador = proyecto_trabajador.get(0);
            } else {
                proyectoTrabajador = new ProyectoTrabajador(trabajador, proyecto);
                proyectoTrabDao.create(proyectoTrabajador);
            }
            RepresentanteProyectoId repId = new RepresentanteProyectoId(Integer.parseInt(v_select_representante), proyectoTrabajador.getIdProyectoTrabajador());
            representanteProyecto.setProyectoTrabajador(proyectoTrabajador);
            representanteProyecto.setId(repId);
            RepresentanteProyectoDaoImpl representanteProyectoDao = new RepresentanteProyectoDaoImpl();
            representanteProyectoDao.update(representanteProyecto);
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void eliminar(RepresentanteProyecto id) {
        RepresentanteProyectoDaoImpl representanteProyectoDao = new RepresentanteProyectoDaoImpl();
        //RepresentanteProyecto repProy = representanteProyectoDao.findById(id);
        representanteProyectoDao.delete(id);
        listaRepresentanteProyecto = Negocio.Consultar_All_Representante_Proyectos();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
