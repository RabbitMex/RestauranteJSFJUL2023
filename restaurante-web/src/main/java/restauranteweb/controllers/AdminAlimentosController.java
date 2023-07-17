/**
 * 
 */
package restauranteweb.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import restauranteentities.entity.Alimento;
import restauranteentities.entity.TipoAlimento;
import restauranteentities.entity.Restaurante;
//import restauranteentities.entity.TipoRestaurante;
import restauranteentities.entity.Menu;
import restauranteservice.services.AdminSucursalService;
import restauranteweb.session.SessionBean;
import restauranteweb.utils.ControllersUtil;

/**
 * @author Green
 * Permite controlar el flujo de la pantalla administracion de alimentos
 */
@ManagedBean
@ViewScoped
public class AdminAlimentosController {
	
	/**
	 * Lista de alimentos que muestra la informacion en el datable
	 */
	private List<Alimento> alimentos;
	
	/**
	 * Lista de tipos de alimentos
	 */
	private List<TipoAlimento> tiposAlimentos;
	
	/**
	 * Objeto que permite utilizar los servicios de logica de negocio de alimentos
	 */
	private AdminSucursalService  adminSucursalService = new AdminSucursalService();
	
	private Alimento alimento;
	
	private Part archivoSeleccionado;
	
	/**
	 * Objeto con la informacion del usuario.
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	
	private static final String PATH_DESTINO_ARCHIVO = "C:\\Users\\Green\\Desktop\\Seccion11\\restaurante-web\\src\\main\\webapp\\resources\\images\\alimentos";
	//private static final String PATH_DESTINO_ARCHIVO = "C:\\Users\\Green\\Desktop\\Seccion11\\restaurante-web\\src\\main\\webapp\\resources\\images\\restaurantes";

	@PostConstruct
	public void init() {
		this.inicializarComponentes();
		this.consultar();
		this.consultarTiposAlimentosPorRestaurante();
	}
	
	/**
	 * Permite inicializar la informacion de los componentes.
	 */
	public void inicializarComponentes() {
		this.alimento = new Alimento();
		this.alimento.setTipoAlimento(new TipoAlimento());
		this.alimento.setMenu(new Menu());
		this.alimento.setRestaurante(new Restaurante());
	}
	
	/**
	 * Permite consultar la tabla de restaurante
	 */
	public void consultar() {
		try {
			this.alimentos = this.adminSucursalService.consultarAlimentosPorRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error -", "al obtener los datos de los alimentos, reintente mas tarde");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite guardar la imagen en un directorio
	 */
	public void guardarImagenDirectorio() {
		if(this.archivoSeleccionado!=null) {
			try {
				InputStream inputStream = this.archivoSeleccionado.getInputStream();
				String nombreArchivo = this.archivoSeleccionado.getSubmittedFileName();
				Files.copy(inputStream, new File(PATH_DESTINO_ARCHIVO, nombreArchivo).toPath());
				this.alimento.setImagen(nombreArchivo);
			} catch (IOException e) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Al guardar el archivo o imagen.");
				e.printStackTrace();
			}
			
		}
	}
	
	public void guardar() {
		try {
			this.alimento.setRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante());
			this.guardarImagenDirectorio();//guardar primero la imagen, despues guardar el bojeto en la base de datos.
			
			int guardado = this.adminSucursalService.guardarAlimento(this.alimento);
			
			if(guardado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Alimento: " + this.alimento.getNombre() + " Se guardo correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ", "Alimento: " + this.alimento.getNombre() + " NO se guardo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Problema al solicitar la informacion del alimento, favor de intentarlo mas tarde");
			e.printStackTrace();
		}
	}
	
	/**
	 * Permite consultar la lista de tipos de alimentos del restaurante del 
	 * usuario en sesion.
	 */
	public void consultarTiposAlimentosPorRestaurante() {
		try {
			this.tiposAlimentos = this.adminSucursalService.consultarTiposAlimentosPorRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Problema lista tiposAlimentos por restaurante");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para poder actualizar el alimento desde la ventana modal.
	 */
	public void actualizar() {
		try {
			this.alimento.setRestaurante(this.sessionBean.getEmpleado().getSucursal().getRestaurante());
			this.guardarImagenDirectorio();//guardar primero la imagen, despues guardar el bojeto en la base de datos.
			int actualizado = this.adminSucursalService.actualizarAlimento(this.alimento);
			if(actualizado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Alimento: " 
						+ this.alimento.getNombre() + " Se actualizo correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
						"Error: ", "Restaurante: " + this.alimento.getNombre() + " NO se actualizo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
					"Error: ", "Problema al solicitar la actualizacion del alimento");
			e.printStackTrace();
		}
	}

//	/**
//	 * Metodo para poder eliminar un elemento tipo restaurante.
//	 */
//	/*public void eliminar() {
//		try {
//			int eliminado = adminGeneralService.eliminarTipoRestaurante(this.restaurante.getIdRestaurante());
//			if(eliminado>0) {
//				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo restaurante: " 
//						+ this.restaurante.getDescripcion() + " Se Elimino correctamente" );
//				this.consultar();
//				this.inicializarComponentes();
//			}
//			else {
//				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
//						"Error: ", "Tipo restaurante: " + this.restaurante.getDescripcion() + " NO se elimino");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
//					"Error: ", "Problema al solicitar eliminacion, favor de intentarlo mas tarde");
//			e.printStackTrace();
//		}
//	}*/
//	
	
	/**
	 * Metodo para poder cargar la informacion desde la pagina, para el objeto Alimento
	 * este objeto se obtiene de la lista y el usuario selecciona de la lista un objeto para que
	 * se pueda editar.
	 * @param Alimento objeto seleccionado de la lista para poder editar
	 */
	public void cargarInformacionModal(Alimento alimento) {
		this.alimento = alimento;
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
	 * @return the archivoSeleccionado
	 */
	public Part getArchivoSeleccionado() {
		return archivoSeleccionado;
	}

	/**
	 * @return the alimentos
	 */
	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	/**
	 * @param alimentos the alimentos to set
	 */
	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	/**
	 * @return the alimento
	 */
	public Alimento getAlimento() {
		return alimento;
	}

	/**
	 * @param alimento the alimento to set
	 */
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
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
	 * @param archivoSeleccionado the archivoSeleccionado to set
	 */
	public void setArchivoSeleccionado(Part archivoSeleccionado) {
		this.archivoSeleccionado = archivoSeleccionado;
	}
}
