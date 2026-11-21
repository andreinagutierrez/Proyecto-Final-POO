package logico;

import java.util.Date;

public class Administrativo extends Personal {
	
	private static final long serialVersionUID = 1L;
	private String departamento;
	private String cargo;
	private String extensionTelefonica;

	public Administrativo(int id, String nombre, String apellidos, String cedula, Date fechaNacimiento, String correo,
			String telefono, double salarioBase, boolean estado, Sucursal sucursal, String departamento, String cargo,
			String extensionTelefonica) {
		super(id, nombre, apellidos, cedula, fechaNacimiento, correo, telefono, salarioBase, estado, sucursal);
		this.departamento = departamento;
		this.cargo = cargo;
		this.extensionTelefonica = extensionTelefonica;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getExtensionTelefonica() {
		return extensionTelefonica;
	}

	public void setExtensionTelefonica(String extensionTelefonica) {
		this.extensionTelefonica = extensionTelefonica;
	}

	@Override
	public double calcularSalario() {
		// TODO Auto-generated method stub
		double salario = this.salarioBase;
		
		if(this.cargo.equalsIgnoreCase("Gerente")) {
			salario += 20000;
		} else if (this.cargo.equalsIgnoreCase("Supervisor")) {
			salario += 8000;
		}
		return salario;
	}

	
}
