/**
 * 
 */
package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import dao.RestauranteDao;
import restauranteentities.entity.Restaurante;
import restauranteentities.entity.TipoRestaurante;
import myexceptions.RestauranteException;

/**
 * @author Green Clase que implementa los metodos del CRUD de la interfaz
 *         RestauranteDAO
 *
 */
public class RestauranteDAOImpl implements RestauranteDao {

//	static {
//		try {
//			ConnectionFactory.conectar();
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.err.println("Error en base de datos: " + e.getMessage());
//		}
//	}

	@Override
	public int guardar(Restaurante restaurante) throws SQLException {
		String sql = "INSERT INTO restaurante(nombre, imagen, slogan, idTipoRestaurante, fechaCreacion, estatus, idMenu)"
				+ " VALUES ('" + restaurante.getNombre() + "', '" + restaurante.getImagen() + "', '" + restaurante.getSlogan() + "', "
				+ restaurante.getTipoRestaurante().getIdTipoRestaurante() + ", '" + restaurante.getFechaCreacion()
				+ "', " + restaurante.isEstatus() + ", " + restaurante.getMenu().getIdMenu() + ");";
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		return ejecutado;
	}

	@Override
	public int actualizar(Restaurante restaurante) throws SQLException {
		String sql = "UPDATE restaurante SET nombre = '" + restaurante.getNombre() + "', imagen = '"
				+ restaurante.getImagen() + "', slogan = '" + restaurante.getSlogan() + "', idTipoRestaurante = "
				+ restaurante.getTipoRestaurante().getIdTipoRestaurante() + ", fechaModificacion = '"
				+ restaurante.getFechaModificacion() + "', estatus = " + restaurante.isEstatus() + ", idMenu = "
				+ restaurante.getMenu().getIdMenu() + " WHERE idRestaurante = " + restaurante.getIdRestaurante() + ";";
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		return ejecutado;
	}

	@Override
	public int eliminar(int idRestaurante) throws SQLException {
		String sql = "DELETE FROM restaurante WHERE idRestaurante = " + idRestaurante + ";";
		int ejecutado = ConnectionFactory.ejecutarSQL(sql);
		return ejecutado;
	}

	@Override
	public List<Restaurante> consultar() throws SQLException {
		List<Restaurante> list = new ArrayList<Restaurante>();
		//String sql = "SELECT * FROM tipo_restaurante ORDER BY descripcion;";
		//String sql = "SELECT * FROM restaurante ORDER BY nombre;";
		//String sql = "SELECT * FROM restaurante;";
		String sql = "SELECT r.*, tr.descripcion FROM restaurante r, tipo_restaurante tr WHERE r.idTipoRestaurante = tr.idTipoRestaurante;";
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				Restaurante restaurante = new Restaurante();
				restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
				restaurante.setNombre(rs.getString("nombre"));
				restaurante.setImagen(rs.getString("imagen"));
				restaurante.setSlogan(rs.getString("slogan"));
				
				TipoRestaurante tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				restaurante.setTipoRestaurante(tipoRestaurante);
				
				restaurante.setFechaCreacion( rs.getTimestamp("fechaCreacion") != null ? rs.getTimestamp("fechaCreacion").toLocalDateTime(): null);
				restaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				restaurante.setEstatus(rs.getBoolean("estatus"));
				
				//restaurante.setMenu();
				
				list.add(restaurante);
			}
		}
		return list;
	}

	@Override
	public Restaurante consultarPorId(int idRestaurante) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}

}
