/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import dao.ClaseTrabajadorDaoImpl;
import dao.ComunidadDaoImpl;
import dao.IntegrantePoblaDaoImpl;
import dao.OcupacionDaoImpl;
import dao.ProyectoDaoImpl;
import dao.ProyectoTrabajadorDaoImpl;
import dao.RepresentanteDaoImpl;
import dao.RepresentanteProyectoDaoImpl;
import dao.TareasDaoImpl;
import dao.TemasDaoImpl;
import dao.TrabajadorDaoImpl;
import dao.UsuarioDaoImpl;
import dao.objetivoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import modelo.ClaseTrabajador;
import modelo.Comunidad;
import modelo.Integrantepoblacion;
import modelo.Objetivo;
import modelo.Ocupacion;
import modelo.Proyecto;
import modelo.ProyectoTrabajador;
import modelo.Representante;
import modelo.RepresentanteProyecto;
import modelo.Tareas;
import modelo.Temas;
import modelo.Trabajador;
import modelo.Usuario;

/**
 *
 * @author hernandario
 */
public class NegocioGeneral implements java.io.Serializable {

    public NegocioGeneral() {
    }

    //HDCG
    public List<Proyecto> Consultar_Proyectos() {
        ProyectoDaoImpl proyectoDao = new ProyectoDaoImpl();
        return (List<Proyecto>) proyectoDao.findAll();
    }

    //HDCG
    public ArrayList<SelectItem> Consultar_Temas_combo() {
        TemasDaoImpl tareasDao = new TemasDaoImpl();
        List<Temas> lista = tareasDao.findAll();
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Temas obj : (ArrayList<Temas>) lista) {
            items.add(new SelectItem(obj.getIdtemas(), obj.getDescripcion()));
        }
        return items;
    }

    //HDCG
    public ArrayList<SelectItem> Consultar_Comunida_combo() {
        ComunidadDaoImpl tareasDao = new ComunidadDaoImpl();
        List<Comunidad> lista = tareasDao.findAll();
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Comunidad obj : (ArrayList<Comunidad>) lista) {
            items.add(new SelectItem(obj.getIdcomunidad(), obj.getNombre()));
        }
        return items;
    }

    //HDCG
    public ArrayList<ProyectoTrabajador> Consultar_ProyectoTrabajador() {
        ProyectoTrabajadorDaoImpl proyectoDao = new ProyectoTrabajadorDaoImpl();
        return (ArrayList<ProyectoTrabajador>) proyectoDao.findAll();
    }

    //HDCG
    public ArrayList<SelectItem> Consultar_Proyectos_combo() {
        ProyectoDaoImpl proyectoDao = new ProyectoDaoImpl();
        List<Proyecto> lista = proyectoDao.findAll();
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Proyecto obj : (ArrayList<Proyecto>) lista) {
            items.add(new SelectItem(obj.getIdProyecto(), obj.getTituloProyecto()));
        }
        return items;
    }

    //HDCG
    public ArrayList<Integrantepoblacion> Consultar_Integrante_poblacion() {
        IntegrantePoblaDaoImpl Dao = new IntegrantePoblaDaoImpl();
        return (ArrayList<Integrantepoblacion>) Dao.findAll();
    }

    //LALR consultar trabajadores paraa el combo                    
    public ArrayList<SelectItem> Consultar_Traba_combo() {
        TrabajadorDaoImpl trabajadorDao = new TrabajadorDaoImpl();
        List<Trabajador> lista = trabajadorDao.findAll();
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Trabajador obj : (ArrayList<Trabajador>) lista) {
            items.add(new SelectItem(obj.getIdtrabajador(), obj.getNombres() + " " + obj.getApellidos()));
        }
        return items;
    }

    //LALR consultar usuarios para la tabla          
    public ArrayList<Usuario> Consultar_Usuarios() {
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        return (ArrayList<Usuario>) usuarioDao.findAll();
    }
    //LALR Consulta los Trabajadores

    public ArrayList<Trabajador> Consultar_Trabajadores() {
        TrabajadorDaoImpl trabajadorDao = new TrabajadorDaoImpl();
        return (ArrayList<Trabajador>) trabajadorDao.findAll();
    }

   
    
     //LALR Combo ocupacion
    public ArrayList<SelectItem> Consultar_Ocupacion_combo() {
        OcupacionDaoImpl ocupacionDao = new OcupacionDaoImpl();
        List<Ocupacion> lista = ocupacionDao.findAll();
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Ocupacion obj : (ArrayList<Ocupacion>) lista) {
            items.add(new SelectItem(obj.getIdocupacion(), obj.getDescripcion()));
        }
        return items;
    }
    
    //LALR consulta las ocupaciones para llenar el panel
     public ArrayList<Ocupacion> Consultar_Ocupaciones() {
        OcupacionDaoImpl ocupacionDao = new OcupacionDaoImpl();
        return (ArrayList<Ocupacion>) ocupacionDao.findAll();
    }
    
    // LALR Consultar clase de trabajador para llenar el combo
    public ArrayList<SelectItem> Consultar_claseTrabajador_combox() {
        ClaseTrabajadorDaoImpl claseDao = new ClaseTrabajadorDaoImpl();
        List<ClaseTrabajador> lista = claseDao.findAll();
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (ClaseTrabajador obj : (ArrayList<ClaseTrabajador>) lista) {
            items.add(new SelectItem(obj.getIdclaseTrabajador(), obj.getDescripcion()));
        }
        return items;
    }
    
    //LALR Consulta las comunidades
    public ArrayList<Comunidad> Consultar_Comunidades() {
        ComunidadDaoImpl comuDao = new ComunidadDaoImpl();
        return (ArrayList<Comunidad>) comuDao.findAll();
    }
    
    //LALR llena el combo con los titulos del proeycto
    public ArrayList<SelectItem> Consultar_Proyectos_comboLAL() {
        ProyectoDaoImpl proDao = new ProyectoDaoImpl();
        List<Proyecto> lista = proDao.findAll();
        ArrayList<SelectItem> items = new ArrayList<SelectItem>();
        for (Proyecto obj : (ArrayList<Proyecto>) lista) {
            items.add(new SelectItem(obj.getIdProyecto(), obj.getTituloProyecto()));
        }
        return items;
    }
     //LALR
    public ArrayList<Objetivo> Consultar_Objetivos() {
        objetivoDaoImpl objDao = new objetivoDaoImpl();
        return (ArrayList<Objetivo>) objDao.findAll();
    }
    
     //CARC
    public ArrayList<SelectItem> Consultar_Proyectos_Trabajador_combo(int idProyecto){
        ProyectoTrabajadorDaoImpl proyectoTrabajadorDao = new ProyectoTrabajadorDaoImpl();
        List<ProyectoTrabajador> lista = proyectoTrabajadorDao.findByIdProyecto(idProyecto);
        ArrayList<SelectItem> items= new ArrayList<>();
        for (  ProyectoTrabajador obj : (ArrayList<ProyectoTrabajador>) lista) {
             //items.add(new SelectItem(obj.getIdProyectoTrabajador(),obj.getTrabajador().getNombres()));
             items.add(new SelectItem(obj.getIdProyectoTrabajador(), obj.getTrabajador().getNombres()));
        }
        return items;
    }
    
     //CARC
    public ArrayList<SelectItem> Consultar_Personas_Comunidad(){
        IntegrantePoblaDaoImpl integrantePoblacionDao = new IntegrantePoblaDaoImpl();
        List<Integrantepoblacion> lista = integrantePoblacionDao.findAll();
        ArrayList<SelectItem> items= new ArrayList<SelectItem>();
        for (  Integrantepoblacion obj : (ArrayList<Integrantepoblacion>) lista) {
             items.add(new SelectItem(obj.getIdIntegrante(),obj.getNombres()));
        }
        return items;
    }

    //CARC
    public ArrayList<SelectItem> Consultar_List_Proyectos(){
        ProyectoDaoImpl proyectoDao = new ProyectoDaoImpl();
        List<Proyecto> lista = proyectoDao.findAll();
        ArrayList<SelectItem> items= new ArrayList<SelectItem>();
        for (  Proyecto obj : (ArrayList<Proyecto>) lista) {
             items.add(new SelectItem(obj.getIdProyecto(),obj.getDescripcion()));
        }
        return items;
    }
    //CARC
    public List<RepresentanteProyecto> Consultar_All_Representante_Proyectos(){
        RepresentanteProyectoDaoImpl representanteProyectoDao = new RepresentanteProyectoDaoImpl();
        return (List<RepresentanteProyecto>) representanteProyectoDao.findAll();
    }
    //CARC
     public List<Representante> Consultar_All_Representante(){
         RepresentanteDaoImpl representanteDao = new RepresentanteDaoImpl();
        return (List<Representante>) representanteDao.findAll();
    }
      //CARC
     public List<Tareas> Consultar_All_Tareas(){
         TareasDaoImpl tareasDao = new TareasDaoImpl();
        return (List<Tareas>) tareasDao.findAll();
    }
    
   

}
