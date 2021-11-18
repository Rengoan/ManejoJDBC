package Test;

import datos.PersonaDao;
import datos.Conexion;
import dominio.*;
import java.sql.*;
import java.util.*;

public class TestManejoPersonas {

    public static void main(String[] args) throws SQLException {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();

            if (conexion.getAutoCommit()) { 
                conexion.setAutoCommit(false);
            }

            PersonaDao personaDao = new PersonaDao(conexion);
            //1. Actualizamos
            int registros = personaDao.actualizar(new Persona(2, "Marta", "Sanchez",
                    "martitasanches@gmail.com", "698123453"));
            //2. Insertamos
            personaDao.insertar(new Persona(3,"Antonio", "Rivera", "arivera@gmail.com", "633718200"));
            //3. Borramos
            //int registrosBorrar = personaDao.borrar(new Persona(3, "Pedro", "Garcia", "pg@gmail.com", "6443222079"));
            //4. Seleccionamos
            List<Persona> personas = personaDao.seleccionar();

            personas.forEach(persona -> {
                System.out.println("Persona = " + persona);
            });
            //Conmiteamos 1,2,3,4
            conexion.commit();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos en el rollback");
            try {
                conexion.rollback();//Si hay algun error vuelve al estado anterior
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }

    }
}
