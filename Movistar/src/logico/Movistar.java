package logico;

import java.util.ArrayList;
import java.util.Date;

public class Movistar {
	private ArrayList<Personal> losEmpleados;
	private ArrayList<Cliente> losClientes;
	private ArrayList<Plan> losPlanes;
	private ArrayList<Factura> lasFacturas;
	private ArrayList<Servicio> losServicios;
	
	public Movistar() {
		super();
		this.losEmpleados = new ArrayList<>();
		this.losClientes = new ArrayList<>();
		this.losPlanes = new ArrayList<>();
		this.lasFacturas = new ArrayList<>();
		this.losServicios = new ArrayList<>();
	}
	
	public ArrayList<Personal> getLosEmpleados() {
		return losEmpleados;
	}
	public ArrayList<Cliente> getLosClientes() {
		return losClientes;
	}
	public ArrayList<Plan> listarPlanes() {
		return losPlanes;
	}
	public ArrayList<Factura> getLasFacturas() {
		return lasFacturas;
	}
	public ArrayList<Servicio> listarServicios() {
		return losServicios;
	}
	
	public boolean registrarCliente(Cliente c) {
		if(c == null) {
			return false;
		}
		losClientes.add(c);
		return true;
	}
	
	public boolean modificarCliente(Cliente c) {
	    if (c == null) {
	        return false;
	    }

	    Cliente aux = buscarCliente(c.getId());
	    if (aux != null) {
	        aux.setNombre(c.getNombre());
	        aux.setApeliidos(c.getApeliidos());
	        aux.setTelefono(c.getTelefono());
	        aux.setDireccion(c.getDireccion());
	        return true;
	    }
	    return false;
	}
	
	public boolean eliminarCliente(int id) {
		Cliente elim = buscarCliente(id);
		
		if(elim != null) {
			losClientes.remove(elim);
			return true;
		}
		
		return false;
	}

	public Cliente buscarCliente(int id) {
		for(Cliente aux: losClientes) {
			if(aux.getId() == id) {
				return aux;
			}
		}
		return null;
	}
	
	public boolean solicitarPlan(int idCliente, int idPlan) {
		Cliente client = buscarCliente(idCliente);
		Plan plan = buscarPlan(idPlan);
		
		if(client == null || plan == null) {
			return false;
		}
		
		return client.agregarPlan(plan);
	}
	
	public boolean modificarPlanCliente(int idCliente, int idPlanActual, int idPlanNuevo) {
		Cliente cliente = buscarCliente(idCliente);
		Plan planNuevo = buscarPlan(idPlanNuevo);
		
		if(cliente == null || planNuevo == null) {
			return false;
		}
		
		if(!cliente.tienePlan(idPlanActual) || cliente.tienePlan(idPlanNuevo)) {
			return false;
		}
		
		cliente.quitarPlan(idPlanActual);
		cliente.agregarPlan(planNuevo);
		return true;
	}
	
	public boolean eliminarPlanCliente(int idCliente, int idPlan) {
		Cliente cliente = buscarCliente(idCliente);

	    if (cliente == null) {
	        return false;
	    }

	    return cliente.quitarPlan(idPlan);
	}
	
	public Personal buscarEmpleado(int id) {
		for(Personal aux: losEmpleados) {
			if(aux.getId() == id) {
				return aux;
			}
		}
		return null;
	}
	
	public boolean registrarPlan(Plan p) {
		if(p == null) {
			return false;
		}
		losPlanes.add(p);
		return true;
	}
	
	public boolean modificarPlan(Plan p) {
	    if (p == null) {
	        return false;
	    }

	    Plan aux = buscarPlan(p.getId());
	    if (aux != null) {
	        aux.setNombre(p.getNombre());
	        return true;
	    }
	    return false;
	}
	
	public boolean eliminarPlan(int id) {
		Plan elim = buscarPlan(id);
		
		if(elim != null) {
			losPlanes.remove(elim);
			return true;
		}
		
		return false;
	}

	public Plan buscarPlan(int id) {
		for(Plan aux: losPlanes) {
			if(aux.getId() == id) {
				return aux;
			}
		}
		return null;
	}
	
	public boolean registrarServicio(Servicio s) {
		if(s == null) {
			return false;
		}
		losServicios.add(s);
		return true;
	}
	
	public boolean modificarServicio(Servicio s) {
	    if (s == null) {
	        return false;
	    }

	    Servicio aux = buscarServicio(s.getId());
	    if (aux != null) {
	        aux.setNombre(s.getNombre());
	        aux.setPrecioIndividual(s.getPrecioIndividual());
	        return true;
	    }
	    return false;
	}
	
	public boolean eliminarServicio(int id) {
		Servicio elim = buscarServicio(id);
		
		if(elim != null) {
			losServicios.remove(elim);
			return true;
		}
		
		return false;
	}

	public Servicio buscarServicio(int id) {
		for(Servicio aux: losServicios) {
			if(aux.getId() == id) {
				return aux;
			}
		}
		return null;
	}
	
	public Factura buscarFactura(int id) {
		for(Factura aux: lasFacturas) {
			if(aux.getId() == id) {
				return aux;
			}
		}
		return null;
	}
	
	public Factura generarFactura(int idCliente, int idVendedor) {
	    Cliente cliente = buscarCliente(idCliente);
	    Personal p = buscarEmpleado(idVendedor);

	    if (cliente == null || !(p instanceof Comercial)) {
	        return null;
	    }
	    if (cliente.getPlanesContratados().isEmpty()) {
	        return null;
	    }

	    Comercial vendedor = (Comercial) p;

	    Factura f = new Factura(lasFacturas.size() + 1, new Date(), cliente, vendedor);
	    lasFacturas.add(f);
	    cliente.aumentarDeuda(f.getMontoTotal());
	    vendedor.registrarVenta(f.getMontoTotal());
	    return f;
	}
	
	public boolean pagarFactura(int idFactura) {
		Factura busqueda = buscarFactura(idFactura);
		
		if(busqueda == null || busqueda.isPagada()) {
			return false;
		}
		
		busqueda.setPagada(true);
		busqueda.getCliente().reducirDeuda(busqueda.getMontoTotal());
		return true;
		
	}
	
	public ArrayList<Plan> consultarPlanesCliente(int idCliente) {
	    Cliente cliente = buscarCliente(idCliente);

	    if (cliente == null) {
	        return new ArrayList<>();
	    }
	    return cliente.getPlanesContratados();
	}

	public double consultarDeuda(int idCliente) {
	    Cliente cliente = buscarCliente(idCliente);

	    if (cliente == null) {
	        return -1;
	    }
	    return cliente.getDeuda();
	}
	
	public ArrayList<Plan> consultarPlanesServicio(int idServicio) {
		ArrayList<Plan> resultadosPlan = new ArrayList<>();
		for(Plan p: losPlanes) {
			if(p.incluyeServicio(idServicio)) {
				resultadosPlan.add(p);
			}
		}
		return resultadosPlan;
	}
	
	public ArrayList<Factura> reportePorPeriodo(Date inicio, Date fin) {
	    ArrayList<Factura> resultados = new ArrayList<>();

	    if (inicio == null || fin == null || inicio.after(fin)) {
	        return resultados;
	    }

	    for (Factura f: lasFacturas) {
	        Date fecha = f.getFechaEmision();
	        if (!fecha.before(inicio) && !fecha.after(fin)) {
	            resultados.add(f);
	        }
	    }
	    return resultados;
	}
	
	public ArrayList<Factura> reportePorLocal(Sucursal sucursal) {
		ArrayList<Factura> resultadosLocal = new ArrayList<>();
		
		if(sucursal == null) {
			return resultadosLocal;
		}
		
		for(Factura f: lasFacturas) {
			if(f.getSucursal().equals(sucursal)) {
				resultadosLocal.add(f);
			}
		}
		return resultadosLocal;
	}
}
