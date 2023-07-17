/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import restauranteentities.entity.Empleado;
import myexceptions.RestauranteException;

/**
 * @author Green
 * Interfaz que proporciona el CRUD para las transacciones hacia la base de datos
 * en la tabla empleado 
 *
 */
public interface EmpleadoDao {
	/**
	 * Metodo que permite guardar resgistros de empleado
	 * @param empleado objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Empleado empleado) throws SQLException;
	/**
	 * Metodo que permite actualizar resgistros de empleado
	 * @param empleado
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Empleado empleado) throws SQLException;
	
	/**
	 * Metodo que permite eliminar registros de empleado.
	 * @param idEmpleado identificador del tipo a eliminar 
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idEmpleado) throws SQLException;
	
	/**
	 * Metodo que permite consultar los registros del Empleado
	 * @return Lista del tipo Empleado o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Empleado> consultar() throws SQLException;
	
	/**
	 * Metodo que permite consultar un registro del empleado por identificador
	 * @param idEmpleado identificador del tipo
	 * @return tipo de empleado consultado o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excpcion personalizada que genera un mensaje mas especifico
	 */
	Empleado consultarPorId(int idEmpleado) throws SQLException, RestauranteException;
}
