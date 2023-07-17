package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionFactory;

import java.util.ArrayList;

import dao.RolDAO;
import myexceptions.RestauranteException;
import restauranteentities.entity.Rol;

public class RolDAOImpl implements RolDAO {

	@Override
	public int guardar(Rol rol) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actualizar(Rol rol) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminar(int idRol) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Rol> consultar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rol consultarPorId(int idRol) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Metodo que permite consultar los roles para el administrador de sucursal 
	 * que desee guardar un empleado
	 * @return lista de roles que no son administradores generales
	 * @throws SQLException Excepcion en caso de error al ejecutar la sentencia SQL
	 */
	public List<Rol> consultarRolesSinAdminGeneral() throws SQLException{
		String sql = "SELECT * FROM rol WHERE nombre!='Administrador General';";
		List<Rol> listaRolesDB = new ArrayList<Rol>();
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				Rol rol = new Rol();
				rol.setIdRol(rs.getInt("idRol"));
				rol.setNombre(rs.getString("nombre"));
				rol.setFechaCreacion(rs.getTimestamp("fechaCreacion") != null ? rs.getTimestamp("fechaCreacion").toLocalDateTime(): null);
				rol.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				rol.setEstatus(rs.getBoolean("estatus"));
				listaRolesDB.add(rol);
			}
		}
		return listaRolesDB;
	}
}
