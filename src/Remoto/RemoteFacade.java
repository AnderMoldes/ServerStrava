package Remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
import Servicios.BidAppService;
import Servicios.LoginAppService;


public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;

	//Data structure for manage Server State
	private Map<Long, Usuario> serverState = new HashMap<>();
	
	//TODO: Remove this instances when Singleton Pattern is implemented
	private LoginAppService loginService = new LoginAppService();
	private BidAppService bidService = new BidAppService();

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
		List<Sesion> categories = bidService.getSesion();
		
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
		List<Reto> categories = bidService.getRetos();
		
		if (categories != null) {
			//Convert domain object to DTO
			return RetoAssembler.getInstance().retoToDTO(categories);
		} else {
			throw new RemoteException("getCategories() fails!");
		}
	}
	
	@Override
	public boolean makeBid(long token, int article, float amount) throws RemoteException {		
		System.out.println(" * RemoteFacade makeBid article : " + article + " / amount " + amount);
		
		if (this.serverState.containsKey(token)) {						
			//Make the bid using Bid Application Service
			if (bidService.makeBid(this.serverState.get(token), article, amount)) {
				return true;
			} else {
				throw new RemoteException("makeBid() fails!");
			}
		} else {
			throw new RemoteException("To place a bid you must first log in");
		}
	}
}