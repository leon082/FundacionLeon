/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Util.HibernateUtil;
import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import modelo.Proyecto;
import java.util.Date;
import modelo.ProyectoTrabajador;
import org.hibernate.HibernateException;

/**
 *
 * @author Johanna
 */
public class ProyectoDaoImpl extends GenericDaoImpl<Proyecto, Integer> implements ProyectoDao{
    
    public List <Proyecto> findByIdComunidad(int idComunidad){
        
        List <Proyecto> list;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from Proyecto where comunidad_idcomunidad = "+idComunidad).list();
            tx.commit();
        } catch (HibernateException e) {
            list = null ;
            tx.rollback();
            throw e;
        }
        return list;
    }
    
     public List <Proyecto> findByPeriodo( Date ini , Date fin){
        
        List <Proyecto> list;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String in = formatter.format(ini);
             String fn = formatter.format(fin);
            
            list = session.createQuery("from Proyecto where fechaInicio BETWEEN   '"+ in +"'  AND  '"+ fn+"'").list();
            tx.commit();
        } catch (HibernateException e) {
            list = null ;
            tx.rollback();
            throw e;
        }
        return list;
    }
}
