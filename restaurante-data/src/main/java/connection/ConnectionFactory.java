/**
 * 
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimeZone;

/**
 * @author Green
 * 
 * Clase que se encarga de la configuracion, la conexion, y habilitar las
 * transacciones de una base de datos.
 *
 */
public class ConnectionFactory {
	/**
	 * Objeto encargado de mantener la coneccion a la base de datos
	 */
	private static Connection connection;
	
	/**
	 * Objeto encargado de ejecutar la sentencias sql
	 */
	private static Statement statement;
	
	/**
	 * Metodo que permite conectarse a la base de datos
	 * @return objeto connection con la informacion de la base de datos	| null
	 * @throws ClassNotFoundException Excepcion generada al no cargar el driver de conexion.
	 * @throws SQLException Exception generada al no conectarse con al base de datos.
	 */
	public static Connection conectar() throws ClassNotFoundException, SQLException {
		//Cargar el driver de conexion
		Class .forName("com.mysql.cj.jdbc.Driver");
		//Establecer los datos de conexion
		//String url = "jdbc:mysql://localhost:3306/restaurante";
		//String url = "jdbc:mysql://localhost:3306/restaurante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimeZone=UTC";
		String url = "jdbc:mysql://localhost:3306/restaurante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimeZone=" + TimeZone.getDefault().getID();
		String user = "root";
		String password = "admin";
		//Establecer la conexion
		connection = DriverManager.getConnection(url, user, password);
		statement = connection.createStatement();//Hbilitar proceso para ejecutar sentencias SQL
		
		return connection;
	}
	
	/**
	 * Permite ejecutar sentencias INSERT, UPDATE o DELETE
	 * 
	 * @param sql sentencia a ejecutarse
	 * @return 1 en caso de ser exitoso, 0 se ha tenido un error
	 * @throws SQLException Excepcion generada en caso de tener un error en el proceso
	 */
	public static int ejecutarSQL(String sql) throws SQLException {
		System.out.println("Query: " + sql);
		int ejecutado = statement.executeUpdate(sql);
		return ejecutado;
	}
	
	/**
	 * Permite ejecutar sentencias SELECT
	 * @param sql sentencia a ejecutarse
	 * @return objeto con la informacion obtenida con la sentencia select
	 * @throws SQLException Excepcion generada en caso de tener un error en el proceso
	 */
	public static ResultSet ejecutarSQLSelect(String sql) throws SQLException {
		System.out.println("Query: " + sql);
		return statement.executeQuery(sql);
	}
}
