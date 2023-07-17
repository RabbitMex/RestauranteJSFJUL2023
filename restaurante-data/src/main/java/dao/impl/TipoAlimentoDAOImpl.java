/**
 * 
 */
package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import dao.TipoAlimentoDAO;
import myexceptions.RestauranteException;
import restauranteentities.entity.TipoAlimento;

/**
 * @author Green
 *
 */
public class TipoAlimentoDAOImpl implements TipoAlimentoDAO {

	@Override
	public int guardar(TipoAlimento tipoAlimento) throws SQLException {
		String sql = "INSERT INTO tipo_alimento(descripcion, fechaCreacion, estatus)"
				+ " VALUES ('" + tipoAlimento.getDescripcion() + "', '" + tipoAlimento.getFechaCreacion()
				+ "', " + tipoAlimento.isEstatus() + ");";
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		return ejecutado;
	}

	@Override
	public int actualizar(TipoAlimento tipoAlimento) throws SQLException {
		//"UPDATE tipo_alimento SET descripcion=\"des123\", fechaModificacion='2023-05-31 13:42:00', estatus=1 WHERE idTipoAlimento=5;"
		String sql = "UPDATE tipo_alimento SET descripcion='" 
		+ tipoAlimento.getDescripcion() + "', fechaModificacion='" 
		+ tipoAlimento.getFechaModificacion() + "', estatus=" 
		+ tipoAlimento.isEstatus() + " WHERE idTipoAlimento=" 
		+ tipoAlimento.getIdTipoAlimento() + ";";
		int ejecuto = ConnectionFactory.ejecutarSQL(sql);
		return ejecuto;
	}

	@Override
	public int eliminar(int idTipoAlimento) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TipoAlimento> consultar() throws SQLException {
		List<TipoAlimento> list = new ArrayList<TipoAlimento>();
		String sql = "SELECT * FROM tipo_alimento;";
		//String sql = "SELECT * FROM tipo_alimento ORDER BY descripcion;";
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcion"));
				tipoAlimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoAlimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				tipoAlimento.setEstatus(rs.getBoolean("estatus"));
				list.add(tipoAlimento);
			}
		}
		return list;
	}

	@Override
	public TipoAlimento consultarPorId(int idTipoAlimento) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Metodo que permite consulatar los tipos de alimentos que corresponden al 
	 * restaurante del usuario en sesion.
	 * 
	 * @param idRestauranteUsuarioSesion identificador del restaurante
	 * @return Lista de tipos de alimentos.
	 * @throws SQLException en caso de error al ejecutar la sentencia SQL
	 */
	public List<TipoAlimento> consultarPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
		List<TipoAlimento> list = new ArrayList<TipoAlimento>();
		
		String sql = "SELECT ta.* FROM tipo_alimento ta, restaurante_has_tipo_alimento rhta, restaurante res "
				+ "WHERE ta.idTipoAlimento = rhta.idTipoAlimento "
				+ "AND res.idRestaurante = rhta.idRestaurante "
				+ "AND res.idRestaurante = " + idRestauranteUsuarioSesion + ";";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcion"));
				tipoAlimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoAlimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				tipoAlimento.setEstatus(rs.getBoolean("estatus"));
				list.add(tipoAlimento);
			}
		}
		return list;
		
	}
	
	/**
	 * Metodo para poder guardar el tipo de alimento pero tambien relacionarlo con 
	 * el id del restaurante del usuario en sesion.
	 * 
	 * @param tipoAlimento Objeto tipo alimento a guardar en la base de datos.
	 * @param idRestauranteUsuarioSesion id del restaurante del usuario en sesion.
	 * @return 1 si todo esta bien, 0 si hay algun error
	 * @throws SQLException excepcion en caso de error al ejecutar la sentencia SQL
	 */
	public int guardar(TipoAlimento tipoAlimento, int idRestauranteUsuarioSesion) throws SQLException {
		String sql = "INSERT INTO tipo_alimento(descripcion, fechaCreacion, estatus)"
				+ " VALUES ('" + tipoAlimento.getDescripcion() + "', '" + tipoAlimento.getFechaCreacion()
				+ "', " + tipoAlimento.isEstatus() + ");";
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		
		sql = "SELECT LAST_INSERT_ID();";
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		
		if(rs!=null) {
			if(rs.next()) {
				int idUltimo;
				idUltimo = rs.getInt(1);
				sql = "INSERT INTO restaurante_has_tipo_alimento(idRestaurante, idTipoAlimento) VALUES(" + idRestauranteUsuarioSesion  + ", " + idUltimo + ");";
				ConnectionFactory.ejecutarSQL(sql);
			}
		}
		
		return ejecutado;
	}

	/**
	 * Metodo que permite consultar los tipos de alimentos del menu del 
	 * restaurante
	 * @param idMenu identificador del menu
	 * @return lista de tipos de alimentos
	 * @throws SQLException excepcion en caso de error al ejecutar la sentencia SQL
	 */
	public List<TipoAlimento> consultarTiposAlimentosPorMenu(int idMenu) throws SQLException{
		String sql = "SELECT DISTINCT * FROM tipo_alimento ta, menu m, alimento a "
				+ "WHERE ta.idTipoAlimento=a.idTipoAlimento "
				+ "AND a.idMenu=m.idMenu "
				+ "AND m.idMenu=" + idMenu + " "
				+ "ORDER BY ta.descripcion;";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		List<TipoAlimento> lista = new ArrayList<TipoAlimento>();
		if(rs!=null){
			while(rs.next()) {
				TipoAlimento tipoAlimento = new TipoAlimento();
				tipoAlimento.setIdTipoAlimento(rs.getInt("idTipoAlimento"));
				tipoAlimento.setDescripcion(rs.getString("descripcion"));
				tipoAlimento.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoAlimento.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				tipoAlimento.setEstatus(rs.getBoolean("estatus"));
				lista.add(tipoAlimento);
			}
		}
		
		return lista;
	}
}
