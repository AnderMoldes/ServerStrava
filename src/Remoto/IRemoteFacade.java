package Remoto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import DTO.*;


//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {	

	public long login(String email, String password) throws RemoteException;
	
	public void logout(long token) throws RemoteException; 
	
	public List<SesionDTO> getSesion() throws RemoteException;
	
	public List<RetoDTO> getRetos() throws RemoteException;
	
	public long loginGoogle(String email, String password) throws RemoteException;
	
	public String comprobar() throws RemoteException;
}