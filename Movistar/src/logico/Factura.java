package logico;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	private int id;
	private Date fechaEmision;
	private Cliente cliente;
	private Comercial vendedor;
	private Sucursal sucursal;
	private ArrayList<Plan> planesFacturados;
	private double montoTotal;
	private boolean pagada;
	
	public Factura(int id, Date fechaEmision, Cliente cliente, Comercial vendedor) {
		super();
		this.id = id;
		this.fechaEmision = fechaEmision;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.sucursal = vendedor.getSucursal();
		this.planesFacturados = new ArrayList<>();
		this.montoTotal = calcularTotal();
		this.pagada = false;
	}
	
	public double calcularTotal() {
		return 0.0; //TODO
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Comercial getVendedor() {
		return vendedor;
	}
	public void setVendedor(Comercial vendedor) {
		this.vendedor = vendedor;
	}
	public ArrayList<Plan> getPlanesFacturados() {
		return planesFacturados;
	}
	public void agregarPlan(Plan p) {
		planesFacturados.add(p);
		this.montoTotal = calcularTotal();
	}
	public double getMontoTotal() {
		return montoTotal;
	}
	public boolean isPagada() {
		return pagada;
	}
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}
	
}
