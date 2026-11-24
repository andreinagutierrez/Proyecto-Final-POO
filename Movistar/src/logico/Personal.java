package logico;

import java.io.Serializable;
import java.util.Date;

public abstract class Personal implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String nombre;
	protected String apellidos;
	protected String cedula;
	protected Date fechaNacimiento;
	protected String correo;
	protected String telefono;
	protected double salarioBase;
	protected boolean estado;
	protected String usuario;
	protected String clave;
	protected Sucursal sucursal;
	protected String tipo;
	
	public Personal(int id, String nombre, String apellidos, String cedula, Date fechaNacimiento, String correo,
			String telefono, double salarioBase, boolean estado, String usuario, String clave, Sucursal sucursal, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.fechaNacimiento = fechaNacimiento;
		this.correo = correo;
		this.telefono = telefono;
		this.salarioBase = salarioBase;
		this.estado = estado;
		this.usuario = usuario;
		this.clave = clave;
		this.sucursal = sucursal;
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public String getCedula() {
		return cedula;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public abstract double calcularSalario();

}
