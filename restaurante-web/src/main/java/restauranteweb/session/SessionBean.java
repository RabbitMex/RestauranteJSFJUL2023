/**
 * 
 */
package restauranteweb.session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import restauranteentities.entity.Empleado;

/**
 * @author Green
 *
 */
@ManagedBean
@SessionScoped
public class SessionBean {
	private Empleado empleado;

	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
}
