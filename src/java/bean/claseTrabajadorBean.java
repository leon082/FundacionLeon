/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.ClaseTrabajadorDaoImpl;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.ClaseTrabajador;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class claseTrabajadorBean {

    /**
     * Creates a new instance of claseTrabajadorBean
     */
    private ClaseTrabajador claseTrabajador;
    private List<ClaseTrabajador> listaClaseTrabajador ;
    private List<ClaseTrabajador> listaClaseTrabajadorEliminar ;
    
    public claseTrabajadorBean() {
        claseTrabajador = new ClaseTrabajador();
    }
    
    @PostConstruct
    public void init(){
        ClaseTrabajadorDaoImpl claseDao = new ClaseTrabajadorDaoImpl();
        listaClaseTrabajador = claseDao.findAll();
    }

    public ClaseTrabajador getClaseTrabajador() {
        return claseTrabajador;
    }

    public void setClaseTrabajador(ClaseTrabajador claseTrabajador) {
        this.claseTrabajador = claseTrabajador;
    }

    public List<ClaseTrabajador> getListaClaseTrabajador() {
        return listaClaseTrabajador;
    }

    public void setListaClaseTrabajador(List<ClaseTrabajador> listaClaseTrabajador) {
        this.listaClaseTrabajador = listaClaseTrabajador;
    }

    public List<ClaseTrabajador> getListaClaseTrabajadorEliminar() {
        return listaClaseTrabajadorEliminar;
    }

    public void setListaClaseTrabajadorEliminar(List<ClaseTrabajador> listaClaseTrabajadorEliminar) {
        this.listaClaseTrabajadorEliminar = listaClaseTrabajadorEliminar;
    }
    
    public void crear(){
        ClaseTrabajadorDaoImpl claseDao = new ClaseTrabajadorDaoImpl();
        claseDao.create(claseTrabajador);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    public void modificar(){
        ClaseTrabajadorDaoImpl claseDao = new ClaseTrabajadorDaoImpl();
        claseDao.update(claseTrabajador);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    
    public void eliminar(){
        ClaseTrabajadorDaoImpl claseDao = new ClaseTrabajadorDaoImpl();
        for (ClaseTrabajador clase_trabajadorEliminar: listaClaseTrabajador) {
            claseDao.delete(clase_trabajadorEliminar);
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
