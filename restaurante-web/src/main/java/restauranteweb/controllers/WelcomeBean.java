/**
 * 
 */
package restauranteweb.controllers;

import javax.faces.bean.ManagedBean;

/**
 * @author Daniel
 *
 */
@ManagedBean
public class WelcomeBean {
	public WelcomeBean() {
		System.out.println("Welcome Bean instantiated ...");
	}
	public String getMessage() {
		return "WelcomeBean esta vivo...";
	}
}
