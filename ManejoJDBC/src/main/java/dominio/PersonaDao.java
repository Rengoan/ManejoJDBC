package dominio;

import static datos.Conexion.*;
import java.sql.*;
import java.util.*;

public class PersonaDao {

    private static final String SQL_SELECT = "SELECT * FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona"
            + "(idpersona, nombre, apellido, email, telefono) VALUES"
            + "(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE  persona SET"
            +" nombre = ?, apellido = ?, email = ?, telefono= ?"
            +" WHERE idpersona = ?";
    
    private static final String SQL_DELETE = "DELETE  FROM persona "
            +"WHERE idpersona = ?";

    public List<Persona> seleccionar() throws SQLException {

        //1. Creamos nuestros objetos a null
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Persona> personas = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery(); //Para ejecutar la sentencia que lee la base de datos.

            while (rs.next()) { //Leo cada registro, creo un objeto, y lo meto en un array
                int idPersona = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                personas.add(new Persona(idPersona, nombre, apellido, email, telefono));

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(rs);
            close(stmt);
            close(conn);
        }
        return personas;
    }

    public int insertar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setInt(1, persona.getIdPersona()); //1 simboliza el interrogante, la posicion. Si es autoincrementable no hace falta ponerlo.
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getApellido());
            stmt.setString(4, persona.getEmail());
            stmt.setString(5, persona.getTelefono());

            registros = stmt.executeUpdate(); //para ejecutar la sentencia que modifica la base de datos

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }
    
    public int actualizar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            //1 simboliza el interrogante, la posicion. Si es autoincrementable no hace falta ponerlo.
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());

            registros = stmt.executeUpdate(); //para ejecutar la sentencia que modifica la base de datos

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }
    
    public int borrar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            //1 simboliza el interrogante, la posicion. Si es autoincrementable no hace falta ponerlo.
            stmt.setInt(1, persona.getIdPersona());

            registros = stmt.executeUpdate(); //para ejecutar la sentencia que modifica la base de datos

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }
}
