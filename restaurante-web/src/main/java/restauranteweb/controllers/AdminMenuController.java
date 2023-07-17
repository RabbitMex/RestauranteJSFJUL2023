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

import org.primefaces.event.DragDropEvent;

import restauranteentities.entity.Alimento;
import restauranteentities.entity.Menu;
import restauranteservice.services.AdminSucursalService;
import restauranteweb.session.SessionBean;
import restauranteweb.utils.ControllersUtil;

/**
 * @author Daniel Bastian Luna
 * 
 * Clase controller que permite la comunicacion con la pantalla de adminmenu.xhtml
 *
 */
@ManagedBean
@ViewScoped
public class AdminMenuController {
	
	/**
	 * Lista de alimentos disponibles a realizar con draggable
	 */
	private List<Alimento> alimentosDisponibles;
	
	/**
	 * Lista de alimentos asignados al menu
	 */
	private List<Alimento> alimentosAsignados;
	
	/**
	 * Objeto que permite realizar la logica de negocio para las pantallas del
	 * administrador de sucursal o restaurante.
	 */
	private AdminSucursalService adminSucursalService = new AdminSucursalService();
	
	/**
	 * Menu del restaurante del usuario
	 */
	private Menu menu;
	
	/**
	 * Objeto que contiene la inforamcion de usuario en sesion.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	/**
	 * Metodo que inicializa la ventana de administracion de menu.
	 */
	@PostConstruct
	public void inti() {
		this.menu = new Menu();
		this.consultar();
	}
	
	/**
	 * Metodo que permite consultar la informacion de los alimentos disponibles
	 * y asignados.
	 */
	public void consultar() {
		int idRestauranteUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
		//Integer idMenuUsuarioSesion = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getMenu().getIdMenu();
		
		//this.menu = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getMenu();
		//this.alimentosAsignados = adminSucursalService.consultarAlimentosAsignadosMenu(idMenuUsuarioSesion);
		//this.alimentosAsignados = adminSucursalService.consultarAlimentosAsignadosMenu(this.menu.getIdMenu());
		
		//ALIMENTOS DISPONIBLES
		try {
			
			this.alimentosDisponibles = adminSucursalService.consultarAlimentosDisponiblesRestaurante(idRestauranteUsuarioSesion);
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Error al procesar la inforamcion de alimentos disponibles");
			e.printStackTrace();
		}
		
		//ALIMENTOS ASIGNADOS
		try {
			this.menu = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getMenu();
			//this.alimentosAsignados = adminSucursalService.consultarAlimentosAsignadosMenu(idMenuUsuarioSesion);
			this.alimentosAsignados = adminSucursalService.consultarAlimentosAsignadosMenu(this.menu.getIdMenu());
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Error al procesar la inforamcion de alimentos asignados al menu");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite asignar el alimento al droppable
	 * @param alimentoEvent evento con el alimento a asignar.
	 */
//	public void asignarAlimentosMenu(DragDropEvent<Alimento> alimentoEvent) {
//		Alimento alimentoSeleccionado = alimentoEvent.getData();
//		this.alimentosDisponibles.remove(alimentoSeleccionado);
//		this.alimentosAsignados.add(alimentoSeleccionado);
//	}
	
	/**
	 * Metodo que permite asignar el alimento al droppable
	 * @param alimentoEvent evento con el alimento a asignar.
	 */
	public void asignarAlimentosMenu(DragDropEvent<Alimento> alimentoEvent) {
		Alimento alimentoSeleccionado = alimentoEvent.getData();
		this.alimentosDisponibles.remove(alimentoSeleccionado);
		this.alimentosAsignados.add(alimentoSeleccionado);
		
		//GUARDAR LOS ALIMENTOS ASIGNADO AL MENU.
		try {
			int asignado = this.adminSucursalService.asignarAlimentoMenu(alimentoSeleccionado.getIdAlimento(), this.menu.getIdMenu());
			if(asignado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ok: ", "Alimento " + alimentoSeleccionado.getNombre() + " agregado correctamente al menu");
				this.consultar();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ", "al intentar agregar un alimento al menu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", e.getMessage());
		}
		
	}
	
	/**
	 * Metodo que permite quitar un alimento del menu.
	 * @param alimento objeto tipo Alimentos que se pasa como parametro para poder obtneer su id
	 */
	public void quitarAlimentoMenu(Alimento alimento) {
		try {
			int removido = this.adminSucursalService.quitarAlimentoMenu(alimento.getIdAlimento());
			if(removido>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ok: ", "Alimento " + alimento.getNombre() + " eliminado correctamente al menu");
				this.consultar();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ", "al intentar eliminar un alimento del menu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", e.getMessage());
		}
	}

	/**
	 * @return the alimentosDisponibles
	 */
	public List<Alimento> getAlimentosDisponibles() {
		return alimentosDisponibles;
	}

	/**
	 * @param alimentosDisponibles the alimentosDisponibles to set
	 */
	public void setAlimentosDisponibles(List<Alimento> alimentosDisponibles) {
		this.alimentosDisponibles = alimentosDisponibles;
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
	 * @return the alimentosAsignados
	 */
	public List<Alimento> getAlimentosAsignados() {
		return alimentosAsignados;
	}

	/**
	 * @param alimentosAsignados the alimentosAsignados to set
	 */
	public void setAlimentosAsignados(List<Alimento> alimentosAsignados) {
		this.alimentosAsignados = alimentosAsignados;
	}
}
