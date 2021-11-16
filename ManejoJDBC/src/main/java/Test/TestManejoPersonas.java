package Test;

import dominio.*;
import java.sql.*;
import java.util.*;

public class TestManejoPersonas {
    public static void main(String[] args) throws SQLException {
        
        PersonaDao personaDao = new PersonaDao();
        
        List<Persona> personas = personaDao.seleccionar();
        
        personas.forEach(persona ->{
            System.out.println("Persona = " + persona);
        });
    }
}
