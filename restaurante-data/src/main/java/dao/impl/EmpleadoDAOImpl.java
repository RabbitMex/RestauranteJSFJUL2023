/**
 * 
 */
package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connection.ConnectionFactory;
import dao.EmpleadoDao;
import myexceptions.RestauranteException;
import restauranteentities.entity.Empleado;
import restauranteentities.entity.Rol;
import restauranteentities.entity.Sucursal;
import restauranteentities.entity.Restaurante;
import restauranteentities.entity.Menu;

/**
 * @author Green
 * 
 * Clase que se encarga de hacer el CRUD  de empleados.
 *
 */
public class EmpleadoDAOImpl implements EmpleadoDao {
	static {
		try {
			ConnectionFactory.conectar();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error en base de datos: " + e.getMessage());
		}
	}
	
	/**
	 * Metodo para poder guardar un empleado
	 * @param empleado a guardar en al base de datos
	 * @return 1 si se guardo correctamente, 0 en caso contrario
	 * @throws SQLException se lanza si hay un problema al ejecutar la sentencia SQL
	 */
	@Override
	public int guardar(Empleado empleado) throws SQLException {
		String sql = "INSERT INTO empleado(nombre, primerApellido, "
				+ "segundoApellido, idRol, idSucursal, usuario, "
				+ "password, email, superadmin, superadmingeneral, "
				+ "fechaCreacion, estatus) "
				+ "VALUES('" + empleado.getNombre() + "', '" + empleado.getPrimerApellido() 
				+ "', '" + empleado.getSegundoApellido() + "', " + empleado.getRol().getIdRol() 
				+ ", " + empleado.getSucursal().getIdSucursal() + ", '" 
				+ empleado.getUsuario() + "', '" + empleado.getPassword() 
				+ "', '" + empleado.getEmail() + "', " + empleado.isSuperadmin() 
				+ ", " + empleado.isSuperadmingeneral() 
				+ ", '" + empleado.getFechaCreacion() + "', " + empleado.isEstatus() + ");";
		int resultado = ConnectionFactory.ejecutarSQL(sql);
		return resultado;
	}

	/**
	 * Metdodo que permite actualizar el empleado en la base de datos o tabla empleado.
	 */
	@Override
	public int actualizar(Empleado empleado) throws SQLException {
		
		//String sql = "UPDATE empleado SET nombre = '', primerapellido='', segundoApellido='', idRol=1, usuario='', password='', email='', superadmin=true, superadmingeneral=true, fechaModificacion='', estatus=1 WHERE idEmpleado=1";
		String sql = "UPDATE empleado SET nombre = '" + empleado.getNombre() 
		+ "', primerapellido='" + empleado.getPrimerApellido() 
		+ "', segundoApellido='" + empleado.getSegundoApellido() 
		+ "', idRol=" + empleado.getRol().getIdRol() + ", usuario='" 
		+ empleado.getUsuario() + "', password='" + empleado.getPassword() 
		+ "', email='" + empleado.getEmail() + "', superadmin=" 
		+ empleado.isSuperadmin() + ", fechaModificacion='" 
		+ empleado.getFechaModificacion() + "', estatus=" 
		+ empleado.isEstatus() + " WHERE idEmpleado=" 
		+ empleado.getIdEmpleado() + ";";
		
		int resultado = ConnectionFactory.ejecutarSQL(sql);
		return resultado;
	}

	@Override
	public int eliminar(int idEmpleado) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Empleado> consultar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado consultarPorId(int idEmpleado) throws SQLException, RestauranteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Metodo que permite consultar un empleado desde el login con sus formacion y perfil
	 * @param usuario parametro capturado por el usuario
	 * @param password parametro capturado por el usuario
	 * @param esSuperAdminGeneral verifica si es administrador general
	 * @return objeto con el empleado loggeado
	 * @throws SQLException en caso de error en la consulta SQL
	 */
	public Empleado ConsultarPorUsuarioYPassword(String usuario, String password, boolean esSuperAdminGeneral) throws SQLException {
		Empleado empleado = null;
		String sql = "";
		if(esSuperAdminGeneral) {
			sql = "SELECT e.*, r.nombre AS nombrePerfil FROM empleado e, rol r " 
				+ "WHERE e.idRol = r.idRol "
			    + "AND (e.usuario = '" + usuario + "' OR e.email = '" + usuario + "') "
				+ "AND e.password = '" + password + "' "
				+ "AND e.idSucursal IS NULL;";
		}
		else {
			//Reemplazar para usuario administrador sucursal y empleado
			sql = "SELECT e.*, r.nombre as nombrePerfil, s.nombre as nombreSucursal, res.nombre as nombreRestaurante, res.idRestaurante, res.imagen, res.idMenu "
					+ "FROM empleado e, rol r, sucursal s, restaurante res "
					+ "WHERE e.idRol = r.idRol "
					+ "AND e.idSucursal = s.idsucursal "
					+ "AND s.idRestaurante = res.idRestaurante "
					+ "AND (e.usuario = '" + usuario + "' OR e.email = '" + usuario + "') "
					+ "AND e.password = '" + password + "';";
		}
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null){
			if(rs.next()) {
				empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt("idEmpleado"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setPrimerApellido(rs.getString("primerApellido"));
				empleado.setSegundoApellido(rs.getString("segundoApellido"));
				empleado.setUsuario(rs.getString("usuario"));
				empleado.setPassword(rs.getString("password"));
				empleado.setEmail(rs.getString("email"));
				empleado.setEstatus(rs.getBoolean("estatus"));
				empleado.setSuperadmin(rs.getBoolean("superadmin"));
				empleado.setSuperadmingeneral(rs.getBoolean("superadmingeneral"));
				
				Rol rol = new Rol();
				rol.setIdRol(rs.getInt("idRol"));
				rol.setNombre(rs.getString("nombrePerfil"));
				
				empleado.setRol(rol);
				
				//Añadir funcionalidad para el caso de administrador de sucursal y empleado
				if(!empleado.isSuperadmingeneral()) {
					//Obtener la sucursal
					Sucursal sucursal = new Sucursal();
					sucursal.setIdSucursal(rs.getInt("idSucursal"));
					sucursal.setNombre(rs.getString("nombreSucursal"));
					
					Restaurante restaurante = new Restaurante();
					restaurante.setIdRestaurante(rs.getInt("idRestaurante"));
					restaurante.setNombre(rs.getString("nombreRestaurante"));
					restaurante.setImagen(rs.getString("imagen"));
					
					Menu menu = new Menu();
					menu.setIdMenu(rs.getInt("idMenu"));
					restaurante.setMenu(menu);
					
					sucursal.setRestaurante(restaurante);
					
					empleado.setSucursal(sucursal);
				}
			}
		}
		
		return empleado;
	}
	
	/**
	 * Metodo que permite consultar la lista de empleados del administrados de 
	 * la sucursal
	 * @param idRestauranteUsuarioSesion id del restaurante
	 * @return lista de empleados
	 * @throws SQLException error al ejecutar la sentencia SQL
	 */
	public List<Empleado> consultarPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
		List<Empleado> empleadosDB = new ArrayList<Empleado>();
		String sql = 
		"SELECT e.*, r.nombre AS nombreRol FROM restaurante res, sucursal s, empleado e, rol r "
		+ "WHERE e.idSucursal = s.idSucursal "
		+ "AND s.idRestaurante = res.idRestaurante "
		+ "AND e.idRol = r.idRol "
		+ "AND s.idRestaurante = " + idRestauranteUsuarioSesion + ";";
		
		ResultSet rs = ConnectionFactory.ejecutarSQLSelect(sql);
		if(rs!=null) {
			while(rs.next()) {
				Empleado empleado = new Empleado();
				empleado.setIdEmpleado(rs.getInt("idEmpleado"));
				empleado.setNombre(rs.getString("nombre"));
				empleado.setPrimerApellido(rs.getString("primerApellido"));
				empleado.setSegundoApellido(rs.getString("segundoApellido"));
				empleado.setUsuario(rs.getString("usuario"));
				empleado.setPassword(rs.getString("password"));
				empleado.setEmail(rs.getString("email"));
				empleado.setEstatus(rs.getBoolean("estatus"));
				empleado.setSuperadmin(rs.getBoolean("superadmin"));
				empleado.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				empleado.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null ? rs.getTimestamp("fechaModificacion").toLocalDateTime(): null);
				
				//empleado.setSuperadmingeneral(rs.getBoolean("superadmingeneral"));
				Rol rol = new Rol();
				rol.setIdRol(rs.getInt("idRol"));
				rol.setNombre(rs.getString("nombreRol"));
				empleado.setRol(rol);
				
				empleadosDB.add(empleado);
			}
		}
		
		return empleadosDB;
	}
}
