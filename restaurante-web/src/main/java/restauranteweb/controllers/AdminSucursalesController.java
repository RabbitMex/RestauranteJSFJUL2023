/**
 * 
 */
package restauranteweb.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import restauranteentities.entity.Sucursal;
import restauranteweb.session.SessionBean;
import restauranteweb.utils.ControllersUtil;
import restauranteservice.services.AdminSucursalService;

/**
 * @author Daniel Bastian Luna
 * 
 * Clase controles que permite la comunicacion con la pantalla adminsucursales
 * 
 */
@ManagedBean
@ViewScoped
public class AdminSucursalesController {
	/**
	 * Lista de sucursales a mostrar en al pagina
	 */
	private List<Sucursal> sucursales;	
	/**
	 * Objeto que contiene la informacion de sesion del usuario
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	/**
	 * Objeto que permite realizar la logica de negocio para el administrador
	 * de sucursal o restaurante.
	 */
	private AdminSucursalService adminSucursalService = new AdminSucursalService();
	
	/**
	 * Metodo que permite inicializar la informacion de la pantalla adminsucurales
	 */
	@PostConstruct
	public void init() {
		this.consultar();
	}
	
	/**
	 * Metodo que permite consultar la lista de sucursales del restaurante del usuario en sesion.
	 */
	public void consultar() {
		//int idRestauranteUsuarioSesion = sessionBean.getEmpleado().getSucursal().getIdSucursal();
		int idRestauranteUsuarioSesion = sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
		try {
			this.sucursales = this.adminSucursalService.consultarSucursalesPorRestaurante(idRestauranteUsuarioSesion);
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error SQL", "Error al consultar la lista de sucursales");
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the sucursales
	 */
	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	/**
	 * @param sucursales the sucursales to set
	 */
	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
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
