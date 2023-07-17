/**
 * 
 */
package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionFactory;

import java.util.ArrayList;

import dao.SucursalDAO;
import myexceptions.RestauranteException;
import restauranteentities.entity.Sucursal;
import restauranteentities.entity.Restaurante;

/**
 * @author Daniel Bastian Luna
 * 
 * 
 *
 */
public class SucursalDAOImpl implements SucursalDAO {

	@Override
	public int guardar(Sucursal sucursal) throws SQLException {
		String sql = "INSERT INTO sucursal(nombre, idRestaurante, fechaCreacion, estatus) VALUES('" 
	+ sucursal.getNombre() + "', " + sucursal.getRestaurante().getIdRestaurante() 
	+ ", '" + sucursal.getFechaCreacion() + "', " 
	+ sucursal.isEstatus() + ");";
		int resultado = ConnectionFactory.ejecutarSQL(sql); 
		return resultado;
	}

	@Override
	public int actualizar(Sucursal sucursal) throws SQLException {
		String sql = "UPDATE sucursal SET nombre='" + sucursal.getNombre() 
		+ "', fechaModificacion='" + sucursal.getFechaModificacion() 
		+ "', estatus=" + sucursal.isEstatus() 
		+ " WHERE idSucursal=" + sucursal.getIdSucursal() + ";";
		int resultado = ConnectionFactory.ejecutarSQL(sql);
		return resultado;
	}

	@Override
	public int eliminar(int idSucursal) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Sucursal> consultar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sucursal consultarPorId(int idSucursal) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Metodo que permite consultar la lista de sucursales de un restaurante
	 * @param idRestauranteUsuarioSesion id del restaurante del usuario en sesion
	 * @return lista de sucursales
	 * @throws SQLException excepcion en caso de error en caso de error al ejecutar la sentencia SQL
	 */
	public List<Sucursal> consultarPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException {
		List<Sucursal> sucursalesDB = new ArrayList<Sucursal>();
		//String sql = "SELECT * FROM sucursal s, restaurante res WHERE s.idRestaurante = res.idRestaurante AND s.idRestaurante = 15;";
		//String sql = "SELECT s.*, res.nombre FROM sucursal s, restaurante res WHERE s.idRestaurante = res.idRestaurante AND s.idRestaurante = 15;";
		//String sql = "SELECT s.*, res.nombre AS nombreRestaurante FROM sucursal s, restaurante res WHERE s.idRestaurante = res.idRestaurante AND s.idRestaurante = 15;";
		String sql = "SELECT s.*, res.nombre AS nombreRestaurante FROM sucursal s, restaurante res WHERE s.idRestaurante = res.idRestaurante AND s.idRestaurante = "
		+ idRestauranteUsuarioSesion + ";";
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				Sucursal sucursal = new Sucursal();
				sucursal.setIdSucursal(rs.getInt("idSucursal"));
				sucursal.setNombre(rs.getString("nombre"));
				
				Restaurante restaurante = new Restaurante();
				restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
				restaurante.setNombre(rs.getString("nombreRestaurante"));
				sucursal.setRestaurante(restaurante);
				
				sucursal.setFechaCreacion(rs.getTimestamp("fechaCreacion") != null ? rs.getTimestamp("fechaCreacion").toLocalDateTime(): null);
				sucursal.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				sucursal.setEstatus(rs.getBoolean("estatus"));
				
				sucursalesDB.add(sucursal);
			}
		}
		return sucursalesDB;
	}
}
