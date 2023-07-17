/**
 * 
 */
package restauranteentities.entity;

import java.util.List;

/**
 * @author Green
 * Clase que representa una entidad tipo_restaurante en la base de datos
 *
 */
public class TipoRestaurante extends CommonEntity{
	/**
	 * Identificador del tipo de restaurante
	 */
	private Integer idTipoRestaurante;
	/**
	 * Descripcion del tipo de restaurante
	 */
	private String descripcion;
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Restaurante que pertenecen a un tipo de restaurante
	 */
	private List<Restaurante> restaurantes;
	
	/**
	 * @return the restaurantes
	 */
	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}
	/**
	 * @param restaurantes the restaurantes to set
	 */
	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the idTipoRestaurante
	 */
	public Integer getIdTipoRestaurante() {
		return idTipoRestaurante;
	}
	/**
	 * @param idTipoRestaurante the idTipoRestaurante to set
	 */
	public void setIdTipoRestaurante(Integer idTipoRestaurante) {
		this.idTipoRestaurante = idTipoRestaurante;
	}
	
	
}
