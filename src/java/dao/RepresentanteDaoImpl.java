/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import modelo.Representante;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Johanna
 */
public class RepresentanteDaoImpl extends GenericDaoImpl<Representante, Integer> implements RepresentanteDao{
    public List <Representante> findLastId(){
        
        List <Representante> list;
        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            list = session.createQuery("from Representante order by idrepresentante desc ").setMaxResults(1).list();
           
            tx.commit();
        } catch (HibernateException e) {
            list = null ;
            tx.rollback();
            throw e;
        }
        return list;
    }
}
