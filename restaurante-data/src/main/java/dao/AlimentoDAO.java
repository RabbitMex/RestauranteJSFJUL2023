/**
 * 
 */
package dao;

import java.sql.SQLException;
import java.util.List;

import myexceptions.RestauranteException;
import restauranteentities.entity.Alimento;

/**
 * @author Daniel Bastian Luna
 * Intefaz que realiza el CRUD de transacciones para la tabla de tipo de alimento
 */
public interface AlimentoDAO {
	/**
	 * Metodo que permite guardar resgistros de alimento
	 * @param Alimento objeto a guardar
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no guardarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int guardar(Alimento alimento) throws SQLException;
	/**
	 * Metodo que permite actualizar resgistros de alimento
	 * @param alimento
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no actualizarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int actualizar(Alimento alimento) throws SQLException;
	
	/**
	 * Metodo que permite eliminar registros de alimento.
	 * @param idAlimento identificador del alimento a eliminar 
	 * @return 1 o mas en caso de ser exitoso, 0 en caso de no eliminarlo
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	int eliminar(int idAlimento) throws SQLException;
	
	/**
	 * Metodo que permite consultar los registros del alimento
	 * @return Lista del alimento o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 */
	List<Alimento> consultar() throws SQLException;
	
	/**
	 * Metodo que permite consultar un registro del alimento por identificador
	 * @param idAlimento identificador del alimento
	 * @return alimento consultado o null
	 * @throws SQLException Excepcion generada en caso de error al ejecutar la sentencia SQL
	 * @throws RestauranteException excpcion personalizada que genera un mensaje mas especifico
	 */
	Alimento consultarPorId(int idAlimento) throws SQLException, RestauranteException;
}
