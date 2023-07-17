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

import restauranteweb.session.SessionBean;
import restauranteweb.utils.ControllersUtil;

import restauranteentities.entity.TipoAlimento;
import restauranteservice.services.AdminSucursalService;

/**
 * @author Green
 * Permite controlar el flujo de la pantalla administracion de tipos de alimentos
 */
@ManagedBean
@ViewScoped
public class AdminTiposAlimentosController {
	
	/**
	 * Lista de tipos de alimentos que muestra la informacion en el datable
	 */
	private List<TipoAlimento> tiposAlimentos;
	
	/**
	 * Objeto que permite utilizar los servicios de logica de negocio de tipos Alimentos
	 */
	private AdminSucursalService  adminSucursalService = new AdminSucursalService();
	
	private TipoAlimento tipoAlimento;
	
	/**
	 * Objeto con la informacion del usuario.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;

	@PostConstruct
	public void init() {
		this.inicializarComponentes();
		this.consultar();
	}
	
	/**
	 * Permite inicializar la informacion de los componentes.
	 */
	public void inicializarComponentes() {
		this.tipoAlimento = new TipoAlimento();
	}
	
	/**
	 * Permite consultar la tabla de tipos de Alimentos
	 */
	public void consultar() {
		try {
			//this.tiposAlimentos = this.adminSucursalService.consultarTiposAlimentos();
			this.tiposAlimentos = this.adminSucursalService.consultarTiposAlimentosPorRestaurante(sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error -", "datos en tipos de alimentos, reintente mas tarde");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para guardar un tipo de alimento
	 */
	public void guardar() {
		try {
			//int guardado = adminSucursalService.guardarTipoAlimento(this.tipoAlimento);
			int idRestauranteSesion = sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
			int guardado = adminSucursalService.guardarTipoAlimentoRestaurante(this.tipoAlimento, idRestauranteSesion);
			if(guardado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo Alimento: " + this.tipoAlimento.getDescripcion() + " Se guardo correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Tipo Alimento: " + this.tipoAlimento.getDescripcion() + " NO se guardo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Problema al solicitar la informacion de los tipos de alimentos, favor de intentarlo mas tarde");
			e.printStackTrace();
		}
	}
	
	public void actualizar() {
		try {
			int actualizado = adminSucursalService.actualizarTipoAlimento(this.tipoAlimento);
			if(actualizado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo alimento: " 
						+ this.tipoAlimento.getDescripcion() + " Se actualizo correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
						"Error: ", "Tipo alimento: " + this.tipoAlimento.getDescripcion() + " NO se actualizo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
					"Error: ", "Problema al solicitar la actualizacion, favor de intentarlo mas tarde");
			e.printStackTrace();
		}
	}
//	
//	/**
//	 * Metodo para poder eliminar un elemento tipo alimento.
//	 */
//	public void eliminar() {
//		try {
//			int eliminado = adminSucursalService.eliminarTipoAlimento(this.tipoAlimento.getIdTipoAlimento());
//			if(eliminado>0) {
//				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo alimento: " 
//						+ this.tipoAlimento.getDescripcion() + " Se Elimino correctamente" );
//				this.consultar();
//				this.inicializarComponentes();
//			}
//			else {
//				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
//						"Error: ", "Tipo restaurante: " + this.tipoAlimento.getDescripcion() + " NO se elimino");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
//					"Error: ", "Problema al solicitar eliminacion, favor de intentarlo mas tarde");
//			e.printStackTrace();
//		}
//	}
//	
	/**
	 * Metodo para poder cargar la informacion desde la pagina, para el objeto
	 * tipo Alimento este objeto se obtiene de la lista.
	 * 
	 * @param tipoAlimento objeto seleccionado de la lista para poder editar
	 */
	public void cargarInformacionModal(TipoAlimento tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
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
	 * @return the tipoAlimento
	 */
	public TipoAlimento getTipoAlimento() {
		return tipoAlimento;
	}

	/**
	 * @param tipoAlimento the tipoAlimento to set
	 */
	public void setTipoAlimento(TipoAlimento tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
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
