/**
 * 
 */
package restauranteservice.services;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import dao.impl.TipoAlimentoDAOImpl;
import dao.impl.AlimentoDAOImpl;
import dao.impl.EmpleadoDAOImpl;
import dao.impl.RolDAOImpl;
import dao.impl.SucursalDAOImpl;
import restauranteentities.entity.Alimento;
import restauranteentities.entity.TipoAlimento;
import restauranteentities.entity.Empleado;
import restauranteentities.entity.Rol;
import restauranteentities.entity.Sucursal;

/**
 * @author Green
 * clase que realiza la logica de negocion para la pantalla de administrador de sucursal
 */
public class AdminSucursalService {
	
	/**
	 * Objeto que permite realizar la consulta en la tabla tipo alimentos
	 */
	private TipoAlimentoDAOImpl tipoAlimentoDaoImpl = new TipoAlimentoDAOImpl();
	private AlimentoDAOImpl alimentoDaoImpl = new AlimentoDAOImpl();
	private EmpleadoDAOImpl empleadoDAOImpl= new EmpleadoDAOImpl();
	private RolDAOImpl rolDAOImpl= new RolDAOImpl();
	private SucursalDAOImpl sucursalDAOImpl = new SucursalDAOImpl();
	
	//TIPOS DE ALIMENTOS
	/**
	 * Metodo que permite consultar la lista de tipos de alimentos
	 * @return Lista con los tipos de alimentos 
	 * @throws SQLException excepcion en caso de erros en la sentencia sql
	 */
	public List<TipoAlimento> consultarTiposAlimentos() throws SQLException{
		return this.tipoAlimentoDaoImpl.consultar();
	}
	
	/**
	 * Guardar el tipo de alimento utilizando un campo para guardar en la base 
	 * de datos. importante inicializar la fecha de la creacion y el estatus.
	 * @param tipoAlimento objeto a guardar
	 * @return 1 en caso de guardar, 0 si no es guardado el objeto
	 * @throws SQLException excepcion en caso de erros en la sentencia sql
	 */
	public int guardarTipoAlimento(TipoAlimento tipoAlimento) throws SQLException {
		tipoAlimento.setFechaCreacion(LocalDateTime.now());
		tipoAlimento.setEstatus(true);
		return this.tipoAlimentoDaoImpl.guardar(tipoAlimento);
	}
	
	/**
	 * Guardar el tipo de alimento utilizando un campo para guardar en la base 
	 * de datos. importante inicializar la fecha de la creacion y el estatus.
	 * Guarda el tipo de alimento conforme al usuario en sesion.
	 * 
	 * @param tipoAlimento objeto a guardar
	 * @param idRestauranteSession identificador del restaurante
	 * @return 1 en caso de guardar, 0 si no es guardado el objeto
	 * @throws SQLException excepcion en caso de erros en la sentencia sql
	 */
	public int guardarTipoAlimentoRestaurante(TipoAlimento tipoAlimento, int idRestauranteSesion) throws SQLException {
		tipoAlimento.setFechaCreacion(LocalDateTime.now());
		tipoAlimento.setEstatus(true);
		return this.tipoAlimentoDaoImpl.guardar(tipoAlimento, idRestauranteSesion);
	}
	
	/**
	 * Metodo que permite actualizar un tipo de alimetno
	 * @param tipoAlimento objeto a editar
	 * @return 1 en caso de actualizar, 0 en caso de no actualizar
	 * @throws SQLException excepcion en caso de erros en la sentencia sql
	 */
	public int actualizarTipoAlimento(TipoAlimento tipoAlimento) throws SQLException {
		tipoAlimento.setFechaModificacion(LocalDateTime.now());
		return this.tipoAlimentoDaoImpl.actualizar(tipoAlimento);
	}
	
	/**
	 * Metodo que permite consultar los tipos de alimentos que corresponden al restaurante
	 * del usuario en sesion.
	 * 
	 * @param idRestauranteUsuarioSesion Identificador del restaurante
	 * @return lista de tipos de alimentos.
	 * @throws SQLException excepcion en caso de error al ejecutar la sentencia SQL.
	 * 
	 */
	public List<TipoAlimento> consultarTiposAlimentosPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
		return this.tipoAlimentoDaoImpl.consultarPorRestaurante(idRestauranteUsuarioSesion);
	}
	
	//Alimentos
	/**
	 * Metodo que permite consultar la lista de alimentos de un restaurante
	 * @param idRestauranteUsuarioSesion identficiador del restaurante
	 * @return lista de alimentos.
	 * @throws SQLException excepcion en caso de erros en la sentencia sql
	 */
	public List<Alimento> consultarAlimentosPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
		return this.alimentoDaoImpl.consultarPorIdRestaurante(idRestauranteUsuarioSesion);
	}
	
	/**
	 * Guardar un registro de alimento
	 * @param alimento objeto a guardar
	 * @return 1 en caso de guardar , 0 en caso de fallar
	 * @throws SQLException excepcion en caso de erros en la sentencia sql
	 */
	public int guardarAlimento(Alimento alimento) throws SQLException {
		alimento.setFechaCreacion(LocalDateTime.now());
		alimento.setEstatus(true);
		return this.alimentoDaoImpl.guardar(alimento);
	}
	
	/**
	 * Metodo que permite actualizar los datos del alimento
	 * @param alimentoobjeto a guardar
	 * @return 1 en caso de guardar , 0 en caso de fallar
	 * @throws SQLException excepcion en caso de erros en la sentencia sql
	 */
	public int actualizarAlimento(Alimento alimento) throws SQLException {
		alimento.setFechaModificacion(LocalDateTime.now());
		return this.alimentoDaoImpl.actualizar(alimento);
	}
	
	/**
	 * Metodo que permite consultar la lista de alimentos disponibles del 
	 * restaurante no asignados al menu
	 * @param idRestauranteUsuarioSesion id del restaurante
	 * @return lista de alimentos disponibles
	 * @throws SQLException exception al ejecutar la sentencia SQL
	 */
	public List<Alimento> consultarAlimentosDisponiblesRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
		return this.alimentoDaoImpl.consultarAlimentosDisponibles(idRestauranteUsuarioSesion);
	}
	
	/**
	 * Metodo que permite consultar la lista de alimentos disponibles del 
	 * restaurante asignados al menu
	 * @param idMenu id del menu
	 * @return lista de alimentos asignados al menu
	 * @throws SQLException exception al ejecutar la sentencia SQL
	 */
	public List<Alimento> consultarAlimentosAsignadosMenu(Integer idMenu) throws SQLException{
		return this.alimentoDaoImpl.consultarAlimentosAsignados(idMenu);
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
		return this.alimentoDaoImpl.asignarAlimentoMenu(idAlimentoSeleccionado, idMenu);
	}
	
	/**
	 * Metodo que permite quitar un alimento del menu del restaurante
	 * @param idAlimentoSeleccionado identificador del alimento seleccionado
	 * @return 1 si la consulta es exitosa, 0 en caso de no remover el alimento
	 * @throws SQLException excepcion en caso de error al ejecutar la sentencia SQL
	 */
	public int quitarAlimentoMenu(int idAlimentoSeleccionado) throws SQLException {
		return this.alimentoDaoImpl.quitarAlimentoMenu(idAlimentoSeleccionado);
	}
	
	//EMPLEADOS
	/**
	 * Metodo que permite consultar la lista de empleados del administrados de 
	 * la sucursal
	 * @param idRestauranteUsuarioSesion id del restaurante
	 * @return lista de empleados
	 * @throws SQLException error al ejecutar la sentencia SQL
	 */
	public List<Empleado> consultarEmpleadosPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
		return this.empleadoDAOImpl.consultarPorRestaurante(idRestauranteUsuarioSesion);
	}
	
	/**
	 * Metodo para poder guardar un empleado
	 * @param empleado a guardar en al base de datos
	 * @return 1 si se guardo correctamente, 0 en caso contrario
	 * @throws SQLException se lanza si hay un problema al ejecutar la sentencia SQL
	 */
	public int guardarEmpleado(Empleado empleado) throws SQLException {
		//Si el rol a asignar al empleado es administrador de sucursal
		if(empleado.getRol().getIdRol()==2) {
			empleado.setSuperadmin(true);
		}
		empleado.setFechaCreacion(LocalDateTime.now());
		empleado.setEstatus(true);
		return this.empleadoDAOImpl.guardar(empleado);
	}
	
	/**
	 * Metodo que permite actualizar la informacion de un empleado. 
	 * @param empleado a actualizar en la tabla empleado.
	 * @return 1 si se guardo correctamente, 0 en caso contrario.
	 * @throws SQLException en caso de que se tenga un problema al ejecutar la sentencia SQL
	 */
	public int actualizarEmpleado(Empleado empleado) throws SQLException {
		//si el rol no es administrador de sucursal
		if(empleado.getRol().getIdRol()!=2) {
			empleado.setSuperadmin(false);
		}
		empleado.setFechaModificacion(LocalDateTime.now());
		return this.empleadoDAOImpl.actualizar(empleado);
	}
	
	//ROLES
	/**
	 * Metodo que permite consultar la lista de roles sin administrador general
	 * @return una lista de roles sin administrador general.
	 * @throws SQLException error al ejecutar la sentencia SQL
	 */
	public List<Rol> consultarRolesSinAdminGeneral() throws SQLException{
		return this.rolDAOImpl.consultarRolesSinAdminGeneral();
	}
	
	//SUCURSALES
	/**
	 * Metodo que permite consultar la lista de sucursales de un restaurante
	 * @param idRestauranteUsuarioSesion id del restaurante del usuario en sesion
	 * @return lista de sucursales
	 * @throws SQLException excepcion en caso de error en caso de error al ejecutar la sentencia SQL
	 */
	public List<Sucursal> consultarSucursalesPorRestaurante(int idRestauranteUsuarioSesion) throws SQLException{
		return this.sucursalDAOImpl.consultarPorRestaurante(idRestauranteUsuarioSesion);
	}
	
	/**
	 * Metodo que permite guardar una sucursal
	 * @param sucursal objeto a guardar
	 * @return 1 en caso de guardado, 0 en caso de no guardar
	 * @throws SQLException excepcion en caso de haber un error al ejecutar la sentencia SQL.
	 */
	public int guardarSucursal(Sucursal sucursal) throws SQLException {
		sucursal.setFechaCreacion(LocalDateTime.now());
		sucursal.setEstatus(true);
		return this.sucursalDAOImpl.guardar(sucursal);
	}
	
	/**
	 * Metodo para poder actualizar el la tabla sucursal el objeto sucursal
	 * @param sucursal objeto para actualizar en la base de datos
	 * @return 1 si se guardo correctamente, 0 si no se guardo
	 * @throws SQLException excepcion si hay un error en la sentencia SQL.
	 */
	public int actualizarSucursal(Sucursal sucursal) throws SQLException {
		sucursal.setFechaModificacion(LocalDateTime.now());
		return this.sucursalDAOImpl.actualizar(sucursal);
	}
}
