/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.Serializable;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Evaluacion;
import modelo.Integrantepoblacion;
import modelo.MiddelwareTrabajador;

/**
 *
 * @author hernan
 */
public class Consultas_BD implements Serializable {

    //HDCG
    public ArrayList<Integrantepoblacion> MenoresPoblacion(String cod_comunidad, Servicio_BD obj) {
        ArrayList<Integrantepoblacion> list = new ArrayList<Integrantepoblacion>();
        if (obj.conectar()) {
            try {
                ResultSet Resultado = obj.Consultar(
                        " SELECT * FROM  integrantepoblacion \n"
                        + " WHERE \n"
                        + " TIMESTAMPDIFF(YEAR,STR_TO_DATE(`fechaNacimiento`, '%d-%m-%Y'),CURDATE()) <=18\n"
                        + " AND comunidad_idcomunidad ="+cod_comunidad+" ");
                while (Resultado.next()) {
                    list.add(new Integrantepoblacion(
                            Resultado.getInt("idIntegrante"),
                            Resultado.getString("nombres"),
                            Resultado.getString("apellidos"),
                            Resultado.getString("fechaNacimiento")
                    ));

                }
                Resultado.close();
            } catch (Exception ex) {
                list = null;
                Logger.getLogger(Servicio_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
            obj.Desconetar();
        }
        return list;
    }

    //LALR Consulta los proyectos y calcula la evaluacion de cada uno
    public ArrayList<Evaluacion> Evaluacion(int valor, Servicio_BD obj) {
        ArrayList<Evaluacion> list = new ArrayList<Evaluacion>();
        if (obj.conectar()) {
            try {
                ResultSet Resultado = obj.Consultar(
                        " SELECT * FROM ( Select p.`tituloProyecto` as 'Titulo proyecto' ,\n"
                        + " IFNULL(round(((select count(*) from proyecto p1\n"
                        + " inner join objetivo obj \n"
                        + " on p1.`idProyecto`= obj.`Proyecto_idProyecto`where cumplimiento = true and p1.`idProyecto`=p.`idProyecto`)/\n"
                        + " (select count(*) from proyecto p2\n"
                        + " inner join objetivo obj \n"
                        + " on p2.`idProyecto`= obj.`Proyecto_idProyecto` where p2.`idProyecto`=p.`idProyecto` ))*100),0) as 'Calificacion' \n"
                        + " from proyecto p ) X \n"
                        + " WHERE Calificacion <=" + valor);
                while (Resultado.next()) {
                    list.add(new Evaluacion(
                            Resultado.getInt("Calificacion"),
                            Resultado.getString("Titulo proyecto")
                    ));

                }
                Resultado.close();
            } catch (Exception ex) {
                list = null;
                Logger.getLogger(Servicio_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
            obj.Desconetar();
        }
        return list;
    }

    public List<MiddelwareTrabajador> listadoTrabajadoresProfesionalesByOcupacion(String ocupacion, Servicio_BD obj) {
        List<MiddelwareTrabajador> trabajador = new ArrayList<MiddelwareTrabajador>();
        if (obj.conectar()) {
            try {
                ResultSet Resultado = obj.Consultar(
                        "SELECT t.idtrabajador, t.nombres, t.apellidos, t.`fechaNacimiento`, o.descripcion, ct.descripcion as clase_trabajador "
                        + " FROM proyecto_trabajador pt "
                        + " inner join trabajador t on t.idtrabajador = pt.trabajador_idtrabajador "
                        + " inner join ocupacion o on o.idocupacion = t.ocupacion_idocupacion "
                        + " inner join clase_trabajador ct on ct.idclase_trabajador = o.clase_trabajador_idclase_trabajador "
                        + " where o.clase_trabajador_idclase_trabajador = 1 "
                        + " and o.idocupacion = " + ocupacion);

                while (Resultado.next()) {

                    MiddelwareTrabajador objTrabajador = new MiddelwareTrabajador(Integer.parseInt(Resultado.getString("idtrabajador")),
                            Resultado.getString("nombres"),
                            Resultado.getString("apellidos"),
                            Resultado.getString("fechaNacimiento"),
                            Resultado.getString("descripcion"),
                            Resultado.getString("clase_trabajador"));
                    trabajador.add(objTrabajador);
                }
                Resultado.close();
            } catch (Exception ex) {
                Logger.getLogger(Servicio_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
            obj.Desconetar();
        }
        return trabajador;
    }

    public List<MiddelwareTrabajador> listadoTrabajadoresProfesionalesByProyecto(String proyecto, Servicio_BD obj) {
        List<MiddelwareTrabajador> trabajador = new ArrayList<MiddelwareTrabajador>();
        if (obj.conectar()) {
            try {
                ResultSet Resultado = obj.Consultar(
                        "SELECT t.idtrabajador, t.nombres, t.apellidos, t.`fechaNacimiento`, o.descripcion, ct.descripcion as clase_trabajador FROM proyecto_trabajador pt"
                        + " inner join  proyecto p on p.`idProyecto` = pt.`Proyecto_idProyecto`"
                        + " inner join trabajador t on t.idtrabajador = pt.trabajador_idtrabajador"
                        + " inner join ocupacion o on o.clase_trabajador_idclase_trabajador = t.ocupacion_idocupacion"
                        + " inner join clase_trabajador ct on ct.idclase_trabajador = o.clase_trabajador_idclase_trabajador"
                        + " where o.clase_trabajador_idclase_trabajador = 1"
                        + " and p.`idProyecto` = " + proyecto);

                while (Resultado.next()) {

                    MiddelwareTrabajador objTrabajador = new MiddelwareTrabajador(Integer.parseInt(Resultado.getString("idtrabajador")),
                            Resultado.getString("nombres"),
                            Resultado.getString("apellidos"),
                            Resultado.getString("fechaNacimiento"),
                            Resultado.getString("descripcion"),
                            Resultado.getString("clase_trabajador"));
                    trabajador.add(objTrabajador);

                }
                Resultado.close();
            } catch (Exception ex) {
                Logger.getLogger(Servicio_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
            obj.Desconetar();
        }
        return trabajador;
    }
}
