package Test;

import datos.PersonaDAOJDBC;
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

            PersonaDAOJDBC personaDao = new PersonaDAOJDBC(conexion);
            //1. Actualizamos
            int registros = personaDao.actualizar(new PersonaDTO(2, "Marta", "Sanchez",
                    "martitasanches@gmail.com", "698123453"));
            //2. Insertamos
            personaDao.insertar(new PersonaDTO(3,"Antonio", "Rivera", "arivera@gmail.com", "633718200"));
            //3. Borramos
            //int registrosBorrar = personaDao.borrar(new PersonaDTO(3, "Pedro", "Garcia", "pg@gmail.com", "6443222079"));
            //4. Seleccionamos
            List<PersonaDTO> personas = personaDao.seleccionar();

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
