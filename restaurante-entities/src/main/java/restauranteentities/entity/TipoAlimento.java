/**
 * 
 */
package restauranteentities.entity;

import java.util.List;

/**
 * @author Daniel Bastian Luna
 * representa la tabla de tipo_alimento
 */
public class TipoAlimento extends CommonEntity{
	private Integer idTipoAlimento;
	private String descripcion;
	/**
	 * Lista de alimentos que contiene el tipo
	 */
	private List<Alimento> alimentos;
	
	/**
	 * @return the idTipoAlimento
	 */
	public Integer getIdTipoAlimento() {
		return idTipoAlimento;
	}
	/**
	 * @param idTipoAlimento the idTipoAlimento to set
	 */
	public void setIdTipoAlimento(Integer idTipoAlimento) {
		this.idTipoAlimento = idTipoAlimento;
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
	 * @return the alimentos
	 */
	public List<Alimento> getAlimentos() {
		return alimentos;
	}
	/**
	 * @param alimentos the alimentos to set
	 */
	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}
}
