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
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import restauranteentities.entity.Restaurante;
import restauranteentities.entity.TipoRestaurante;
import restauranteentities.entity.Menu;
import restauranteservice.services.AdminGeneralService;
import restauranteweb.utils.ControllersUtil;

/**
 * @author Green
 * Permite controlar el flujo de la pantalla administracion de restaurantes
 */
@ManagedBean
@ViewScoped
public class AdminRestaurantesController {
	
	/**
	 * Lista de restaurantes que muestra la informacion en el datable
	 */
	private List<Restaurante> restaurantes;
	
	/**
	 * Objeto que permite utilizar los servicios de logica de negocio de tipos restaurantes
	 */
	private AdminGeneralService  adminGeneralService = new AdminGeneralService();
	
	private Restaurante restaurante;
	
	private Part archivoSeleccionado;
	
	private static final String PATH_DESTINO_ARCHIVO = "C:\\Users\\Green\\Desktop\\Seccion11\\restaurante-web\\src\\main\\webapp\\resources\\images\\restaurantes";

	@PostConstruct
	public void init() {
		this.inicializarComponentes();
		this.consultar();
	}
	
	/**
	 * Permite inicializar la informacion de los componentes.
	 */
	public void inicializarComponentes() {
		this.restaurante = new Restaurante();
		this.restaurante.setTipoRestaurante(new TipoRestaurante());
		this.restaurante.setMenu(new Menu());
	}
	
	/**
	 * Permite consultar la tabla de restaurante
	 */
	public void consultar() {
		try {
			this.restaurantes = this.adminGeneralService.consultarRestaurantes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error -", "datos de restaurante, reintente mas tarde");
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
				this.restaurante.setImagen(nombreArchivo);
			} catch (IOException e) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Al guardar el archivo o imagen.");
				e.printStackTrace();
			}
			
		}
	}
	
	public void guardar() {
		try {
			this.guardarImagenDirectorio();//guardar primero la imagen, despues guardar el bojeto en la base de datos.
			int guardado = this.adminGeneralService.guardarRestaurante(this.restaurante);
			if(guardado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Restaurante: " + this.restaurante.getNombre() + " Se guardo correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ", "Restaurante: " + this.restaurante.getNombre() + " NO se guardo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Problema al solicitar la informacion de los restaurantes, favor de intentarlo mas tarde");
			e.printStackTrace();
		}
	}
	
	
	public void actualizar() {
		try {
			this.guardarImagenDirectorio();//guardar primero la imagen, despues guardar el bojeto en la base de datos.
			int actualizado = adminGeneralService.actualizarRestaurante(this.restaurante);
			if(actualizado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Restaurante: " 
						+ this.restaurante.getNombre() + " Se actualizo correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
						"Error: ", "Restaurante: " + this.restaurante.getNombre() + " NO se actualizo");
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
	/*public void eliminar() {
		try {
			int eliminado = adminGeneralService.eliminarTipoRestaurante(this.restaurante.getIdRestaurante());
			if(eliminado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Tipo restaurante: " 
						+ this.restaurante.getDescripcion() + " Se Elimino correctamente" );
				this.consultar();
				this.inicializarComponentes();
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
						"Error: ", "Tipo restaurante: " + this.restaurante.getDescripcion() + " NO se elimino");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, 
					"Error: ", "Problema al solicitar eliminacion, favor de intentarlo mas tarde");
			e.printStackTrace();
		}
	}*/
	
	/**
	 * Metodo para poder cargar la informacion desde la pagina, para el objeto tipo restaurante
	 * este objeto se obtiene de la lista y el usuario selecciona de la lista un objeto para que
	 * se pueda editar.
	 * @param Restaurante objeto seleccionado de la lista para poder editar
	 */
	public void cargarInformacionModal(Restaurante restaurante) {
		//System.out.println("AdminTiposRestaurantesController.carcargarInformacionModal");
		//System.out.println(tipoRestaurante);
		this.restaurante = restaurante;
	}
	
	/**
	 * @return the restaurantes
	 */
	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	/**
	 * @param tiposRestaurantes the tiposRestaurantes to set
	 */
	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
	
	/**
	 * @return the restaurante
	 */
	public Restaurante getRestaurante() {
		return restaurante;
	}

	/**
	 * @param Restaurante the restaurante to set
	 */
	public void setTipoRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	/**
	 * @return the archivoSeleccionado
	 */
	public Part getArchivoSeleccionado() {
		return archivoSeleccionado;
	}

	/**
	 * @param archivoSeleccionado the archivoSeleccionado to set
	 */
	public void setArchivoSeleccionado(Part archivoSeleccionado) {
		this.archivoSeleccionado = archivoSeleccionado;
	}
}
