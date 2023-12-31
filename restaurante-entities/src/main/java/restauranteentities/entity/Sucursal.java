/**
 * 
 */
package restauranteentities.entity;

/**
 * @author Daniel Bastian Luna
 * 
 * Clase que representa una entidad de la tabla sucursal
 *
 */
public class Sucursal extends CommonEntity {
	private Integer idSucursal;
	private String nombre;
	private Restaurante restaurante;
	/**
	 * @return the idSucursal
	 */
	public Integer getIdSucursal() {
		return idSucursal;
	}
	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
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
	
}
