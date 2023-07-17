package restauranteentities.entity;

import java.time.LocalDateTime;

/**
 * 
 * @author Green
 * Clase que contine campos que se reutilizan en todas las clases que mapean en las tablas
 *
 */
public class CommonEntity {
	/**
	 * Fecha de creacion del tipo de restaurante
	 */
	private LocalDateTime fechaCreacion;
	/**
	 * Fecha de modificacion del tipo de restaurante
	 */
	private LocalDateTime fechaModificacion;
	/**
	 * Estado del tipo de restaurante
	 */
	private boolean estatus;
	/**
	 * @return the fechaCreacion
	 */
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the fechaModificacion
	 */
	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	/**
	 * @return the estatus
	 */
	public boolean isEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
}
