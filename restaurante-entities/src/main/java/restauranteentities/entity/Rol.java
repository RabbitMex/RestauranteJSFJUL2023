/**
 * 
 */
package restauranteentities.entity;

/**
 * @author Green
 *
 */
public class Rol extends CommonEntity{
	private int idRol;
	private String nombre;
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the idRol
	 */
	public int getIdRol() {
		return idRol;
	}
	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	
}
