/**
 * 
 */
package restauranteentities.entity;

/**
 * @author Green
 * Clase que representa una entidad de restaurante en la base de datos
 *
 */
public class Restaurante extends CommonEntity{
	/**
	 * Identificador del restaurante
	 */
	private Integer idRestaurante;
	/**
	 * Nombre del restaurante
	 */
	private String nombre;
	/**
	 * Imagen del restaurante
	 */
	private String imagen;
	/**
	 * Sologan del restaurante
	 */
	private String slogan;
	/**
	 * Tipo de restaurante
	 */
	private TipoRestaurante tipoRestaurante;
	/**
	 * Menu del restaurante
	 */
	private Menu menu;
	/**
	 * @return the idRestaurante
	 */
	public Integer getIdRestaurante() {
		return idRestaurante;
	}
	/**
	 * @param idRestaurante the idRestaurante to set
	 */
	public void setIdRestaurante(Integer idRestaurante) {
		this.idRestaurante = idRestaurante;
	}
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
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}
	/**
	 * @param imagen the imagen to set
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	/**
	 * @return the slogan
	 */
	public String getSlogan() {
		return slogan;
	}
	/**
	 * @param slogan the slogan to set
	 */
	public void setSlogan(String slogan) {
		this.slogan = slogan;
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
	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
