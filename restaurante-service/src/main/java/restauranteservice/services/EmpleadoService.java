/**
 * 
 */
package restauranteservice.services;

import java.sql.SQLException;
import java.util.List;

import restauranteentities.entity.Alimento;
import restauranteentities.entity.TipoAlimento;
import dao.impl.TipoAlimentoDAOImpl;
import dao.impl.AlimentoDAOImpl;

/**
 * @author Green
 * 
 * Clase que realiza la logica de negocio para la pantalla de empleado
 */
public class EmpleadoService {
	/**
	 * Objeto que implementa las transacciones a la tabla tipo alimento
	 */
	private TipoAlimentoDAOImpl tipoAlimentoDaoImpl = new TipoAlimentoDAOImpl();
	/**
	 * Objeto que implementa las transacciones a la tabla Alimento.
	 */
	private AlimentoDAOImpl alimentoDaoImpl = new AlimentoDAOImpl();
	
	/**
	 * Metodo que permite consultar los tipos de alimentos del menu del 
	 * restaurante
	 * @param idMenu identificador del menu
	 * @return lista de tipos de alimentos
	 * @throws SQLException excepcion en caso de error al ejecutar la sentencia SQL
	 */
	public List<TipoAlimento> consultarAlimentosPorMenu(int idMenu) throws SQLException{
		List<TipoAlimento> tiposAlimentos = this.tipoAlimentoDaoImpl.consultarTiposAlimentosPorMenu(idMenu);
		for(TipoAlimento tipoAlimento:tiposAlimentos) {
			List<Alimento> alimentos = this.alimentoDaoImpl.consultarAlimentosPorTipoAndMenu(tipoAlimento.getIdTipoAlimento(), idMenu);
			tipoAlimento.setAlimentos(alimentos);
		}
		return tiposAlimentos;
	}
}
