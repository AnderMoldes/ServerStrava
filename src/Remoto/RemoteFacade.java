package Remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.RetoAssembler;
import DTO.RetoDTO;
import DTO.SesionAssembler;
import DTO.SesionDTO;
import Dominio.Reto;
import Dominio.Sesion;
import Dominio.Usuario;
import Servicios.LoginAppService;
import Servicios.RetoAppService;
import Servicios.SesiosAppService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, Usuario> serverState = new HashMap<>();
	
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
		Usuario user = loginService.login(email, password);
			
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
		List<Sesion> categories = new ArrayList<Sesion>();
		categories.add(sesionService.getSesion());
		
		if (categories != null) {
			//Convert domain object to DTO
			return SesionAssembler.getInstance().categoryToDTO(categories);
		} else {
			throw new RemoteException("getSesion() fails!");
		}
	}

	@Override
	public ArrayList<RetoDTO> getRetos() throws RemoteException {
		System.out.println(" * RemoteFacade getRetos()");
		
		//Get Categories using BidAppService
		List<Reto> categories = new ArrayList<Reto>();
		categories.add(retoService.getReto());
		
		if (categories != null) {
			//Convert domain object to DTO
			return RetoAssembler.getInstance().retoToDTO(categories);
		} else {
			throw new RemoteException("getCategories() fails!");
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
}