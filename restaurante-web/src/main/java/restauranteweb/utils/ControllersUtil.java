/**
 * 
 */
package restauranteweb.utils;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @author Green
 *
 */
public class ControllersUtil {
	
	/**
	 * Metodo para poder redireccionar de pagina
	 * 
	 * @param pagina Direccion a direccionar
	 * @throws IOException exception por si hay algun problema.
	 */
	public static void redireccionar(String pagina) throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String contextPath = ec.getRequestContextPath();
		//ec.redirect(contextPath + pagina);
		ec.redirect(contextPath.concat(pagina));
	}
	
	/**
	 * Metodo que permite mostrar un mensaje al usuario
	 * 
	 * @param severity tipo de mensaje
	 * @param titulo Titulo de la ventana
	 * @param mensaje descripcion a mostrar al usuario
	 */
	public static void mostrarMensaje(Severity severity, String titulo, String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, titulo, mensaje));
	}
}
