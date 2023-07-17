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
import javax.faces.view.ViewScoped;

import restauranteentities.entity.TipoAlimento;
import restauranteservice.services.EmpleadoService;
import restauranteweb.session.SessionBean;
import restauranteweb.utils.ControllersUtil;

/**
 * @author Green
 * 
 * Clase controller que permite interactuar con la pantalla empleado
 */
@ManagedBean
@ViewScoped
public class MenuEmpleadoController {
	/**
	 * Lista de Tipos de alimentos a mostrar en el menu con sus respectivos
	 * alimentos 
	 */
	private List<TipoAlimento> tiposAlimentos;
	/**
	 * Objeto que realiza la logica de negocio para los empleados
	 */
	private EmpleadoService empleadoService = new EmpleadoService();
	/**
	 * objeto que contiene la informacion de la sesion de usuario
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	/**
	 * Metodo que permite inicializar la pantalla de menu de usuario.
	 */
	@PostConstruct
	public void ini() {
		this.consultar();
	}

	/**
	 * Meotodo que permite consultar los alimentos del menu.
	 */
	public void consultar() {
		int idMenu = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getMenu().getIdMenu();
		try {
			this.tiposAlimentos = this.empleadoService.consultarAlimentosPorMenu(idMenu);
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Problema al mostrar los tipos de alimentos del menu de restaurante");
			e.printStackTrace();
		}
	}
	/**
	 * @return the tiposAlimentos
	 */
	public List<TipoAlimento> getTiposAlimentos() {
		return tiposAlimentos;
	}

	/**
	 * @param tiposAlimentos the tiposAlimentos to set
	 */
	public void setTiposAlimentos(List<TipoAlimento> tiposAlimentos) {
		this.tiposAlimentos = tiposAlimentos;
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
