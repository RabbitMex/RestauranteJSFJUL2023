/**
 * 
 */
package restauranteweb.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import restauranteentities.entity.TipoRestaurante;
import restauranteservice.services.AdminGeneralService;
import restauranteweb.utils.ControllersUtil;

/**
 * @author Green
 * Permite controlar el flujo de la pantalla administracion de tipos de restaurantes
 */
@ManagedBean
@ViewScoped
public class AdminTiposRestaurantesController {
	
	/**
	 * Lista de tipos de restaurantes que muestra la informacion en el datable
	 */
	private List<TipoRestaurante> tiposRestaurantes;
	
	/**
	 * Objeto que permite utilizar los servicios de logica de negocio de tipos restaurantes
	 */
	private AdminGeneralService  adminGeneralService = new AdminGeneralService();
	
	private TipoRestaurante tipoRestaurante;

	@PostConstruct
	public void init() {
		this.inicializarComponentes();
		this.consultar();
	}
	
	/**
	 * Permite inicializar la informacion de los componentes.
	 */
	public void inicializarComponentes() {
		this.tipoRestaurante = new TipoRestaurante();
	}
	
	/**
	 * Permite consultar la tabla de tipos de restaurante
	 */
	public void consultar() {
		try {
			this.tiposRestaurantes = this.adminGeneralService.consultarTiposRestaurantes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error -", "datos en tipos de restaurante, reintente mas tarde");
			e.printStackTrace();
		}
	}
	
	public void guardar() {
		try {
			int guardado = adminGeneralService.guardarTipoRestaurante(this.tipoRestaurante);
			if(guardado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo restaurante: " + this.tipoRestaurante.getDescripcion() + " Se guardo correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Tipo restaurante: " + this.tipoRestaurante.getDescripcion() + " NO se guardo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Problema al solicitar la informacion de los tipos restaurantes, favor de intentarlo mas tarde");
			e.printStackTrace();
		}
	}
	
	public void actualizar() {
		try {
			int actualizado = adminGeneralService.actualizarTipoRestaurante(this.tipoRestaurante);
			if(actualizado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo restaurante: " 
						+ this.tipoRestaurante.getDescripcion() + " Se actualizo correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
						"Error: ", "Tipo restaurante: " + this.tipoRestaurante.getDescripcion() + " NO se actualizo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
					"Error: ", "Problema al solicitar la actualizacion, favor de intentarlo mas tarde");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para poder eliminar un elemento tipo restaurante.
	 */
	public void eliminar() {
		try {
			int eliminado = adminGeneralService.eliminarTipoRestaurante(this.tipoRestaurante.getIdTipoRestaurante());
			if(eliminado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo restaurante: " 
						+ this.tipoRestaurante.getDescripcion() + " Se Elimino correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
						"Error: ", "Tipo restaurante: " + this.tipoRestaurante.getDescripcion() + " NO se elimino");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
					"Error: ", "Problema al solicitar eliminacion, favor de intentarlo mas tarde");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para poder cargar la informacion desde la pagina, para el objeto tipo restaurante
	 * este objeto se obtiene de la lista y el usuario selecciona de la lista un objeto para que
	 * se pueda editar.
	 * @param tipoRestaurante objeto seleccionado de la lista para poder editar
	 */
	public void cargarInformacionModal(TipoRestaurante tipoRestaurante) {
		//System.out.println("AdminTiposRestaurantesController.carcargarInformacionModal");
		//System.out.println(tipoRestaurante);
		this.tipoRestaurante = tipoRestaurante;
	}
	
	
	
	/**
	 * @return the tiposRestaurantes
	 */
	public List<TipoRestaurante> getTiposRestaurantes() {
		return tiposRestaurantes;
	}

	/**
	 * @param tiposRestaurantes the tiposRestaurantes to set
	 */
	public void setTiposRestaurantes(List<TipoRestaurante> tiposRestaurantes) {
		this.tiposRestaurantes = tiposRestaurantes;
	}
	
	/**
	 * @return the tipoRestaurante
	 */
	public TipoRestaurante getTipoRestaurante() {
		return tipoRestaurante;
	}

	/**
	 * @param tipoRestaurante the tipoRestaurante to set
	 */
	public void setTipoRestaurante(TipoRestaurante tipoRestaurante) {
		this.tipoRestaurante = tipoRestaurante;
	}
}
