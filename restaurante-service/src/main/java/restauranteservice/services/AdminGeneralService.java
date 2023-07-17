/**
 * 
 */
package restauranteservice.services;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import dao.impl.*;
import restauranteentities.entity.Menu;
import restauranteentities.entity.Restaurante;
import restauranteentities.entity.TipoRestaurante;

/**
 * @author Green
 * Clase de servicios que se encarga de realizar la logica de negocio para
 * la administracion de restaurantes para el administrador general.
 * 
 */
public class AdminGeneralService {
	
	/**
	 * Objeto que permite realizar las transacciones para la tabla de tipo restaurante
	 */
	private TipoRestauranteDAOImpl tipoRestauranteDAOImpl = new TipoRestauranteDAOImpl();
	
	/**
	 * Objeto que permite realizar las transacciones para la tabla restaurante
	 */
	private RestauranteDAOImpl restauranteDAOImpl = new RestauranteDAOImpl();
	
	//TIPOS RESTAURANTES
	public List<TipoRestaurante> consultarTiposRestaurantes() throws SQLException{
		return this.tipoRestauranteDAOImpl.consultar();
	}
	
	public int guardarTipoRestaurante(TipoRestaurante tipoRestaurante) throws SQLException {
		tipoRestaurante.setFechaCreacion(LocalDateTime.now());
		tipoRestaurante.setEstatus(true);
		return this.tipoRestauranteDAOImpl.guardar(tipoRestaurante);
	}
	
	public int actualizarTipoRestaurante(TipoRestaurante tipoRestaurante) throws SQLException {
		tipoRestaurante.setFechaModificacion(LocalDateTime.now());
		return this.tipoRestauranteDAOImpl.actualizar(tipoRestaurante);
	}
	
	/**
	 * Metodo que permite eliminar un registro del tipo restaurante
	 * 
	 * @param idTipoRestaurante del tipo de restaurante a eliminar
	 * @return 1 en caso de eliminar, 0 en caso de error
	 * @throws SQLException excepcion en caso de fallo en la base MySQL
	 */
	public int eliminarTipoRestaurante(int idTipoRestaurante) throws SQLException {
		return this.tipoRestauranteDAOImpl.eliminar(idTipoRestaurante);
	}
	
	//-------------------------------RESTAURANTES
	/**
	 * Metodo que permite consultar el listado de restaurantes.
	 * @return Lista de restaurantes consultados
	 * @throws SQLException excepcion en caso de error al ejecutar la sentencia SQL
	 */
	public List<Restaurante> consultarRestaurantes() throws SQLException{
		return this.restauranteDAOImpl.consultar();
	}
	
	/**
	 * Metodo para poder guardar un restaurante en al base de datos
	 * @param restaurante objeto del tipo Restaurante para poder guardar en la base de datos.
	 * @return si es mayor a cero todo salio bien, de lo contrario hay un error
	 * @throws SQLException excepcion ocurrida en algun proceso de guardado en la base de datos.
	 */
	public int guardarRestaurante(Restaurante restaurante) throws SQLException {
		restaurante.setFechaCreacion(LocalDateTime.now());
		restaurante.setEstatus(true);
		return this.restauranteDAOImpl.guardar(restaurante);
	}
	
	/**
	 * Metodo para poder actualizar un restaurante
	 * @param restaurante objeto para guardar en la base de datos.
	 * @return si es mayor a cero todo salio bien, de lo contrario hay un error
	 * @throws SQLException excepcion ocurrida en algun proceso de guardado en la base de datos.
	 */
	public int actualizarRestaurante(Restaurante restaurante) throws SQLException {
		restaurante.setFechaModificacion(LocalDateTime.now());
		restaurante.setMenu(new Menu());
		return this.restauranteDAOImpl.actualizar(restaurante);
	}
}
