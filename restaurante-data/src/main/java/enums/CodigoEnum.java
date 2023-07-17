package enums;
/**
 * Enumeracion que contiene los codigos de error del aplicativo
 * 
 * @author Green
 *
 */
public enum CodigoEnum {
	
	/**
	 * Constante con el codigo de error de sintaxis.
	 */
	SINTAXIS_SQL_ERROR_CODE(1064);
	
	/**
	 * Codigo de error
	 */
	private int code;
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Constructor privado 
	 * @param code codigo de error
	 */
	CodigoEnum(int code){
		this.code = code;
	}
}
