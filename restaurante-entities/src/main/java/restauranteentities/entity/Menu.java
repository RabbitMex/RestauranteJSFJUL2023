/**
 * 
 */
package restauranteentities.entity;

/**
 * @author Green
 * Clase que representa una entidad menu de la base de datos
 */
public class Menu extends CommonEntity{
	/**
	 * Identificador del menu
	 */
	private Integer idMenu;
	/**
	 * Clave del menu
	 */
	private String clave;
	/**
	 * Descripcion del menu
	 */
	private String descripcion;
	/**
	 * @return the idMenu
	 */
	public Integer getIdMenu() {
		return idMenu;
	}
	/**
	 * @param idMenu the idMenu to set
	 */
	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
