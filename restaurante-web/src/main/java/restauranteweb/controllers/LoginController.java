/**
 * 
 */
package restauranteweb.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import restauranteservice.services.LoginService;
import restauranteweb.session.SessionBean;
import restauranteweb.utils.ControllersUtil;
import restauranteentities.entity.Empleado;

/**
 * @author Green Clase bean de jsf que se comunica con la pantallla login.xhtml
 *         Nota: siempre agregar @ManagedBean para realizar el proceso de
 *         atender un evento en la pagina
 */
@ManagedBean
@ViewScoped
public class LoginController {

	/**
	 * Usuario capturado por el usuario final
	 */
	private String username;

	/**
	 * Password capturado por el usuario final
	 */
	private String password;

	/**
	 * Indicador para saber si el usuario ingresado es un administrador general.
	 */
	private boolean esSuperAdminGeneral;
	
	/**
	 * Objeto que mantiene la informacion de sesion del aplicativo
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;

	/**
	 * Objeto para obtener la informacion de la logica de negocio del login
	 */
	private LoginService loginService = new LoginService();

	public void entrar() {
//		System.out.println("LoginController.entrada()");
//		System.out.println("Usuario: " + getUsername());
//		System.out.println("Password: " + getPassword());
		try {
			Empleado empleadoConsultado = this.loginService.consultarPorUsuarioYPassword(this.username, this.password,
					this.esSuperAdminGeneral);
			if (empleadoConsultado != null) {
				if (empleadoConsultado.isEstatus()) {
					if (empleadoConsultado.isSuperadmingeneral()) {
						//System.out.println("Si es super admin general");
						// Redireccionamos
						ControllersUtil.redireccionar("/adminrestaurantes.xhtml");
					}
					else if(empleadoConsultado.isSuperadmin()){
						//ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "No es", "No es administrador general");
						ControllersUtil.redireccionar("/adminmenu.xhtml");
					}
					else {
						ControllersUtil.redireccionar("/menuempleado.xhtml");
					}
					//Agregar al usuario en la sesion
					this.sessionBean.setEmpleado(empleadoConsultado);
				}
				else {
					ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: status", "Usuario no habilitado");
				}
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: null", "Usuario o contraseña no existe");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: SQL", "Verificar SQL");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("No fue posible redireccionar la pantalla");
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: Archivo", "adminrestaurantes.xhtml");
			e.printStackTrace();
		}
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the esSuperAdminGeneral
	 */
	public boolean isEsSuperAdminGeneral() {
		return esSuperAdminGeneral;
	}

	/**
	 * @param esSuperAdminGeneral the esSuperAdminGeneral to set
	 */
	public void setEsSuperAdminGeneral(boolean esSuperAdminGeneral) {
		this.esSuperAdminGeneral = esSuperAdminGeneral;
	}
	
	/**
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}

	/**
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
}
