package Remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.RetoAssembler;
import DTO.RetoDTO;
import DTO.SesionAssembler;
import DTO.SesionDTO;
import Dominio.Retos;
import Dominio.Sesiones;
import Dominio.Usuarios;
import Servicios.LoginAppService;
import Servicios.RetoAppService;
import Servicios.SesiosAppService;


public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, Usuarios> serverState = new HashMap<>();
	private Map<Long, Retos> serverState2 = new HashMap<>();
	private Map<Long, Sesiones> serverState3 = new HashMap<>();
	
	//TODO: Remove this instances when Singleton Pattern is implemented
	private LoginAppService loginService = new LoginAppService();
	private RetoAppService retoService = new RetoAppService();
	private SesiosAppService sesionService = new SesiosAppService();

	public RemoteFacade() throws RemoteException {
		super();		
	}
	
	@Override
	public synchronized long login(String email, String password) throws RemoteException {
		System.out.println(" * RemoteFacade login(): " + email + " / " + password);
				
		//Perform login() using LoginAppService
		Usuarios user = loginService.login(email, password);
			
		//If login() success user is stored in the Server State
		if (user != null) {
			//If user is not logged in 
			if (!this.serverState.values().contains(user)) {
				Long token = Calendar.getInstance().getTimeInMillis();		
				this.serverState.put(token, user);		
				return(token);
			} else {
				throw new RemoteException("User is already logged in!");
			}
		} else {
			throw new RemoteException("Login fails!");
		}
	}
	
	@Override
	public synchronized void logout(long token) throws RemoteException {
		System.out.println(" * RemoteFacade logout(): " + token);
		
		if (this.serverState.containsKey(token)) {
			//Logout means remove the User from Server State
			this.serverState.remove(token);
		} else {
			throw new RemoteException("User is not logged in!");
		}
	}
	
	@Override
	public List<SesionDTO> getSesion() throws RemoteException {
		System.out.println(" * RemoteFacade getSesion()");
		
		//Get Categories using BidAppService
		List<Sesiones> categories = new ArrayList<Sesiones>();
		categories.add(sesionService.getSesion());
		
		if (categories != null) {
			//Convert domain object to DTO
			return SesionAssembler.getInstance().categoryToDTO(categories);
		} else {
			throw new RemoteException("getSesion() fails!");
		}
	}

	@Override
	public List<RetoDTO> getRetos() throws RemoteException {
		System.out.println(" * RemoteFacade getRetos()");
		
		//Get Categories using BidAppService
		ArrayList<Retos> retos = RetoAppService.getInstance().getReto();
		if (retos != null) {
			//Convert domain object to DTO
			return RetoAssembler.getInstance().retosToDTO(retos);
		} else {
			throw new RemoteException("getRetos() fails!");
		}
	}
	public long loginGoogle(String email, String password) throws RemoteException {
		System.out.println(" * RemoteFacade loginGoogle(): " + email + " / " + password);
				
		long login = loginService.loginGoogle(email, password);
		
		if (login != -1) {
			return login;
		} else {
			throw new RemoteException("loginGoogle() fails!");
		}
	}
	public String comprobar() throws RemoteException {
		System.out.println(" * RemoteFacade comprobar()");
		
		String resultado = loginService.comprobar();
		
		if (resultado != "") {
			return resultado;
		} else {
			throw new RemoteException("comprobar() fails!");
		}
	}
	public long crearReto(int number, String name, Date fecha_ini, Date fecha_fin, double distanciaObjetivo,
			String deporte) throws RemoteException {
		// TODO Auto-generated method stub
		Retos reto = retoService.crearReto(number, name, fecha_ini, fecha_fin, distanciaObjetivo, deporte);
		System.out.println(" * RemoteFacade crearReto(): " + number + " / " + name);
		if (reto != null) {
			Long token = Calendar.getInstance().getTimeInMillis();
			this.serverState2.put(token, reto);		
			return(token);	
		}else {
			throw new RemoteException("Reto is in the database!");
		}
	}

	@Override
	public long crearSesion(int number, String titulo, String deporte, double distancia, Date fecha_ini, String hora_ini,
			double duracion) throws RemoteException {
		// TODO Auto-generated method stub
		Sesiones sesion = sesionService.crearSesion(number, titulo, fecha_ini, hora_ini, distancia, deporte, duracion);
		System.out.println(" * RemoteFacade crearSesion(): " + number + " / " + titulo);
		if (sesion != null) {
			Long token = Calendar.getInstance().getTimeInMillis();
			this.serverState3.put(token, sesion);		
			return(token);	
		}else {
			throw new RemoteException("Sesion is in the database!");
		}
	}

	@Override
	public long registro(String email, String name, Date fecha_nac, double peso, String contrasenya, String frec, String modoRe)
			throws RemoteException {
		// TODO Auto-generated method stub
		Usuarios usuario = loginService.registro(email, name, fecha_nac, peso, contrasenya, frec, modoRe);
		System.out.println(" * RemoteFacade registro(): " + email + " / " + contrasenya);
		if (usuario != null) {
			Long token = Calendar.getInstance().getTimeInMillis();
			this.serverState.put(token, usuario);		
			return(token);	
		}else {
			throw new RemoteException("User is in the database!");
		}
	}
	
}