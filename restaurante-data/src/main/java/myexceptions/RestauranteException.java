/**
 * 
 */
package myexceptions;

import enums.CodigoEnum;

/**
 * @author Green
 * Excepcion personalizada para los errores ocasionados en restaurante.
 *
 */
public class RestauranteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Codigo de error generado por la excepcion
	 */
	private int errorCode;
	
	/**
	 * Constructor por default
	 */
	public RestauranteException() {
		
	}
	/**
	 * Constructor sobrecargado, que muestra el mensaje y el codigo de error
	 * @param mensaje mensaje a mostrar al usuario
	 * @param codigoEnum codigo de error de la enumeracion
	 */
	public RestauranteException(String mensaje, CodigoEnum codigoEnum) {
		super(mensaje);
		this.errorCode = codigoEnum.getCode();
	}
	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
