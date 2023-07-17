/**
 * 
 */
package restauranteservice.services;

import restauranteentities.entity.Empleado;

import java.sql.SQLException;

import dao.impl.EmpleadoDAOImpl;

/**
 * @author Green
 * Clase que realiza la logica de negocio para la fincionalidad de la pantalla de login.
 */
public class LoginService {
	
	/**
	 * Objeto para poder obtener los resultados de las transacciones a las bases de datos
	 * en la tabla de empleados.
	 */
	private EmpleadoDAOImpl empleadoDAOImpl = new EmpleadoDAOImpl();
	
	/**
	 * Metodo que permite consultar un empleado desde el login con sus formacion y perfil
	 * @param usuario parametro capturado por el usuario
	 * @param password parametro capturado por el usuario
	 * @param esSuperAdminGeneral verifica si es administrador general
	 * @return objeto con el empleado loggeado
	 * @throws SQLException en caso de error en la consulta SQL
	 */
	public Empleado consultarPorUsuarioYPassword(String usuario, String password, boolean esSuperAdminGeneral) throws SQLException {
		Empleado empleado = empleadoDAOImpl.ConsultarPorUsuarioYPassword(usuario, password, esSuperAdminGeneral);
		return empleado;
	}
}
