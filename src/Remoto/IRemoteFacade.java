package Remoto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import DTO.RetoDTO;
import DTO.SesionDTO;

//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {	

	public long login(String email, String password) throws RemoteException;
	
	public void logout(long token) throws RemoteException; 
	
	public List<SesionDTO> getSesion() throws RemoteException;
	
	public List<RetoDTO> getRetos() throws RemoteException;
	
	public boolean makeBid(long token, int article, float amount) throws RemoteException;	
}