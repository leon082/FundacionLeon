/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import dao.TemasDaoImpl;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Temas;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class TemasBean {

    /**
     * Creates a new instance of proyectoBean
     */
    private Temas temas;
    private List<Temas> listaTemas;
    private List<Temas> listaTemasEliminar;

    public Temas getTemas() {
        return temas;
    }

    public void setTemas(Temas temas) {
        this.temas = temas;
    }

    public List<Temas> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(List<Temas> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public List<Temas> getListaTemasEliminar() {
        return listaTemasEliminar;
    }

    public void setListaTemasEliminar(List<Temas> listaTemasEliminar) {
        this.listaTemasEliminar = listaTemasEliminar;
    }

    
    
    public TemasBean() {
        temas = new Temas();
    }
    
    @PostConstruct
    public void init(){
        TemasDaoImpl temasDao = new TemasDaoImpl();
        listaTemas = temasDao.findAll();
        
    }
    
    public void crear(){
        TemasDaoImpl temasDao = new TemasDaoImpl();
        temasDao.create(temas);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void modificar(){
        TemasDaoImpl temasDao = new TemasDaoImpl();
        temasDao.update(temas);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void eliminar(){
        TemasDaoImpl temasDao = new TemasDaoImpl();
        for (Temas temasEliminar : listaTemasEliminar) {
            temasDao.delete(temasEliminar);
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
