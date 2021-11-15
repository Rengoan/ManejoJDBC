package dominio;

import static datos.Conexion.*;
import java.sql.*;
import java.util.*;

public class PersonaDao {

    private static final String SQL_SELECT = "SELECT * FROM persona";

    public List<Persona> seleccionar() throws SQLException {

        //1. Creamos nuestros objetos a null
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Persona> personas = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

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
        } finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        return personas;
    }
}
