/**
 * 
 */
package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import dao.AlimentoDAO;
import myexceptions.RestauranteException;
import restauranteentities.entity.Alimento;
import restauranteentities.entity.Restaurante;
import restauranteentities.entity.Menu;
import restauranteentities.entity.TipoAlimento;

/**
 * @author Green
 * Clase que implementa el CRUD de transacciones para la tabla de alimento.
 *
 */
public class AlimentoDAOImpl implements AlimentoDAO {

	@Override
	public int guardar(Alimento alimento) throws SQLException {
		String sql = "INSERT INTO alimento(nombre, imagen, descripcion, idTipoAlimento, descuento, precio, fechaCreacion, estatus, idRestaurante)"
				+ " VALUES ('" + alimento.getNombre() + "', '" + alimento.getImagen() + "', '" + alimento.getDescripcion() + "', "
				+ alimento.getTipoAlimento().getIdTipoAlimento() + ", " + alimento.getDescuento() 
				+ ", " + alimento.getPrecio() + ", '" + alimento.getFechaCreacion()
				+ "', " + alimento.isEstatus() + ", " + alimento.getRestaurante().getIdRestaurante() + ");";
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int actualizar(Alimento alimento) throws SQLException {
		String sql = "UPDATE alimento SET nombre='" + alimento.getNombre()
			+ "', imagen='" + alimento.getImagen() + "', descripcion='" + alimento.getDescripcion() 
			+ "', idTipoAlimento= " + alimento.getTipoAlimento().getIdTipoAlimento() + ", descuento=" + alimento.getDescuento() 
			+ ", precio= " + alimento.getPrecio() + ", fechaModificacion='" + alimento.getFechaModificacion() 
			+ "', estatus= " + alimento.isEstatus() + " WHERE idAlimento= " + alimento.getIdAlimento() + ";";
		
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		return ejecutado;
	}

	@Override
	public int eliminar(int idAlimento) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Alimento> consultar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alimento consultarPorId(int idAlimento) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Metodo que permite consultar los alimentos del restaurante del usuario en sesion
	 * 
	 * @param idRestauranteUsuarioSesion identificador del restaurante
	 * @return Una lista de alimentos.
	 * @throws SQLException excepcion generada en caso de error al ejecutar la sentencia sql
	 */
	public List<Alimento> consultarPorIdRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
		List<Alimento> list = new ArrayList<Alimento>();
		
		String sql = "SELECT a.*, ta.descripcion AS descripcionTipo "
				+ "FROM alimento a, restaurante r, tipo_alimento ta, restaurante_has_tipo_alimento rha "
				+ "WHERE a.idRestaurante = r.idRestaurante "
				+ "AND a.idTipoAlimento = ta.idTipoAlimento "
				+ "AND r.idRestaurante = rha.idRestaurante "
				+ "AND ta.idTipoAlimento = rha.idTipoAlimento "
				+ "AND r.idRestaurante = " + idRestauranteUsuarioSesion + ";";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				Alimento alimento = new Alimento();
				alimento.setIdAlimento(rs.getInt("idAlimento"));
				alimento.setDescripcion(rs.getString("descripcion"));
				alimento.setNombre(rs.getString("nombre"));
				alimento.setImagen(rs.getString("imagen"));
				alimento.setPrecio(rs.getDouble("precio"));
				alimento.setDescuento(rs.getDouble("descuento"));
				alimento.setEstatus(rs.getBoolean("estatus"));
				alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				
				//Tipo de alimento
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcionTipo"));
				
				alimento.setTipoAlimento(tipoAlimento);
				
				list.add(alimento);
			}
		}
		return list;
	}
	
	/**
	 * Metodo que permite consultar la lista de alimentos disponibles del 
	 * restaurante no asignados al menu
	 * @param idRestauranteUsuarioSesion id del restaurante
	 * @return lista de alimentos disponibles
	 * @throws SQLException exception al ejecutar la sentencia SQL
	 */
	public List<Alimento> consultarAlimentosDisponibles(int idRestauranteUsuarioSesion) throws SQLException{
		List<Alimento> alimentosDB = new ArrayList<Alimento>();
		String sql = "SELECT a.*, ta.descripcion AS descripcionTipo "
				+ "FROM alimento a, tipo_alimento ta, restaurante res "
				+ "WHERE a.idTipoAlimento = ta.idTipoAlimento "
				+ "AND a.idRestaurante = res.idRestaurante "
				+ "AND a.idMenu IS NULL "
				+ "AND a.idrestaurante = " + idRestauranteUsuarioSesion + ";";
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				Alimento alimento = new Alimento();
				alimento.setIdAlimento(rs.getInt("idAlimento"));
				alimento.setDescripcion(rs.getString("descripcion"));
				alimento.setNombre(rs.getString("nombre"));
				alimento.setImagen(rs.getString("imagen"));
				alimento.setPrecio(rs.getDouble("precio"));
				alimento.setDescuento(rs.getDouble("descuento"));
				alimento.setEstatus(rs.getBoolean("estatus"));
				alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				
				//Tipo de alimento
				TipoAlimento tipoAlimento = new TipoAlimento();
				//tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcionTipo"));
				
				alimento.setTipoAlimento(tipoAlimento);
				
				alimentosDB.add(alimento);
			}
		}
		return alimentosDB;
	}
	
	/**
	 * Metodo que permite consultar la lista de alimentos disponibles del 
	 * restaurante asignados al menu
	 * @param idMenu id del menu
	 * @return lista de alimentos asignados
	 * @throws SQLException exception al ejecutar la sentencia SQL
	 */
	public List<Alimento> consultarAlimentosAsignados(Integer idMenu) throws SQLException{
		List<Alimento> alimentosDB = new ArrayList<Alimento>();
		String sql = "SELECT a.*, ta.descripcion AS descripcionTipo "
				+ "FROM alimento a, tipo_alimento ta, restaurante res "
				+ "WHERE a.idTipoAlimento = ta.idTipoAlimento "
				+ "AND a.idRestaurante = res.idRestaurante "
				+ "AND a.idMenu = " + idMenu + ";";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				Alimento alimento = new Alimento();
				alimento.setIdAlimento(rs.getInt("idAlimento"));
				alimento.setDescripcion(rs.getString("descripcion"));
				alimento.setNombre(rs.getString("nombre"));
				alimento.setImagen(rs.getString("imagen"));
				alimento.setPrecio(rs.getDouble("precio"));
				alimento.setDescuento(rs.getDouble("descuento"));
				alimento.setEstatus(rs.getBoolean("estatus"));
				alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				
				//Tipo de alimento
				TipoAlimento tipoAlimento = new TipoAlimento();
				//tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcionTipo"));
				
				alimento.setTipoAlimento(tipoAlimento);
				
				alimentosDB.add(alimento);
			}
		}
		return alimentosDB;
	}
	
	/**
	 * Metodo que permite asignar un alimento al menu del restaurante.
	 * 
	 * @param idAlimentoSeleccionado identificador del alimento a asignar al menu
	 * @param idMenu identificador del menu
	 * @return 1 en caso de asigando, 0 en caso de falla
	 * @throws SQLException excepcion al presentar alguna error en la accion de base de datos.
	 */
	public int asignarAlimentoMenu(int idAlimentoSeleccionado, int idMenu) throws SQLException {
		String sql = "UPDATE alimento SET idMenu=" + idMenu + " WHERE idAlimento=" + idAlimentoSeleccionado  + ";";
		int resultado = ConnectionFactory.ejecutarSQL(sql);
		return resultado;
	}
	
	/**
	 * Metodo que permite quitar un alimento del menu del restaurante
	 * @param idAlimentoSeleccionado identificador del alimento seleccionado
	 * @return 1 si la consulta es exitosa, 0 en caso de no remover el alimento
	 * @throws SQLException excepcion en caso de error al ejecutar la sentencia SQL
	 */
	public int quitarAlimentoMenu(int idAlimentoSeleccionado) throws SQLException {
		String sql = "UPDATE alimento SET idMenu=NULL WHERE idAlimento = " + idAlimentoSeleccionado + ";";
		int resultado = ConnectionFactory.ejecutarSQL(sql);
		return resultado;
	}
	
	/**
	 * Metodo que permite consultar los alimentos por tipo de alimento y su menu
	 * correspondiente
	 * @param idTipoalimento identificador del tipo de alimento
	 * @param idMenu identificador del menu
	 * @return lista con los alimentos
	 * @throws SQLException excepcion en caso de error al ejecutar la sentencia SQL
	 */
	public List<Alimento> consultarAlimentosPorTipoAndMenu(int idTipoalimento, int idMenu) throws SQLException{
		String sql = "SELECT a.* FROM alimento a, tipo_alimento ta "
				+ "WHERE a.idTipoAlimento = ta.idTipoAlimento "
				+ "AND a.idTipoAlimento = " + idTipoalimento + " "
				+ "AND a.idMenu=" + idMenu + ";";
		List<Alimento> lista = new ArrayList<Alimento>();
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				Alimento alimento = new Alimento();
				alimento.setIdAlimento(rs.getInt("idAlimento"));
				alimento.setDescripcion(rs.getString("descripcion"));
				alimento.setNombre(rs.getString("nombre"));
				alimento.setImagen(rs.getString("imagen"));
				alimento.setPrecio(rs.getDouble("precio"));
				alimento.setDescuento(rs.getDouble("descuento"));
				alimento.setEstatus(rs.getBoolean("estatus"));
				alimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				alimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				//Tipo de alimento
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				//Restaurante
				Restaurante restaurante = new Restaurante();
				restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
				//Menu
				Menu menu = new Menu();
				menu.setIdMenu(rs.getInt("idMenu"));
				
				alimento.setTipoAlimento(tipoAlimento);
				alimento.setRestaurante(restaurante);
				alimento.setMenu(menu);
				
				lista.add(alimento);
			}
		}
		return lista;
	}
}
