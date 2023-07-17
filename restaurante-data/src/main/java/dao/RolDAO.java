/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import myexceptions.RestauranteException;
import restauranteentities.entity.Rol;

/**
 * @author Daniel Bastian Luna
 * Intefaz que realiza el CRUD de transacciones para la tabla de tipo de rol.
 */
public interface RolDAO {
	/**
	 * Metodo que permite guardar resgistros de rol
	 * @param rol objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Rol rol) throws SQLException;
	/**
	 * Metodo que permite actualizar resgistros de rol
	 * @param rol
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Rol rol) throws SQLException;
	
	/**
	 * Metodo que permite eliminar registros de rol.
	 * @param idRol identificador del rol a eliminar 
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idRol) throws SQLException;
	
	/**
	 * Metodo que permite consultar los registros del rol
	 * @return Lista del rol o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Rol> consultar() throws SQLException;
	
	/**
	 * Metodo que permite consultar un registro del rol por identificador
	 * @param idRol identificador del rol
	 * @return rol consultado o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excpcion personalizada que genera un mensaje mas especifico
	 */
	Rol consultarPorId(int idRol) throws SQLException, RestauranteException;
}
