/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import restauranteentities.entity.TipoRestaurante;
import myexceptions.RestauranteException;

/**
 * @author Green
 * Interfaz que representa el CRUD de transaccion para la tabla de tipo_restaurante.
 *
 */
public interface TipoRestauranteDAO {
	/**
	 * Metodo que permite guardar resgistros del tipo restaurantes
	 * @param tipoRestaurante objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(TipoRestaurante tipoRestaurante) throws SQLException;
	/**
	 * Metodo que permite actualizar resgistros del tipo restaurantes
	 * @param tipoRestaurante
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(TipoRestaurante tipoRestaurante) throws SQLException;
	
	/**
	 * Metodo que permite eliminar registros de tipo restaurantes.
	 * @param idTipoRestaurante identificador del tipo a eliminar 
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idTipoRestaurante) throws SQLException;
	
	/**
	 * Metodo que permite consultar los registros del tipos de restaurantes
	 * @return Lista del tipo restaurante o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<TipoRestaurante> consultar() throws SQLException;
	
	/**
	 * Metodo que permite consultar un registro del tipo TipoRestaurante por identificador
	 * @param idTipoRestaurante identificador del tipo
	 * @return tipo de restaurante consultado o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excpcion personalizada que genera un mensaje mas especifico
	 */
	TipoRestaurante consultarPorId(int idTipoRestaurante) throws SQLException, RestauranteException;
}
