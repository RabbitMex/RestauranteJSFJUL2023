/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import myexceptions.RestauranteException;
import restauranteentities.entity.TipoAlimento;

/**
 * @author Daniel Bastian Luna
 * Intefaz que realiza el CRUD de transacciones para la tabla de tipo de alimento.
 */
public interface TipoAlimentoDAO {
	/**
	 * Metodo que permite guardar resgistros de tipo de alimento
	 * @param tipoAlimento objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(TipoAlimento tipoAlimento) throws SQLException;
	/**
	 * Metodo que permite actualizar resgistros de tipo alimento
	 * @param tipoAlimento
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(TipoAlimento tipoAlimento) throws SQLException;
	
	/**
	 * Metodo que permite eliminar registros de tipoAlimento.
	 * @param idTipoAlimento identificador del tipo a eliminar 
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idTipoAlimento) throws SQLException;
	
	/**
	 * Metodo que permite consultar los registros del tipo alimento
	 * @return Lista del tipo alimento o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<TipoAlimento> consultar() throws SQLException;
	
	/**
	 * Metodo que permite consultar un registro del tipo Alimento por identificador
	 * @param idTipoAlimento identificador del tipo de alimento
	 * @return tipo de alimento consultado o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excpcion personalizada que genera un mensaje mas especifico
	 */
	TipoAlimento consultarPorId(int idTipoAlimento) throws SQLException, RestauranteException;
}
