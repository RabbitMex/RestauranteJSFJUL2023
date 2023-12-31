/**
 * 
 */
package restauranteweb.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import restauranteentities.entity.Empleado;
import restauranteentities.entity.Rol;
import restauranteentities.entity.Sucursal;
import restauranteservice.services.AdminSucursalService;
import restauranteweb.session.SessionBean;
import restauranteweb.utils.ControllersUtil;

/**
 * @author Daniel Bastian Luna
 * Clase controller que interactura con la pantalla de adminempleados.xhtml
 *
 */
@ManagedBean
@ViewScoped
public class AdminEmpleadosController {
	/**
	 * Lista de empleados a mostrar en la administracion
	 */
	private List<Empleado> empleados;
	private Empleado empleado;
	private List<Rol> roles;
	
	/**
	 * Objeto de logica de negocio para el usuario de administrador de sucursal
	 * o restaurante
	 */
	private AdminSucursalService adminSucursalService = new AdminSucursalService();
	/**
	 * Objeto que contiene la informacion de sesion del usuario
	 */
	@ManagedProperty("#{sessionBean}")
	private SessionBean sessionBean;
	/**
	 * Inicializa la informacion de la pantalla de adminempleados.xhtml
	 */
	@PostConstruct
	public void init() {
		this.consultar();
		this.consultarRoles();
		this.inicializarComponentes();
	}

	public void inicializarComponentes() {
		//System.out.println("inicializar el empleado");
		this.empleado = new Empleado();
		this.empleado.setRol(new Rol());
		this.empleado.setSucursal(new Sucursal());
		//System.out.println(this.empleado.getIdEmpleado());
	}
	
	/**
	 * Metodo que permite consultar la lista de roles sin administrador general
	 * de la ventana adminempleado.xhtml.
	 */
	public void consultarRoles() {
		try {
			this.roles = this.adminSucursalService.consultarRolesSinAdminGeneral();
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error SQL: ", "Problema al procesar la informacion para consultar Roles");
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que permite consultar los empleados de la sucursal
	 */
	public void consultar() {
		int idRestaurante = this.sessionBean.getEmpleado().getSucursal().getRestaurante().getIdRestaurante();
		try {
			this.empleados = this.adminSucursalService.consultarEmpleadosPorRestaurante(idRestaurante);
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error SQL: ", "Problema al procesar la informacion para consultar empleados");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite guardar un empleado.
	 */
	public void guardar() {
		Sucursal sucursalSession = this.sessionBean.getEmpleado().getSucursal();
		this.empleado.setSucursal(sucursalSession);
		try {
			int guardar = this.adminSucursalService.guardarEmpleado(this.empleado);
			if(guardar>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ok: ", "Empleado: " + this.empleado.getNombre() + " se guardo correctamente");
				this.consultar();//Para actualizar la tabla
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ",  this.empleado.getNombre() + " No guardado");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error SQL: ", "Problema al Guardar un empleado");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que precarga la informacion del empleado seleccionado a actualizar.
	 * 
	 * @param empleado cargado desde la pagina adminempleados.xhtml
	 */
	public void cargarInformacionModal(Empleado empleado) {
		this.empleado = empleado;
	}
	
	/**
	 * Metodo que permite actualizar la informacion de un empleado
	 */
	public void actualizar() {
		System.out.println("Entrando a actualizar() empleado");
		try {
			int resultado = this.adminSucursalService.actualizarEmpleado(this.empleado);
			if(resultado>0) {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ok: ", "Empleado: " + this.empleado.getNombre() + " actualizado correctamente");
				this.consultar();//Para actualizar la tabla
			}
			else {
				ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: ",  this.empleado.getNombre() + " No ACTUALIZADO");
			}
		} catch (SQLException e) {
			ControllersUtil.mostrarMensaje(FacesMessage.SEVERITY_FATAL, "Error SQL: ", "Problema al ACTUALIZAR un empleado");
			e.printStackTrace();
		}
	}
	
	public void imprimirMenzaje() {
		System.out.println("Mensaje para entrar al metodo");
	}
	
	/**
	 * @return the empleados
	 */
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	/**
	 * @param empleados the empleados to set
	 */
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	/**
	 * @return the sessionBean
	 */
	public SessionBean getSessionBean() {
		return sessionBean;
	}
	/**
	 * @param sessionBean the sessionBean to set
	 */
	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	/**
	 * @return the roles
	 */
	public List<Rol> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
}
