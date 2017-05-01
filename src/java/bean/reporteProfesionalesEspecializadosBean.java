/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Control.Consultas_BD;
import Control.NegocioGeneral;
import Control.Servicio_BD;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import modelo.MiddelwareTrabajador;
import modelo.Trabajador;

/**
 *
 * @author EQUIPO_5
 */
@Named(value = "reporteProfesionalesEspecializadosBean")
@RequestScoped
public class reporteProfesionalesEspecializadosBean {

    /**
     * Creates a new instance of reporteProfesionalesEspecializadosBean
     */
    private ArrayList<SelectItem> itemsProyectos;
    private String v_select_proyectos;
    private NegocioGeneral negocio;
    private List<MiddelwareTrabajador> listadoTrabajadoresProfesionales;

    public reporteProfesionalesEspecializadosBean() {
        negocio = new NegocioGeneral();
        itemsProyectos = negocio.Consultar_Proyectos_combo();
    }

    public NegocioGeneral getNegocio() {
        return negocio;
    }

    public void setNegocio(NegocioGeneral negocio) {
        this.negocio = negocio;
    }

    public List<MiddelwareTrabajador> getListadoTrabajadoresProfesionales() {
        return listadoTrabajadoresProfesionales;
    }

    public void setListadoTrabajadoresProfesionales(List<MiddelwareTrabajador> listadoTrabajadoresProfesionales) {
        this.listadoTrabajadoresProfesionales = listadoTrabajadoresProfesionales;
    }

    public ArrayList<SelectItem> getItemsProyectos() {
        return itemsProyectos;
    }

    public void setItemsProyectos(ArrayList<SelectItem> itemsProyectos) {
        this.itemsProyectos = itemsProyectos;
    }

    public String getV_select_proyectos() {
        return v_select_proyectos;
    }

    public void setV_select_proyectos(String v_select_proyectos) {
        this.v_select_proyectos = v_select_proyectos;
    }
    
    public void consultar(){
        Servicio_BD servicio = new Servicio_BD();
        Consultas_BD consulta = new Consultas_BD();
        listadoTrabajadoresProfesionales = consulta.listadoTrabajadoresProfesionalesByProyecto(v_select_proyectos, servicio);
    }

}
