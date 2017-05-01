/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.Serializable;
import java.sql.*;


/**
 *
 * @author hernan
 */
public class Servicio_BD implements Serializable {

    public String nameDB = "proyecto";
    public String login = "root";
    public String password = "luisleon9";
    public String url = "jdbc:mysql://localhost:3306/" + nameDB;
    public Connection Link = null;

    public boolean conectar() {
        try {
            //Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            this.Link = DriverManager.getConnection(this.url, this.login, this.password);
            if (this.Link != null) {
                System.err.println("MY SQL CONEXION ABIERTAR{");
                return true;
            }
        } catch (Exception ex) {
            System.out.println("ERROR DE CONECCION A: " + this.url + " " + ex);
        }
        return false;
    }

    public void desconectar() {
        this.Link = null;
    }

    public Connection GetConection() {
        return this.Link;
    }

    public boolean Guardar() {
        try {
            Link.commit();
             System.err.println("}MY SQL GUARDAR ");
            return true;
        } catch (Exception ex) {
            roolback();
            System.out.println("ERROR  Guardar : " + ex);
            return false;
        }

    }

    public boolean Ejecutar(String SQL) {
        try {
            Link.setAutoCommit(false);
            PreparedStatement Query = this.GetConection().prepareStatement(SQL);
            Query.execute();
            return true;
        } catch (Exception ex) {
            roolback();
            System.out.println("ERROR  Ejecutar: " + ex);
            return false;
        }

    }

    public ResultSet Consultar(String SQL) {
        try {
            PreparedStatement Query = this.GetConection().prepareStatement(SQL);
            ResultSet Resultado = Query.executeQuery();
            return Resultado;
        } catch (Exception ex) {
            System.out.println("ERROR  Consultar : " + ex);
            return null;
        }
    }

    public void roolback() {
        try {
            Link.rollback();
            System.err.println("}MY SQL ROOLBACK ");
        } catch (Exception ex) {
            System.out.println("ERROR  roolback(): " + ex);
        }
    }

    public void Desconetar() {
        try {
            Link.close();
            desconectar();
            System.err.println("}MY SQL CONEXION CERRADA");
        } catch (Exception ex) {
            System.out.println("ERROR  Desconetar(): " + ex);

        }
    }
}
