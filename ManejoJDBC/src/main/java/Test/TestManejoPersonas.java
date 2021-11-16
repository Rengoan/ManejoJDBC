package Test;

import dominio.*;
import java.sql.*;
import java.util.*;

public class TestManejoPersonas {
    public static void main(String[] args) throws SQLException {
        
        PersonaDao personaDao = new PersonaDao();
        int registros = personaDao.actualizar(new Persona(2, "Marta", "Sanchez",
                "msanches@gmail.com", "698123453"));
                
                
        
        List<Persona> personas = personaDao.seleccionar();
        
        personas.forEach(persona ->{
            System.out.println("Persona = " + persona);
        });
        
        
        
       
    }
}
