package datos;

import java.sql.*;

public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_java?useSSL=FALSE&"+
                "useTimezone=TRUE&serverTimezone=UTC&"+
                "allowPublicKeyRetrieval=TRUE";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    
    public static Connection getConnection() throws SQLException{ //Establece una conexion a la base de datos
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    
    //Metodos para cerrar la conexion
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(Statement stmt) throws SQLException{
        stmt.close();
    }
    
    public static void close(PreparedStatement stmt) throws SQLException{
        stmt.close();
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
}
