/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Control.NegocioGeneral;
import dao.TrabajadorDaoImpl;
import dao.UsuarioDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Trabajador;
import modelo.Usuario;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Johanna
 */
@ManagedBean
@RequestScoped
public class UsuarioBean implements java.io.Serializable  {

    /**
     * Creates a new instance of claseTrabajadorBean
     */
    private Usuario usuario;
    private List<Usuario> listaUsuario ;
    private List<Usuario> listaUsuarioEliminar ;
    private Trabajador trabajador;
 
    //Combos
    private ArrayList<SelectItem> itemsTrabajadores;      
    private String v_select_trabajador;
    private NegocioGeneral Negocio;


    
    public UsuarioBean() {   
        
        usuario = new Usuario();
        Negocio = new NegocioGeneral();

        itemsTrabajadores = Negocio.Consultar_Traba_combo();
        listaUsuario = Negocio.Consultar_Usuarios();
  
 }


    public void limpiarForma(){
        usuario = new Usuario();
        listaUsuario = Negocio.Consultar_Usuarios();
        v_select_trabajador="";
    }
    
    public void crear(){
        
        UsuarioDaoImpl claseDao = new UsuarioDaoImpl();
        
        //Se crea la ocupacion seleccionada selecionado
        usuario.setTrabajador(new Trabajador(Integer.parseInt(v_select_trabajador)));
     
        claseDao.create(usuario);
        
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        limpiarForma();
    
    }   
          public void modificar(RowEditEvent event) {
       
        Object ob= event.getObject();
        Usuario tb = (Usuario) ob ;
        
        //Se modifica el valor en la tabla
        for (SelectItem proyecto1 : itemsTrabajadores) {
            if(proyecto1.getValue() ==  tb.getTrabajador().getIdtrabajador()){
                tb.getTrabajador().setNombres(proyecto1.getLabel());
                break;
            }
        }

        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        usuarioDao.update(tb);

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "Operacion realizado con exito");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }    
  
   
    public void eliminar( int id){
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
      for (Usuario usuarioEliminar : listaUsuario) {
            if(usuarioEliminar.getIdUsuario() == id){
               usuarioDao.delete(usuarioEliminar); 
               break;
            }
        }
        listaUsuario = Negocio.Consultar_Usuarios();

       FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "informacion", "El proyecto Fue eliminado con exito.");
       FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

   

    public NegocioGeneral getNegocio() {
        return Negocio;
    }

    public void setNegocio(NegocioGeneral Negocio) {
        this.Negocio = Negocio;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public ArrayList<SelectItem> getItemsTrabajadores() {
        return itemsTrabajadores;
    }

    public void setItemsTrabajadores(ArrayList<SelectItem> itemsTrabajadores) {
        this.itemsTrabajadores = itemsTrabajadores;
    }

    public String getV_select_trabajador() {
        return v_select_trabajador;
    }

    public void setV_select_trabajador(String v_select_trabajador) {
        this.v_select_trabajador = v_select_trabajador;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<Usuario> getListaUsuarioEliminar() {
        return listaUsuarioEliminar;
    }

    public void setListaUsuarioEliminar(List<Usuario> listaUsuarioEliminar) {
        this.listaUsuarioEliminar = listaUsuarioEliminar;
    }


    }