/**
 * 
 */
package restauranteentities.entity;

/**
 * @author Green
 * Clase que representa una entidad de la tabla alimento.
 */
public class Alimento extends CommonEntity {
	/**
	 * Identificador del alimento
	 */
	private Integer idAlimento;
	/**
	 * Nombre del alimento
	 */
	private String nombre;
	/**
	 * Imagen del alimento
	 */
	private String imagen;
	/**
	 * Descripcion del alimento
	 */
	private String descripcion;
	/**
	 * Tipo de alimento al que pertenece
	 */
	private TipoAlimento tipoAlimento;
	/**
	 * Descuento del alimento
	 */
	private double descuento;
	/**
	 * Precio unitario
	 */
	private double precio;
	/**
	 * Restaurante donde se encuentra el alimento
	 */
	private Restaurante restaurante;
	/**
	 * Menu al que se asigna el alimento
	 */
	private Menu menu;
	/**
	 * @return the idAlimento
	 */
	public Integer getIdAlimento() {
		return idAlimento;
	}
	/**
	 * @param idAlimento the idAlimento to set
	 */
	public void setIdAlimento(Integer idAlimento) {
		this.idAlimento = idAlimento;
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
	 * @return the descuento
	 */
	public double getDescuento() {
		return descuento;
	}
	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
	 * @return the restaurante
	 */
	public Restaurante getRestaurante() {
		return restaurante;
	}
	/**
	 * @param restaurante the restaurante to set
	 */
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
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
