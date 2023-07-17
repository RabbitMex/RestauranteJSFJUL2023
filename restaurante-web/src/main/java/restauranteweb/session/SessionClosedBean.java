/**
 * 
 */
package restauranteweb.session;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import restauranteweb.utils.ControllersUtil;

/**
 * @author Green
 *
 */
@ManagedBean
@ViewScoped
public class SessionClosedBean {
	public void cerrarSesion() {
		//Linea de codigo que cierra la sesion del usuario.
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		try {
			ControllersUtil.redireccionar("/login.xhtml");
		} catch (IOException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error: ", "Al cerrar la sesion.");
			e.printStackTrace();
		}
	}
}
