/**
 * 
 */
package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connection.ConnectionFactory;
import dao.TipoRestauranteDAO;
import restauranteentities.entity.TipoRestaurante;
import enums.CodigoEnum;
import myexceptions.RestauranteException;

/**
 * @author Green
 * Clase que implementa el crud y otros metodos personalizados de transacciones a la base de datos
 * en la tabla tipo_restaurante
 *
 */
public class TipoRestauranteDAOImpl implements TipoRestauranteDAO{
	
	//No es necesario porque el objeto ya es existente desde el login.
//	static {
//		try {
//			ConnectionFactory.conectar();
//		} catch (ClassNotFoundException | SQLException  e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.err.println("Error en base de datos: " + e.getMessage());
//		} 
//	}

	@Override
	public int guardar(TipoRestaurante tipoRestaurante) throws SQLException {
		String sql = "INSERT INTO tipo_restaurante(descripcion, fechaCreacion, estatus) VALUES ('"+tipoRestaurante.getDescripcion()+"', '"+tipoRestaurante.getFechaCreacion()+"', "+tipoRestaurante.isEstatus()+");";
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		return ejecutado;
	}

	@Override
	public int actualizar(TipoRestaurante tipoRestaurante) throws SQLException {
		String sql = "UPDATE tipo_restaurante SET descripcion = '"+ 
				tipoRestaurante.getDescripcion()+"', fechaModificacion = '"+
				tipoRestaurante.getFechaModificacion()+"', estatus = " + 
				tipoRestaurante.isEstatus() + " WHERE idTipoRestaurante = " + 
				tipoRestaurante.getIdTipoRestaurante() + ";";
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		return ejecutado;
	}

	@Override
	public int eliminar(int idTipoRestaurante) throws SQLException {
		String sql = "DELETE FROM tipo_restaurante WHERE idTipoRestaurante = " + idTipoRestaurante + ";";
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		return ejecutado;
	}

	@Override
	public List<TipoRestaurante> consultar() throws SQLException {
		List<TipoRestaurante> list = new ArrayList<TipoRestaurante>();
		//String sql = "SELECT * FROM tipo_restaurante ORDER BY descripcion;";
		String sql = "SELECT * FROM tipo_restaurante;";
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				TipoRestaurante tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				tipoRestaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoRestaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				tipoRestaurante.setEstatus(rs.getBoolean("estatus"));
				list.add(tipoRestaurante);
			}
		}
		return list;
	}

	@Override
	public TipoRestaurante consultarPorId(int idTipoRestaurante) throws SQLException, RestauranteException {
		String sql = "SELECT * FROM tipo_restaurante where idTipoRestaurante = " + idTipoRestaurante + ";";
		ResultSet rs = null;
		
		try {
			rs = ConnectionFactory.ejecutarSQLSelect(sql);
		}
		catch(Exception e) {
			if(e.getClass().getName().contains("SQLSyntaxErrorException")) {
				throw new RestauranteException("Error de sintaxis de la setencia " + sql, CodigoEnum.SINTAXIS_SQL_ERROR_CODE);
			}
		}
		
		TipoRestaurante tipoRestaurante = null;
		
		if(rs!=null) {
			if(rs.next()) {
				tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				tipoRestaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoRestaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				tipoRestaurante.setEstatus(rs.getBoolean("estatus"));
			}
		}
		
		return tipoRestaurante;
	}
	
}
