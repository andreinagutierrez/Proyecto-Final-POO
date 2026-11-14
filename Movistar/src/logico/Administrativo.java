package logico;

import java.util.Date;

public class Administrativo extends Personal {
	private String departamento;
	private String cargo;
	private String extensionTelefonica;

	public Administrativo(int id, String nombre, String apellidos, String cedula, Date fechaNacimiento, String correo,
			String telefono, double salarioBase, boolean estado, String departamento, String cargo,
			String extensionTelefonica) {
		super(id, nombre, apellidos, cedula, fechaNacimiento, correo, telefono, salarioBase, estado);
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

}
