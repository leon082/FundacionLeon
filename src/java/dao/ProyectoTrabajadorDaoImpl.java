/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import modelo.ProyectoTrabajador;
import org.hibernate.HibernateException;

/**
 *
 * @author Johanna
 */
public class ProyectoTrabajadorDaoImpl extends GenericDaoImpl<ProyectoTrabajador, Integer> implements ProyectoTrabajadorDao{
    public List <ProyectoTrabajador> findByIdProyecto(int idProyecto){
        
        List <ProyectoTrabajador> list;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ProyectoTrabajador where proyecto.id = "+idProyecto).list();
            tx.commit();
        } catch (HibernateException e) {
            list = null ;
            tx.rollback();
            throw e;
        }
        return list;
    }
    
    public List <ProyectoTrabajador> findByIdProyectoIdTrabajador(int idProyecto, int idRepresentante){
        
        List <ProyectoTrabajador> list;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from ProyectoTrabajador where proyecto.id = "+idProyecto+" and trabajador.id = "+idRepresentante).list();
            tx.commit();
        } catch (HibernateException e) {
            list = null ;
            tx.rollback();
            throw e;
        }
        return list;
    }
    
    
}
