package logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Movistar implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final String RUTA_DAT = "movistar.dat";
	private static Movistar instancia;
	private ArrayList<Personal> losEmpleados;
	private ArrayList<Cliente> losClientes;
	private ArrayList<Plan> losPlanes;
	private ArrayList<Factura> lasFacturas;
	private ArrayList<Servicio> losServicios;
	private static Personal usuarioActual;
	
	public Movistar() {
		super();
		this.losEmpleados = new ArrayList<>();
		this.losClientes = new ArrayList<>();
		this.losPlanes = new ArrayList<>();
		this.lasFacturas = new ArrayList<>();
		this.losServicios = new ArrayList<>();
	}
	
	public static Movistar getInstance() {
		if(instancia == null) {
			cargarDatos();
		}
		return instancia;
	}
	public static void setInstance(Movistar nueva) {
		instancia = nueva;
		
	}
	
	public static Personal getUsuarioActual() {
		return usuarioActual;
	}

	public boolean confirmarSesion(String user, String clave) {
		for(Personal p: losEmpleados) {
			if (user.equals(p.getUsuario()) && clave.equals(p.getClave()) && p.isEstado()) {
				usuarioActual = p;
				return true;
			}
		}
		return false;
	}
	
	public static Movistar cargarDatos() {
		File archivo = new File(RUTA_DAT);
		if(!archivo.exists()) {
			Movistar nuevaInstancia = new Movistar();
			nuevaInstancia.cargarDatosIniciales();
			setInstance(nuevaInstancia);
			nuevaInstancia.guardarDatos();
			return nuevaInstancia;
		}
		try(FileInputStream lectura = new FileInputStream(archivo);
			ObjectInputStream conversion = new ObjectInputStream(lectura)){
			Movistar empresaCargada = (Movistar)conversion.readObject();
			setInstance(empresaCargada);
			return empresaCargada;
		}catch(IOException | ClassNotFoundException e) {
			Movistar respaldo = new Movistar();
			respaldo.cargarDatosIniciales();
			setInstance(respaldo);
			return respaldo;
		}
	}
	public void guardarDatos() {
		try(FileOutputStream apertura = new FileOutputStream(RUTA_DAT);
			ObjectOutputStream escritura = new ObjectOutputStream(apertura)){
			
			escritura.writeObject(this);
			
		}catch(IOException e) {
			System.out.println("Error al guardar: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void cargarDatosIniciales() {
		Internet internet = new Internet(1, "Internet qsy", 100, 50, 25, "Fibra");
		Cable cable = new Cable(2, "Cable idk", 150, 165);
		Telefono telefono = new Telefono(3, "Telefono", 50, "505", 100);
		this.registrarServicio(internet);
		this.registrarServicio(cable);
		this.registrarServicio(telefono);
		Plan plan = new Plan(1, "Plan");
		plan.agregarServicio(internet);
		plan.agregarServicio(cable);
		plan.agregarServicio(telefono);
		this.registrarPlan(plan);
		Sucursal sucu = new Sucursal(1, "Manoguayabo", "Manoguayabo??");
		Administrativo admin = new Administrativo(1, "John", "Doeee", "402182392", new Date(), "dumb@gmail.com",
		        "8081031", 500, true, "admin", "admin", sucu, "no se", "no se tampoco", "ni idea");
		this.losEmpleados.add(admin);
			
	}
	
	public ArrayList<Personal> getLosEmpleados() {
		return losEmpleados;
	}
	public ArrayList<Cliente> getLosClientes() {
		return losClientes;
	}
	public ArrayList<Plan> getLosPlanes() {
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

	    if (client == null || plan == null) {
	        return false;
	    }
	    if (!client.isContratoActivo()) {
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
	
	public boolean registrarPersonal(Personal p) {
	    if (p == null || buscarEmpleado(p.getId()) != null) {
	        return false;
	    }
	    losEmpleados.add(p);
	    return true;
	}

	public boolean eliminarPersonal(int id) {
	    Personal p = buscarEmpleado(id);
	    if (p == null) {
	        return false;
	    }
	    return losEmpleados.remove(p);
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
	    if (aux == null || aux.getClass() != s.getClass()) {
	        return false;
	    }

	    aux.setNombre(s.getNombre());
	    aux.setPrecioIndividual(s.getPrecioIndividual());

	    if (aux instanceof Cable) {
	    	((Cable) aux).setCantCanales(((Cable) s).getCantCanales());
	    } else if (aux instanceof Internet) {
	        Internet destino = (Internet) aux;
	        Internet origen = (Internet) s;
	        destino.setVelocidadSubida(origen.getVelocidadSubida());
	        destino.setVelocidadBajada(origen.getVelocidadBajada());
	        destino.setTipo(origen.getTipo());
	    } else if (aux instanceof Telefono) {
	        Telefono destino = (Telefono) aux;
	        Telefono origen = (Telefono) s;
	        destino.setNumTelefono(origen.getNumTelefono());
	        destino.setCantMinutos(origen.getCantMinutos());
	    }
	    return true;
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
	    Calendar hoy = Calendar.getInstance();
	    int mes = hoy.get(Calendar.MONTH) + 1;
	    int anio = hoy.get(Calendar.YEAR);

	    if (cliente == null || !(p instanceof Comercial)) {
	        return null;
	    }
	    
	    if(!cliente.isContratoActivo()) {
	    	return null;
	    }
	    
	    if (buscarFacturaClienteMes(idCliente, mes, anio) != null) {
	        return null;
	    }
	    
	    if(contarFacturasPendientes(cliente.getId()) >= 2) {
	    	cancelarContrato(idCliente);
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
	
	public int contarFacturasPendientes(int idCliente) {
		int contador = 0;
		for(Factura f: lasFacturas) {
			if(f.getCliente().getId() == idCliente && !f.isPagada()) {
				contador++;
			}
		}
		return contador;
	}
	
	public Factura buscarFacturaClienteMes(int idCliente, int mes, int anio) {
		Calendar calendario = Calendar.getInstance();
		for(Factura f: lasFacturas) {
			calendario.setTime(f.getFechaEmision());
			if(f.getCliente().getId() == idCliente && calendario.get(Calendar.MONTH) + 1 == mes && calendario.get(Calendar.YEAR) == anio) {
				return f;
			}
		}
		return null;
	}
	
	public boolean cancelarContrato(int idCliente) {
		Cliente cliente = buscarCliente(idCliente);
		
		if(cliente == null) {
			return false;
		}
		
		if(!cliente.isContratoActivo()) {
			return false;
		}
		
		cliente.quitarTodosLosPlanes();
		cliente.setContratoActivo(false);
		return true;
	}
	
	public boolean renovarContrato(int idCliente) {
		Cliente cliente = buscarCliente(idCliente);
		
		if(cliente == null) {
			return false;
		}
		
		if(cliente.isContratoActivo()) {
			return false;
		}
		
		if(contarFacturasPendientes(cliente.getId()) >= 2) {
			return false;
		}
		
		cliente.setContratoActivo(true);
		return true;
	}
	
	public ArrayList<Cliente> listarClientesQueDeben() {
	    ArrayList<Cliente> deben= new ArrayList<>();
	    for (Cliente c : losClientes) {
	        if (contarFacturasPendientes(c.getId()) > 0) {
	            deben.add(c);
	        }
	    }
	    return deben;
	}

	public ArrayList<Cliente> listarContratosCancelados() {
	    ArrayList<Cliente> cancelados = new ArrayList<>();
	    for (Cliente c : losClientes) {
	        if (!c.isContratoActivo()) {
	            cancelados.add(c);
	        }
	    }
	    return cancelados;
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

	    if (sucursal == null) {
	        return resultadosLocal;
	    }

	    for (Factura f : lasFacturas) {
	        if (sucursal.equals(f.getSucursal())) {
	            resultadosLocal.add(f);
	        }
	    }
	    return resultadosLocal;
	}
	
	public ArrayList<Factura> facturasCliente(int idCliente){
		ArrayList<Factura> facturasCliente = new ArrayList<>();
		for(Factura f: lasFacturas) {
			if(f.getCliente() != null && f.getCliente().getId() == idCliente) {
				facturasCliente.add(f);
			}
		}
		return facturasCliente;
	}
}
