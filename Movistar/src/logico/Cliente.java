package logico;

import java.util.ArrayList;

public class Cliente {
	private int id;
	private String nombre;
	private String apeliidos;
	private String telefono;
	private String direccion;
	private double deuda;
	private ArrayList<Plan> planesContratados;

	public Cliente(int id, String nombre, String apeliidos, String telefono, String direccion, double deuda) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apeliidos = apeliidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.deuda = deuda;
		this.planesContratados = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApeliidos() {
		return apeliidos;
	}

	public void setApeliidos(String apeliidos) {
		this.apeliidos = apeliidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getDeuda() {
		return deuda;
	}

	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}

	public ArrayList<Plan> getPlanesContratados() {
		return planesContratados;
	}

	public void contratarPlan(Plan nuevoPlan) {
		this.planesContratados.add(nuevoPlan);
	}

	public int getId() {
		return id;
	}

	public boolean agregarPlan(Plan p) {
	    if (p == null || tienePlan(p.getId())) {
	        return false;
	    }
	    planesContratados.add(p);
	    return true;
	}

	public boolean tienePlan(int idPlan) {
	    for (Plan p : planesContratados) {
	        if (p.getId() == idPlan) {
	            return true;
	        }
	    }
	    return false;
	}

	public boolean quitarPlan(int idPlan) {
		for(int ind = 0; ind < planesContratados.size(); ind++) {
			if(planesContratados.get(ind).getId() == idPlan) {
				planesContratados.remove(ind);
				return true;
			}
		}
		return false;
	}

	public void aumentarDeuda(double montoTotal) {
		if(montoTotal > 0) {
			deuda += montoTotal;
		}
	}
	
	public void reducirDeuda(double monto) {
	    if (monto > 0) {
	        deuda -= monto;
	        if (deuda < 0) {
	            deuda = 0;
	        }
	    }
	}
}
