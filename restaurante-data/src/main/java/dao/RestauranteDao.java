/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import restauranteentities.entity.Restaurante;
import myexceptions.RestauranteException;

/**
 * @author Green
 * Interfaz que proporciona el CRUD para las transacciones hacia la base de datos
 * en la tabla restaurante 
 *
 */
public interface RestauranteDao {
	/**
	 * Metodo que permite guardar resgistros de restaurante
	 * @param restaurante objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Restaurante restaurante) throws SQLException;
	/**
	 * Metodo que permite actualizar resgistros de restaurante
	 * @param restaurante
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Restaurante restaurante) throws SQLException;
	
	/**
	 * Metodo que permite eliminar registros de restaurantes.
	 * @param idRestaurante identificador del tipo a eliminar 
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idRestaurante) throws SQLException;
	
	/**
	 * Metodo que permite consultar los registros del restaurante
	 * @return Lista del tipo restaurante o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Restaurante> consultar() throws SQLException;
	
	/**
	 * Metodo que permite consultar un registro del Restaurante por identificador
	 * @param idRestaurante identificador del tipo
	 * @return tipo de restaurante consultado o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excpcion personalizada que genera un mensaje mas especifico
	 */
	Restaurante consultarPorId(int idRestaurante) throws SQLException, RestauranteException;
}
