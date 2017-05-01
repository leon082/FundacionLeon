/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Control.Consultas_BD;
import Control.NegocioGeneral;
import Control.Servicio_BD;
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
import modelo.Evaluacion;
import modelo.ProyectoTrabajador;
import modelo.Tareas;
import org.primefaces.event.RowEditEvent;


/**
 *
 * @author Cesar
 */
@ManagedBean
@ViewScoped
public class EvaluacionBean {

    /**
     * Creates a new instance of proyectoBean
     */

    private List<Evaluacion> listaEvaluaciones;   
    private  Consultas_BD BD;
    private Servicio_BD obj;
    private int valor;
    
    public EvaluacionBean() {
        obj=new Servicio_BD();
        BD=new Consultas_BD();
        valor = 100;
        listaEvaluaciones = BD.Evaluacion(valor,obj);
        
    }
    
     public void Consultar() {
          
        listaEvaluaciones = BD.Evaluacion(valor,obj);
        
    }

    public List<Evaluacion> getListaEvaluaciones() {
        return listaEvaluaciones;
    }

    public void setListaEvaluaciones(List<Evaluacion> listaEvaluaciones) {
        this.listaEvaluaciones = listaEvaluaciones;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
