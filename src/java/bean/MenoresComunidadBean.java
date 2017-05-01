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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import modelo.Integrantepoblacion;


/**
 *
 * @author Cesar
 */
@ManagedBean
@ViewScoped
public class MenoresComunidadBean {

    /**
     * Creates a new instance of proyectoBean
     */

    private List<Integrantepoblacion> listaMenoresComunidad;   
    private Consultas_BD BD;
    private Servicio_BD obj;
    private NegocioGeneral Negocio;
    
     //Combos
    private ArrayList<SelectItem> itemsComunida;
    private String v_select_comunidad;

    public MenoresComunidadBean() {
        
        Negocio = new NegocioGeneral();
        obj=new Servicio_BD();
        BD=new Consultas_BD();
        itemsComunida = Negocio.Consultar_Comunida_combo();
        
    }
    
     public void Consultar() {
          
        listaMenoresComunidad = BD.MenoresPoblacion(v_select_comunidad,obj);
        
    }

 
    public Consultas_BD getBD() {
        return BD;
    }

    public void setBD(Consultas_BD BD) {
        this.BD = BD;
    }

    public Servicio_BD getObj() {
        return obj;
    }

    public void setObj(Servicio_BD obj) {
        this.obj = obj;
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

   

    public List<Integrantepoblacion> getListaMenoresComunidad() {
        return listaMenoresComunidad;
    }

    public void setListaMenoresComunidad(List<Integrantepoblacion> listaMenoresComunidad) {
        this.listaMenoresComunidad = listaMenoresComunidad;
    }

    public NegocioGeneral getNegocio() {
        return Negocio;
    }

    public void setNegocio(NegocioGeneral Negocio) {
        this.Negocio = Negocio;
    }
    
    
    
}
