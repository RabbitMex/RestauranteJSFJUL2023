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
import restauranteentities.entity.Restaurante;
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
	private Sucursal sucursal;
	
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
		this.inicializarComponentes();
	}
	
	/**
	 * Permite inicializar los componetnes de la pantalla de sucursales.
	 */
	public void inicializarComponentes() {
		this.sucursal = new Sucursal();
		this.sucursal.setRestaurante(new Restaurante());
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
	 * Metodo que permite guardar la sucursal
	 */
	public void guardar() {
		//Obtener el restaurante en sesion.
		Restaurante restaurante = this.sessionBean.getEmpleado().getSucursal().getRestaurante();
		this.sucursal.setRestaurante(restaurante);
		try {
			int resultado = this.adminSucursalService.guardarSucursal(this.sucursal);
			if(resultado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK: ", "Se Guardo la sucursal: " + this.sucursal.getNombre());
				this.consultar();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ", "No se guardo la sucursal");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error SQL", "Error al guardar la sucursal");
			e.printStackTrace();
		}
	}
	
	public void cargarInformacionModal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	/**
	 * Metodo para poder actualizar un registro en la tabla sucursal.
	 */
	public void actualizar() {
		//System.out.println("Actualizar informacion de la sucursal");
		try {
			int resultado = this.adminSucursalService.actualizarSucursal(this.sucursal);
			if(resultado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK: ", "Se actualizo la sucursal: " + this.sucursal.getNombre());
				this.consultar();
			}
			else{
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ", "No se actualizo la sucursal");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error SQL: ", "Error al actualizar la sucursal");
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
	/**
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
}
