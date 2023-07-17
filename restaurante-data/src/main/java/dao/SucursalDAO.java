/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import myexceptions.RestauranteException;
import restauranteentities.entity.Sucursal;

/**
 * @author Daniel Bastian Luna
 * Intefaz que realiza el CRUD de transacciones para la tabla de tipo de sucursal.
 */
public interface SucursalDAO {
	/**
	 * Metodo que permite guardar resgistros de sucursal
	 * @param sucursal objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Sucursal sucursal) throws SQLException;
	/**
	 * Metodo que permite actualizar resgistros de sucursal
	 * @param sucursal
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Sucursal sucursal) throws SQLException;
	
	/**
	 * Metodo que permite eliminar registros de sucursal.
	 * @param idSucursal identificador del sucursal a eliminar 
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idSucursal) throws SQLException;
	
	/**
	 * Metodo que permite consultar los registros del sucursal
	 * @return Lista del sucursal o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Sucursal> consultar() throws SQLException;
	
	/**
	 * Metodo que permite consultar un registro del sucursal por identificador
	 * @param idSucursal identificador del sucursal
	 * @return sucursal consultado o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excpcion personalizada que genera un mensaje mas especifico
	 */
	Sucursal consultarPorId(int idSucursal) throws SQLException, RestauranteException;
}
